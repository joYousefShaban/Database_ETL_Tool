package com.example.demo.configurations;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.Nullable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Log4j2
@Lazy
public class HikariDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.source")
    public HikariDataSource sourceHikariDataSource() {
        return getHikariDataSource();
        return createHikariDataSource();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.destination")
    public HikariDataSource destinationHikariDataSource() {
        return getHikariDataSource();
        return createHikariDataSource();
    }

    @Nullable
    private HikariDataSource createHikariDataSource() {
        try {
            HikariDataSource hikariDataSource = DataSourceBuilder.create().type(HikariDataSource.class).build();
            log.info("Process of establishing the following HikariDataSource connection was successful: " + hikariDataSource);
            return hikariDataSource;
        } catch (Exception e) {
            log.fatal("Process of establishing a HikariDataSource connection was unsuccessful, and caused the following exception: " + e.getMessage());
        }
        return null;
    }
}