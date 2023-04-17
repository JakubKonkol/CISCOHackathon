package com.appd.hackathon.instrumentation.interceptors;

public abstract class AMethodInterceptor {

    public abstract Object onMethodBegin(Object invokedObject, String className, String methodName,
                                         Object[] paramValues);

    public abstract void onMethodEnd(Object invokedObject, String className, String methodName,
                                       Object[] paramValues, Object returnValue);
}
