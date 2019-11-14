package com.bnpparibas.crafters.tutorial.junit4;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Unit test for simple App.
 */
public class JUnitBasicTest {

    @Test
    public void should_pass_when_no_exception() {
        System.out.println("a test that does nothing passes");
    }

    @Ignore
    @Test
    public void should_fail_when_exception() throws Throwable {
        System.out.println("a test that does throws an exception fails");
        throw new Throwable("a test that does throws an exception fails");
    }

    @Test(expected = Throwable.class)
    public void should_success_when_exception_is_expected() throws Throwable {
        throw new Throwable("a test that does throws an exception fails");
    }

    @Test
    public void should_success_when_exception_is_managed() throws Throwable {
        try {
            String errorCase = "case XYZ";
            if (true) {
                throw new Throwable("Error due to: " + errorCase);
            }
            fail("exception expected but not thrown");
        } catch (Throwable t) {
            assertTrue("Exception messages should support debug",
                    t.getMessage()
                            .toLowerCase()
                            .contains(
                                    "XYZ".toLowerCase()));
        }
    }

    @Rule
    public ExpectedException should_raise = ExpectedException.none();

    @Test
    public void should_success_when_exception_is_expected_by_rule() throws Throwable {
        should_raise.expect(Throwable.class);
        should_raise.expectMessage("XYZ");
        String errorCase = "case XYZ";
        if (true) {
            throw new Throwable("Error due to: " + errorCase);
        }
    }


    @Ignore
    @Test(timeout = 1000)
    public void should_fail_after_timeout() throws InterruptedException {
        Thread.sleep(2000);
    }

    /**
     * With java 8 and anonymous functions (lambdas)
     * <code>
     @Test public void should_success_when_exception_is_managed_with_lambda() throws Throwable {
     String errorCase = "case XYZ";
     Assertions.assertThatThrownBy(
     () -> {
     if (true) {
     throw new Throwable("Error due to: " + errorCase);
     }
     })
     .hasMessageContaining("XYZ")
     .hasCauseInstanceOf(Throwable.class);
     fail("exception expected but not thrown");
     }
      * </code>
     */

}