package com.robin.encuesta.configuration.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "datasource")
@RequiredArgsConstructor
@Data
public class DataSourceProperties {
    String host;
    Integer port;
    String username;
    String password;
    String db;
}
