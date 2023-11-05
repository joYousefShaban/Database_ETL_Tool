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

    /**
     * @deprecated (since"3-Nov-23", forRemoval = false, calling could be avoided if mapping had the key at last)
     */
    @Deprecated(since = "3-Nov-23", forRemoval = false)
    public void moveKeyToTheEnd(String key) {
        data.put(key, data.remove(key));
    }
}