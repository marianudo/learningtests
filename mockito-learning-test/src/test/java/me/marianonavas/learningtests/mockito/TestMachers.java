package me.marianonavas.learningtests.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class TestMachers {
    static class TestClass {
        public String signMyName(String myName) {
            return String.format("Signed: %s", myName);
        }
    }

    @Test
    public void testAnyOnRealClass() throws Exception {
        TestClass testClass = mock(TestClass.class);
        String expected = "Mocked Value";
        when(testClass.signMyName(any(String.class))).thenReturn(expected);
        String actual = testClass.signMyName("any");
        assertEquals(expected, actual);
    }
}
