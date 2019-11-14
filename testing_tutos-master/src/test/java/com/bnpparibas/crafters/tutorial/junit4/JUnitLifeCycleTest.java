package com.bnpparibas.crafters.tutorial.junit4;

import org.junit.*;

public class JUnitLifeCycleTest {

    private static Counter classCount = new Counter("class");
    private Counter instanceCount = new Counter("instance");

    @BeforeClass
    public static void setUpOnce() throws Exception {
        classCount.increment();
        System.out.println("BeforeClass is now "+staticStatusMessage());
    }

    @Before
    public void setUp() throws Exception {
        instanceCount.increment();
        System.out.println("Before is now "+statusMessage());
    }

    @Test
    public void test_1() {
        System.out.println("test 1 is now "+statusMessage());
    }

    @Test
    public void test_2() {
        System.out.println("test 2 is now "+statusMessage());
    }

    @After
    public void tearDown() throws Exception {
        instanceCount.decrement();
        System.out.println("After is now "+statusMessage());
    }

    @AfterClass
    public static void tearDownOnce() throws Exception {
        classCount.decrement();
        System.out.println("AfterClass is now "+staticStatusMessage());
    }

    public static String staticStatusMessage() {
        return "with "+classCount;
    }

    public String statusMessage() {
        return staticStatusMessage() + " with " + instanceCount;
    }

    private static class Counter {
        private int counter = 0;
        private String message;

        public Counter(String message) {
            this.message = message;
            System.out.println("new counter: " + toString());
        }

        public void decrement() {
            counter--;
        }

        public void increment() {
            this.counter++;
        }

        @Override
        public String toString() {
            return message + "{" + counter + "}";
        }
    }

}
