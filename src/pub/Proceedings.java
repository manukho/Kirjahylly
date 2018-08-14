package pub;

import java.util.ArrayList;

public class Proceedings extends Publication {
	
	String title;
	Integer year;
	
	ArrayList<String> editors;
	Integer volume;
	Integer number;
	String series;
	String address;
	String month;
	String publisher;
	String organization;
	
	public Proceedings(Integer id, String t, Integer y) {
		this.id = id;
		title = t;
		year = y;
		editors = new ArrayList<String>();
	}
	
	public Proceedings() {
		editors = new ArrayList<String>();
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

	@Override
	public String getAddress() {
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
	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Override
	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Override
	public String getYearString() {
		return year.toString();
	}

	@Override
	void generateKey() {
		key = Integer.toString(id);
	}

}
