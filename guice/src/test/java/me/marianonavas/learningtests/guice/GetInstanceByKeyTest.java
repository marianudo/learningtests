package me.marianonavas.learningtests.guice;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.ConfigurationException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class GetInstanceByKeyTest {
	private static final Named NAMED1 = Names.named("its1");
	private static final Named NAMED2 = Names.named("its2");
	private static final Named NAMED3 = Names.named("its3");

	static class Module extends AbstractModule {

		@Override
		protected void configure() {
			bind(Injected.class).annotatedWith(NAMED1).to(InjectedImpl1.class);
			bind(Injected.class).annotatedWith(NAMED2).to(InjectedImpl2.class);
		}
		
	}
	
	static interface Injected {}
	
	static class InjectedImpl1 implements Injected {}
	
	static class InjectedImpl2 implements Injected {}
	
	@Test
	public void testNamedBinded1() throws Exception {
		Named named = NAMED1;
		Class<InjectedImpl1> expectedinstanceClass = InjectedImpl1.class;
		genericTest(named, expectedinstanceClass);
	}
	
	@Test
	public void testBindedNamed2() {
		Named named = NAMED2;
		Class<InjectedImpl2> expectedinstanceClass = InjectedImpl2.class;
		genericTest(named, expectedinstanceClass);
	}

	private void genericTest(Named named, Class<? extends Injected> expectedinstanceClass) {
		Injected instance = getInjectedInstance(named);
		assertNotNull(instance);
		assertEquals(expectedinstanceClass, instance.getClass());
	}

	private Injected getInjectedInstance(Named named) {
		Injector injector = Guice.createInjector(new Module());
		Key<Injected> key = Key.get(Injected.class, named);
		Injected instance = injector.getInstance(key);
		return instance;
	}
	
	@Test(expected=ConfigurationException.class)
	public void testGetNamedNotBinded() throws Exception {
		Injected instance = getInjectedInstance(NAMED3);
		assertNull(instance);
	}
}
