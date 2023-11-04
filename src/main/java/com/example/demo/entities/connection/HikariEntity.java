package com.example.demo.entities.connection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HikariEntity {
    private int connectionTimeout;

    private int maximumPoolSize;
}