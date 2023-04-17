package com.appd.hackathon;

import java.lang.instrument.Instrumentation;

public class MiniAgent {
    public static void premain(String args, Instrumentation inst) throws Exception {
        System.out.println("--| Starting MiniAgent |--");
        inst.addTransformer(new Transformer());

    }
}