package nico.wata.java.models.MYSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import nico.wata.java.models.Dienststellung;
import nico.wata.java.models.MitArbeit;
import nico.wata.java.exceptions.DAOSQLException;
import nico.wata.java.models.DAO.MitArbeitDAO;

public class MitArbeitMYSQL implements MitArbeitDAO {
	private Connection connection;
	private static String INSERT = "INSERT INTO `MitArbeiten`(`name`, `vorname`, ` geburtsdatum`, `telefonnummer`, `gehalt`, `dienststellung`, `idArbeitlung`) VALUES(?,?,?,?,?,?,?)";
	private static String DELETE = "DELETE FROM MitArbeiten where idMitarbeit = ?";
	private static String GETONE = "SELECT * FROM MitArbeiten where idMitarbeit = ?";
	private static String GETALL = "SELECT * FROM MitArbeiten";
	private static String UPDATE = "UPDATE `MitArbeiten` SET `name`=?,`vorname`=?,"
			+ "` geburtsdatum`=?,` telefonnummer`=?,`gehalt`=?,`dienststellung`=?,`idArbeitlung`=? WHERE idMitarbeit = ?";
	private static String GETBYFIRMA = "select * from MitARbeiten where idArbeitlung in(SELECT idAbtelung FROM Abteilungen WHERE idFirma = ?) order by idMitarbeit";
	private static String GETBYABTEILUNG = "SELECT * FROM MitArbeiten where idArbeitlung = ?";
	private static String GETBYSTELLUNG = "Select * from MitArbeiten where dienststellung = ?";

	public MitArbeitMYSQL(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(MitArbeit x) throws DAOSQLException {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, x.getName());
			statement.setString(2, x.getVorname());
			statement.setString(3, x.getGeburtsdatum().toString());
			statement.setString(4, x.getTelefonNummer());
			statement.setDouble(5, x.getGehalt());
			statement.setString(6, x.getStellung().toString());
			if (x.getIdAbteilung() == null) {
				statement.setLong(7, new Long(-1));
			} else {
				statement.setLong(7, x.getIdAbteilung());
			}
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs != null && rs.next()) {
				x.setIdMitArbeit(rs.getLong(1));
			}

		} catch (SQLException e) {
			throw new DAOSQLException("Error in der Verbindung: " + e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new DAOSQLException("Error beim Schließen statement: " + e.getMessage());
				}
			}
		}

	}

	@Override
	public void delete(Long k) throws DAOSQLException {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(DELETE);
			statement.setLong(1, k);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOSQLException("Error in der Verbindung: " + e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new DAOSQLException("Error beim Schließen statement: " + e.getMessage());
				}
			}
		}

	}

	@Override
	public MitArbeit getOne(Long k) throws DAOSQLException {
		PreparedStatement statement = null;
		ResultSet result = null;
		MitArbeit MitArbeit = null;
		try {
			statement = connection.prepareStatement(GETONE);
			statement.setLong(1, k);
			result = statement.executeQuery();
			if (result != null) {
				MitArbeit = Convertir(result);
			} else {
				throw new DAOSQLException("Keine Ergebnisse gefunden");
			}

		} catch (SQLException e) {
			throw new DAOSQLException("Error in der Verbindung: " + e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new DAOSQLException("Error beim Schließen statement: " + e.getMessage());
				}
			}
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					throw new DAOSQLException("Error beim Schließen ResultSet: " + e.getMessage());
				}
			}
		}
		return MitArbeit;
	}

	@Override
	public List<MitArbeit> getAll() throws DAOSQLException {
		PreparedStatement statement = null;
		ResultSet result = null;
		List<MitArbeit> MitArbeiten = new ArrayList<MitArbeit>();
		try {
			statement = connection.prepareStatement(GETALL);
			result = statement.executeQuery();
			while (result.next()) {
				MitArbeiten.add(Convertir(result));
			}

		} catch (SQLException e) {
			throw new DAOSQLException("Error in der Verbindung: " + e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new DAOSQLException("Error beim Schließen statement: " + e.getMessage());
				}
			}
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					throw new DAOSQLException("Error beim Schließen ResultSet: " + e.getMessage());
				}
			}
		}
		return MitArbeiten;
	}

	@Override
	public void update(MitArbeit t) throws DAOSQLException {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(UPDATE);
			statement.setString(1, t.getName());
			statement.setString(2, t.getVorname());
			statement.setString(3, t.getGeburtsdatum().toString());
			statement.setString(4, t.getTelefonNummer());
			statement.setDouble(5, t.getGehalt());
			statement.setString(6, t.getStellung().toString());
			statement.setLong(7, t.getIdAbteilung());
			statement.setLong(8, t.getIdMitArbeit());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOSQLException("Error in der Verbindung: " + e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new DAOSQLException("Error beim Schließen statement: " + e.getMessage());
				}
			}
		}
	}

	@Override
	public List<MitArbeit> getMitArbeitenByFirma(Long idFirma) throws DAOSQLException {
		List<MitArbeit> mitArbeiten = new ArrayList<MitArbeit>();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = connection.prepareStatement(GETBYFIRMA);
			statement.setLong(1, idFirma);
			result = statement.executeQuery();
			while (result.next()) {
				mitArbeiten.add(Convertir(result));	
			}
		} catch (SQLException e) {
			throw new DAOSQLException("Error in der Verbindung: " + e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new DAOSQLException("Error beim Schiließen statement: " + e.getMessage());
				}
			}
		}
		return mitArbeiten;
	}

	@Override
	public List<MitArbeit> getMitArbeitenByAbteilung(Long idAbteilung) throws DAOSQLException {
		List<MitArbeit> mitArbeiten = new ArrayList<MitArbeit>();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = connection.prepareStatement(GETBYABTEILUNG);
			statement.setLong(1, idAbteilung);
			result = statement.executeQuery();
			while (result.next()) {
				mitArbeiten.add(Convertir(result));
			}
		} catch (SQLException e) {
			throw new DAOSQLException("Error in der Verbindung: " + e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new DAOSQLException("Error beim Schiließen statement: " + e.getMessage());
				}
			}
		}
		return mitArbeiten;
	}

	private MitArbeit Convertir(ResultSet result) throws SQLException {
		Long idMitArbeit = result.getLong("idMitarbeit");
		Long idAbteilung = result.getLong("idArbeitlung");
		String name = result.getString("name");
		String vorname = result.getString("vorname");
		String telefonNummer = result.getString(5);
		LocalDate geburtsdatum = LocalDate.parse(result.getString(4));
		double gehalt = result.getDouble("gehalt");
		
		String stell = result.getString("dienststellung");
		
		Dienststellung stellung = null;
		for (Dienststellung d : Dienststellung.values()) {
			if (d.toString().equals(stell)) {
				stellung = d;
			}
		}
		
		MitArbeit mitArbeit = new MitArbeit(name, vorname, telefonNummer, geburtsdatum, gehalt, stellung, idAbteilung);
		mitArbeit.setIdMitArbeit(idMitArbeit);
		
		return mitArbeit;

	}

	@Override
	public List<MitArbeit> getAllByDienststellung(Dienststellung dienststellung) throws DAOSQLException {
		List<MitArbeit> mitArbeiten = new ArrayList<MitArbeit>();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = connection.prepareStatement(GETBYSTELLUNG);
			statement.setString(1, dienststellung.toString());
			result = statement.executeQuery();
			while (result.next()) {
				mitArbeiten.add(Convertir(result));
			}
		} catch (SQLException e) {
			throw new DAOSQLException("Error in der Verbindung: " + e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new DAOSQLException("Error beim Schiließen statement: " + e.getMessage());
				}
			}
		}
		return mitArbeiten;
	}

}
