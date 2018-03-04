package pub;

import java.util.ArrayList;

public class Misc extends Publication {
	
	ArrayList<Person> authors;
	String title;
	String howpublished;
	String month;
	int year;
	
	public Misc(int id) {
		if (id == 0) generateId();
		else this.id = id;	
		authors = new ArrayList<Person>();
	}
	
	void addAuthors(ArrayList<Person> auth) {
		authors.addAll(auth);
	}
	
	void addAuthor(Person auth) {
		authors.add(auth);
	}
	
	void setTitle(String t) {
		title = t;
	}
	
	void setHowpublished(String s) {
		howpublished = s;
	}
	
	void setMonth(String m) {
		month = m;
	}
	
	void setYear(int y) {
		year = y;
	}

	@Override
	void generateKey() {
		if (!authors.isEmpty() && year != 0) generateKey(authors, year);
		else key = Integer.toString(id);
	}	

}
