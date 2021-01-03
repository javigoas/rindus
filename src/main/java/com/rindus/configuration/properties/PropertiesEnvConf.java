package com.rindus.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "url")
@Getter
@Setter
public class PropertiesEnvConf {
    private String base;
}
