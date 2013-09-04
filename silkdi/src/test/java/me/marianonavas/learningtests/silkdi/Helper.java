package me.marianonavas.learningtests.silkdi;

import se.jbee.inject.Dependency;
import se.jbee.inject.Injector;

/*
 * TODO MNG Candidate to commit to original project
 */
public class Helper {
	
	public static <T> T resolveDependency(Injector injector, Class<T> clazz) {
		T bean = injector.resolve(Dependency.dependency(clazz));
		return bean;
	}
}
