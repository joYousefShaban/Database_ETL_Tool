package com.example.demo.services.yaml_reader;

import com.example.demo.entities.connection.DataSourceEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

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

            log.info("Process of loading external yaml file has been successful");
        } catch (IOException e) {
            log.fatal("Process of loading external yaml file has caused the following exception: " + e.getMessage());
        }
    }

}