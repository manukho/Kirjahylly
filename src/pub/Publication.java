package pub;

import java.util.ArrayList;

public abstract class Publication {
	
	Integer id;
	String note;
	String key;	
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String s) {
		note = s;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String s) {
		key = s;
	}
	
	public Integer getId() {
		System.out.println("getId: " + id);
		return id;
	}
	
	public void setId(Integer ID) {
		id = ID;
	}
	
	abstract void generateKey();
	
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


	/**
	 * This method will be overridden by most subclasses.
	 * If the subclass has a title, then the title should be returned.
	 * Otherwise, the return value is an empty string.
	 */
	public String getTitle() {
		return "";
	}
	
	/**
	 * This method will be overridden by most subclasses.
	 * If the subclass has authors, then the list of authors should be returned.
	 * Otherwise, the return value is null.
	 */
	public ArrayList<String> getAuthors(){
		return null;
	}
	
	/**
	 * This method will be overridden by most subclasses.
	 * If the subclass has a year, then the year should be returned as a String
	 * Otherwise, the return value is an empty String.
	 */
	public String getYearString() {
		return "";
	}
	
	public String getAuthorString() {
		String s;
		if (getAuthors() == null || getAuthors().isEmpty()) {
			s = "";
		} else {			
			s = getAuthors().get(0);
			for (int i = 1; i < getAuthors().size(); i++) {
				s = s + " and " + getAuthors().get(i);
			}
		}
		return s;
	}
	
	public String getType() {
    	Class<? extends Publication> c = getClass();
    	if (c == Article.class) 		return "article";
    	if (c == Book.class) 			return "book";
    	if (c == Booklet.class) 		return "booklet";
    	if (c == Inbook.class) 			return "inbook";
    	if (c == Incollection.class) 	return "incollection";
    	if (c == Inproceedings.class) 	return "inproceedings";
    	if (c == Manual.class) 			return "manual";
    	if (c == Mastersthesis.class) 	return "mastersthesis";
    	if (c == Misc.class) 			return "misc";
    	if (c == Phdthesis.class) 		return "phdthesis";
    	if (c == Proceedings.class) 	return "proceedings";
    	if (c == Techreport.class) 		return "techreport";
    	if (c == Unpublished.class) 	return "unpublished";
    	return null;
	}
	
}
