package com.etl.tool.mappers;

import com.etl.tool.entities.DataRow;

public interface IEntityMapper {
    DataRow transformToEntity(DataRow sourceRow);
}