package com.example.demo.entities;

import java.util.Map;

public interface IDataRow {
    Map<String, Object> values = null;

    void addValueInData(String key, Object value);

    int size();
}