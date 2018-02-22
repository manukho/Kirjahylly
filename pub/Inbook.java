package pub;

import java.util.ArrayList;

public class Inbook extends Publication {
	
	ArrayList<Person> authors;
	ArrayList<Person> editors;
	String title;
	int chapter;
	int[] pages;
	String publisher;
	int year;
	
	int volume;
	int number;
	String series;
	String type;
	String address;
	String edition;
	String month;
	
	public Inbook(int id, ArrayList<Person> pers, boolean auth, String t, int chap, String pub, int y) {
		this.id = id;
		authors = new ArrayList<Person>();
		editors = new ArrayList<Person>();
		if (auth) authors = pers;
		else editors = pers;
		title = t;
		chapter = chap;
		publisher = pub;
		year = y;
		if (id == 0) generateId();
	}
	
	public Inbook(int id, ArrayList<Person> pers, boolean auth, String t, int[] p, String pub, int y) {
		this.id = id;
		authors = new ArrayList<Person>();
		editors = new ArrayList<Person>();
		if (auth) authors = pers;
		else editors = pers;
		title = t;
		pages = p;
		publisher = pub;
		year = y;
		if (id == 0) generateId();
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
	
	void addType(String t) {
		type = t;
	}
	
	void addAddress(String s) {
		address = s;
	}
	
	void addEdition(String ed) {
		edition = ed;
	}
	
	void addMonth(String m) {
		month = m;
	}

	@Override
	void generateKey() {
		if (authors != null && !authors.isEmpty()) generateKey(authors, year);
		else generateKey(editors, year);
	}

}
