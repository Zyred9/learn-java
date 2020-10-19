package com.example.dome.configures.component;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@Configuration
@ComponentScan(value = "com.example.project",
        includeFilters = {
        @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {Controller.class}
                )
        },
        useDefaultFilters = false)
public class ComponentConfig {
}
