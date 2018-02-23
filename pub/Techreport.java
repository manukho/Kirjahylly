package pub;

import java.util.ArrayList;

public class Techreport extends Publication {
	
	ArrayList<Person> authors;
	String title;
	String institution;
	int year;
	
	String type;
	int number;
	String address;
	String month;
	
	public Techreport(int id, ArrayList<Person> auth, String t, String inst, int y) {
		if (id == 0) generateId();
		else this.id = id;
		authors = auth;
		title = t;
		institution = inst;
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
	
	void setInstitution(String inst) {
		institution = inst;
	}
	
	void setYear(int y) {
		year = y;
	}
	
	void setType(String t){
		type = t;
	}
	
	void setNumber(int n) {
		number = n;
	}
	
	void setAddress(String s) {
		address = s;
	}
	
	void setMonth(String m) {
		month = m;
	}

	@Override
	void generateKey() {
		// TODO Auto-generated method stub

	}

}
