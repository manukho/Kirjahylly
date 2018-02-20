package pub;

import java.util.ArrayList;

public class Article extends Publication {
	
	String title;
	ArrayList<Person> authors;
	String journal;
	int year;
	int volume;
	
	int number;
	int[] pages;
	String month;
	
	Article(String t, ArrayList<Person> auth, String j, int y, int vol){
		title = t;
		authors = auth;
		journal = j;
		year = y;
		volume = vol;
	}
	
	void addNumber(int n) {
		number = n;
	}
	
	void addPages(int first, int second) {
		pages = new int[]{first, second};
	}
	
	void addMonth(String m) {
		month = m;
	}
	
	@Override
	void generateKey() {
		generateKey(authors, year);
	}
}
