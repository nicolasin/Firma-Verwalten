package nico.wata.java.main;

import java.util.*;
import org.apache.log4j.PropertyConfigurator;
import nico.wata.java.view.Menu;
import nico.wata.java.view.*;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class Main extends HilfFunctions {
	private static String PropertyPath = "log4j.properties";
	private static Menu hauptMenu;
	private static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {

		PropertyConfigurator.configure(PropertyPath);
		hauptMenu = Hauptmenueladen();

		int option = 0;
		do {
			System.out.println(hauptMenu.getListOptionen());
			System.out.println("Gibt mehr Option");
			option = s.nextInt();
			if (option < hauptMenu.getNumOption() && option > 0) {
				hauptMenu.selectOption(option);
			}
		} while (option != hauptMenu.getNumOption());
		System.out.println("Auf Wiedersehen");
		s.close();
	}

}
