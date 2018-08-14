package pub;

import java.util.ArrayList;

public class Inbook extends Publication {
	
	ArrayList<String> authors;
	ArrayList<String> editors;
	String title;
	Integer chapter;
	Integer firstPage;
	Integer lastPage;
	String publisher;
	Integer year;
	
	Integer volume;
	Integer number;
	String series;
	String PType;
	String address;
	String edition;
	String month;
	
	public Inbook(Integer id, ArrayList<String> pers, boolean auth, String t, Integer chap, String pub, Integer y) {
		this.id = id;
		authors = new ArrayList<String>();
		editors = new ArrayList<String>();
		if (auth) authors = pers;
		else editors = pers;
		title = t;
		chapter = chap;
		publisher = pub;
		year = y;
	}
	
	public Inbook(Integer id, ArrayList<String> pers, boolean auth, String t, Integer fp, Integer lp, String pub, Integer y) {
		this.id = id;
		authors = new ArrayList<String>();
		editors = new ArrayList<String>();
		if (auth) authors = pers;
		else editors = pers;
		title = t;
		firstPage = fp;
		lastPage = lp;
		publisher = pub;
		year = y;
	}
	
	public Inbook() {
		authors = new ArrayList<String>();
		editors = new ArrayList<String>();
	}

	public ArrayList<String> getAuthors() {
		return authors;
	}

	public void setAuthors(ArrayList<String> authors) {
		this.authors = authors;
	}

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

	public Integer getChapter() {
		return chapter;
	}

	@Override
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

	public void setPType(String IBPType) {
		this.PType = IBPType;
	}

	@Override
	public String getAddress() {
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
	public void setPages(Integer first, Integer last) {
		firstPage = first;
		lastPage = last;
	}
	
	@Override
	public String getPageString() {
		if (firstPage == null || lastPage == null)
			return "";
		return firstPage.toString() + " -- " + lastPage.toString();
	}

	@Override
	void generateKey() {
		if (authors != null && !authors.isEmpty()) generateKey2(authors, year);
		else generateKey2(editors, year);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("\nINBOOK { \n");
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
		
		sb.append("\nChapter: ");
		sb.append(chapter);
		
		sb.append("\nPages: ");
		sb.append(firstPage);
		sb.append(" - ");
		sb.append(lastPage);
		
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
		
		if (PType != null && !PType.isEmpty()) {
			sb.append("\nType: ");
			sb.append(PType);
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
