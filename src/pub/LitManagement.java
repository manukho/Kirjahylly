package pub;

import java.util.ArrayList;

public class LitManagement {
	
	static LitManagement lm;
	private ArrayList<Bookstack> bookstacks;
	private ArrayList<String> keys;
	
	public static LitManagement getLitManagementInstance() {
		if (lm == null) lm = new LitManagement();
		return lm;
	}
	
	private LitManagement(){
		bookstacks = new ArrayList<Bookstack>();
		bookstacks.add(new Bookstack("default"));
		keys = new ArrayList<String>();
	}
	
	void addBookstack(Bookstack bs) {
		bookstacks.add(bs);
	}

	void addBookstacks(ArrayList<Bookstack> bs) {
		bookstacks.addAll(bs);
	}
	
	public ArrayList<String> getBSNames() {
		ArrayList<String> list = new ArrayList<String>();
		for (Bookstack bs : bookstacks) {
			list.add(bs.name);
		}
		return list;
	}
	
	public Bookstack getBSByName(String s) {
		for (Bookstack bs: bookstacks) {
			if (s.equals(bs.name)) return bs;
		}
		return null;
	}
	
	boolean isKeyUnique(String key) {
		if (keys.contains(key)) return false;
		return true;
	}
	
	void addKey(String key) {
		keys.add(key);
	}
}
