package me.marianonavas.learningtests.java;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StaticReferencesTest {
    static String prop;

    @Test
    public void testIt() throws Exception {
        String expected = "Value";
        prop = expected;
        String notExpected = "NotExpectedValue";
        StaticReferencesTest.prop = notExpected;
        assertEquals(notExpected, prop);
    }
}
