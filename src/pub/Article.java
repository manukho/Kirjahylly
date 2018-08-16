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
	
	@Override
	public String getTitle() {
		return title;
	}
	
	@Override
	public void setTitle(String s) {
		title = s;
	}
	
	@Override
	public ArrayList<String> getAuthors(){
		return authors;
	}
	
	@Override
	public void setAuthors(ArrayList<String> auth) {
		authors = auth;
	}
	
	public String getJournal() {
		return journal;
	}
	
	public void setJournal(String j) {
		journal = j;
	}
	
	@Override
	public Integer getYear() {
		return year;
	}
	
	@Override
	public void setYear(Integer y) {
		year = y;
	}
	
	@Override
	public String getYearString() {
		if (year == null) return "";
		return year.toString();
	}
	
	public Integer getVolume() {
		return volume;
	}
	
	@Override
	public void setVolume(Integer vol) {
		volume = vol;
	}
	
	@Override
	public String getVolumeString() {
		return volume.toString();
	}
	
	public Integer getNumber() {
		return number;
	}
	
	@Override
	public void setNumber(Integer n) {
		number = n;
	}
	
	@Override
	public String getNumberString() {
		if (number == null) return "";
		return number.toString();
	}
	
	@Override
	public void setPages(Integer first, Integer last) {
		firstPage = first;
		lastPage = last;
	}

	public Integer getFirstPage() {
		return firstPage;
	}
	
	public void setFirstPage(Integer p) {
		firstPage = p;
	}
	
	@Override
	public String getMonth() {
		return month;
	}
	
	public Integer getLastPage() {
		return lastPage;
	}
	
	public void setLastPage(Integer p) {
		lastPage = p;
	}
	
	@Override
	public String getPageString() {
		if (firstPage == null || lastPage == null)
			return "";
		return firstPage.toString() + " -- " + lastPage.toString();
	}
	
	@Override
	public void setMonth(String m) {
		month = m;
	}
	
	@Override
	void generateKey() {
		generateKey2(authors, year);
	}
	
	public Integer getId(){
		return id;
	}
	
	@Override
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
