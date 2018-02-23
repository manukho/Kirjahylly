package pub;

import java.util.ArrayList;

public class Proceedings extends Publication {
	
	String title;
	int year;
	
	ArrayList<Person> editors;
	int volume;
	int number;
	String series;
	String address;
	String month;
	String publisher;
	String organization;
	
	public Proceedings(int id, String t, int y) {
		if (id == 0) generateId();
		else this.id = id;
		title = t;
		year = y;
		editors = new ArrayList<Person>();
	}
	
	void setTitle(String t) {
		title = t;
	}
	
	void setYear(int y) {
		year = y;
	}
	
	void addEditors(ArrayList<Person> ed) {
		editors.addAll(ed);
	}
	
	void addEditor(Person ed) {
		editors.add(ed);
	}
	
	void setVolume(int vol) {
		volume = vol;
	}
	
	void setNumber(int n) {
		number = n;
	}
	
	void setSeries(String s) {
		series = s;
	}
	
	void setAddress(String s) {
		address = s;
	}
	
	void setMonth(String m) {
		month = m;
	}
	
	void setPublisher(String pub) {
		publisher = pub;
	}
	
	void setOrganization(String org) {
		organization = org;
	}

	@Override
	void generateKey() {
		key = Integer.toString(id);
	}

}
