package com.example.demo.services.yaml_reader;

import com.example.demo.entities.connection.DataSourceEntity;
import com.example.demo.services.logging.ANSI;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Log4j2
@Getter
public class YamlDeserializer {

    public static DataSourceEntity externalConfig;

    private YamlDeserializer() {

    }

    public static void load() {
        try {
            // Create an ObjectMapper configured to read and write YAML data
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

            // Read the YAML data from the external file and convert it into a DataSourceConfig object
            externalConfig = mapper.readValue(new File("./ConnectionConfigurations.yml"), DataSourceEntity.class);

            log.info(ANSI.colour("Service of loading the external configuration file is successful", ANSI.TEAL_BOLD));
        } catch (Exception e) {
            log.fatal(ANSI.colour("Service of loading the external configuration file failed and caused following exception: " + e.getMessage(), ANSI.RED_BOLD));
        }
    }

}