package pub;

import java.util.ArrayList;

public class Phdthesis extends Publication {
	
	ArrayList<String> authors;
	String title;
	String school;
	Integer year;
	
	String type;
	String address;
	String month;
	
	public Phdthesis(int id, ArrayList<String> auth, String t, String s, Integer y) {
		this.id = id;
		authors = auth;
		title = t;
		school = s;
		year = y;
	}
	
	public Phdthesis() {
		authors = new ArrayList<String>();
	}

	public ArrayList<String> getAuthors() {
		return authors;
	}

	public void setAuthors(ArrayList<String> authors) {
		this.authors = authors;
	}

	@Override
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	@Override
	public String getYearString() {
		return year.toString();
	}

	@Override
	void generateKey() {
		generateKey2(authors, year);
	}

}
