package com.etl.tool.configurations;

import com.etl.tool.services.logging.ANSI;
import com.etl.tool.services.yaml_reader.YamlDeserializer;
import com.etl.tool.entities.connection.ConnectionEntity;
import com.etl.tool.entities.connection.DestinationEntity;
import com.etl.tool.entities.connection.SourceEntity;
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
            log.info(ANSI.colour("Establishing the following HikariDataSource connection is successful: " + config, ANSI.TEAL_BOLD));
            return new HikariDataSource(config);
        } catch (Exception e) {
            log.fatal(ANSI.colour("Establishing a HikariDataSource connection failed, and generated the following exception: \r\n" + e.getMessage(), ANSI.RED_BOLD));
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