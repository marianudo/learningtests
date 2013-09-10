package me.marianonavas.learningtests.guice;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

public class ScopesTest {
	static class MyClass {}
	
	static class Module extends AbstractModule {

		@Override
		protected void configure() {
			bind(MyClass.class).in(Singleton.class);
		}
		
	}
	
	private Injector injector;
	
	@Before
	public void setUp() {
		injector = Guice.createInjector(new Module());
	}
	
	@Test
	public void testInstanceSingleton() {
		MyClass instance1 = injector.getInstance(MyClass.class);
		MyClass instance2 = injector.getInstance(MyClass.class);
		assertSame(instance1, instance2);
	}
}
