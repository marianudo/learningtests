package me.marianonavas.learningtests.silkdi;

import static me.marianonavas.learningtests.silkdi.Helper.resolveDependency;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import me.marianonavas.learningtests.silkdi.beansfortest.Dependee;
import me.marianonavas.learningtests.silkdi.beansfortest.IDependee;

import org.junit.Before;
import org.junit.Test;

import se.jbee.inject.Injector;
import se.jbee.inject.bootstrap.Bootstrap;

public class NormalInjectionDITest {
	private Injector injector;

	@Before
	public void setUp() {
		injector = Bootstrap.injector(POJInjectionModule.class);
	}

	@Test
	public void testConcreteClassInstantiationByDIContainer() {
		Dependee actual = resolveDependency(injector, Dependee.class);
		assertTypeOfResolvedDependency(actual);
	}
	
	@Test
	public void testBindedInterfaceInstantiationByContainer() {
		IDependee actual = resolveDependency(injector, IDependee.class);
		assertTypeOfResolvedDependency(actual);
	}

	private void assertTypeOfResolvedDependency(IDependee actual) {
		assertNotNull(actual);
		assertEquals(Dependee.class, actual.getClass());
	}

}
