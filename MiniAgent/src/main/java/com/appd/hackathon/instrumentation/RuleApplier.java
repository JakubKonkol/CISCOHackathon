package com.appd.hackathon.instrumentation;

import com.appd.hackathon.instrumentation.interceptors.AMethodInterceptor;
import com.appd.hackathon.instrumentation.interceptors.ServletMethodInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RuleApplier {

    private List<Rule> rules = new ArrayList<>();

    //Servlet rule
    private static String SERVLET_CLASS = "javax.servlet.http.HttpServlet";
    private static String SERVLET_METHOD = "service";
    private static Class<? extends AMethodInterceptor> SERVLET_INTERCEPTOR = ServletMethodInterceptor.class;

    public RuleApplier() {
        this.generateRules();
    }

    public void generateRules() {
        {
            Rule rule = new Rule(SERVLET_CLASS, SERVLET_METHOD, SERVLET_INTERCEPTOR);
            this.rules.add(rule);
            System.out.println(this.rules.toString());
        }
    }

    public List<Rule> matches(String className) {
        return rules.stream()
                .filter(r -> r.getCl()
                .equals(className.replace("/",".")))
                .collect(Collectors.toList());
    }
}