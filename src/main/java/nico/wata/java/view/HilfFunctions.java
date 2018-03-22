package nico.wata.java.view;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import nico.wata.java.controlls.ControlAbteilung;
import nico.wata.java.controlls.ControlFirma;
import nico.wata.java.controlls.ControlMitArbeit;
import nico.wata.java.main.ConexionDB;
import nico.wata.java.models.Dienststellung;
import nico.wata.java.models.MitArbeit;

public class HilfFunctions {
	//PRUEBA
	private static Scanner s = new Scanner(System.in);
	private static ConexionDB conexion = new ConexionDB("FirmaVerwalten", "localhost", "nico", "nico");
	private static ControlFirma controlFirma = new ControlFirma(conexion);
	private static ControlMitArbeit controlMitArbeit = new ControlMitArbeit(conexion);
	private static ControlAbteilung controlAbteilung = new ControlAbteilung(conexion);

	// MENUS LADEN
	public static Menu Hauptmenueladen() {
		Menu menu = new Menu();
		Menu FirmeMenu = firmeMenuLaden();
		Menu abteilungenMenu = abteilungenMenuLaden();
		Menu mitArbeitenMenu = mitArbeitenMenuLaden();
		menu.addOption("FIRME OPTIONS", () -> {

			int option = 0;
			do {
				System.out.println(FirmeMenu.getListOptionen());
				System.out.println("Gibt mehr Option");
				option = s.nextInt();
				if (option < FirmeMenu.getNumOption() && option > 0) {
					FirmeMenu.selectOption(option);
				}
			} while (option > 0 && FirmeMenu.getNumOption() > option);

		});
		menu.addOption("ABTEILUNG OPTION", () -> {

			int option = 0;
			do {
				System.out.println(abteilungenMenu.getListOptionen());
				System.out.println("Gibt mehr Option");
				option = s.nextInt();
				if (option < abteilungenMenu.getNumOption() && option > 0) {
					abteilungenMenu.selectOption(option);
				}
			} while (option > 0 && abteilungenMenu.getNumOption() > option);
		});
		menu.addOption("MITARBEIT OPTION", () -> {

			int option = 0;
			do {
				System.out.println(mitArbeitenMenu.getListOptionen());
				System.out.println("Gibt mehr Option");
				option = s.nextInt();
				if (option < mitArbeitenMenu.getNumOption() && option > 0) {
					mitArbeitenMenu.selectOption(option);
				}
			} while (option > 0 && mitArbeitenMenu.getNumOption() > option);
		});
		menu.addOption("Beenden", () -> {

			System.out.println("Auf Wiedersehen");
		});
		return menu;
	}

	private static Menu firmeMenuLaden() {
		Menu menu = new Menu();
		menu.addOption("Create Firma", () -> {
			createFirme();
		});
		menu.addOption("List Firmen", () -> {
			listeFirme();
		});
		menu.addOption("List Firmen sorted Name", () -> {
			listeFirmeOrderByName();
		});
		menu.addOption("Delete Firme", () -> {
			listeFirme();
			deleteFirme();
		});
		menu.addOption("Änder Name", () -> {
			listeFirme();
			aenderNameFirme();
		});
		menu.addOption("Änder richtung", () -> {
			listeFirme();
			aenderRichtungFirme();
		});
		menu.addOption("zurück", () -> {
		});
		return menu;
	}

	private static Menu abteilungenMenuLaden() {
		Menu menu = new Menu();
		menu.addOption("Create Abteilung", () -> {
			createAbteilung();
		});
		menu.addOption("List Abteilung By Firma", () ->

		{
			showAbteilungfuerEineFirme();
		});
		menu.addOption("List MitArbeit By Abteilung", () -> {
			showAbteilungfuerEineFirme();
			showMitARbeitfuerEineAbteilung();
		});
		menu.addOption("Delete Abteilung", () -> {
			showAbteilungfuerEineFirme();
			deleteAbteilung();
		});
		menu.addOption("Änder Name", () -> {
			showAbteilungfuerEineFirme();
			aenderNameAbteilung();
		});
		menu.addOption("Äender Chef", () -> {
			aenderChefAbteilung();
		});
		menu.addOption("zurück", () -> {
		});

		return menu;
	}

	private static Menu mitArbeitenMenuLaden() {
		Menu menu = new Menu();
		menu.addOption("Add Mit Arbeit", () -> {
			addMitArbeit();
		});
		menu.addOption("Miete Mit Arbeit", () -> {
			mieten();
		});
		menu.addOption("Entlassen Mit Arbeit", () -> {
			entlassen();
		});
		menu.addOption("Änder Telefon Nummer", () -> {
			aenderTelefonNummer();
		});
		menu.addOption("Änder Gehalt", () -> {
			aenderGehaltMitArbeit();
		});
		menu.addOption("Änder Dienststellung", () -> {
			aenderDienststellungMitArbeit();
		});
		menu.addOption("zurück", () -> {
		});
		return menu;
	}

	// FIRME MENU FUNCTIONS

	private static void createFirme() {
		System.out.println("Gibt Nombre");
		String name = s.next();
		System.out.println("Gibt richtung");
		String richtung = s.next();
		MitArbeit direktor = createDirektor();
		controlFirma.CreateFirma(name, richtung, direktor);
	}

	private static MitArbeit createDirektor() {
		System.out.println("Gibt name");
		String name = s.next();
		System.out.println("Gibt Vorname");
		String vorname = s.next();
		System.out.println("Gibt Geburstag yyyy-MM-dd");
		String geburstag = s.next();
		System.out.println("gibt TelefonNumer");
		String telefon = s.next();
		MitArbeit direktor = new MitArbeit(name, vorname, telefon, LocalDate.parse(geburstag),
				Dienststellung.DIREKTOR.getGehalt(), Dienststellung.DIREKTOR, null);
		return direktor;
	}

	private static void deleteFirme() {
		System.out.println("Gibt idFirma für Firme zu löschen ");
		Long idFirma = s.nextLong();
		controlFirma.deleteFirma(idFirma);
	}

	private static void listeFirme() {
		List<String> firmen = controlFirma.ListFirmen();
		firmen.stream().forEach(System.out::println);
	}

	private static void listeFirmeOrderByName() {
		List<String> listeFirmen = controlFirma.ListFirmenByName();
		listeFirmen.forEach(System.out::println);
	}

	private static void aenderNameFirme() {

		System.out.println("Gibt idFirma");
		Long idFirma = s.nextLong();

		System.out.println("Gibt neu name");
		String name = s.next();

		controlFirma.aenderName(idFirma, name);
	}

	private static void aenderRichtungFirme() {
		System.out.println("Gibt neu richtung");
		String richtung = s.next();
		System.out.println("Gibt idFirma");
		Long idFirma = s.nextLong();
		controlFirma.aenderRichtung(idFirma, richtung);
	}

	// ABTEILUNG MENU FUNCTIONS

	private static void createAbteilung() {
		controlFirma.ListFirmen().forEach(System.out::println);
		System.out.println("Gibt id Firme");
		Long id = s.nextLong();
		System.out.println("Gibt name");
		String name = s.next();
		
		controlMitArbeit.getMitArbeiteVonFirma(id).forEach(System.out::println);
		System.out.println("Gibt id für MitArbeit");
		Long idMitArbeit = s.nextLong();
		controlAbteilung.createAbteilung(name, id, idMitArbeit);
	}

	private static void deleteAbteilung() {
		System.out.println("Gibt id für Abteilung");
		Long idAbteilung = s.nextLong();
		controlAbteilung.deleteAbteilung(idAbteilung);

	}

	private static void aenderNameAbteilung() {
		System.out.println("Gibt id für Abteilung");
		Long idAbteilung = s.nextLong();
		System.out.println("Gibt neu name");
		String name = s.next();
		controlAbteilung.aenderName(idAbteilung, name);
	}

	private static void aenderChefAbteilung() {
		controlFirma.ListFirmen().forEach(System.out::println);
		System.out.println("Gibt id Firme");
		Long idFirma = s.nextLong();

		controlAbteilung.listAbteilungByFirma(idFirma).forEach(System.out::println);
		System.out.println("Gibt id für Abteilung");
		Long idAbteilung = s.nextLong();

		controlMitArbeit.getMitArbeiteVonFirma(idFirma).forEach(System.out::println);
		System.out.println("Gibt id MitArbeit");
		Long idMitArbeit = s.nextLong();
		controlAbteilung.aendernChef(idAbteilung, idMitArbeit);
		controlMitArbeit.aendernDienstellung(idMitArbeit, Dienststellung.CHEF.toString());
		controlMitArbeit.aendernAbteilung(idMitArbeit, idAbteilung);

	}

	private static void showAbteilungfuerEineFirme() {
		controlFirma.ListFirmen().forEach(System.out::println);
		System.out.println("Gibt id Firme");
		Long id = s.nextLong();
		controlAbteilung.listAbteilungByFirma(id).forEach(System.out::println);
	}

	private static void showMitARbeitfuerEineAbteilung() {
		System.out.println("Gibt id Abteilung");
		long idAbteilung = s.nextLong();
		controlMitArbeit.getMitArbeiteVonAbteilung(idAbteilung).forEach(System.out::println);
	}

	// MITARBEITEN MENU FUNCTIONS

	private static void addMitArbeit() {
		System.out.println("Gibt Name");
		String name = s.next();
		System.out.println("Gibt Vorname");
		String vorname = s.next();
		System.out.println("Gibt TelefonNummer");
		String telefon = s.next();
		System.out.println("Gibt geburstag datum yyyy-MM-dd");
		String datum = s.next();
		LocalDate geburstag = LocalDate.parse(datum);
		MitArbeit mitarbeit = new MitArbeit(name, vorname, telefon, geburstag, Dienststellung.JUNIOR.getGehalt(),
				Dienststellung.JUNIOR, new Long(-1));
		controlMitArbeit.addMitArbeit(mitarbeit);
	}

	private static void mieten() {
		controlFirma.ListFirmen().forEach(System.out::println);
		System.out.println("Gibt id Firma");
		Long idFirma = s.nextLong();
		controlMitArbeit.getAllMitArbeite().forEach(System.out::println);
		System.out.println("Gibt Arbeite");
		Long idMitArbeit = s.nextLong();
		controlAbteilung.listAbteilungByFirma(idFirma).forEach(System.out::println);
		System.out.println("Gibt id Abteilung");
		Long idAbteilung = s.nextLong();
		controlMitArbeit.aendernAbteilung(idMitArbeit, idAbteilung);
		controlMitArbeit.aendernDienstellung(idMitArbeit, Dienststellung.JUNIOR.toString());
		controlMitArbeit.aendernGehalt(idMitArbeit, Dienststellung.JUNIOR.getGehalt());

	}

	private static void entlassen() {
		controlFirma.ListFirmen().forEach(System.out::println);
		System.out.println("Gibt id Firma");
		Long idFirma = s.nextLong();
		controlMitArbeit.getMitArbeiteVonFirma(idFirma).forEach(System.out::println);
		System.out.println("Gibt MitArbeite");
		Long idArbeit = s.nextLong();
		controlMitArbeit.aendernAbteilung(idArbeit, new Long(-1));
	}

	private static void aenderTelefonNummer() {
		controlMitArbeit.getAllOrderByName().forEach(System.out::println);
		System.out.println("Gibt id");
		Long id = s.nextLong();
		System.out.println("neu Teleffon Nummer");
		String telefonNumer = s.next();
		controlMitArbeit.aenderTelefonnumer(id, telefonNumer);
	}

	private static void aenderGehaltMitArbeit() {
		controlMitArbeit.getAllOrderByName().forEach(System.out::println);
		System.out.println("Gibt id");
		Long id = s.nextLong();
		System.out.println("neu Gehalt");
		double gehalt = s.nextDouble();
		controlMitArbeit.aendernGehalt(id, gehalt);
	}

	private static void aenderDienststellungMitArbeit() {
		controlMitArbeit.getAllOrderByName().forEach(System.out::println);
		System.out.println("Gibt id");
		Long id = s.nextLong();
		System.out.println("neu DIENSTSTELLUNG (LEHRLING, JUNIOR, SENIOR, CHEF, DIREKTOR)");
		String stellung = s.next();
		stellung.toUpperCase();
		controlMitArbeit.aendernDienstellung(id, stellung);
	}
}
