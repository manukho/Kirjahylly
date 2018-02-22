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
		this.id = id;
		authors = auth;
		title = t;
		booktitle = bt;
		publisher = pub;
		year = y;
		if (id == 0) generateId();
	}

	@Override
	void generateKey() {
		// TODO Auto-generated method stub

	}

}
