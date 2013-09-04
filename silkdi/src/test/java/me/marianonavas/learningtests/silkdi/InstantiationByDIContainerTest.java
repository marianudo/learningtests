package me.marianonavas.learningtests.silkdi;

import static me.marianonavas.learningtests.silkdi.Helper.resolveDependency;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import se.jbee.inject.Injector;
import se.jbee.inject.bind.BinderModule;
import se.jbee.inject.bootstrap.Bootstrap;
import se.jbee.inject.bootstrap.Bundle;

public class InstantiationByDIContainerTest {

	private static final String HI = "Hi!";

	private static final String SPECIFIC_HI = "Specific Hi!";

	private Injector injector;

	private void createInjectorFromModule(Class<? extends Bundle> clazz) {
		injector = Bootstrap.injector(clazz);
	}
	
	static interface IStandaloneBean {
		String getDependency();
	}

	static class StandaloneBean implements IStandaloneBean {
		private final String dependency;

		public StandaloneBean(String dependency) {
			super();
			this.dependency = dependency;
		}

		public String getDependency() {
			return dependency;
		}
	}

	static class ConstructModule extends BinderModule {

		@Override
		protected void declare() {
			bind(String.class).to(HI);
			construct(StandaloneBean.class);
		}

	}

	static class SpecificConstructModule extends BinderModule {

		@Override
		protected void declare() {
			injectingInto(StandaloneBean.class).bind(String.class).to(SPECIFIC_HI);
			construct(StandaloneBean.class);
		}

	}
	
	static class InterfaceBindModule extends BinderModule {

		@Override
		protected void declare() {
			bind(String.class).to(HI);
			bind(IStandaloneBean.class).to(StandaloneBean.class);
		}
		
	}
	
	static class SpecificInterfaceBindModule extends BinderModule {
		
		@Override
		protected void declare() {
			injectingInto(IStandaloneBean.class).bind(String.class).to(SPECIFIC_HI);
			bind(IStandaloneBean.class).to(StandaloneBean.class);
		}
		
	}

	@Test
	public void testRegisterObjectByConstruct() {
		createInjectorFromModule(ConstructModule.class);
		StandaloneBean bean = resolveStandAloneBean();
		assertNotNull(bean);
	}

	private StandaloneBean resolveStandAloneBean() {
		Class<StandaloneBean> clazz = StandaloneBean.class;
		StandaloneBean bean = resolveDependency(injector, clazz);
		return bean;
	}

	@Test
	public void testConstructorRegisteredObjectIsSingleton() {
		createInjectorFromModule(ConstructModule.class);
		StandaloneBean bean1 = resolveStandAloneBean();
		StandaloneBean bean2 = resolveStandAloneBean();
		assertSame(bean1, bean2);
	}

	@Test
	public void testRegisteredObjectDependencies() {
		String expected = HI;
		Class<? extends Bundle> clazz = ConstructModule.class;
		genericTestObjectDependencies(expected, clazz);
	}

	@Test
	public void testRegisteredSpecificObjectDependencies() {
		String expected = SPECIFIC_HI;
		Class<? extends Bundle> clazz = SpecificConstructModule.class;
		genericTestObjectDependencies(expected, clazz);
	}
	
	@Test
	public void testInterfaceBindingObjectDependencies() {
		String expected = HI;
		Class<? extends Bundle> clazz = InterfaceBindModule.class;
		genericTestObjectDependencies(expected, clazz);
	}
	
	@Test
	public void testSpecificInterfaceBindingObjectDependencies() {
		String expected = SPECIFIC_HI;
		Class<? extends Bundle> clazz = SpecificInterfaceBindModule.class;
		genericTestObjectDependencies(expected, clazz);
	}

	private void genericTestObjectDependencies(String expected, Class<? extends Bundle> clazz) {
		createInjectorFromModule(clazz);
		StandaloneBean standaloneBean = resolveStandAloneBean();
		assertEquals(expected, standaloneBean.getDependency());
	}
}
