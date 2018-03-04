package pub;

import java.util.ArrayList;

public abstract class Publication {
	
	Integer id;
	String note;
	String key;
	
	int generateId(){
		id = LitManagement.getLitManagementInstance().getPubID();
		return id;
	}
	
	public void setNote(String s) {
		note = s;
	}
	
	public void setKey(String s) {
		key = s;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer ID) {
		id = ID;
	}
	
	abstract void generateKey();
	
	String generateKey(ArrayList<Person> plist, int y) {
		StringBuilder k = new StringBuilder();
		if (plist.size() == 1) {
			String s = plist.get(0).name.split(",")[0];
			if (s.length() <= 3) {
				k.append(s); 
			} else {
				k.append(s.substring(0, 3));
			}
		} else {
			for (Person p: plist) {
				k.append(p.name.substring(0, 1).toUpperCase());
			}
		}
		if (y != 0) {
			String year = String.valueOf(y);
			k.append(year.substring(year.length() - 2));
		}
		
		// now make sure the key is unique
		int counter = 0;
		while (!LitManagement.getLitManagementInstance().isKeyUnique(k.append(counter).toString())) {
			k.replace(k.length() - String.valueOf(counter).length(), k.length(), "");
			counter++;
		}
		key = k.toString();
		return key;
	}
	
	String generateKey2(ArrayList<String> plist, int y) {
		StringBuilder k = new StringBuilder();
		if (plist.size() == 1) {
			String s = plist.get(0).split(",")[0];
			if (s.length() <= 3) {
				k.append(s); 
			} else {
				k.append(s.substring(0, 3));
			}
		} else {
			for (String s: plist) {
				k.append(s.substring(0, 1).toUpperCase());
			}
		}
		if (y != 0) {
			String year = String.valueOf(y);
			k.append(year.substring(year.length() - 2));
		}
		
		// now make sure the key is unique
		int counter = 0;
		while (!LitManagement.getLitManagementInstance().isKeyUnique(k.append(counter).toString())) {
			k.replace(k.length() - String.valueOf(counter).length(), k.length(), "");
			counter++;
		}
		key = k.toString();
		return key;
	}

}
