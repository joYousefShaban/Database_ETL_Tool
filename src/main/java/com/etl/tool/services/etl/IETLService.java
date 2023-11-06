package com.etl.tool.services.etl;

import com.etl.tool.entities.DataRow;
import com.etl.tool.mappers.IEntityMapper;
import org.springframework.jdbc.core.RowMapper;

import java.util.Optional;

public interface IETLService {
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    void startTransfer(String sourceTableName, String destinationTableName, RowMapper<DataRow> rowMapper, IEntityMapper menuEntityMapper, Optional<String> sortFKColumn);
}