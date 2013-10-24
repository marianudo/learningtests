package me.marianonavas.learningtests.guice.interfaceassisted;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class InterfaceAssistedModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Integer.class).toInstance(150);
        install(new FactoryModuleBuilder().implement(AssistedService.class, AssistedServiceImpl.class).build(AssistedServiceFactory.class));
    }

}
