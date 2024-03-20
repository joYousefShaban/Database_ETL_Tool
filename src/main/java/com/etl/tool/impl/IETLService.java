package com.etl.tool.impl;

import com.etl.tool.mappers.IRowMappers;
import com.etl.tool.mappers.IEntityMapper;

import java.util.Optional;

public interface IETLService {
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    void startTransfer(String destinationTableName, IRowMappers rowMapper, IEntityMapper menuEntityMapper, Optional<String> sortFKColumn);
}