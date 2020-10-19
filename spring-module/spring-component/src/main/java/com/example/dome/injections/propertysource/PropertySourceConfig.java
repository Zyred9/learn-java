package com.example.dome.injections.propertysource;

import com.example.project.entity.Bird;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Tom.
 */
@Configuration
@PropertySource("classpath:values.properties")
public class PropertySourceConfig {

    @Bean
    public Bird bird(){
        return new Bird();
    }
}
