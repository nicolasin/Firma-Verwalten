package nico.wata.java.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.sql.Connection;
import java.sql.Statement;

import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nico.wata.java.controlls.ControlAbteilung;
import nico.wata.java.controlls.ControlFirma;
import nico.wata.java.test.h2.ConnectH2DataBase;

class ControlMitArbeitTest {
	private static String PropertyPath = "log4j.properties";
	ConnectH2DataBase conexion;
	File dbFile;
	ControlFirma cf;
	@BeforeEach
	void setUp() throws Exception {
		PropertyConfigurator.configure(PropertyPath);
		dbFile = new File("CreateTables.txt");
		conexion = new ConnectH2DataBase("FirmaVerwalten", "root", "1234");
		conexion.getConexionDB().ConnectH2();
		Statement r = conexion.getConexionDB().getConnection().createStatement();
		String sql = conexion.readFile(dbFile);
		r.execute(sql);
		
		cf = new ControlFirma(conexion.getConexionDB());
	}

	@AfterEach
	void tearDown() throws Exception {
		//conexion.deleteDB();
	}

	@Test
	void test() {
		cf.ListFirmen().forEach(System.out::println);
		System.out.println("HOLA");
	}

}
