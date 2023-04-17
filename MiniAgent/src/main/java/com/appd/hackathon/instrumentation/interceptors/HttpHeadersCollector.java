package com.appd.hackathon.instrumentation.interceptors;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class HttpHeadersCollector {
    public Map<String, String> getHeaders(HttpServletRequest request){
        Map<String, String> map = new HashMap<String, String>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String key = (String) headerNames.nextElement();
            String val = request.getHeader(key);
            map.put(key, val);
        }
        return map;
    }
}
