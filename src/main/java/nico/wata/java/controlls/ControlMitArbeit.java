package nico.wata.java.controlls;

import nico.wata.java.exceptions.DAOSQLException;
import nico.wata.java.main.ConexionDB;
import nico.wata.java.models.DAO.MitArbeitDAO;
import nico.wata.java.models.MYSQL.MitArbeitMYSQL;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;


import nico.wata.java.models.*;

public class ControlMitArbeit {
	private MitArbeitDAO modelMitArbeit;
	private ConexionDB connexion;
	Logger logger = Logger.getLogger(ControlMitArbeit.class);

	public ControlMitArbeit(ConexionDB connexion) {
		this.connexion = connexion;
	}

	public void addMitArbeit(MitArbeit mitArbeit) {
		try {
			connexion.Connect();
			modelMitArbeit = new MitArbeitMYSQL(connexion.getConnection());
			modelMitArbeit.insert(mitArbeit);
			logger.info("addMitArbeit");
			connexion.Disconnect();
		} catch (SQLException e) {
			logger.error(" Conexion addMitArbeit: " + e.getMessage());
		} catch (DAOSQLException ex) {
			logger.error(" Inserción addMitArbeit: " + ex.getMessage());
		}
	}

	public void deleteMitArbeit(Long idMitArbeit) {
		try {
			connexion.Connect();
			modelMitArbeit = new MitArbeitMYSQL(connexion.getConnection());
			modelMitArbeit.delete(idMitArbeit);
			logger.info("deleteMitArbeit");
			connexion.Disconnect();
		} catch (SQLException e) {
			logger.error(" Conexion deleteMitARbeit : " + e.getMessage());
		} catch (DAOSQLException ex) {
			logger.error(" Insert deleteMitARbeit : " + ex.getMessage());
		}
	}

	public List<MitArbeit> getAllMitArbeite() {
		List<MitArbeit> mitArbeiten = null;
		try {
			connexion.Connect();
			modelMitArbeit = new MitArbeitMYSQL(connexion.getConnection());
			mitArbeiten = modelMitArbeit.getAll();
			logger.info("getAllMitArbeite");
			connexion.Disconnect();
		} catch (SQLException e) {
			logger.error(" Conexion getAllMitARbeit : " + e.getMessage());
		} catch (DAOSQLException ex) {
			logger.error(" Insert getAllMitARbeit : " + ex.getMessage());
		}
		return mitArbeiten;
	}

	public List<MitArbeit> getMitArbeiteVonFirma(Long idFirma) {
		List<MitArbeit> mitArbeiten = null;
		try {
			connexion.Connect();
			modelMitArbeit = new MitArbeitMYSQL(connexion.getConnection());
			mitArbeiten = modelMitArbeit.getMitArbeitenByFirma(idFirma);
			logger.info("getMitArbeiteVonFirma");
			connexion.Disconnect();
		} catch (SQLException e) {
			logger.error(" Conect getMitArbeiteVonFirma : " + e.getMessage());
		} catch (DAOSQLException e) {
			logger.error(" Insert getMitArbeiteVonFirma : " + e.getMessage());
		}
		return mitArbeiten;
	}

	public List<MitArbeit> getMitArbeiteVonAbteilung(Long idAbteilung) {
		List<MitArbeit> mitArbeiten = null;
		try {
			connexion.Connect();
			modelMitArbeit = new MitArbeitMYSQL(connexion.getConnection());
			mitArbeiten = modelMitArbeit.getMitArbeitenByAbteilung(idAbteilung);
			logger.info("getMitArbeiteVonAbteilung");
			connexion.Disconnect();
		} catch (SQLException e) {
			logger.error(" Conect getMitArbeiteVonFirma : " + e.getMessage());
		} catch (DAOSQLException e) {
			logger.error(" Insert getMitArbeiteVonFirma : " + e.getMessage());
		}
		return mitArbeiten;
	}

	public List<MitArbeit> getAllMitARbeiten() {
		List<MitArbeit> mitArbeiten = null;
		try {
			connexion.Connect();
			modelMitArbeit = new MitArbeitMYSQL(connexion.getConnection());
			mitArbeiten = modelMitArbeit.getAll();
			logger.info("getAllMitARbeiten");
			connexion.Disconnect();
		} catch (SQLException e) {
			logger.error(" Conect getMitArbeiteVonFirma : " + e.getMessage());
		} catch (DAOSQLException e) {
			logger.error(" Insert getMitArbeiteVonFirma : " + e.getMessage());
		}
		return mitArbeiten;
	}

	public MitArbeit getOne(Long id) {
		MitArbeit mitArbeit = null;
		try {
			connexion.Connect();
			modelMitArbeit = new MitArbeitMYSQL(connexion.getConnection());
			mitArbeit = modelMitArbeit.getOne(id);
			logger.info("getOne");
			connexion.Disconnect();
		} catch (SQLException e) {
			logger.error(" Conect getMitArbeiteVonFirma : " + e.getMessage());
		} catch (DAOSQLException e) {
			logger.error(" Insert getMitArbeiteVonFirma : " + e.getMessage());
		}
		return mitArbeit;
	}

	public List<MitArbeit> getAllOrderByName() {
		List<MitArbeit> mitArbeiten = new ArrayList<MitArbeit>();
		try {
			connexion.Connect();
			modelMitArbeit = new MitArbeitMYSQL(connexion.getConnection());
			mitArbeiten = modelMitArbeit.getAll();
			connexion.Disconnect();
			logger.info("getAllOrderByName");
		} catch (SQLException e) {
			logger.error(" Conect getAllOrderByName : " + e.getMessage());
		} catch (DAOSQLException e) {
			logger.error(" Insert getAllOrderByName : " + e.getMessage());
		}
		return mitArbeiten.stream().sorted((x, y) -> x.getName().compareTo(y.getName())).collect(Collectors.toList());
	}

	public List<MitArbeit> getAllOrderByGehalt() {
		List<MitArbeit> mitArbeiten = new ArrayList<MitArbeit>();
		try {
			connexion.Connect();
			modelMitArbeit = new MitArbeitMYSQL(connexion.getConnection());
			mitArbeiten = modelMitArbeit.getAll();
			logger.info("getAllOrderByGehalt");
			connexion.Disconnect();
		} catch (SQLException e) {
			logger.error(" Conect getAllOrderByGehalt : " + e.getMessage());
		} catch (DAOSQLException e) {
			logger.error(" Insert getAllOrderByGehalt : " + e.getMessage());
		}
		return mitArbeiten.stream().sorted((x, y) -> (int) (x.getGehalt() - y.getGehalt()))
				.collect(Collectors.toList());
	}

	public List<MitArbeit> getAllByDienstellung(Dienststellung dienstellung) {
		List<MitArbeit> mitArbeiten = new ArrayList<MitArbeit>();
		try {
			connexion.Connect();
			modelMitArbeit = new MitArbeitMYSQL(connexion.getConnection());
			mitArbeiten = modelMitArbeit.getAllByDienststellung(dienstellung);
			connexion.Disconnect();
			logger.info("getAllByDienstellung");
		} catch (SQLException e) {
			logger.error(" Conect getAllByDienstellung : " + e.getMessage());
		} catch (DAOSQLException e) {
			logger.error(" Insert getAllByDienstellung : " + e.getMessage());
		}
		return mitArbeiten;
	}

	public double berechnenDurchschnittlicheGehalt() {
		double durchschnittlich = 0;
		List<MitArbeit> mitArbeiten = new ArrayList<MitArbeit>();
		try {
			connexion.Connect();
			modelMitArbeit = new MitArbeitMYSQL(connexion.getConnection());
			mitArbeiten = modelMitArbeit.getAll();
			connexion.Disconnect();
			logger.info("berechnenDurchschnittlicheGehalt");
		} catch (SQLException e) {
			logger.error(" Conect berechnenDurchschnittlicheGehalt : " + e.getMessage());
		} catch (DAOSQLException e) {
			logger.error(" Insert berechnenDurchschnittlicheGehalt : " + e.getMessage());
		}
		durchschnittlich = mitArbeiten.stream().mapToDouble(x -> x.getGehalt()).average().getAsDouble();
		return durchschnittlich;
	}

	public double berechnenDurchschnittlicheGehaltByFirma(Long idFirma) {
		List<MitArbeit> mitArbeiten = new ArrayList<MitArbeit>();
		double durchschnittlich = 0;
		try {
			connexion.Connect();
			modelMitArbeit = new MitArbeitMYSQL(connexion.getConnection());
			mitArbeiten = modelMitArbeit.getMitArbeitenByFirma(idFirma);
			connexion.Disconnect();
			logger.info("berechnenDurchschnittlicheGehaltByFirma");
		} catch (SQLException e) {
			logger.error(" Conect berechnenDurchschnittlicheGehaltByFirma : " + e.getMessage());
		} catch (DAOSQLException e) {
			logger.error(" Insert berechnenDurchschnittlicheGehaltByFirma : " + e.getMessage());
		}
		durchschnittlich = mitArbeiten.stream().mapToDouble(x -> x.getGehalt()).average().getAsDouble();
		return durchschnittlich;
	}

	public void aendernName(Long idMitArbeit, String name) {
		MitArbeit mitArbeit = null;
		try {
			connexion.Connect();
			modelMitArbeit = new MitArbeitMYSQL(connexion.getConnection());
			mitArbeit = modelMitArbeit.getOne(idMitArbeit);
			mitArbeit.setName(name);
			modelMitArbeit.update(mitArbeit);
			connexion.Disconnect();
			logger.info("aendernName");
		} catch (SQLException e) {
			logger.error(" Conect aendernName : " + e.getMessage());
		} catch (DAOSQLException e) {
			logger.error(" Insert aendernName : " + e.getMessage());
		}
	}

	public void aendernVorname(Long idMitArbeit, String vorname) {
		MitArbeit mitArbeit = null;
		try {
			connexion.Connect();
			modelMitArbeit = new MitArbeitMYSQL(connexion.getConnection());
			mitArbeit = modelMitArbeit.getOne(idMitArbeit);
			mitArbeit.setVorname(vorname);
			modelMitArbeit.update(mitArbeit);
			connexion.Disconnect();
			logger.info("aendernVorname");
		} catch (SQLException e) {
			logger.error(" Conect aendernVorname : " + e.getMessage());
		} catch (DAOSQLException e) {
			logger.error(" Insert aendernVorname : " + e.getMessage());
		}
	}

	public void aenderTelefonnumer(Long idMitArbeit, String telefonNumer) {
		MitArbeit mitArbeit = null;
		try {
			connexion.Connect();
			modelMitArbeit = new MitArbeitMYSQL(connexion.getConnection());
			mitArbeit = modelMitArbeit.getOne(idMitArbeit);
			mitArbeit.setTelefonNummer(telefonNumer);
			modelMitArbeit.update(mitArbeit);
			connexion.Disconnect();
			logger.info("aenderTelefonnumer");
		} catch (SQLException e) {
			logger.error(" Conect aenderTelefonnumer : " + e.getMessage());
		} catch (DAOSQLException e) {
			logger.error(" Insert aenderTelefonnumer : " + e.getMessage());
		}
	}

	public void aendernGehalt(Long idMitArbeit, double gehalt) {
		MitArbeit mitArbeit = null;
		try {
			connexion.Connect();
			modelMitArbeit = new MitArbeitMYSQL(connexion.getConnection());
			mitArbeit = modelMitArbeit.getOne(idMitArbeit);
			mitArbeit.setGehalt(gehalt);
			modelMitArbeit.update(mitArbeit);
			connexion.Disconnect();
			logger.info("aendernGehalt");
		} catch (SQLException e) {
			logger.error(" Conect aendernGehalt : " + e.getMessage());
		} catch (DAOSQLException e) {
			logger.error(" Insert aendernGehalt : " + e.getMessage());
		}
	}

	public void aendernDienstellung(Long idMitArbeit, String dienstellung) {
		MitArbeit mitArbeit = null;
		Dienststellung aux = null;
		for (Dienststellung x : Dienststellung.values()) {
			if (x.toString().equals(dienstellung)) {
				aux = x;
			}
		}
		try {
			connexion.Connect();
			modelMitArbeit = new MitArbeitMYSQL(connexion.getConnection());
			mitArbeit = modelMitArbeit.getOne(idMitArbeit);
			mitArbeit.setStellung(aux);
			mitArbeit.setGehalt(aux.getGehalt());
			modelMitArbeit.update(mitArbeit);
			connexion.Disconnect();
			logger.info("aendernDienstellung");
		} catch (SQLException e) {
			logger.error(" Conect aendernDienstellung : " + e.getMessage());
		} catch (DAOSQLException e) {
			logger.error(" Insert aendernDienstellung : " + e.getMessage());
		}
	}

	public void aendernAbteilung(Long idMitArbeit, Long idAbteilung) {
		MitArbeit mitArbeit = null;
		try {
			connexion.Connect();
			modelMitArbeit = new MitArbeitMYSQL(connexion.getConnection());
			mitArbeit = modelMitArbeit.getOne(idMitArbeit);
			mitArbeit.setIdAbteilung(idAbteilung);
			modelMitArbeit.update(mitArbeit);
			logger.info("aendernAbteilung");
			connexion.Disconnect();
		} catch (SQLException e) {
			logger.error(" Conect aendernAbteilung : " + e.getMessage());
		} catch (DAOSQLException e) {
			logger.error(" Insert aendernAbteilung : " + e.getMessage());
		}
	}
}
