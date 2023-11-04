package com.example.demo.impl;

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

        try {
            log.info(ANSI.colour("TOOL IS BOOTING UP", ANSI.BLUE_BOLD));

            //create logs
            LoggingService loggingService = context.getBean(LoggingService.class);
            loggingService.hideFile("LOGS/SysLogs");

            //validate configurations file
            YamlDeserializer.load();
            if (YamlValidator.isYamlValid()) {

                //start console dialogs
                HomePageDialog homePageDialog = context.getBean(HomePageDialog.class);
                if (homePageDialog.startUIAndConfirmConfigurations()) {
                    //start transfer service
                    ETLService dataTransferService = context.getBean(ETLService.class);
                    startTransferringTables(dataTransferService);
                }
            }
        } catch (Exception e) {
            log.fatal("INITIALIZATION FAILED, and threw the following exception: " + e.getMessage());
        }
        context.close();
    }

    public static void startTransferringTables(ETLService dataTransferService) {
        dataTransferService.startTransfer("dbo.AspNetUsers", "dbo.user_table", new UserDatabaseRowMapper(), new UserEntityMapper(), Optional.empty());
        dataTransferService.startTransfer("dbo.Menu", "dbo.Menu", new MenuDatabaseRowMapper(), new MenuEntityMapper(), Optional.of("parent_id"));
        dataTransferService.startTransfer("dbo.Ranks", "dbo.rank", new RankDatabaseRowMapper(), new RankEntityMapper(), Optional.empty());
        //Add more tables if needed
    }
}