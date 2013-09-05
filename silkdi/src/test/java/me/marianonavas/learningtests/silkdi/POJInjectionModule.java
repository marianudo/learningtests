package me.marianonavas.learningtests.silkdi;

import me.marianonavas.learningtests.silkdi.beansfortest.Dependee;
import me.marianonavas.learningtests.silkdi.beansfortest.IDependee;
import se.jbee.inject.bind.BinderModule;

public class POJInjectionModule extends BinderModule {

	@Override
	protected void declare() {
		bind(IDependee.class).to(Dependee.class);
	}

}
