package me.marianonavas.learningtests.guice;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class AssistedInjectionTwoAssistedParametersTest {
    static class Subject {
        final String strVar;
        final Integer intVar;

        @Inject
        public Subject(@Assisted String strVar, @Assisted Integer intVar) {
            super();
            this.strVar = strVar;
            this.intVar = intVar;
        }
    }

    static interface SubjectFactory {
        Subject create(String str, int integer);
    }

    static class Module extends AbstractModule {

        @Override
        protected void configure() {
            install(new FactoryModuleBuilder().build(SubjectFactory.class));
        }

    }

    private SubjectFactory factory;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new Module());
        factory = injector.getInstance(SubjectFactory.class);
    }

    @Test
    public void testFirstArgumentInjection() throws Exception {
        String expected = "Hola";
        Subject subject = factory.create(expected, 500);
        assertEquals(expected, subject.strVar);
    }

    @Test
    public void testSecondArgumentInjection() throws Exception {
        Integer expected = 600;
        Subject subject = factory.create("Hola", expected);
        assertEquals(expected, subject.intVar);
    }
}
