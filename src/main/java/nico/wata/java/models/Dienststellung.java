package nico.wata.java.models;

public enum Dienststellung {
	LEHRLING(10000), JUNIOR(15000), SENIOR(20000), CHEF(25000), DIREKTOR(30000);
	private double gehalt;
	public double getGehalt() {
		return gehalt;
	}
	private Dienststellung(double valor) {
		this.gehalt = valor;
	}
}
