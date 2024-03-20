package com.etl.tool.impl;

import com.etl.tool.entities.DataRow;
import com.etl.tool.mappers.IRowMappers;
import com.etl.tool.mappers.IEntityMapper;
import com.etl.tool.services.logging.ANSI;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Log4j2
@Lazy
@SuppressWarnings("squid:S1192")
public class ETLService extends ETLHelper implements IETLService {

    //<editor-fold desc="Connections Setups">
    private final JdbcTemplate sourceJdbcTemplate;
    private final JdbcTemplate destinationJdbcTemplate;

    @Autowired
    public ETLService(@Qualifier("getSourceJdbcTemplate") JdbcTemplate sourceJdbcTemplate, @Qualifier("getDestinationJdbcTemplate") JdbcTemplate destinationJdbcTemplate) {
        this.sourceJdbcTemplate = sourceJdbcTemplate;
        this.destinationJdbcTemplate = destinationJdbcTemplate;
    }
    //</editor-fold>

    //<editor-fold desc="ETL Operations">

    //IMPORTANT: Consider multithreading this method for better performance
    public void startTransfer(String destinationTableName, IRowMappers rowMappers, IEntityMapper entityMapper, Optional<String> sortFKColumn) {
        log.info(ANSI.colour("DATA TRANSFORMATION from " + rowMappers.getSourceDatabaseName() + " to " + destinationTableName + " HAS STARTED", ANSI.TEAL));

        // Extract data from the source schema
        HashMap<String, List<DataRow>> extractedData = extractData(rowMappers);

        //Exit if there's no data to transfer
        if (!extractedData.get(rowMappers.getSourceDatabaseName()).isEmpty()) {
            // Transform the data
            List<DataRow> destinationTableData = transformData(rowMappers.getSourceDatabaseName(), extractedData, entityMapper);

            //Sort a column if needed
            sortFKColumn.ifPresent(columnToCompare -> sortData(destinationTableData, columnToCompare));

            // Load the transformed data into the destination schema
            loadData(destinationTableName, destinationTableData);
        } else {
            log.warn(ANSI.colour("There was no records found in: " + rowMappers.getSourceDatabaseName(), ANSI.YELLOW_BRIGHT));
        }

        log.info(ANSI.colour("DATA TRANSFORMATION from " + rowMappers.getSourceDatabaseName() + " to " + destinationTableName + "HAS FINISHED", ANSI.TEAL));
    }

    private HashMap<String, List<DataRow>> extractData(IRowMappers rowMappers) {

        HashMap<String, List<DataRow>> extracted = new HashMap<>();
        try {
            for (Map.Entry<String, RowMapper<DataRow>> rowMapper : rowMappers.getRowMappers().entrySet()) {
                extracted.put(rowMapper.getKey(), sourceJdbcTemplate.query("SELECT * FROM " + rowMapper.getKey(), rowMapper.getValue()));
            }
        } catch (Exception e) {
            log.fatal(ANSI.colour("PROCESS OF EXTRACTING DATA ", ANSI.RED_BOLD) + ANSI.colour(": The select query on: \"" + rowMappers.getSourceDatabaseName() + "\" failed, and generated the following exception: \r\n" + e.getMessage(), ANSI.RED));
        }

        return extracted;
    }

    private List<DataRow> transformData(String sourceDatabaseName, HashMap<String, List<DataRow>> extractedData, IEntityMapper entityMapper) {
        List<DataRow> destinationRowData = new ArrayList<>();

        for (DataRow sourceData : extractedData.get(sourceDatabaseName)) {
            try {
                destinationRowData.add(entityMapper.transformToEntity(sourceData, extractedData));
            } catch (Exception e) {
                log.fatal(ANSI.colour("PROCESS OF TRANSFORMING DATA ", ANSI.RED_BOLD) + ANSI.colour(": The mapping of: \"" + sourceData.getData() + "\" failed, and generated the following exception: \r\n" + e.getMessage(), ANSI.RED));
            }
        }
        return destinationRowData;
    }

    private void loadData(String destinationTableName, List<DataRow> destinationData) {
        String updateSQL = generateUpdateSQL(destinationTableName, destinationData.get(0));
        if (updateSQL == null) {
            return;
        }
        String insertSQL = generateInsertSQL(destinationTableName, destinationData.get(0));
        if (insertSQL == null) {
            return;
        }


        //Enable insertion of Identity
        setIdentityInsert(destinationTableName, true);

        //Update each entity, and insert of not exist
        for (DataRow destinationRow : destinationData) {
            try {
                if (destinationJdbcTemplate.update(updateSQL, destinationRow.getData().values().toArray()) == 0) {
                    destinationJdbcTemplate.update(insertSQL, destinationRow.getData().values().toArray());
                }
            } catch (Exception e) {
                log.fatal(ANSI.colour("PROCESS OF LOADING DATA ", ANSI.RED_BOLD) + ANSI.colour(": The mapping of: \"" + destinationRow.getData() + "\" failed, and generated the following exception: \r\n" + e.getMessage(), ANSI.RED));
            }
        }
        //Disable insertion of Identity
        setIdentityInsert(destinationTableName, false);

    }

    private void setIdentityInsert(String destinationTableName, boolean activate) {
        try {
            if (activate) {
                destinationJdbcTemplate.execute("SET IDENTITY_INSERT " + destinationTableName + " ON");
            } else {
                destinationJdbcTemplate.execute("SET IDENTITY_INSERT " + destinationTableName + " OFF");
            }
        } catch (UncategorizedSQLException e) {
            log.warn(ANSI.colour("Couldn't modify rule of identity_insert for \"" + destinationTableName + "\" table ", ANSI.YELLOW_BRIGHT));
        } catch (Exception e) {
            log.fatal(ANSI.colour("\"" + destinationTableName + "\" table failed, and generated the following exception: \r\n" + e.getMessage(), ANSI.YELLOW_BRIGHT));
        }
    }


    //</editor-fold>
}