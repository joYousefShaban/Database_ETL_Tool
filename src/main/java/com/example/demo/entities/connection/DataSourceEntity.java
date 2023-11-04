package com.example.demo.entities.connection;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
public class DataSourceEntity {
    private SourceEntity source;

    private DestinationEntity destination;
}