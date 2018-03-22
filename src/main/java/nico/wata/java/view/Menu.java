package nico.wata.java.view;

import java.util.*;

public class Menu {

	class OptionExecute {
		private Runnable execute;
		private String option;

		public OptionExecute(String option, Runnable execute) {
			this.option = option;
			this.execute = execute;
		}

		public Runnable getExecute() {
			return execute;
		}

		public void setExecute(Runnable execute) {
			this.execute = execute;
		}

		public String getOption() {
			return option;
		}

		public void setOption(String option) {
			this.option = option;
		}
	};

	private Map<Integer, OptionExecute> optionen;
	private Integer numOption;

	public Menu() {
		optionen = new HashMap<Integer, OptionExecute>();
		numOption = 1;
	}

	public void addOption(String menuOption, Runnable execute) {
		optionen.put(numOption, new OptionExecute(menuOption, execute));
		numOption++;
	}

	public void abaendernOption(Integer index, String menuOption, Runnable execute) {
		optionen.get(index).setOption(menuOption);
		optionen.get(index).setExecute(execute);
	}

	public String getListOptionen() {
		String optionenlist = "------MENU----\n";
		for (Map.Entry<Integer, OptionExecute> entry : optionen.entrySet()) {
			optionenlist = optionenlist.concat(entry.getKey() + ") " + entry.getValue().getOption() + " \n");
		}
		return optionenlist;
	}

	public void selectOption(Integer index) {
		optionen.get(index).getExecute().run();
	}

	public int getNumOption() {
		return optionen.size();
	}

}
