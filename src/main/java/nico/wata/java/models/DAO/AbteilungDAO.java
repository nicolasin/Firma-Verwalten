package nico.wata.java.models.DAO;

import nico.wata.java.exceptions.DAOSQLException;
import nico.wata.java.models.Abteilung;

public interface AbteilungDAO extends DAO<Abteilung, Long> {
	public void deleteByFirma(Long idFirma)throws DAOSQLException;
	
}
