package com.etl.tool.entities.connection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ConnectionEntity {
    private String jdbcUrl;

    private String username;

    private String password;

    private String driverClassName;

    private HikariEntity hikari;
}
