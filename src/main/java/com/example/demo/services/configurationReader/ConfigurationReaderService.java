package com.example.demo.services.configurationReader;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Log4j2
@Service
public class ConfigurationReaderService {

    public void replaceYAMLConfigurations() {

        try {
            // Load the new YAML file from the resources folder
            File newPropertiesYaml = ResourceUtils.getFile("./ConnectionConfigurations.yaml");

            // Define the path to the existing properties.yaml file
            Path existingPropertiesYaml = Path.of("src\\main\\resources\\application.yaml");

            // Replace the existing properties.yaml with the new one
            Files.copy(newPropertiesYaml.toPath(), existingPropertiesYaml, StandardCopyOption.REPLACE_EXISTING);
            log.info("Process of replacing YAML configurations has been successful");
        } catch (Exception e) {
            log.fatal("Process of replacing YAML configurations has caused the following exception: " + e.getMessage());
        }
    }
}
