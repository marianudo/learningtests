package me.marianonavas.learningtests.silkdi;

import static me.marianonavas.learningtests.silkdi.Helper.resolveDependency;
import static org.junit.Assert.assertSame;
import me.marianonavas.learningtests.silkdi.beansfortest.IDependee;

import org.junit.Before;
import org.junit.Test;

import se.jbee.inject.Injector;
import se.jbee.inject.bootstrap.Bootstrap;

public class ScopedBindingTest {
	private Injector injector;
	
	@Before
	public void setUp() {
		injector = Bootstrap.injector(POJInjectionModule.class);
	}
	
	@Test
	public void testDefaultScopBinding() {
		IDependee actual1 = resolveDependency(injector, IDependee.class);
		IDependee actual2 = resolveDependency(injector, IDependee.class);
		assertSame(actual1, actual2);
	}
	
}
