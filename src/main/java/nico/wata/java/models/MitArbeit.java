package nico.wata.java.models;

import java.time.LocalDate;

public class MitArbeit {
	@Override
	public String toString() {
		return "MitArbeit [idMitArbeit=" + idMitArbeit + ", idAbteilung=" + idAbteilung + ", name=" + name
				+ ", vorname=" + vorname + ", telefonNummer=" + telefonNummer + ", geburtsdatum=" + geburtsdatum
				+ ", gehalt=" + gehalt + ", stellung=" + stellung + "]";
	}

	private Long idMitArbeit;
	private Long idAbteilung;
	private String name;
	private String vorname;
	private String telefonNummer;
	private LocalDate geburtsdatum;
	private double gehalt;
	private Dienststellung stellung;

	public MitArbeit(String name, String vorname, String telefonNummer, LocalDate geburtsdatum, double gehalt,
			Dienststellung stellung, Long idAbteilung) {
		
		this.idAbteilung = idAbteilung;
		this.name = name;
		this.vorname = vorname;
		this.telefonNummer = telefonNummer;
		this.geburtsdatum = geburtsdatum;
		this.gehalt = gehalt;
		this.stellung = stellung;
	}

	public Long getIdMitArbeit() {
		return idMitArbeit;
	}

	public void setIdMitArbeit(Long idMitArbeit) {
		this.idMitArbeit = idMitArbeit;
	}

	public Long getIdAbteilung() {
		return idAbteilung;
	}

	public void setIdAbteilung(Long idAbteilung) {
		this.idAbteilung = idAbteilung;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getTelefonNummer() {
		return telefonNummer;
	}

	public void setTelefonNummer(String telefonNummer) {
		this.telefonNummer = telefonNummer;
	}

	public LocalDate getGeburtsdatum() {
		return geburtsdatum;
	}

	public void setGeburtsdatum(LocalDate geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	public double getGehalt() {
		return gehalt;
	}

	public void setGehalt(double gehalt) {
		this.gehalt = gehalt;
	}

	public Dienststellung getStellung() {
		return stellung;
	}

	public void setStellung(Dienststellung stellung) {
		this.stellung = stellung;
	}

}
