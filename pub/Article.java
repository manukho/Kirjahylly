package pub;

import java.util.ArrayList;

public class Article extends Publication {
	
	String title;
	ArrayList<Person> authors;
	String author;
	String journal;
	int year;
	int volume;
	
	int number;
	int[] pages;
	String month;
	
	public Article(int id, String t, ArrayList<Person> auth, String j, int y, int vol){
		this.id = id;
		title = t;
		authors = auth;
		journal = j;
		year = y;
		volume = vol;
		if (id == 0) generateId();
	}
	
	public void addNumber(int n) {
		number = n;
	}
	
	public void addPages(int first, int second) {
		pages = new int[]{first, second};
	}
	
	public void addMonth(String m) {
		month = m;
	}
	
	@Override
	void generateKey() {
		generateKey(authors, year);
	}
}
