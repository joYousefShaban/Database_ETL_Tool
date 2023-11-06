package com.etl.tool.services.yaml_reader;

import com.etl.tool.entities.connection.ConnectionEntity;
import com.etl.tool.services.logging.ANSI;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class YamlValidator {

    private YamlValidator() {

    }

    public static boolean isYamlValid() {

        boolean isValidSource = isConnectionValid(YamlDeserializer.externalConfig.getSource(), "source");
        boolean isValidDestination = isConnectionValid(YamlDeserializer.externalConfig.getDestination(), "destination");

        log.info("Is YAML valid? " +
                ANSI.colour("Source = " + isValidSource, isValidSource ? ANSI.TEAL_BOLD : ANSI.RED_BOLD) + ", " +
                ANSI.colour("Destination = " + isValidDestination, isValidDestination ? ANSI.TEAL_BOLD : ANSI.RED_BOLD));

        return isValidSource && isValidDestination;
    }

    @SuppressWarnings("squid:S2178")
    private static boolean isConnectionValid(ConnectionEntity connection, String connectionType) {
        boolean isValid = false;

        if (connection != null) {
            isValid = isFieldValid(connection.getJdbcUrl(), "jdbcUrl", connectionType) &
                    isFieldValid(connection.getUsername(), "username", connectionType) &
                    isFieldValid(connection.getPassword(), "password", connectionType) &
                    isFieldValid(connection.getDriverClassName(), "driverClassName", connectionType);

            if (connection.getHikari() != null) {
                isValid = isValid && isFieldValid(connection.getHikari().getConnectionTimeout(), "connectionTimeout", connectionType) &
                        isFieldValid(connection.getHikari().getMaximumPoolSize(), "maximumPoolSize", connectionType);
            } else {
                log.error(ANSI.colour("Please enter the hikari values for " + connectionType + "  configuration", ANSI.RED_BOLD));
            }
        } else {
            log.error(ANSI.colour("Please enter the " + connectionType + " configurations", ANSI.RED_BOLD));
        }

        log.info(ANSI.colour("The configuration for " + connectionType + " resulted as " + (isValid ? "valid" : "invalid"), isValid ? ANSI.TEAL_BOLD : ANSI.RED_BOLD));
        return isValid;
    }

    private static boolean isFieldValid(String field, String fieldName, String connectionType) {
        if (field == null || field.isBlank()) {
            log.error(ANSI.colour("Please enter the value of " + fieldName + " in " + connectionType + " configuration", ANSI.RED_BOLD));
            return false;
        }
        return true;
    }

    private static boolean isFieldValid(int field, String fieldName, String connectionType) {
        if (field == 0) {
            log.error(ANSI.colour("Please enter the value of " + fieldName + " in: " + connectionType + " configuration", ANSI.RED_BOLD));
            return false;
        }
        return true;
    }
}