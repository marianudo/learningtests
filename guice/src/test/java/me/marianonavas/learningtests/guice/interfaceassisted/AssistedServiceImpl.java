package me.marianonavas.learningtests.guice.interfaceassisted;

import javax.inject.Inject;

import com.google.inject.assistedinject.Assisted;

public class AssistedServiceImpl implements AssistedService {

    private final Integer variable;

    private final Integer fixed;

    @Inject
    AssistedServiceImpl(@Assisted Integer variable, Integer fixed) {
        super();
        this.variable = variable;
        this.fixed = fixed;
    }

    @Override
    public Integer getVariable() {
        return variable;
    }

    @Override
    public Integer getFixed() {
        return fixed;
    }
}
