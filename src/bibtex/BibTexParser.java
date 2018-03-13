package bibtex;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pub.*;

public class BibTexParser {
	
	private final static String word = "\\w*";
	private final static String notAt = "[^@]*";
	
	private ArrayList<Publication> list;
	
	public BibTexParser(String s) {
		list = new ArrayList<Publication>();
		Pattern p = Pattern.compile("@(\\w*)\\s*\\{(\\w*)([^@]*)");
		Matcher m = p.matcher(s);
		
		while(m.find()) {
			String type = m.group(1);
			String key = m.group(2);
			String rest = m.group(3);
			addPublicationToList(type, key, rest);
			System.out.println(type + ": " + key);
		}
	}

	private void addPublicationToList(String type, String key, String rest) {
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
				return;
		}
		p.setKey(key);
		addFields(p, rest);
	}

	private void addFields(Publication p, String rest) {
		Pattern pattern = Pattern.compile("(\\w*)\\s=\\s\\{[^\\}]*)\\}");
		Matcher matcher = pattern.matcher(rest);
		while(matcher.find()) {
			String fieldName = matcher.group(1);
			String s1 = matcher.group(2);
			String s2 = matcher.group(3);
			switch(fieldName.toLowerCase()) {
			case "title":
				// TODO: set title
			}
		}
	}
}
