package nico.wata.java.models;

public class Abteilung {
	private Long idAbteilung;
	private Long idChefAbteilung;
	private String name;
	private Long idFirma;

	public Abteilung(String name, Long idFirma, Long idChefAbteilung) {
		this.idAbteilung = null;
		this.idChefAbteilung = idChefAbteilung;
		this.name = name;
		this.idFirma = idFirma;
	}

	public Abteilung(Long id, String name, Long idFirma, Long idChefAbteilung) {
		this.idAbteilung = id;
		this.idChefAbteilung = idChefAbteilung;
		this.name = name;
		this.idFirma = idFirma;
	}

	public Long getIdAbteilung() {
		return idAbteilung;
	}

	public void setIdAbteilung(Long idAbteilung) {
		this.idAbteilung = idAbteilung;
	}

	public Long getIdChefAbteilung() {
		return idChefAbteilung;
	}

	public void setIdChefAbteilung(Long idChefAbteilung) {
		this.idChefAbteilung = idChefAbteilung;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getIdFirma() {
		return idFirma;
	}

	public void setIdFirma(Long idFirma) {
		this.idFirma = idFirma;
	}

	@Override
	public String toString() {
		return "Abteilung [idAbteilung=" + idAbteilung + ", idChefAbteilung=" + idChefAbteilung + ", name=" + name
				+ ", idFirma=" + idFirma + "]";
	}

}
