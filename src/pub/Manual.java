package pub;

import java.util.ArrayList;

public class Manual extends Publication {
	
	String title;
	
	ArrayList<String> authors;
	String organization;
	String address;
	String edition;
	String month;
	Integer year;
	
	public Manual(int id, String t) {
		this.id = id;
		title = t;
		authors = new ArrayList<String>();
	}
	
	public Manual() {
		authors = new ArrayList<String>();
	}
	
	@Override
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<String> getAuthors() {
		return authors;
	}

	public void setAuthors(ArrayList<String> authors) {
		this.authors = authors;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	@Override
	public String getYearString() {
		if (year != null) return year.toString();
		return "";
	}

	@Override
	void generateKey() {
		if (!authors.isEmpty() && year != 0) {
			generateKey2(authors, year);
		} else {
			key = Integer.toString(id);
		}
	}
}
