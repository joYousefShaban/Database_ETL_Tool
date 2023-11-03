package com.example.demo.configurations;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@Log4j2
@Lazy
public class JdbcTemplateConfig {

    @Bean
    public JdbcTemplate sourceJdbcTemplate(@Qualifier("sourceHikariDataSource") HikariDataSource sourceHikariDataSource) {
        return createJdbcTemplate(sourceHikariDataSource);
    }

    @Bean
    public JdbcTemplate destinationJdbcTemplate(@Qualifier("destinationHikariDataSource") HikariDataSource destinationHikariDataSource) {
        return createJdbcTemplate(destinationHikariDataSource);
    }

    @Nullable
    private JdbcTemplate createJdbcTemplate(HikariDataSource hikaridataSource) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(hikaridataSource);
            log.info("Process of establishing the following JdbcTemplate was successful: " + jdbcTemplate);
            return jdbcTemplate;
        } catch (Exception e) {
            log.fatal("Process of establishing a JdbcTemplate was unsuccessful, and caused the following exception: " + e.getMessage());
        }
        return null;
    }
}