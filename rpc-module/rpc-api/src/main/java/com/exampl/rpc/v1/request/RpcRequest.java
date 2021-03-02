package com.exampl.rpc.v1.request;

import java.io.Serializable;

/**
 *  RPC远程调用通讯协议
 *
 * @author zyred
 * @since v 0.1
 **/
public class RpcRequest implements Serializable{

    private String className;
    private String methodName;
    private Object[] args;
    private Class<?>[] types;

    public String getClassName() {
        return className;
    }

    public RpcRequest setClassName(String className) {
        this.className = className;
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public RpcRequest setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public Object[] getArgs() {
        return args;
    }

    public RpcRequest setArgs(Object[] args) {
        this.args = args;
        return this;
    }

    public Class<?>[] getTypes() {
        return types;
    }

    public RpcRequest setTypes(Class<?>[] types) {
        this.types = types;
        return this;
    }
}
