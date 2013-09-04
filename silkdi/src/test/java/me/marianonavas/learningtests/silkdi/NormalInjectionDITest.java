package me.marianonavas.learningtests.silkdi;

import static me.marianonavas.learningtests.silkdi.Helper.resolveDependency;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import se.jbee.inject.Injector;
import se.jbee.inject.bind.BinderModule;
import se.jbee.inject.bootstrap.Bootstrap;

public class NormalInjectionDITest {
	private Injector injector;

	static class InjectionModule extends BinderModule {

		@Override
		protected void declare() {
			bind(IDependee.class).to(Dependee.class);
		}

	}

	static class ConstructorDepender {
		private final IDependee dependee;

		public ConstructorDepender(IDependee dependee) {
			super();
			this.dependee = dependee;
		}

		public IDependee getDependee() {
			return dependee;
		}

	}

	static class Dependee implements IDependee {

		@Override
		public void doIt() {
			// Do nothing
		}
	}

	static interface IDependee {
		public void doIt();
	}

	@Before
	public void setUp() {
		injector = Bootstrap.injector(InjectionModule.class);
	}

	@Test
	public void testConcreteClassInstantiationByDIContainer() {
		Dependee dependee = resolveDependency(injector, Dependee.class);
		assertNotNull(dependee);
	}
	
	@Test
	public void testBindedInterfaceInstantiationByContainer() {
		IDependee actual = resolveDependency(injector, IDependee.class);
		assertNotNull(actual);
		assertEquals(Dependee.class, actual.getClass());
	}

}
