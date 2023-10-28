package com.example.demo.impl;

import com.example.demo.services.logging.ANSI;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Log4j2
@Component
public class HomePageDialog {
    private final Environment env;

    @Autowired
    HomePageDialog(Environment env) {
        this.env = env;
    }

    public static boolean isAffirmativeResponse() {
        return new Scanner(System.in).nextLine().equalsIgnoreCase("yes");
    }

    public boolean startUIAndConfirmConfigurations() {
        // Display a welcome message to the user
        log.info(ANSI.colour("Welcome to the ETL Tool!", ANSI.CYAN_BOLD));
        log.info(ANSI.colour("This tool helps you extract, transform, and load data from and to a database.", ANSI.CYAN_BOLD));
        log.info(ANSI.colour("---", ANSI.BLACK_BRIGHT));
        log.info("Bellow are the configurations from the text file:");

        // Display the entered URLs
        log.info("Source Database URL: " + ANSI.colour(env.getProperty("spring.datasource.source.jdbc-url"), ANSI.GRAY_BACKGROUND));
        log.info("Destination Database URL: " + ANSI.colour(env.getProperty("spring.datasource.destination.jdbc-url"), ANSI.GRAY_BACKGROUND));
        log.info(ANSI.colour("---", ANSI.BLACK_BRIGHT));
        log.info("Press " + ANSI.colour("yes", ANSI.TEAL_BOLD) + " if you desire to continue this data migration, otherwise please " + ANSI.colour("type anything else or close this window.", ANSI.RED_BOLD));

        if (isAffirmativeResponse()) {
            log.info(ANSI.colour("---", ANSI.BLACK_BRIGHT));
            log.info(ANSI.colour("Data migration started! Please wait until completion.", ANSI.BLUE_BOLD));
            return true;
        }
        return false;
    }
}
