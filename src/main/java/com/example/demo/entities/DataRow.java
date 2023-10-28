package com.example.demo.entities;

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

    public void addValueInData(String key, Object value) {
        data.put(key, value);
    }

    public int size() {
        return data.size();
    }

    @Deprecated
    public void moveKeyToTheEnd(String key) {
        data.put(key, data.remove(key));
    }
}