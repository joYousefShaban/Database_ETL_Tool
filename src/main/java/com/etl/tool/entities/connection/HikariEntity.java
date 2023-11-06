package com.etl.tool.entities.connection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HikariEntity {
    private int connectionTimeout;

    private int maximumPoolSize;
}