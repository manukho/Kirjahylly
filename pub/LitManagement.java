package pub;

import java.util.ArrayList;

public class LitManagement {
	
	static LitManagement lm;
	
	private int lastPubID;
	private int lastPersID;
	private int lastJournalID;
	
	private ArrayList<Bookstack> bookstacks;
	private ArrayList<Person> personList;
	private ArrayList<String> journalList;
	private ArrayList<String> publisherList;
	private ArrayList<String> seriesList;
	private ArrayList<String> keys;
	
	public static LitManagement getLitManagementInstance() {
		if (lm == null) lm = new LitManagement();
		return lm;
	}
	
	private LitManagement(){
		bookstacks = new ArrayList<Bookstack>();
		personList = new ArrayList<Person>();
		journalList = new ArrayList<String>();
		publisherList = new ArrayList<String>();
		seriesList = new ArrayList<String>();		
		keys = new ArrayList<String>();
		
		lastPubID = 0; /* TODO: do something useful here */
		lastPersID = 0; /* TODO: do something useful here */		
		lastJournalID = 0; /* TODO: do something useful here */		
	}
	
	int getPubID() {
		return ++lastPubID;
	}
	
	int getPersID() {
		return ++lastPersID;
	}
	
	int getJID() {
		return ++lastJournalID;
	}
	
	void addPerson(Person p) {
		personList.add(p);
	}
	
	void addPerson(ArrayList<Person> ps) {
		personList.addAll(ps);
	}
	
	void addJournal(String j) {
		journalList.add(j);
	}
	
	void addJournals(ArrayList<String> js) {
		journalList.addAll(js);
	}
	
	void addPublisher(String j) {
		publisherList.add(j);
	}
	
	void addPublishers(ArrayList<String> js) {
		publisherList.addAll(js);
	}
	
	void addSeries(String j) {
		seriesList.add(j);
	}
	
	void addSeries(ArrayList<String> js) {
		seriesList.addAll(js);
	}
	
	void addBookstack(Bookstack bs) {
		bookstacks.add(bs);
	}

	void addBookstacks(ArrayList<Bookstack> bs) {
		bookstacks.addAll(bs);
	}
	
	boolean isKeyUnique(String key) {
		if (keys.contains(key)) return false;
		return true;
	}
	
	void addKey(String key) {
		keys.add(key);
	}
}
