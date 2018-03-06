package pub;

import java.util.ArrayList;

public class Techreport extends Publication {
	
	ArrayList<String> authors;
	String title;
	String institution;
	Integer year;
	
	String type;
	Integer number;
	String address;
	String month;
	
	public Techreport(Integer id, ArrayList<String> auth, String t, String inst, Integer y) {
		this.id = id;
		authors = auth;
		title = t;
		institution = inst;
		year = y;
	}
	
	public Techreport() {
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

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
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

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
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
