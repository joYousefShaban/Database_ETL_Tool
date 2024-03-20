package com.etl.tool.mappers.TEMPLATE;

import com.etl.tool.entities.DataRow;
import com.etl.tool.mappers.RowMappers;
import lombok.Getter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

//This mapper will be responsible for the migration from database to the program
@Getter
public class TemplateRowMapper extends RowMappers {

    private final String sourceDatabaseName;

    private final HashMap<String, RowMapper<DataRow>> rowMappers;

    public TemplateRowMapper() {
        sourceDatabaseName = "dbo.source";

        this.rowMappers = new HashMap<>();
        // Define your row mapper for "XYZ" database
        rowMappers.put("dbo.source", (
                ResultSet resultSet, int rowNum) -> {
            // Define column names and their corresponding types
            Map<String, Class<?>> columnMappings = new HashMap<>();
            columnMappings.put("Id", Integer.class);
            columnMappings.put("Name", String.class);
            //etc


            return populateColumnMapping(resultSet, columnMappings);
        });

        // in case data is needed from external tables
        /*rowMappers.put("dbo.source2", (
                ResultSet resultSet, int rowNum) -> {
            // Define column names and their corresponding types
            Map<String, Class<?>> columnMappings = new HashMap<>();
            columnMappings.put("UserId", Integer.class);
            columnMappings.put("RoleId", String.class);
            //etc

            return populateColumnMapping(resultSet, columnMappings);
        });*/
    }
}