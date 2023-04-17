package com.appd.hackathon.instrumentation;

import com.appd.hackathon.instrumentation.interceptors.AMethodInterceptor;

public class Rule {

    private String cl;
    private String method;
    private Class<? extends AMethodInterceptor> interceptor;

    public Rule(String cl, String method, Class<? extends AMethodInterceptor> interceptor) {
        this.cl = cl;
        this.method = method;
        this.interceptor = interceptor;
    }

    public String getCl() {
        return this.cl;
    }

    public String getMethod() {
        return this.method;
    }

    public Class<? extends AMethodInterceptor> getInterceptor() {
        return this.interceptor;
    }


}
