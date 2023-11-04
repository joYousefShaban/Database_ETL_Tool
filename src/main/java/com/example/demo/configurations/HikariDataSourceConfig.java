package com.example.demo.configurations;

import com.example.demo.services.yaml_reader.YamlDeserializer;
import com.example.demo.entities.connection.ConnectionEntity;
import com.example.demo.entities.connection.DestinationEntity;
import com.example.demo.entities.connection.SourceEntity;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Log4j2
@Lazy
public class HikariDataSourceConfig {

    @Bean
    public HikariDataSource sourceHikariDataSource() {
        SourceEntity externalSourceEntity = YamlDeserializer.externalConfig.getSource();
        return createHikariDataSource(externalSourceEntity);
    }

    @Bean
    public HikariDataSource destinationHikariDataSource() {
        DestinationEntity externalDestinationConfig = YamlDeserializer.externalConfig.getDestination();
        return createHikariDataSource(externalDestinationConfig);
    }

    @Nullable
    private HikariDataSource createHikariDataSource(ConnectionEntity externalConnectionEntity) {
        try {
            HikariConfig config = getHikariConfig(externalConnectionEntity);
            log.info("Process of establishing the following HikariDataSource connection was successful: " + config);
            return new HikariDataSource(config);
        } catch (Exception e) {
            log.fatal("Process of establishing a HikariDataSource connection was unsuccessful, and caused the following exception: " + e.getMessage());
        }
        return null;
    }

    @NotNull
    private static HikariConfig getHikariConfig(ConnectionEntity externalConnectionEntity) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(externalConnectionEntity.getJdbcUrl());
        config.setUsername(externalConnectionEntity.getUsername());
        config.setPassword(externalConnectionEntity.getPassword());
        config.setDriverClassName(externalConnectionEntity.getDriverClassName());
        config.setConnectionTimeout(externalConnectionEntity.getHikari().getConnectionTimeout());
        config.setMaximumPoolSize(externalConnectionEntity.getHikari().getMaximumPoolSize());
        return config;
    }
}