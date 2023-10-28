package com.example.demo.impl;

import com.example.demo.mappers.table1.Table2DatabaseRowMapper;
import com.example.demo.mappers.table1.Table2EntityMapper;
import com.example.demo.mappers.table2.Table1DatabaseRowMapper;
import com.example.demo.mappers.table2.Table1EntityMapper;
import com.example.demo.services.configurationReader.ConfigurationReaderService;
import com.example.demo.services.etl.ETLService;
import com.example.demo.services.logging.ANSI;
import com.example.demo.services.logging.LoggingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Log4j2
@Component
public class Initializer {

    private Initializer() {

    }

    public static void performInitialization(ConfigurableApplicationContext context) {

        try {
            log.debug(ANSI.colour("TEST! This is a debug message on", ANSI.LAVENDER_BOLD));
            log.warn(ANSI.colour("TEST! This is a warning message on", ANSI.LAVENDER_BOLD));
            log.fatal(ANSI.colour("TEST! This is a fatal message on", ANSI.LAVENDER_BOLD));
            LoggingService loggingService = context.getBean(LoggingService.class);
            loggingService.hideFile("LOGS/SysLogs");

            ConfigurationReaderService configurationReaderService = context.getBean(ConfigurationReaderService.class);
            configurationReaderService.replaceYAMLConfigurations();

            HomePageDialog homePageDialog = context.getBean(HomePageDialog.class);
            if (homePageDialog.startUIAndConfirmConfigurations()) {
                ETLService dataTransferService = context.getBean(ETLService.class);
                startTransferringTables(dataTransferService);
            }
        } catch (Exception e) {
            log.fatal("INITIALIZATION FAILED, and threw the following exception: " + e.getMessage());
        }
        context.close();
    }

    public static void startTransferringTables(ETLService dataTransferService) {
        dataTransferService.startTransfer("dbo.Ranks", "dbo.rank", new Table1DatabaseRowMapper(), new Table1EntityMapper(), Optional.empty());
        dataTransferService.startTransfer("dbo.AspNetUsers", "dbo.user_table", new Table2DatabaseRowMapper(), new Table2EntityMapper(), Optional.empty());
        //Add more tables if needed
    }
}