package com.example.demo.services.logging;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
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
                log.info(ANSI.colour("Service of hiding" + file.getFileName() + " file is successful", ANSI.TEAL_BOLD));
            } else {
                throw new FileNotFoundException("File does not exist");
            }
        } catch (Exception e) {
            log.fatal(ANSI.colour("Service of hiding" + file.getFileName() + " file failed, and generated the following exception: \r\n" + e.getMessage(), ANSI.RED_BOLD));
        }
    }
}