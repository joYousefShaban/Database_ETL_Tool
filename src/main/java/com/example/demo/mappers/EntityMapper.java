package com.example.demo.mappers;

public abstract class EntityMapper implements IEntityMapper {

    //The return type is object so that it'll be able to return null
    public Object transferToLong(Object value) {
        return (value != null) ? ((Number) value).longValue() : null;
    }

    public Object transferToInt(Object value) {
        return (value != null) ? ((Number) value).intValue() : null;
    }

    public Object transferToString(Object value) {
        return String.valueOf(value);
    }
}