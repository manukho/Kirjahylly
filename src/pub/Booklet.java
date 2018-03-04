package pub;

import java.util.ArrayList;

public class Booklet extends Publication {
	
	String title;
	
	ArrayList<String> authors;
	String howpublished;
	String address;
	String month;
	Integer year;
	
	public Booklet(int id, String t) {
		if (id == 0) generateId();
		else this.id = id;
		title = t;
		authors = new ArrayList<String>();
	}
	
	public Booklet() {
		authors = new ArrayList<String>();
	}

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

	public String getHowpublished() {
		return howpublished;
	}

	public void setHowpublished(String howpublished) {
		this.howpublished = howpublished;
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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	void generateKey() {

	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("\nBOOKLET { \n");
		sb.append("ID: ");
		sb.append(id);
		sb.append("\nTitle: ");
		sb.append(title);
		
		if (!authors.isEmpty()) {
			sb.append("\nAuthors: ");
			for (int i = 0; i < authors.size(); i++) {
				if (i != 0) {
					sb.append("\n");
					sb.append("\t");
				}
				sb.append(authors.get(i));
			}
		}
		
		if (howpublished != null && !howpublished.isEmpty()) {
			sb.append("\nHowpublished: ");
			sb.append(howpublished);
		}
		
		if (address != null && !address.isEmpty()) {
			sb.append("\nAddress: ");
			sb.append(address);
		}
		
		if (month != null && !month.isEmpty()) {
			sb.append("\nMonth: ");
			sb.append(month);
		}
		
		if (year != null) {
			sb.append("\nYear: ");
			sb.append(year);
		}
		
		if (note != null && !note.isEmpty()) {
			sb.append("\nNote: ");
			sb.append(note);
		}
		if (key != null && !key.isEmpty()) {
			sb.append("\nKey: ");
			sb.append(key);
		}
		sb.append("\n}");
		
		return sb.toString();
	}

}