package com.example.demo.services.yaml_reader;

import com.example.demo.services.yaml_reader.template.DataSourceConfig;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Service
public class YamlConfigurationValidator {

    //TODO
    public static boolean isYamlValid() throws FileNotFoundException {
        /*InputStream inputStream = new FileInputStream("ConnectionConfigurations.yaml"); // Make sure the YAML file name matches your file name

        Yaml yaml = new Yaml();
        DataSourceConfig config = yaml.loadAs(inputStream, DataSourceConfig.class);

        if (config == null) {
            throw new YAMLException("Failed to parse the YAML file");
        }
*/
        return true;
    }
}
