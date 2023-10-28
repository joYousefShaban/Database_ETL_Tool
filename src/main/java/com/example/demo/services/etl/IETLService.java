package com.example.demo.services.etl;

import com.example.demo.entities.DataRow;
import com.example.demo.mappers.IEntityMapper;
import org.springframework.jdbc.core.RowMapper;

import java.util.Optional;

public interface IETLService {
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    void startTransfer(String sourceTableName, String destinationTableName, RowMapper<DataRow> rowMapper, IEntityMapper menuEntityMapper, Optional<String> sortFKColumn);
}