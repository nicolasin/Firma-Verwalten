package nico.wata.java.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nico.wata.java.models.Firma;

class FirmaTest {
	Firma firma ;
	@BeforeEach
	void setUp() throws Exception {
		firma = new Firma("WATA", "Calle Chapineria 1", LocalDate.parse("2014-01-01"), new Long(1));	
	}
	
	@Test
	@DisplayName("Geters and Seters")
	void testGetAndSet() {
		assertEquals("WATA", firma.getName());
		assertEquals("Calle Chapineria 1", firma.getRichtung());
		assertEquals("2014-01-01", firma.getDate().toString());
		assertEquals(new Long(1), firma.getIdDirektor());
		assertNull(firma.getIdFirme());
		
		firma.setName("4a-side");
		firma.setRichtung("Barcelona");
		firma.setDate(LocalDate.parse("2017-01-01"));
		firma.setIdFirme(new Long(1));
		firma.setIdDirektor(new Long(2));
		
		assertEquals("4a-side", firma.getName());
		assertEquals("Barcelona", firma.getRichtung());
		assertEquals("2017-01-01", firma.getDate().toString());
		assertEquals(new Long(2), firma.getIdDirektor());
		assertEquals(new Long(1), firma.getIdFirme());
	}
	@Test
	@DisplayName("toString")
	void testToString() {
		assertEquals("Firma [idFirme=null, name=WATA, idDirektor=1, richtung=Calle Chapineria 1, date=2014-01-01]", firma.toString());
	}
	

}
