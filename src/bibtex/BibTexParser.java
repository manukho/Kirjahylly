package bibtex;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pub.*;

public class BibTexParser {
	
	private ArrayList<Publication> list;
	
	public BibTexParser(String s) {
		list = new ArrayList<Publication>();
		// look for entries
		Pattern p = Pattern.compile("@(\\w*)\\s*\\{(\\w*)([^@]*)");
		Matcher m = p.matcher(s);
		
		while(m.find()) {
			// @type{key, 
			// [rest]
			String type = m.group(1);
			String key = m.group(2);
			String rest = m.group(3);
			Publication pub = createPublication(type, key, rest);
			if (pub != null) {
				list.add(pub);
			}
		}
	}

	private Publication createPublication(String type, String key, String rest) {
		type = type.toLowerCase();
		Publication p;
		switch(type.toLowerCase()) {
			case "article":
				p = new Article();
				break;
			case "book":
				p = new Book();
				break;
			case "booklet":
				p = new Booklet();
				break;
			case "conference":
				p = new Inproceedings();
				break;
			case "inbook":
				p = new Inbook();
				break;
			case "incollection":
				p = new Incollection();
				break;
			case "inproceedings":
				p = new Inproceedings();
				break;
			case "manual":
				p = new Manual();
				break;
			case "mastersthesis":
				p = new Mastersthesis();
				break;
			case "misc": 
				p = new Misc();
				break;
			case "phdthesis":
				p = new Phdthesis();
				break;
			case "proceedings":
				p = new Proceedings();
				break;
			case "techreport":
				p = new Techreport();
				break;
			case "unpublished":
				p = new Unpublished();
				break;
			default:
				return null;
		}
		p = addFields(p, rest);
		return p;
	}

	private Publication addFields(Publication p, String rest) {
		Pattern pattern = Pattern.compile("(\\w*)\\s=\\s\\{([^\\}]*)\\}");
		Matcher matcher = pattern.matcher(rest);
		while(matcher.find()) {
			// fieldName = {val}
			String fieldName = matcher.group(1);
			String val = matcher.group(2);
			int end = matcher.end();
			String string = getValue(val, end, rest);
			switch(fieldName.toLowerCase()) {
			case "title": 
				p.setTitle(string);
				break;
			case "author":
				p.setAuthors(getPersonList(string));
				break;
			case "editor":
				p.setEditors(getPersonList(string));
				break;
			case "journal":
				p.setJournal(string);
				break;
			case "volume":
				Integer vol = getNumber(string);
				if (vol != null) {
					p.setVolume(vol);
				}
				break;
			case "number": 
				Integer num = getNumber(string);
				if (num != null) {
					p.setNumber(num);
				}
				break;
			case "month": 
				p.setMonth(string);
				break;
			case "year":
				Integer y = getNumber(string);
				if (y != null) {
					p.setYear(y);
				}
				break;
			case "pages":
				Integer[] pages = getPages(string);
				if (pages != null) {
					p.setPages(pages[0], pages[1]);
				}
				break;
			case "booktitle":
				p.setBooktitle(string);
				break;
			case "publisher":
				p.setPublisher(string);
				break;
			case "address":
				p.setAddress(string);
				break;
			case "edition":
				p.setEdition(string);
				break;
			case "url":
				p.setURL(string);
				break;
			case "note":
				p.setNote(string);
				break;
			case "key":
				p.setKey(string);
				break;
			case "howpublished":
				p.setHowpublished(string);
				break;
			case "chapter":
				Integer c = getNumber(string);
				if (c != null) {
					p.setChapter(c);
				}
				break;
			case "series":
				p.setSeries(string);
				break;
			case "type":
				p.setPType(string);
				break;
			case "organization":
				p.setOrganization(string);
				break;
			case "school":
				p.setSchool(string);
				break;
			case "institution":
				p.setInstitution(string);
			}
		}
		return p;
	}
	
	private Integer[] getPages(String string) {
		String[] s = string.split("--");
		if (s.length != 2) return null;
		s[0].trim();
		s[1].trim();
		Integer[] res = {getNumber(s[0]), getNumber(s[1])};
		if (res[0] != null && res[1] != null) return res;
		return null;
	}
	
	private Integer getNumber(String string) {
		try {  
			int i = Integer.parseInt(string);  
			return i;
		}  catch(NumberFormatException e) {  
			return null;  
		}
	}

	private String getValue(String value, int end, String rest) {		
		while (!rest.substring(end + 1, end + 2).matches(",|\\n")) {
            Pattern p = Pattern.compile("[^\\}]*");
            Matcher m = p.matcher(rest);
            m.find(end + 1);
            value = value + "}" + m.group();
            end = m.end();		
        }   		
		return value;
	}
	
	private ArrayList<String> getPersonList(String s){
		ArrayList<String> list = new ArrayList<String>();
		s.replaceAll("AND", "and");
		String[] arr = s.split("and");
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}
		return list;
	}

	public ArrayList<Publication> getPublicationList() {
		return list;
	}
}
