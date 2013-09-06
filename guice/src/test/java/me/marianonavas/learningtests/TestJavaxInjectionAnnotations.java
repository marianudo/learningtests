package me.marianonavas.learningtests;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;

public class TestJavaxInjectionAnnotations {
	
	static interface NamedItem {
		FirstName getFirstName();
	}
	
	static class Mariano implements NamedItem {

		@Override
		public FirstName getFirstName() {
			return FirstName.MARIANO;
		}
	}
	
	static class Francisco implements NamedItem {
		
		@Override
		public FirstName getFirstName() {
			return FirstName.FRANCISCO;
		}
	}
	
	static enum FirstName {
		MARIANO, FRANCISCO
	}
	
	static class NamesHolder {
		final NamedItem mariano;
		final NamedItem francisco;
		
		@Inject
		public NamesHolder(@Named("Mariano") NamedItem mariano, @Named("Francisco") NamedItem francisco) {
			super();
			this.mariano = mariano;
			this.francisco = francisco;
		}
	}
	
	static class Module extends AbstractModule {

		@Override
		protected void configure() {
			//The following bind is not necessary, although including it doesn't harm 
//			bind(NamesHolder.class).asEagerSingleton();
			bind(NamedItem.class).annotatedWith(Names.named("Mariano")).to(Mariano.class).asEagerSingleton();
			bind(NamedItem.class).annotatedWith(Names.named("Francisco")).to(Francisco.class).asEagerSingleton();
		}
		
	}
	
	private Injector injector;
	
	@Before
	public void setUp() {
		injector = Guice.createInjector(new Module());
	}
	
	@Test
	public void testInjectMariano() {
		NamesHolder holder = injector.getInstance(NamesHolder.class);
		assertEquals(FirstName.MARIANO, holder.mariano.getFirstName());
		assertEquals(FirstName.FRANCISCO, holder.francisco.getFirstName());
	}
}
