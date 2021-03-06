package pub;

import java.util.ArrayList;

public class Book extends Publication {

	ArrayList<String> authors;
	ArrayList<String> editors;
	String title;
	String publisher;
	Integer year;
	
	Integer volume;
	Integer number;
	String series;
	String address;
	String edition;
	String month;
	String url;
	
	
	public Book(int id, ArrayList<String> pers, boolean auth, String t, String pub, int y){
		this.id = id;
		authors = new ArrayList<String>();
		editors = new ArrayList<String>();
		if (auth) authors = pers;
		else editors = pers;
		title = t;
		publisher = pub;
		year = y;
	}
	
	public Book() {
		authors = new ArrayList<String>();
		editors = new ArrayList<String>();
	}
	
	@Override
	public ArrayList<String> getAuthors() {
		return authors;
	}

	public void setAuthors(ArrayList<String> authors) {
		this.authors = authors;
	}

	@Override
	public ArrayList<String> getEditors() {
		return editors;
	}

	public void setEditors(ArrayList<String> editors) {
		this.editors = editors;
	}

	@Override
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getYear() {
		return year;
	}

	@Override
	public void setYear(Integer y) {
		this.year = y;
	}
	
	@Override
	public String getYearString() {
		return year.toString();
	}

	public Integer getVolume() {
		return volume;
	}

	@Override
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
		if (number == null) return "";
		return number.toString();
	}
	
	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	@Override
	public String getAddress() {
		if (address == null || address.isEmpty()) return "";
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

	@Override
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Override
	public String getURL() {
		if (url == null || url.isEmpty()) return "";
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	void generateKey() {
		if (authors != null && !authors.isEmpty()) generateKey2(authors, year);
		else generateKey2(editors, year);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("\nBOOK { \n");
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
		} else {
			sb.append("\nEditors: ");
			for (int i = 0; i < editors.size(); i++) {
				if (i != 0) {
					sb.append("\n");
					sb.append("\t");
				}
				sb.append(editors.get(i));
			}
		}
		sb.append("\nPublisher: ");
		sb.append(publisher);
		
		sb.append("\nYear: ");
		sb.append(year);
		
		if (volume != null) {
			sb.append("\nVolume: ");
			sb.append(volume);
		}

		if (number != null) {
			sb.append("\nNumber: ");
			sb.append(number);
		}
		
		if (series != null && !series.isEmpty()) {
			sb.append("\nSeries: ");
			sb.append(series);
		}
		
		if (address != null && !address.isEmpty()) {
			sb.append("\nAddress: ");
			sb.append(address);
		}
		
		if (edition != null && !edition.isEmpty()) {
			sb.append("\nEdition: ");
			sb.append(edition);
		}

		if (month != null && !month.isEmpty()) {
			sb.append("\nMonth: ");
			sb.append(month);
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
