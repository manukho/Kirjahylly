package pub;

import java.util.ArrayList;

public class Inproceedings extends Publication {
	
	ArrayList<Person> authors;
	String title;
	String booktitle;
	int year;
	
	ArrayList<Person> editors;
	int volume;
	int number;
	String series;
	int[] pages;
	String address;
	String month;
	String organization;
	String publisher;
	
	public Inproceedings(int id, ArrayList<Person> auth, String t, String bt, int y) {
		if (id == 0) generateId();
		else this.id = id;
		authors = auth;
		title = t;
		booktitle = bt;
		year = y;
		editors = new ArrayList<Person>();
	}
	
	void addEditors(ArrayList<Person> ed) {
		editors.addAll(ed);
	}
	
	void addEditors(Person ed) {
		editors.add(ed);
	}
	
	void addVolume(int vol) {
		volume = vol;
	}
	
	void addNumber(int n) {
		number = n;
	}
	
	void addSeries(String s) {
		series = s;
	}
	
	void addPages(int[] p) {
		pages = p;
	}
	
	void addAddress(String s) {
		address = s;
	}
	
	void addMonth(String m) {
		month = m;
	}
	
	void addOrganization(String org) {
		organization = org;
	}
	
	void addPublisher(String pub) {
		publisher = pub;
	}

	@Override
	void generateKey() {
		generateKey(authors, year);
	}

}
