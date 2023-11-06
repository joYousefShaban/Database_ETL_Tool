package com.etl.tool.impl;

import com.etl.tool.services.logging.ANSI;
import com.etl.tool.services.yaml_reader.YamlDeserializer;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Log4j2
@Component
public class HomePageDialog {

    public static boolean isAffirmativeResponse() {
        return new Scanner(System.in).nextLine().equalsIgnoreCase("yes");
    }

    public boolean startUIAndConfirmConfigurations() {
        // Display a welcome message to the user
        log.info(ANSI.colour("---", ANSI.ORANGE_BRIGHT));
        log.info(ANSI.colour("Bellow are the configurations from the text file:", ANSI.ORANGE_BRIGHT));

        // Display the entered URLs
        log.info(ANSI.colour("Source Database URL: ", ANSI.ORANGE_BRIGHT) + ANSI.colour(YamlDeserializer.externalConfig.getSource().getJdbcUrl(), ANSI.GRAY_BACKGROUND));
        log.info(ANSI.colour("Destination Database URL: ", ANSI.ORANGE_BRIGHT) + ANSI.colour(YamlDeserializer.externalConfig.getDestination().getJdbcUrl(), ANSI.GRAY_BACKGROUND));
        log.info(ANSI.colour("---", ANSI.ORANGE_BRIGHT));
        log.info("Type " + ANSI.colour("yes", ANSI.TEAL_BOLD) + " if you desire to continue this data migration, otherwise please " + ANSI.colour("type anything else or close this window.", ANSI.RED_BOLD));

        if (isAffirmativeResponse()) {
            log.info(ANSI.colour("Data migration started! Please wait until completion.", ANSI.BLUE_BOLD));
            return true;
        }
        return false;
    }
}
