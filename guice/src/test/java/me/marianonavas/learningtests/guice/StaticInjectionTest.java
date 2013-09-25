package me.marianonavas.learningtests.guice;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class StaticInjectionTest {
    static class NotStaticClass {
        private final Injector injector;

        @Inject
        public NotStaticClass(Injector injector) {
            super();
            this.injector = injector;
        }

        protected Injector getInjector() {
            return injector;
        }
    }

    @Test
    public void testModulessInjection() throws Exception {
        Injector injector = Guice.createInjector();
        NotStaticClass instance = injector.getInstance(NotStaticClass.class);
        assertNotNull(instance.getInjector());
    }

    static class StaticClass {
        private static Injector INJECTOR;

        @Inject
        public StaticClass(Injector injector) {
            INJECTOR = injector;
        }
    }

    @Test
    public void testStaticInjection() throws Exception {
        Injector injector = Guice.createInjector();
        assertNull(StaticClass.INJECTOR);
        injector.getInstance(StaticClass.class);
        assertNotNull(StaticClass.INJECTOR);
    }
}
