package pub;

import java.util.ArrayList;

public class Booklet extends Publication {
	
	String title;
	
	ArrayList<Person> authors;
	String howpublished;
	String address;
	String month;
	int year;
	
	public Booklet(int id, String t) {
		if (id == 0) generateId();
		else this.id = id;
		title = t;
		authors = new ArrayList<Person>();
	}
	
	void addAuthors(ArrayList<Person> auth) {
		authors.addAll(auth);
	}
	
	void addAuthor(Person author) {
		authors.add(author);
	}
	
	void addHowpublished(String s) {
		howpublished = s;
	}
	
	void addAddress(String s) {
		address = s;
	}
	
	void addMonth(String s) {
		month = s;
	}
	
	void addYear(int y) {
		year = y;
	}

	@Override
	void generateKey() {

	}

}
