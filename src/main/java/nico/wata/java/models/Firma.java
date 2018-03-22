package nico.wata.java.models;

import java.time.LocalDate;

public class Firma {
	

	private Long idFirme;
	private String name;
	private Long idDirektor;
	private String richtung;
	private LocalDate date;

	public Firma(String name, String richtung, LocalDate date, Long idDirektor) {
		this.name = name;
		this.idDirektor = idDirektor;
		this.richtung = richtung;
		this.date = date;
	}

	public Long getIdFirme() {
		return idFirme;
	}

	public void setIdFirme(Long idFirme) {
		this.idFirme = idFirme;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getIdDirektor() {
		return idDirektor;
	}

	public void setIdDirektor(Long idDirektor) {
		this.idDirektor = idDirektor;
	}

	public String getRichtung() {
		return richtung;
	}

	public void setRichtung(String richtung) {
		this.richtung = richtung;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Firma [idFirme=" + idFirme + ", name=" + name + ", idDirektor=" + idDirektor + ", richtung=" + richtung
				+ ", date=" + date + "]";
	}

}
