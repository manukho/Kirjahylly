package pub;

import java.util.ArrayList;

public class Article extends Publication {
	
	String title;
	ArrayList<String> authors;
	String journal;
	Integer year;
	Integer volume;
	
	Integer number;
	Integer firstPage;
	Integer lastPage;
	String month;
	
	public Article() {
		authors = new ArrayList<String>();
	}
	
	public Article(int id, String t, ArrayList<String> auth, String j, int y, int vol){
		this.id = id;
		title = t;
		authors = auth;
		journal = j;
		year = y;
		volume = vol;
	}

	public Article(String t, ArrayList<String> auth, String j, int y, int vol){
		title = t;
		authors = auth;
		journal = j;
		year = y;
		volume = vol;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String s) {
		title = s;
	}
	
	public ArrayList<String> getAuthors(){
		return authors;
	}
	
	public void setAuthors(ArrayList<String> auth) {
		authors = auth;
	}
	
	public void setJournal(String j) {
		journal = j;
	}
	
	public void setYear(int y) {
		year = y;
	}
	
	public void setVolume(int vol) {
		volume = vol;
	}
	
	public void setNumber(int n) {
		number = n;
	}
	
	public void setPages(int first, int last) {
		firstPage = first;
		lastPage = last;
	}

	public Integer getFirstPage() {
		return firstPage;
	}
	
	public void setFirstPage(Integer p) {
		firstPage = p;
	}
	
	public String getMonth() {
		return month;
	}
	
	public Integer getLastPage() {
		return lastPage;
	}
	
	public void setLastPage(Integer p) {
		lastPage = p;
	}
	
	public void setMonth(String m) {
		month = m;
	}
	
	@Override
	void generateKey() {
		generateKey2(authors, year);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("\nARTICLE { \n");
		sb.append("ID: ");
		sb.append(id);
		sb.append("\nTitle: ");
		sb.append(title);
		sb.append("\nAuthors: ");
		for (int i = 0; i < authors.size(); i++) {
			if (i != 0) {
				sb.append("\n");
				sb.append("\t");
			}
			sb.append(authors.get(i));
		}
		sb.append("\nJournal: ");
		sb.append(journal);
		sb.append("\nYear: ");
		sb.append(year);
		sb.append("\nVolume: ");
		sb.append(volume);
		if (number != null) {
			sb.append("\nNumber: ");
			sb.append(number);
		}
		if (firstPage != null && lastPage != null) {
			sb.append("\nPages: ");
			sb.append(firstPage);
			sb.append(" - ");
			sb.append(lastPage);
		}
		if (month != null && !month.isEmpty()) {
			sb.append("\nMonth: ");
			sb.append(month);
		}
		if (note != null && !month.isEmpty()) {
			sb.append("\nNote: ");
			sb.append(note);
		}
		if (key != null && !month.isEmpty()) {
			sb.append("\nKey: ");
			sb.append(key);
		}
		sb.append("\n}");
		
		return sb.toString();
	}
}
