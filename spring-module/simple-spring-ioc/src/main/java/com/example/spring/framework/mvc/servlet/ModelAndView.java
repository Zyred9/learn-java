package com.example.spring.framework.mvc.servlet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@Setter
@Getter
@AllArgsConstructor
public class ModelAndView {

    private String viewName;

    private Map<String, ?> model;

    public ModelAndView(String viewName) {
        this.viewName = viewName;
    }
}
