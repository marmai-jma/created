package com.bnpparibas.crafters.tutorial.junit4;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class JUnitAdvancedRulesTest {
    @ClassRule
    public static TestRule staticRule = new MyTestRule("static");

    @Rule
    public TestRule instanceRule = new MyTestRule("instance");

    @Test
    public void test_1() {
        System.out.println("test 1 is now ");
    }

    @Test
    public void test_2() {
        System.out.println("test 2 is now ");
    }


    static class MyTestRule implements TestRule {
        private String context;

        public MyTestRule(String context) {
            this.context = context;
        }

        @Override
        public Statement apply(Statement base, Description description) {
            System.out.println("MyTestRule.apply "+context);
            return new MyStatement(base, context);
        }
    };

    static class MyStatement extends Statement {
        private final Statement next;
        private String context;

        MyStatement(Statement next, String context) {
            this.next = next;
            this.context = context;
        }
        @Override
        public void evaluate() throws Throwable {
            System.out.println("MyStatement.evaluate "+context+" before");
            next.evaluate();
            System.out.println("MyStatement.evaluate "+context+" after");
        }
    };
}
