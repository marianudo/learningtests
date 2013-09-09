package me.marianonavas.learningtests.guice.packagetests;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import me.marianonavas.learningtests.guice.packagetests.classes.IThing;
import me.marianonavas.learningtests.guice.packagetests.classes.ThingImpl;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;


/*
 * A not public constructor is accessible to Guice DI, but not to the rest of the world.
 */
public class PackageAccesibilityTest {
	static class Module extends AbstractModule {

		@Override
		protected void configure() {
			bind(IThing.class).to(ThingImpl.class).asEagerSingleton();
		}
		
	}
	
	private Injector injector;
	
	@Before
	public void setUp() {
		injector = Guice.createInjector(new Module());
	}
	
	@Test
	public void testNotPublicConstructorAccessible() {
		IThing instance = injector.getInstance(IThing.class);
		assertNotNull(instance);
	}
}
