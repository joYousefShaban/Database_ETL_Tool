package com.example.demo.services.yaml_reader;

import com.example.demo.entities.connection.ConnectionEntity;
import com.example.demo.services.logging.ANSI;
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
        boolean isValid = isFieldValid(connection.getJdbcUrl(), "jdbcUrl", connectionType) &
                isFieldValid(connection.getUsername(), "username", connectionType) &
                isFieldValid(connection.getPassword(), "password", connectionType) &
                isFieldValid(connection.getDriverClassName(), "driverClassName", connectionType) &
                isFieldValid(connection.getHikari().getConnectionTimeout(), "connectionTimeout", connectionType) &
                isFieldValid(connection.getHikari().getMaximumPoolSize(), "maximumPoolSize", connectionType);

        log.info(ANSI.colour("The connection configuration of " + connectionType + " resulted as: " + (isValid ? "valid" : "invalid"), isValid ? ANSI.TEAL_BOLD : ANSI.RED_BOLD));
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
            log.error(ANSI.colour("Please enter the value of " + fieldName + " in: " + connectionType, ANSI.RED_BOLD));
            return false;
        }
        return true;
    }
}