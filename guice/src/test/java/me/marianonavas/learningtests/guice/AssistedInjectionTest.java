package me.marianonavas.learningtests.guice;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class AssistedInjectionTest {
	private static final Integer INTEGER_VALUE = 100;
	
	private static final String EXPECTED_STRING = "Hello World!!";
	
	static class Subject {
		final String variableValue;
		final Integer fixedValue;

		@Inject
		public Subject(@Assisted String variableValue, Integer fixedValue) {
			super();
			this.variableValue = variableValue;
			this.fixedValue = fixedValue;
		}
	}
	
	static class Module extends AbstractModule {

		@Override
		protected void configure() {
			bind(Integer.class).toInstance(INTEGER_VALUE);
			install(new FactoryModuleBuilder().build(SubjectFactory.class));
		}
		
	}
	
	static interface SubjectFactory {
		Subject create(String str);
	}
	
	private SubjectFactory subjectFactory;
	
	@Before
	public void setUp() {
		Injector injector = Guice.createInjector(new Module());
		subjectFactory = injector.getInstance(SubjectFactory.class);
	}
	
	@Test
	public void testModuleDrivenInjection() {
		Subject subject = subjectFactory.create(EXPECTED_STRING);
		assertEquals(INTEGER_VALUE, subject.fixedValue);
	}
	
	@Test
	public void testAssitedInjection() {
		Subject subject = subjectFactory.create(EXPECTED_STRING);
		assertEquals(EXPECTED_STRING, subject.variableValue);
	}
}
