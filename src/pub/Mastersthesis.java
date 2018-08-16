package pub;

import java.util.ArrayList;

public class Mastersthesis extends Publication {
	
	ArrayList<String> authors;
	String title;
	String school;
	Integer year;
	
	String PType;
	String address;
	String month;
	
	public Mastersthesis(Integer id, ArrayList<String> auth, String t, String s, Integer y) {
		this.id = id;
		authors = auth;
		title = t;
		school = s;
		year = y;
	}
	
	public Mastersthesis() {
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

	@Override
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

	@Override
	public String getPType() {
		return PType;
	}

	public void setPType(String PType) {
		this.PType = PType;
	}

	@Override
	public String getAddress() {
		if (address == null || address.isEmpty()) return "";
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
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
