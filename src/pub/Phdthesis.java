package pub;

import java.util.ArrayList;

public class Phdthesis extends Publication {
	
	ArrayList<Person> authors;
	String title;
	String school;
	int year;
	
	String type;
	String address;
	String month;
	
	public Phdthesis(int id, ArrayList<Person> auth, String t, String s, int y) {
		if (id == 0) generateId();
		else this.id = id;
		authors = auth;
		title = t;
		school = s;
		year = y;
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
	
	void setSchool(String s) {
		school = s;
	}
	
	void setYear(int y) {
		year = y;
	}
	
	void setType(String t) {
		type = t;
	}
	
	void setAddress(String s) {
		address = s;
	}
	
	void setMonth(String m) {
		month = m;
	}

	@Override
	void generateKey() {
		generateKey(authors, year);
	}

}
