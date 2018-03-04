package pub;

import java.util.ArrayList;

public class Manual extends Publication {
	
	String title;
	
	ArrayList<Person> authors;
	String organization;
	String address;
	String edition;
	String month;
	int year;
	
	public Manual(int id, String t) {
		if (id == 0) generateId();
		else this.id = id;
		title = t;
		authors = new ArrayList<Person>();
	}
	
	void setTitle(String t) {
		title = t;
	}
	
	void addAuthors(ArrayList<Person> auth) {
		authors.addAll(auth);
	}
	
	void addAuthor(Person auth) {
		authors.add(auth);
	}
	
	void addOrganization(String org) {
		organization = org;
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
		if (!authors.isEmpty() && year != 0) {
			generateKey(authors, year);
		} else {
			key = Integer.toString(id);
		}
	}

}
