package com.example.demo.impl;

import com.example.demo.configurations.JdbcTemplateConfig;
import com.example.demo.mappers.menu.MenuDatabaseRowMapper;
import com.example.demo.mappers.menu.MenuEntityMapper;
import com.example.demo.mappers.rank.RankDatabaseRowMapper;
import com.example.demo.mappers.rank.RankEntityMapper;
import com.example.demo.mappers.user.UserDatabaseRowMapper;
import com.example.demo.mappers.user.UserEntityMapper;
import com.example.demo.services.etl.ETLService;
import com.example.demo.services.logging.ANSI;
import com.example.demo.services.logging.LoggingService;
import com.example.demo.services.yaml_reader.YamlDeserializer;
import com.example.demo.services.yaml_reader.YamlValidator;
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

        try (context) {
            log.info(ANSI.colour("TOOL IS BOOTING UP", ANSI.BLUE_BOLD));

            //create logs
            LoggingService loggingService = context.getBean(LoggingService.class);
            loggingService.hideFile("LOGS/SysLogs");

            //deserialize and validate external configurations
            if (YamlDeserializer.load() && YamlValidator.isYamlValid()) {
                log.info(ANSI.colour("Welcome to the ETL Tool!", ANSI.BLUE_BOLD));
                log.info(ANSI.colour("This tool helps you extract, transform, and load data from and to a database.", ANSI.BLUE_BOLD));

                //start console dialogs
                HomePageDialog homePageDialog = context.getBean(HomePageDialog.class);
                if (homePageDialog.startUIAndConfirmConfigurations()) {

                    //Connections Setups
                    context.getBean(JdbcTemplateConfig.class);

                    //start transfer service
                    ETLService dataTransferService = context.getBean(ETLService.class);
                    startTransferringTables(dataTransferService);
                }
                log.info(ANSI.colour("Tool has finished all migrations", ANSI.BLUE_BOLD));
            }
        } catch (Exception e) {
            log.fatal(ANSI.colour("INITIALIZATION FAILED, and threw the following exception: \r\n" + e.getMessage(), ANSI.RED_BOLD));
        } finally {
            log.info(ANSI.colour("Cleanup Starting...", ANSI.BLUE_BOLD));
        }
    }

    public static void startTransferringTables(ETLService dataTransferService) {
        dataTransferService.startTransfer("dbo.AspNetUsers", "dbo.user_table", new UserDatabaseRowMapper(), new UserEntityMapper(), Optional.empty());
        dataTransferService.startTransfer("dbo.Menu", "dbo.Menu", new MenuDatabaseRowMapper(), new MenuEntityMapper(), Optional.of("parent_id"));
        dataTransferService.startTransfer("dbo.Ranks", "dbo.rank", new RankDatabaseRowMapper(), new RankEntityMapper(), Optional.empty());
        //Add more tables if needed
    }
}