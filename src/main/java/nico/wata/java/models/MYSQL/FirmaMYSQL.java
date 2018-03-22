package nico.wata.java.models.MYSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import nico.wata.java.models.Firma;
import nico.wata.java.exceptions.DAOSQLException;
import nico.wata.java.models.DAO.FirmaDAO;

public class FirmaMYSQL implements FirmaDAO {
	private Connection connection;
	private static String INSERT = "INSERT INTO Firmen(name, richtung, id_direktor, datum) values(?,?,?,?)";
	private static String DELETE = "DELETE FROM Firmen where id_Firme = ?";
	private static String GETONE = "SELECT * FROM Firmen where id_Firme = ?";
	private static String GETALL = "SELECT * FROM Firmen";
	private static String UPDATE = "UPDATE Firmen SET name = ?, richtung = ?, id_direktor = ?, datum = ? where id_Firme = ?";

	public FirmaMYSQL(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Firma x) throws DAOSQLException {
		PreparedStatement statement = null;
		Long id = null;
		try {
			statement = connection.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, x.getName());
			statement.setString(2, x.getRichtung());
			statement.setLong(3, x.getIdDirektor());
			statement.setString(4, x.getDate().toString());
			statement.executeUpdate();
			
			ResultSet rs = statement.getGeneratedKeys();
			if(rs!=null && rs.next()) {
				id = rs.getLong(1);
			}
			x.setIdFirme(id);
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
	public Firma getOne(Long k) throws DAOSQLException {
		PreparedStatement statement = null;
		ResultSet result = null;
		Firma firma = null;
		try {
			statement = connection.prepareStatement(GETONE);
			statement.setLong(1, k);
			System.out.println(statement);
			result = statement.executeQuery();
			if (result.next()) {
				firma = Convertir(result);
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
		return firma;
	}

	@Override
	public List<Firma> getAll() throws DAOSQLException {
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Firma> firmen = new ArrayList<Firma>();
		try {
			statement = connection.prepareStatement(GETALL);
			result = statement.executeQuery();
			
			if (result != null) {
				while (result.next()) {
					firmen.add(Convertir(result));
				}
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
		return firmen;
	}

	@Override
	public void update(Firma t) throws DAOSQLException {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(UPDATE);
			statement.setString(1, t.getName());
			statement.setString(2, t.getRichtung());
			statement.setLong(3, t.getIdDirektor());
			statement.setString(4, t.getDate().toString());
			statement.setLong(5, t.getIdFirme());
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

	private Firma Convertir(ResultSet result) throws SQLException {
		Long id = result.getLong("id_Firme");
		String name = result.getString("name");
		String richtung = result.getString("richtung");
		LocalDate datum = LocalDate.parse(result.getString("datum"));
		Long idDirektor = result.getLong("id_direktor");

		Firma neueFirma = new Firma(name, richtung, datum, idDirektor);
		neueFirma.setIdFirme(id);
		return neueFirma;
	}

}
