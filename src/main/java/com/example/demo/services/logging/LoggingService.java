package com.example.demo.services.logging;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributeView;

@Log4j2
@Service
public class LoggingService {
    //Configurations of the logging service could be found at "logback.xml"
    public void hideFile(String filePath) {
        Path file = Path.of(filePath);

        try {
            if (Files.exists(file)) {

                DosFileAttributeView dosView = Files.getFileAttributeView(file, DosFileAttributeView.class);
                dosView.setHidden(true);
                log.info("Process of hiding SysLog File has been successful");
            } else {
                log.error("Process of hiding SysLog File has failed");
            }
        } catch (IOException e) {
            log.fatal("Process of hiding SysLog File has caused the following exception: " + e.getMessage());
        }
    }
}