package me.marianonavas.learningtests.silkdi;

import static me.marianonavas.learningtests.silkdi.Helper.resolveDependency;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import se.jbee.inject.Injector;
import se.jbee.inject.bind.BinderModule;
import se.jbee.inject.bootstrap.Bootstrap;

public class InstantiationByDIContainerTest {
	private Injector injector;

	static class StandaloneBean {
		public void doMyChore() {
			System.out.println("I'm alone :-(");
		}
	}

	static class InjectionModule extends BinderModule {

		@Override
		protected void declare() {
			construct(StandaloneBean.class);
		}

	}
	
	@Before
	public void setUp() {
		injector = Bootstrap.injector(InjectionModule.class);
	}
	
	@Test
	public void testRegisterObject() {
		StandaloneBean bean = resolveStandAloneBean();
		assertNotNull(bean);
	}

	private StandaloneBean resolveStandAloneBean() {
		Class<StandaloneBean> clazz = StandaloneBean.class;
		StandaloneBean bean = resolveDependency(injector, clazz);
		return bean;
	}

	@Test
	public void testRegisteredObjectIsSingleton() {
		StandaloneBean bean1 = resolveStandAloneBean();
		StandaloneBean bean2 = resolveStandAloneBean();
		assertSame(bean1, bean2);
	}
}
