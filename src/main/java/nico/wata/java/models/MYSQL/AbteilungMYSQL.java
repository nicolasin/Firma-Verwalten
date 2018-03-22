package nico.wata.java.models.MYSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nico.wata.java.exceptions.DAOSQLException;
import nico.wata.java.models.Abteilung;
import nico.wata.java.models.DAO.AbteilungDAO;

public class AbteilungMYSQL implements AbteilungDAO {
	private Connection connection;
	private static String INSERT = "INSERT INTO Abteilungen(name, idChrefAbteilung, idFirma) values(?,?,?)";
	private static String DELETE = "DELETE FROM Abteilungen where idAbtelung = ?";
	private static String GETONE = "SELECT * FROM Abteilungen where idAbtelung = ?";
	private static String GETALL = "SELECT * FROM Abteilungen";
	private static String UPDATE = "UPDATE Abteilungen SET name = ?, idChrefAbteilung = ?, idFirma = ? where idAbtelung = ?";
	private static String DELETEBYFIRMA = "DELETE FROM Abteilungen where idFirma = ?";

	public AbteilungMYSQL(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Abteilung x) throws DAOSQLException {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, x.getName());
			statement.setLong(2, x.getIdChefAbteilung());
			statement.setLong(3, x.getIdFirma());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs != null && rs.next()) {
				x.setIdAbteilung(rs.getLong(1));
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
	public Abteilung getOne(Long k) throws DAOSQLException {
		PreparedStatement statement = null;
		ResultSet result = null;
		Abteilung Abteilung = null;
		try {
			statement = connection.prepareStatement(GETONE);
			statement.setLong(1, k);
			result = statement.executeQuery();
			if (result.next()) {
				Abteilung = Convertir(result);
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
		return Abteilung;
	}

	@Override
	public List<Abteilung> getAll() throws DAOSQLException {
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Abteilung> Abteilungen = new ArrayList<Abteilung>();
		try {
			statement = connection.prepareStatement(GETALL);
			result = statement.executeQuery();
			while (result.next()) {
				Abteilungen.add(Convertir(result));
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
		return Abteilungen;
	}

	@Override
	public void update(Abteilung t) throws DAOSQLException {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(UPDATE);
			statement.setString(1, t.getName());
			statement.setLong(2, t.getIdChefAbteilung());
			statement.setLong(3, t.getIdFirma());
			statement.setLong(4, t.getIdAbteilung());
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

	public void deleteByFirma(Long idFirma) throws DAOSQLException {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(DELETEBYFIRMA);
			statement.setLong(1, idFirma);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOSQLException("Error to delete by firma: " + e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new DAOSQLException("Error to close statement");
				}
			}
		}
	}

	private Abteilung Convertir(ResultSet result) throws SQLException {
		Long idAbteilung = result.getLong("idAbtelung");
		String name = result.getString("name");
		Long idChefAbteilung = result.getLong("idChrefAbteilung");
		Long idFirma = result.getLong("idFirma");
		Abteilung abteilung = new Abteilung(idAbteilung, name, idFirma, idChefAbteilung);
		return abteilung;
	}
}
