package com.example.dome.injections.autowired;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Tom.
 */
@Configuration
@ComponentScan({
        "com.example.project.controller",
        "com.example.project.service",
        "com.example.project.dao"
            })
public class AutowiredConfig {

}
