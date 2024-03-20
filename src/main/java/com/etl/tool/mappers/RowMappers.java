package com.etl.tool.mappers;

import com.etl.tool.entities.DataRow;
import com.etl.tool.services.logging.ANSI;
import lombok.extern.log4j.Log4j2;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.Map;

@Log4j2
public abstract class RowMappers implements IRowMappers {

    protected DataRow populateColumnMapping(ResultSet resultSet, Map<String, Class<?>> columnMappings) {

        DataRow sourceDataRow = new DataRow();

        // Populate DataRow object from ResultSet
        for (Map.Entry<String, Class<?>> entry : columnMappings.entrySet()) {
            try {

                String columnName = entry.getKey();
                Class<?> columnType = entry.getValue();
                if (columnType == String.class)
                    sourceDataRow.addValue(columnName, resultSet.getString(columnName));
                else if (columnType == Boolean.class)
                    sourceDataRow.addValue(columnName, resultSet.getBoolean(columnName));
                else if (columnType == int.class)
                    sourceDataRow.addValue(columnName, resultSet.getInt(columnName));
                else if (columnType == Integer.class)
                    sourceDataRow.addValue(columnName, resultSet.getObject(columnName, Integer.class));
                else if (columnType == Date.class)
                    sourceDataRow.addValue(columnName, resultSet.getTimestamp(columnName));
                // Add more conditions for other types as needed

            } catch (Exception e) {
                log.fatal(ANSI.colour("PROCESS OF EXTRACTING DATA ", ANSI.RED_BOLD) + ANSI.colour(": The populating of: \"" + entry.getValue() + "\" failed in " + entry.getKey() + " database, and generated the following exception: \r\n" + e.getMessage(), ANSI.RED));
            }
        }
        return sourceDataRow;
    }
}