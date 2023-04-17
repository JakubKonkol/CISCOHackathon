package com.appd.hackathon;

import com.appd.hackathon.instrumentation.Rule;
import com.appd.hackathon.instrumentation.RuleApplier;
import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Transformer implements ClassFileTransformer {

    private final RuleApplier ruleApplier = new RuleApplier();
    public List<String> headers = new ArrayList<String>();

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) {

        if (className == null) {
            return new byte[0];
        }

        try {
            List<Rule> rules = ruleApplier.matches(className);

            if (!rules.isEmpty()) {
                CtClass cc = getClass(className);

                for (Rule rule : rules) {
                    this.applyInterceptor(cc, rule);
                }
                return cc.toBytecode();
            }

        } catch (NotFoundException | RuntimeException | CannotCompileException | IOException e) {
            System.err.printf("%s class was not modified\nSome error exception occur: %s%n",
                    className, e.getMessage());
        }
        return new byte[0];
    }

    private void applyInterceptor(CtClass cc, Rule rule) throws NotFoundException, CannotCompileException,
            IOException {
        String interceptorClass = rule.getInterceptor().getCanonicalName();
        String interceptorClassVariable = "interceptorClassVariable_" + new Random().nextInt(Integer.MAX_VALUE);
        CtMethod cm = cc.getDeclaredMethod(rule.getMethod());

        System.out.printf("Applying interceptor: %s on: %n", interceptorClass);
        System.out.printf(" cc: %s ", cc.getName());
        System.out.printf("cm: %s ", cm.getName());

        CtField f = CtField.make("private " + interceptorClass + " " + interceptorClassVariable + ";", cc);
        cc.addField(f);


        String insertBefore = String.format("this.%s = new %s(); this.%s.onMethodBegin($0, \"%s\", \"%s\", $args); ",
                interceptorClassVariable, interceptorClass, interceptorClassVariable, cc.getName(), cm.getName());
        System.out.println(insertBefore);
        cm.insertBefore(insertBefore);

        String insertAfter = String.format("this.%s.onMethodEnd($0, \"%s\", \"%s\", $args, $_); ",
                interceptorClassVariable, cc.getName(), cm.getName());
        System.out.println(insertAfter);

        cm.insertAfter(insertAfter);
    }

    private CtClass getClass(String className) throws NotFoundException {
        ClassPool cp = new ClassPool(ClassPool.getDefault());
        cp.insertClassPath(new ClassClassPath(this.getClass()));
        cp.insertClassPath(new LoaderClassPath(Thread.currentThread().getContextClassLoader()));
        return cp.get(className.replace("/", "."));
    }

}
