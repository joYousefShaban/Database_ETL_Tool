package com.example.demo.services.etl;

import com.example.demo.entities.DataRow;
import com.example.demo.mappers.IEntityMapper;
import com.example.demo.services.logging.ANSI;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@Lazy
@SuppressWarnings("squid:S1192")
public class ETLService extends ETLHelper implements IETLService {

    //<editor-fold desc="Connections Setups">
    private final JdbcTemplate sourceJdbcTemplate;
    private final JdbcTemplate destinationJdbcTemplate;

    public ETLService(@Qualifier("sourceJdbcTemplate") JdbcTemplate sourceJdbcTemplate, @Qualifier("destinationJdbcTemplate") JdbcTemplate destinationJdbcTemplate) {
        this.sourceJdbcTemplate = sourceJdbcTemplate;
        this.destinationJdbcTemplate = destinationJdbcTemplate;
    }
    //</editor-fold>

    //<editor-fold desc="ETL Operations">

    //IMPORTANT: Consider multithreading this method for better performance
    public void startTransfer(String sourceTableName, String destinationTableName, RowMapper<DataRow> rowMapper, IEntityMapper menuEntityMapper, Optional<String> sortFKColumn) {
        log.info(ANSI.colour("DATA TRANSFORMATION from " + sourceTableName + " to " + destinationTableName + " HAS STARTED", ANSI.TEAL));

        // Extract data from the source schema
        List<DataRow> sourceTableData = extractData(sourceTableName, rowMapper);

        //Exit if there's no data to transfer
        if (!sourceTableData.isEmpty()) {
            // Transform the data
            List<DataRow> destinationTableData = transformData(sourceTableData, menuEntityMapper);

            //Sort a column if needed
            sortFKColumn.ifPresent(columnToCompare -> sortData(destinationTableData, columnToCompare));

            // Load the transformed data into the destination schema
            loadData(destinationTableName, destinationTableData);
        } else {
            log.warn(ANSI.colour("There was no records found in: " + sourceTableName, ANSI.YELLOW_BRIGHT));
        }

        log.info(ANSI.colour("DATA TRANSFORMATION from " + sourceTableName + " to " + destinationTableName + "HAS FINISHED", ANSI.TEAL));
    }

    private List<DataRow> extractData(String sourceTableName, RowMapper<DataRow> rowMapper) {
        try {
            return sourceJdbcTemplate.query("SELECT * FROM " + sourceTableName, rowMapper);
        } catch (Exception e) {
            log.fatal(ANSI.colour("PROCESS OF EXTRACTING DATA: The select query on: \"" + sourceTableName + "\" failed, and generated the following exception: " + e.getMessage(), ANSI.RED_BOLD));
        }
        return Collections.emptyList();
    }

    private List<DataRow> transformData(List<DataRow> sourceRowData, IEntityMapper entityMapper) {
        List<DataRow> destinationRowData = new ArrayList<>();

        for (DataRow sourceData : sourceRowData) {

            try {
                DataRow destinationData = entityMapper.transformToEntity((sourceData));

                //This extra operation "destinationData.moveKeyToTheEnd("id");" could be easily avoided by changing entry order in entityMapper and ensuring id is the last element.

                destinationRowData.add(destinationData);
            } catch (Exception e) {
                log.fatal(ANSI.colour("PROCESS OF TRANSFORMING DATA: The mapping of: \"" + sourceData.getData() + "\" failed, and generated the following exception: " + e.getMessage(), ANSI.RED_BOLD));
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
        for (DataRow person : destinationData) {
            try {
                if (destinationJdbcTemplate.update(updateSQL, person.getData().values().toArray()) == 0) {
                    destinationJdbcTemplate.update(insertSQL, person.getData().values().toArray());
                }
            } catch (Exception e) {
                log.fatal(ANSI.colour("PROCESS OF LOADING DATA: The query on: \"" + destinationTableName + "\" failed, and generated the following exception: " + e.getMessage(), ANSI.RED_BOLD));
            }
        }
        //Disable insertion of Identity
        setIdentityInsert(destinationTableName, false);

    }

    private void setIdentityInsert(String destinationTableName, boolean activate) {
        try {
            if (activate) destinationJdbcTemplate.execute("SET IDENTITY_INSERT " + destinationTableName + " ON");
            else {
                destinationJdbcTemplate.execute("SET IDENTITY_INSERT " + destinationTableName + " OFF");
            }
        } catch (UncategorizedSQLException e) {
            log.warn(ANSI.colour("\"" + destinationTableName + "\" table generated the following warning: " + e.getMessage(), ANSI.YELLOW_BRIGHT));
            log.info(ANSI.colour("Please notice that the previous exception is normal if the table doesn't accept SET IDENTITY_INSERT specific rule.", ANSI.GRAY));
        } catch (Exception e) {
            log.error(ANSI.colour("\"" + destinationTableName + "\" table failed, and generated the following exception: " + e.getMessage(), ANSI.YELLOW_BRIGHT));
        }
    }


    //</editor-fold>
}