package com.example.demo.mappers;

import com.example.demo.entities.DataRow;

public interface IEntityMapper {
    DataRow transformToEntity(DataRow sourceRow);
}