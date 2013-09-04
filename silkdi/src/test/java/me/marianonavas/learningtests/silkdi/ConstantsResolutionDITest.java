package me.marianonavas.learningtests.silkdi;

import static org.junit.Assert.assertEquals;
import static se.jbee.inject.Dependency.dependency;
import static se.jbee.inject.Name.named;

import org.junit.Before;
import org.junit.Test;

import se.jbee.inject.Injector;
import se.jbee.inject.bind.BinderModule;
import se.jbee.inject.bootstrap.Bootstrap;

public class ConstantsResolutionDITest {
	private static final String DEFAULT_AGE = "defaultAge";
	private Injector injector;

	static class ConstantsResolutionTestModule extends BinderModule {

		@Override
		protected void declare() {
			bind(Integer.class).to(100);
			bind(named(DEFAULT_AGE), Integer.class).to(37);
		}

	}

	@Before
	public void setup() {
		injector = Bootstrap.injector(ConstantsResolutionTestModule.class);
	}

	@Test
	public void testIntegerInjection() {
		Integer actual = injector.resolve(dependency(Integer.class));
		assertEquals(new Integer(100), actual);
	}

	@Test
	public void testNamedInjection() {
		Integer actual = injector.resolve(dependency(Integer.class).named(DEFAULT_AGE));
		assertEquals(new Integer(37), actual);
	}

}
