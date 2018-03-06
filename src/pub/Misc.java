package pub;

import java.util.ArrayList;

public class Misc extends Publication {
	
	ArrayList<String> authors;
	String title;
	String howpublished;
	String month;
	Integer year;
	
	public Misc(int id) {
		this.id = id;	
		authors = new ArrayList<String>();
	}
	
	public Misc() {
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
		if (title == null) return "";
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHowpublished() {
		return howpublished;
	}

	public void setHowpublished(String howpublished) {
		this.howpublished = howpublished;
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
		if (!authors.isEmpty() && year != 0) generateKey2(authors, year);
		else key = Integer.toString(id);
	}	
}
