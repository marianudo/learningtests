package me.marianonavas.learningtests.java;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

public class LoadPropertiesTest {
    @Test
    public void testLoadPropertiesFromClasspath() throws Exception {
        String propsFile = "/myprops.properties";
        InputStream resourceAsStream = getClass().getResourceAsStream(propsFile);
        Properties props = new Properties();
        props.load(resourceAsStream);
        String actual = props.getProperty("prop1");
        assertEquals("1", actual);
        resourceAsStream.close();
    }

    @Test
    public void testStaticLoadPropertiesFromClassPath() throws Exception {
        try {
            InputStream resourceAsStream = LoadPropertiesTest.class.getResourceAsStream("/ogt.properties");
            Properties props = new Properties();
            props.load(resourceAsStream);
            fail("Should have thrown NullPointerException");
        } catch (NullPointerException ex) {
            assertNotNull(ex);
        }
    }
}
