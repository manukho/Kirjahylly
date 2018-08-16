package pub;

import java.util.ArrayList;

public class Incollection extends Publication {
	
	ArrayList<String> authors;
	String title;
	String booktitle;
	String publisher;
	Integer year;
	
	ArrayList<String> editors;
	Integer volume;
	Integer number;
	String series;
	String PType;
	Integer chapter;
	Integer firstPage;
	Integer lastPage;
	String address;
	String edition;
	String month;
	
	public Incollection(int id, ArrayList<String> auth, String t, String bt, String pub, int y) {
		this.id = id;		
		authors = auth;
		title = t;
		booktitle = bt;
		publisher = pub;
		year = y;
		editors = new ArrayList<String>();
	}
	
	public Incollection() {
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
	public String getPType() {
		return PType;
	}

	public void setPType(String PType) {
		this.PType = PType;
	}

	public Integer getChapter() {
		return chapter;
	}

	public void setChapter(Integer chapter) {
		this.chapter = chapter;
	}
	
	@Override 
	public String getChapterString() {
		if (chapter == null) return "";
		return chapter.toString();
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
	public String getYearString() {
		return year.toString();
	}	

	@Override
	void generateKey() {
		generateKey2(authors, year);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("\nINCOLLECTION { \n");
		sb.append("ID: ");
		sb.append(id);
		sb.append("\nTitle: ");
		sb.append(title);
		sb.append("\nBooktitle: ");
		sb.append(booktitle);
		sb.append("\nAuthors: ");
		for (int i = 0; i < authors.size(); i++) {
			if (i != 0) {
				sb.append("\n");
				sb.append("\t");
			}
			sb.append(authors.get(i));
		}
		sb.append("\nYear: ");
		sb.append(year);
		
		if (!editors.isEmpty()) {
			sb.append("\nEditors: ");
			for (int i = 0; i < editors.size(); i++) {
				if (i != 0) {
					sb.append("\n");
					sb.append("\t");
				}
				sb.append(editors.get(i));
			}
		}
		
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
		
		if (PType != null && !PType.isEmpty()) {
			sb.append("\nType: ");
			sb.append(PType);
		}
		
		if (chapter != null) {
			sb.append("\nChapter: ");
			sb.append(chapter);
		}
		
		if (firstPage != null && lastPage != null) {
			sb.append("\nPages: ");
			sb.append(firstPage);
			sb.append(" - ");
			sb.append(lastPage);
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

		return sb.toString();
	}

}
