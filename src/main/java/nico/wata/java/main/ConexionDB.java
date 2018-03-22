package nico.wata.java.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
	private String nameDB;
	private String maquina;
	private String usuario;
	private String clave;
	private int puerto;

	private Connection conexion;

	public ConexionDB(String name, String url, String user, String pass, int puerto) {
		this.nameDB = name;
		this.maquina = url;
		this.puerto = puerto;
		this.usuario = user;
		this.clave = pass;
		this.conexion = null;
	}

	public ConexionDB(String name, String url, String user, String pass) {
		this.nameDB = name;
		this.maquina = url;
		this.puerto = 3306;
		this.usuario = user;
		this.clave = pass;
		this.conexion = null;
	}

	public void Connect() throws SQLException {

		String url = "jdbc:mysql://" + this.maquina + ":" + this.puerto + "/" + this.nameDB;
		conexion = DriverManager.getConnection(url, this.usuario, this.clave);
	}

	public boolean isConnect() {
		return conexion != null;
	}

	public Connection getConnection() {
		return conexion;
	}

	public void Disconnect() throws SQLException {
		if (conexion != null) {
			conexion.close();
		}
	}

}