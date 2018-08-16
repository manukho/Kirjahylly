package pub;

import java.util.ArrayList;

public class Inproceedings extends Publication {
	
	ArrayList<String> authors;
	String title;
	String booktitle;
	Integer year;
	
	ArrayList<String> editors;
	Integer volume;
	Integer number;
	String series;
	Integer firstPage;
	Integer lastPage;
	String address;
	String month;
	String organization;
	String publisher;
	
	public Inproceedings(Integer id, ArrayList<String> auth, String t, String bt, Integer y) {
		this.id = id;
		authors = auth;
		title = t;
		booktitle = bt;
		year = y;
		editors = new ArrayList<String>();
	}
	
	public Inproceedings() {
		authors = new ArrayList<String>();
		editors = new ArrayList<String>();
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
	public String getBooktitle() {
		return booktitle;
	}

	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public ArrayList<String> getEditors() {
		return editors;
	}

	public void setEditors(ArrayList<String> editors) {
		this.editors = editors;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}
	
	@Override
	public String getVolumeString() {
		if (volume == null) return "";
		return volume.toString();
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	@Override
	public String getNumberString() {
		if (volume == null) return "";
		return number.toString();
	}

	@Override
	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public Integer getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(Integer firstPage) {
		this.firstPage = firstPage;
	}

	public Integer getLastPage() {
		return lastPage;
	}

	public void setLastPage(Integer lastPage) {
		this.lastPage = lastPage;
	}
	
	@Override
	public String getPageString() {
		if (firstPage == null || lastPage == null)
			return "";
		return firstPage.toString() + " -- " + lastPage.toString();
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
	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
	@Override
	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	@Override
	public String getYearString() {
		if (year != null)
			return year.toString();
		else return "";
	}	

	@Override
	void generateKey() {
		generateKey2(authors, year);
	}

}
