package com.appd.hackathon.instrumentation.interceptors;

import java.util.ArrayList;
import java.util.List;

public class ServletMethodInterceptor extends MethodInterceptor {
    public long startTime, endTime, totalTime;
    public List<Long> aggregatedList = new ArrayList<>();

    @Override
    public Object onMethodBegin(Object invokedObject, String className, String methodName, Object[] paramValues) {
        super.onMethodBegin(invokedObject, className, methodName, paramValues);
        startTime = System.nanoTime();
        System.out.println("ServletMethodInterceptor - onMethodBegin");
        return null;
    }

    @Override
    public void onMethodEnd(Object invokedObject, String className, String methodName, Object[] paramValues,
                            Object returnValue) {
        super.onMethodEnd(invokedObject, className, methodName, paramValues, returnValue);
        endTime = System.nanoTime();
        System.out.println("ServletMethodInterceptor - onMethodEnd");
        totalTime = endTime - startTime;
        totalTime = totalTime / 1000000;
        System.out.println("METHOD EXECUTION TIME:"  + (totalTime)+"ms");
        System.out.println("AVG EXECUTION TIME:" + (calculateTime(totalTime)+"ms"));

    }
    public Long calculateTime(Long totalTime){
        aggregatedList.add(totalTime);
        long sum = aggregatedList.stream().mapToInt(Long::intValue).sum();
        return sum/aggregatedList.size();

    }

}