package com.etl.tool.mappers;

import com.etl.tool.entities.DataRow;

import java.util.List;
import java.util.Map;

public interface IEntityMapper {
    DataRow transformToEntity(DataRow sourceRow, Map<String, List<DataRow>> extractedData);
}