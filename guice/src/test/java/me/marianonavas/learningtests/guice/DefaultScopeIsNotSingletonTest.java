package me.marianonavas.learningtests.guice;

import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class DefaultScopeIsNotSingletonTest {
	static class MyClass {}
	
	private Injector injector;
	
	@Before
	public void setUp() {
		injector = Guice.createInjector();
	}
	
	@Test
	public void testGetInstanceIsNotSingletonByDefatult() {
		MyClass instance1 = injector.getInstance(MyClass.class);
		MyClass instance2 = injector.getInstance(MyClass.class);
		assertNotSame(instance1, instance2);
	}
}
