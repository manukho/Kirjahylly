package pub;

import java.util.ArrayList;

public class Unpublished extends Publication {
	
	ArrayList<String> authors;
	String title;
	
	Integer year;
	String month;
	
	public Unpublished(int id, ArrayList<String> auth, String t, String n) {
		this.id = id;
		authors = auth;
		title = t;
		note = n;
	}

	public Unpublished() {
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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	public String getYearString() {
		if (year == null) return "";
		return year.toString();
	}

	@Override
	void generateKey() {
		if (year != 0) generateKey2(authors, year); 
		else generateKey2(authors, 0);
	}

}
