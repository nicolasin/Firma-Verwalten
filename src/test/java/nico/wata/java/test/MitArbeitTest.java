package nico.wata.java.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nico.wata.java.models.Dienststellung;
import nico.wata.java.models.MitArbeit;

class MitArbeitTest {

	MitArbeit mitarbeit, mitarbeit2;

	@BeforeEach
	void setUp() throws Exception {
		mitarbeit = new MitArbeit("Nico", "Martos Gragamol", "123456789", LocalDate.parse("1988-08-07"),
				Dienststellung.JUNIOR.getGehalt(), Dienststellung.JUNIOR, new Long(1));
	}

	@Test
	@DisplayName("Geters and Seters")
	void testGetAndSet() {
		assertEquals("Nico", mitarbeit.getName());
		assertEquals("Martos Gragamol", mitarbeit.getVorname());
		assertEquals("123456789", mitarbeit.getTelefonNummer());
		assertEquals("1988-08-07", mitarbeit.getGeburtsdatum().toString());
		assertEquals("JUNIOR", mitarbeit.getStellung().toString());
		assertEquals(Dienststellung.JUNIOR.getGehalt(), mitarbeit.getGehalt());
		assertNull(mitarbeit.getIdMitArbeit());
		
		mitarbeit.setIdMitArbeit(new Long(1));
		mitarbeit.setName("Manolo");
		mitarbeit.setVorname("Manuel Marquez");
		mitarbeit.setGehalt(35000);
		mitarbeit.setGeburtsdatum(LocalDate.parse("1996-01-01"));
		mitarbeit.setStellung(Dienststellung.SENIOR);
		mitarbeit.setTelefonNummer("987654321");
		
		assertEquals("Manolo", mitarbeit.getName());
		assertEquals("Manuel Marquez", mitarbeit.getVorname());
		assertEquals("987654321", mitarbeit.getTelefonNummer());
		assertEquals("1996-01-01", mitarbeit.getGeburtsdatum().toString());
		assertEquals("SENIOR", mitarbeit.getStellung().toString());
		assertEquals(35000, mitarbeit.getGehalt());
		assertEquals(new Long(1),mitarbeit.getIdMitArbeit());
	}

	@Test
	@DisplayName("to String")
	public void testToString() {
		assertEquals("MitArbeit [idMitArbeit=null, idAbteilung=1, name=Nico, vorname=Martos Gragamol, telefonNummer=123456789, geburtsdatum=1988-08-07, gehalt=15000.0, stellung=JUNIOR]",mitarbeit.toString());
	}
}
