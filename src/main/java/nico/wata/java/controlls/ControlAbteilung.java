package nico.wata.java.controlls;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import java.sql.SQLException;

import nico.wata.java.exceptions.DAOSQLException;
import nico.wata.java.main.ConexionDB;
import nico.wata.java.models.Abteilung;
import nico.wata.java.models.DAO.AbteilungDAO;
import nico.wata.java.models.MYSQL.AbteilungMYSQL;

public class ControlAbteilung {
	private AbteilungDAO modelAbteilung;
	ConexionDB connexion;
	Logger logger = Logger.getLogger(ControlAbteilung.class);

	public ControlAbteilung(ConexionDB con) {
		this.connexion = con;
	}

	public void createAbteilung(String name, Long idChef, Long idFirma){

		try {
			Abteilung abteilung = new Abteilung(name, idChef, idFirma);
			connexion.Connect();
			modelAbteilung = new AbteilungMYSQL(connexion.getConnection());
			modelAbteilung.insert(abteilung);
			logger.info("Create neu Abteilung");
			logger.debug(abteilung.toString());
			connexion.Disconnect();
		} catch (SQLException e) {
			logger.error("Create Abteilung SQL " + e.getMessage());
		} catch (DAOSQLException e) {
			logger.error("Create Abteilung DAO " + e.getMessage());
		}
	}

	public List<String> listAbteilungByFirma(Long id) {
		List<String> abteilungFirma = null;
		try {
			connexion.Connect();
			modelAbteilung = new AbteilungMYSQL(connexion.getConnection());
			List<Abteilung> abteilungen = modelAbteilung.getAll();
			abteilungFirma = abteilungen.stream().filter(x -> x.getIdFirma().equals(id)).map(x -> x.toString())
					.collect(Collectors.toList());
			logger.info("züruck list Abteilung");
		} catch (SQLException e) {
			logger.error("List Abteilubg by Firma  SQL " + e.getMessage());
		} catch (DAOSQLException e) {
			logger.error("List Abteilubg by Firma DAO " + e.getMessage());
		}

		return abteilungFirma;
	}

	public void deleteAbteilung(Long idAbteilung) {
		try {
			connexion.Connect();
			modelAbteilung = new AbteilungMYSQL(connexion.getConnection());
			modelAbteilung.delete(idAbteilung);
			connexion.Disconnect();
			logger.info("Delete Abteilung");
		} catch (SQLException e) {
			logger.error("Delete Abteilung SQL " + e.getMessage());
		} catch (DAOSQLException e) {
			logger.error("Delete Abteilubg DAO " + e.getMessage());
		}
	}

	public void deleteByFirma(Long idFirma) {
		try {
			connexion.Connect();
			modelAbteilung = new AbteilungMYSQL(connexion.getConnection());
			modelAbteilung.deleteByFirma(idFirma);
			connexion.Disconnect();
			logger.info("delete abteilung by Firma:" + idFirma);
			logger.debug("delete abteilung by Firma:" + idFirma);
		} catch (SQLException e) {
			logger.error("Delete Abteilung By Firma SQL " + e.getMessage());
		} catch (DAOSQLException e) {
			logger.error("Delete Abteilubg by Firma DAO " + e.getMessage());
		}
	}

	public void aendernChef(Long id, Long idMitArbeiter) {
		try {
			connexion.Connect();
			modelAbteilung = new AbteilungMYSQL(connexion.getConnection());
			Abteilung abteilung = modelAbteilung.getOne(id);
			abteilung.setIdChefAbteilung(idMitArbeiter);
			modelAbteilung.update(abteilung);
			logger.info("Abteilung änder Chef ");
			connexion.Disconnect();
		} catch (SQLException e) {
			logger.error("Abteilung änder Chef SQL" + e.getMessage());
		} catch (DAOSQLException e) {
			logger.error("Abteilung änder Chef DAO " + e.getMessage());
		}
	}

	public void aenderName(Long idAbteilung, String name) {
		try {
			connexion.Connect();
			modelAbteilung = new AbteilungMYSQL(connexion.getConnection());
			Abteilung abteilung = modelAbteilung.getOne(idAbteilung);
			System.out.println(abteilung.toString());
			abteilung.setName(name);
			modelAbteilung.update(abteilung);
			logger.info("Abteilung änder Name ");
			connexion.Disconnect();
		} catch (SQLException e) {
			logger.error("Abteilung änder Name SQL" + e.getMessage());
		} catch (DAOSQLException e) {
			logger.error("Abteilung änder Name DAO " + e.getMessage());
		}
	}

	public void aenderFirma(Long idAbteilung, Long idFirma) {
		try {
			connexion.Connect();
			modelAbteilung = new AbteilungMYSQL(connexion.getConnection());
			Abteilung abteilung = modelAbteilung.getOne(idAbteilung);
			abteilung.setIdFirma(idFirma);
			modelAbteilung.update(abteilung);
			logger.info("änder Firma ");
			connexion.Disconnect();
		} catch (SQLException e) {
			logger.error("Abteilung änder Firma SQL" + e.getMessage());
		} catch (DAOSQLException e) {
			logger.error("Abteilung änder Firma DAO " + e.getMessage());
		}
	}
}
