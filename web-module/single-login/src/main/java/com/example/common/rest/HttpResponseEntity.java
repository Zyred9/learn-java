package com.example.common.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@NoArgsConstructor
public class HttpResponseEntity {

    private Integer code;

    private String message;

    private Object data;

}
