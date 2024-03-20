package com.etl.tool.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
public class DataRow implements IDataRow {

    private Map<String, Object> data;

    public DataRow() {
        data = new LinkedHashMap<>();
    }

    public void addValue(String key, Object value) {
        data.put(key, value);
    }
}