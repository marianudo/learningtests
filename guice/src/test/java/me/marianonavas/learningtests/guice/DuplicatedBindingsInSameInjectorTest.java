package me.marianonavas.learningtests.guice;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class DuplicatedBindingsInSameInjectorTest {
	
	static interface Task {
		public void doIt();
	}
	
	static class TurnOnTheLights implements Task {

		@Override
		public void doIt() {
			//Do nothing
		}
		
	}
	
	static class Module1 extends AbstractModule {

		@Override
		protected void configure() {
			bind(Task.class).to(TurnOnTheLights.class).asEagerSingleton();
		}
		
	}
	
	static class Module2 extends AbstractModule {

		@Override
		protected void configure() {
			bind(Task.class).to(TurnOnTheLights.class).asEagerSingleton();
		}
		
	}
	
	private Injector injector;
	
	@Before
	public void setUp() {
		injector = Guice.createInjector(new Module1(), new Module2());
	}
	
	@Test
	public void testDuplicatedBindingAndNothingHappens() {
		Task task = injector.getInstance(Task.class);
		assertEquals(TurnOnTheLights.class, task.getClass());
	}
}
