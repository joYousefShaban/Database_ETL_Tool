package com.etl.tool.mappers;

public abstract class EntityMapper implements IEntityMapper {

    //<editor-fold desc="Common Operation">
    //To allow null returns, The return type will be always as an object
    public Object transferToLong(Object value) {
        return (value != null) ? ((Number) value).longValue() : null;
    }

    public Object transferToInt(Object value) {
        return (value != null) ? ((Number) value).intValue() : null;
    }

    public Object transferToString(Object value) {
        return String.valueOf(value);
    }

    public Object transferToBoolean(Object value) {
        return (value != null) ? Boolean.valueOf(value.toString())  : null;
    }
    //</editor-fold>
}