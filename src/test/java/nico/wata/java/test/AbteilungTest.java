package nico.wata.java.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nico.wata.java.models.Abteilung;

class AbteilungTest {

	Abteilung abteilung, abteilung2;
	@BeforeEach
	void setUp() throws Exception {
		abteilung = new Abteilung("Carpinteria", new Long(1), new Long(2));
		abteilung2 = new Abteilung(new Long(2), "PHP", new Long(1), new Long(2));
	}

	@Test
	@DisplayName("Geters and Seters")
	void testGetAndSet() {
		assertEquals("Carpinteria",abteilung.getName());
		assertEquals(new Long(1), abteilung.getIdFirma());
		assertEquals(new Long(2), abteilung.getIdChefAbteilung());
		assertNull(abteilung.getIdAbteilung());
		
		abteilung.setIdAbteilung(new Long(1));
		abteilung.setIdChefAbteilung(new Long(3));
		abteilung.setIdFirma(new Long(2));
		abteilung.setName("Limpieza");
		
		assertEquals(new Long(1), abteilung.getIdAbteilung());
		assertEquals("Limpieza", abteilung.getName());
		assertEquals(new Long(2), abteilung.getIdFirma());
		assertEquals(new Long(3), abteilung.getIdChefAbteilung());
	
	}
	@Test
	@DisplayName("Constructors ID")
	void testConstructors(){
		assertNull(abteilung.getIdAbteilung());
		assertEquals(new Long(2), abteilung2.getIdAbteilung());
	}
	
	@Test
	@DisplayName("To String")
	void testToString() {
		assertEquals("Abteilung [idAbteilung=null, idChefAbteilung=2, name=Carpinteria, idFirma=1]",abteilung.toString());
	}
	

}
