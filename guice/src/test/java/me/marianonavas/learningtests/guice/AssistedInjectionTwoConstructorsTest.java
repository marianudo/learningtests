package me.marianonavas.learningtests.guice;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.assistedinject.AssistedInject;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class AssistedInjectionTwoConstructorsTest {
    static class Subject {
        String name;
        Integer age;

        @AssistedInject
        Subject(String name) {
            this.name = name;
        }

        @AssistedInject
        Subject(Integer age) {
            this.age = age;
        }
    }

    static interface SubjectFactory {
        Subject create(String name);

        Subject create(Integer age);
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
    public void testStringConstructorInjection() throws Exception {
        String expected = "Mariano";
        Subject subject = factory.create(expected);
        assertEquals(expected, subject.name);
    }
}
