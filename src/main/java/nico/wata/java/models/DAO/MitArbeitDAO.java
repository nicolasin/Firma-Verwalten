package nico.wata.java.models.DAO;
import nico.wata.java.models.*;
import nico.wata.java.models.MitArbeit;
import nico.wata.java.exceptions.*;
import java.util.*;

public interface MitArbeitDAO extends DAO<MitArbeit, Long> {
	public List<MitArbeit> getMitArbeitenByFirma(Long idFirma) throws DAOSQLException;

	public List<MitArbeit> getMitArbeitenByAbteilung(Long idAbteilung) throws DAOSQLException;
	
	public List<MitArbeit> getAllByDienststellung(Dienststellung dienststellung)throws DAOSQLException;
}
