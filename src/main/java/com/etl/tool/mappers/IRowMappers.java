package com.etl.tool.mappers;

import com.etl.tool.entities.DataRow;
import org.springframework.jdbc.core.RowMapper;

import java.util.HashMap;

public interface IRowMappers {

    String getSourceDatabaseName();
    HashMap<String, RowMapper<DataRow>> getRowMappers();
}