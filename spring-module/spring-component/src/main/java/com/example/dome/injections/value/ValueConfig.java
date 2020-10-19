package com.example.dome.injections.value;

import com.example.project.entity.Bird;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Tom.
 */
@Configuration
public class ValueConfig {

    @Bean
    public Bird bird(){
        return new Bird();
    }
}
