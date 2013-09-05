package me.marianonavas.learningtests.silkdi.beansfortest;

public class Dependee implements IDependee {

	@Override
	public void doIt() {
		throw new UnsupportedOperationException("Not meant to be called");
	}

}
