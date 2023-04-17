package com.appd.hackathon.instrumentation.interceptors;

import com.appd.hackathon.Transformer;

public class MethodInterceptor extends AMethodInterceptor {
    public long startTime, endTime, totalTime;

    @Override
    public Object onMethodBegin(Object invokedObject, String className, String methodName, Object[] paramValues) {
        startTime = System.nanoTime();
        System.out.println("MethodInterceptor - onMethodBegin");

        return null;
    }

    @Override
    public void onMethodEnd(Object invokedObject, String className, String methodName, Object[] paramValues,
                            Object returnValue) {
        endTime = System.nanoTime();
        System.out.println("MethodInterceptor - onMethodEnd");
        totalTime = endTime - startTime;
        totalTime = totalTime / 1000000;
//        System.out.println("METHOD EXECUTION TIME:\t"  + (totalTime)+"ms");
    }
}
