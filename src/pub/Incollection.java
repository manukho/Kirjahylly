package pub;

import java.util.ArrayList;

public class Incollection extends Publication {
	
	ArrayList<Person> authors;
	String title;
	String booktitle;
	String publisher;
	int year;
	
	ArrayList<Person> editors;
	int volume;
	int number;
	String series;
	String type;
	int chapter;
	int[] pages;
	String address;
	String edition;
	String month;
	
	public Incollection(int id, ArrayList<Person> auth, String t, String bt, String pub, int y) {
		if (id == 0) generateId();
		else this.id = id;		
		authors = auth;
		title = t;
		booktitle = bt;
		publisher = pub;
		year = y;
		editors = new ArrayList<Person>();
	}
	
	void addEditors(ArrayList<Person> ed) {
		editors.addAll(ed);
	}
	
	void addEditor(Person ed) {
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
	
	void addType(String t) {
		type = t;
	}
	
	void addChapter(int chap) {
		chapter = chap;
	}
	
	void addPages(int[] p) {
		pages = p;
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
		generateKey(authors, year);
	}

}
