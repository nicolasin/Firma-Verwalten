package nico.wata.java.controlls;

import nico.wata.java.exceptions.DAOSQLException;
import nico.wata.java.main.ConexionDB;
import nico.wata.java.models.DAO.DAO;
import nico.wata.java.models.MYSQL.*;
import nico.wata.java.models.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import org.apache.log4j.Logger;

public class ControlFirma {
	private DAO<Firma, Long> modelFirma;
	private DAO<Abteilung, Long> modelAbteilung;
	private DAO<MitArbeit, Long> modelMitARbeit;
	ConexionDB connexion;
	Logger logger = Logger.getLogger(ControlFirma.class);
	public ControlFirma(ConexionDB con) {
		this.connexion = con;
	}

	public List<String> ListFirmen() {
		List<String> listFirmen = new ArrayList<String>();
		try {
			this.connexion.Connect();
			modelFirma = new FirmaMYSQL(this.connexion.getConnection());
			modelFirma.getAll().forEach(x -> listFirmen.add(x.toString()));
			logger.info("Züruck Liste Firme");
			this.connexion.Disconnect();
		} catch (SQLException e) {
			logger.error("List Abteilung SQL " + e.getMessage());
		} catch (DAOSQLException e) {
			logger.error("List Abteilung DAO " + e.getMessage());
		}
		return listFirmen;

	}

	public List<String> ListFirmenByName() {
		List<String> listFirmen = new ArrayList<String>();
		try {
			this.connexion.Connect();
			modelFirma = new FirmaMYSQL(this.connexion.getConnection());
			modelFirma.getAll().stream().sorted((x, y) -> x.getName().compareTo(y.getName()))
					.forEach(x -> listFirmen.add(x.toString()));
			logger.info("Züruck Liste Firme");
			this.connexion.Disconnect();
		} catch (SQLException e) {
			logger.error("List Abteilung SQL " + e.getMessage());
		} catch (DAOSQLException e) {
			logger.error("List Abteilung DAO " + e.getMessage());
		}
		return listFirmen;

	}

	public void CreateFirma(String name, String richtung, MitArbeit direktor) {
		try {
			this.connexion.Connect();
			this.connexion.getConnection().setAutoCommit(false);
			
			modelFirma = new FirmaMYSQL(this.connexion.getConnection());
			modelAbteilung = new AbteilungMYSQL(this.connexion.getConnection());
			modelMitARbeit = new MitArbeitMYSQL(this.connexion.getConnection());

			modelMitARbeit.insert(direktor);

			Firma firma = new Firma(name, richtung, LocalDate.now(), direktor.getIdMitArbeit());
			modelFirma.insert(firma);

			Abteilung abteilung = new Abteilung("Direccion", firma.getIdFirme(), direktor.getIdMitArbeit());
			modelAbteilung.insert(abteilung);

			direktor.setIdAbteilung(abteilung.getIdAbteilung());
			modelMitARbeit.update(direktor);
			logger.debug("Firma: "+firma.getName()+" Direktor"+direktor.getName()+ " Abteilung : "+abteilung.getName());
			logger.info("Create neu Firma: "+firma.toString());
			this.connexion.getConnection().commit();
			this.connexion.Disconnect();
		} catch (SQLException e) {
			logger.error("Error to DDBB conect :" + e.getMessage());
			try {
				this.connexion.getConnection().rollback();
			} catch (SQLException e1) {
				logger.error("Error to Rollback");
			}
		} catch (DAOSQLException e) {
			logger.error("Error to Create neu Firma: " + e.getMessage());
			try {
				this.connexion.getConnection().rollback();
			} catch (SQLException e1) {
				logger.error("Error to Rollback");
			}
		} finally {
			try {
				this.connexion.getConnection().setAutoCommit(true);
			} catch (SQLException e) {
				logger.error("Error to set AutoCommit to True");
			}
		}
	}

	public void deleteFirma(Long idFirma) {
		try {
			ControlAbteilung controlAbteilung = new ControlAbteilung(connexion);
			controlAbteilung.deleteByFirma(idFirma);
			this.connexion.Connect();
			modelFirma = new FirmaMYSQL(this.connexion.getConnection());
			modelFirma.delete(idFirma);
			this.connexion.Disconnect();
		} catch (SQLException e) {
			logger.error("Delete Firma "+e.getMessage());
		} catch (DAOSQLException e) {
			logger.error("Delete Firma "+e.getMessage());
		}
	}

	public void aenderName(Long idFirma, String name) {

		try {
			connexion.Connect();
			modelFirma = new FirmaMYSQL(connexion.getConnection());
			
			Firma firma = modelFirma.getOne(idFirma);
			firma.setName(name);
			
			modelFirma.update(firma);
			connexion.Disconnect();
			logger.info("Äender Name");
		} catch (SQLException e) {
			logger.error("Firma Äender Name"+e.getMessage());
		} catch (DAOSQLException e) {
			logger.error("Firma Äender Name"+e.getMessage());
		}
	}

	public void aenderDirektor(Long idFirma, Long idMitArbeit) {

		try {
			connexion.Connect();
			modelFirma = new FirmaMYSQL(connexion.getConnection());
			Firma firma = modelFirma.getOne(idFirma);
			firma.setIdDirektor(idMitArbeit);
			modelFirma.update(firma);
			connexion.Disconnect();
			logger.info("Äender Direktor");
		} catch (SQLException e) {
			logger.error("Firma änder Direktor "+e.getMessage());
		} catch (DAOSQLException e) {
			logger.error("Firma änder Direktor "+e.getMessage());
		}
	}

	public void aenderRichtung(Long idFirma, String richtung) {

		try {
			connexion.Connect();
			modelFirma = new FirmaMYSQL(connexion.getConnection());
			Firma firma = modelFirma.getOne(idFirma);
			firma.setRichtung(richtung);
			modelFirma.update(firma);
			connexion.Disconnect();
			logger.info("Äender Richtung");
		} catch (SQLException e) {
			logger.error("Firma änder Richtung "+e.getMessage());
		} catch (DAOSQLException e) {
			logger.error("Firma änder Richtung "+e.getMessage());
		}
	}

}
