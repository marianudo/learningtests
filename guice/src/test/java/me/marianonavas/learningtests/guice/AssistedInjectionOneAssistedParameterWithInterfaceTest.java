package me.marianonavas.learningtests.guice;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class AssistedInjectionOneAssistedParameterWithInterfaceTest {
    private static final Integer INTEGER_VALUE = 100;

    private static final String EXPECTED_STRING = "Hello World!!";

    static interface ISubject {

        Integer getInteger();

        String getString();

    }

    static class Subject implements ISubject {
        final String variableValue;
        final Integer fixedValue;

        @Inject
        public Subject(@Assisted String variableValue, Integer fixedValue) {
            super();
            this.variableValue = variableValue;
            this.fixedValue = fixedValue;
        }

        @Override
        public Integer getInteger() {
            return fixedValue;
        }

        @Override
        public String getString() {
            return variableValue;
        }
    }

    static class Module extends AbstractModule {

        @Override
        protected void configure() {
            bind(Integer.class).toInstance(INTEGER_VALUE);
            install(new FactoryModuleBuilder().implement(ISubject.class, Subject.class).build(SubjectFactory.class));
        }

    }

    static interface SubjectFactory {
        ISubject create(String str);
    }

    private SubjectFactory subjectFactory;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new Module());
        subjectFactory = injector.getInstance(SubjectFactory.class);
    }

    @Test
    public void testModuleDrivenInjection() {
        ISubject subject = subjectFactory.create(EXPECTED_STRING);
        assertEquals(INTEGER_VALUE, subject.getInteger());
    }

    @Test
    public void testAssistedInjection() throws Exception {
        ISubject subject = subjectFactory.create(EXPECTED_STRING);
        assertEquals(EXPECTED_STRING, subject.getString());
    }

}
