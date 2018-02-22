package pub;

import java.util.ArrayList;

public class Book extends Publication {
	
	ArrayList<Person> authors;
	ArrayList<Person> editors;
	String title;
	String publisher;
	int year;
	
	int volume;
	int number;
	String series;
	String address;
	String edition;
	String month;
	String url;
	
	
	public Book(int id, ArrayList<Person> pers, boolean auth, String t, String pub, int y){
		this.id = id;
		authors = new ArrayList<Person>();
		editors = new ArrayList<Person>();
		if (auth) authors = pers;
		else editors = pers;
		title = t;
		publisher = pub;
		year = y;
		if (id == 0) generateId();
	}
	
	void addVolume(int vol) {
		volume = vol;
	}
	
	void addNumber(int num) {
		number = num;
	}
	
	void addSeries(String s) {
		series = s;
	}
	
	void addAddress(String a) {
		address = a;
	}
	
	void addEdition(String ed) {
		edition = ed;
	}
	
	void addMonth(String m) {
		month = m;
	}
	
	void addURL(String s) {
		url = s;
	}

	@Override
	void generateKey() {
		if (authors != null && !authors.isEmpty()) generateKey(authors, year);
		else generateKey(editors, year);
	}

}
