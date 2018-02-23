package pub;

import java.util.ArrayList;

public abstract class Publication {
	
	int id;
	String note;
	String key;
	
	int generateId(){
		id = LitManagement.getLitManagementInstance().getPubID();
		return id;
	}
	
	void setNote(String s) {
		note = s;
	}
	
	void setKey(String s) {
		key = s;
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
		String year = String.valueOf(y);
		k.append(year.substring(year.length() - 2));
		
		int counter = 0;
		while (!LitManagement.getLitManagementInstance().isKeyUnique(k.append(counter).toString())) {
			k.replace(k.length() - String.valueOf(counter).length(), k.length(), "");
			counter++;
		}
		key = k.toString();
		return key;
	}

}
