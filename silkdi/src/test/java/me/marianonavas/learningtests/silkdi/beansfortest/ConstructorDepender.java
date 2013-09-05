package me.marianonavas.learningtests.silkdi.beansfortest;

public class ConstructorDepender {
	private final IDependee dependee;

	public ConstructorDepender(IDependee dependee) {
		super();
		this.dependee = dependee;
	}

	public IDependee getDependee() {
		return dependee;
	}
}
