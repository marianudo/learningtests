package me.marianonavas.learningtests.guice.interfaceassisted;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class InterfaceAssistedRealTest {
    private AssistedService service;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new InterfaceAssistedModule());
        AssistedServiceFactory factory = injector.getInstance(AssistedServiceFactory.class);
        service = factory.create(200);
    }

    @Test
    public void testFixedInjection() throws Exception {
        assertEquals(150, service.getFixed().intValue());
    }

    @Test
    public void testVariableInjection() throws Exception {
        assertEquals(200, service.getVariable().intValue());
    }
}
