package pub;

import java.util.ArrayList;

public class Unpublished extends Publication {
	
	ArrayList<Person> authors;
	String title;
	
	int year;
	String month;
	
	public Unpublished(int id, ArrayList<Person> auth, String t, String n) {
		if (id == 0) generateId();
		else this.id = id;
		authors = auth;
		title = t;
		note = n;
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
	
	void setMonth(String m) {
		month = m;
	}
	
	void setYear(int y) {
		year = y;
	}

	@Override
	void generateKey() {
		if (year != 0) generateKey(authors, year); 
		else generateKey(authors, 0);
	}

}
