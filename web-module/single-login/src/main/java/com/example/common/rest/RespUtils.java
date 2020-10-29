package com.example.common.rest;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class RespUtils {

    public static HttpResponseEntity ok (){
        return new HttpResponseEntity(200, "成功", null);
    }

    public static HttpResponseEntity ok (String msg){
        return new HttpResponseEntity(200, msg, null);
    }

    public static HttpResponseEntity error (){
        return new HttpResponseEntity(500, "失败", null);
    }
}
