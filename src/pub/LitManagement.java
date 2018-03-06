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
		keys = new ArrayList<String>();
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
