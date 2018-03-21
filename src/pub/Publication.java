package pub;

import java.util.ArrayList;

/**
 * Publication represents a generic publication. 
 * It contains the fields id, note and key that all subclasses have as well as the getters and setters for them.
 * It also contains methods to set the fields that any of its subclasses can have.
 * If the specific subclass has the field, the subclass should override the method.
 * Otherwise it does nothing.
 *
 * @author Manuela Hopp
 */
public abstract class Publication {
	
	Integer id;
	String note;
	String key;	
	int weight = 0; // for sorting purposes
	
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
	 * If the subclass has editors, then the list of editors should be returned.
	 * Otherwise, the return value is null.
	 */
	public ArrayList<String> getEditors(){
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
	
	/*
	 * the following methods do not do anything and are just here for convenience.
	 * if the respective fields exist in the subclass, they will be overwritten 
	 */
	public void setTitle(String s) {}
	public void setBooktitle(String s) {}
	public void setAuthors(ArrayList<String> al) {}
	public void setEditors(ArrayList<String> al) {}
	public void setJournal(String string) {}
	public void setYear(Integer i) {}
	public void setVolume(Integer i) {}
	public void setNumber(Integer i) {}
	public void setPages(Integer first, Integer last) {}
	public void setMonth(String s) {}
	public void setPublisher(String s) {}
	public void setSeries(String s) {}
	public void setAddress(String s) {}
	public void setEdition(String s) {}
	public void setURL(String s) {}
	public void setHowpublished(String s) {}
	public void setChapter(Integer i) {}
	public void setPType(String s) {}
	public void setOrganization(String s) {}
	public void setSchool(String s) {}
	public void setInstitution(String s) {}

	
	public String getAuthorString() {
		String s;
		if (getAuthors() == null || getAuthors().isEmpty()) {
			return s = "";
		}
		s = getAuthors().get(0);
		for (int i = 1; i < getAuthors().size(); i++) {
			s = s + " and " + getAuthors().get(i);
		}
		
		return s;
	}
	
	public String getEditorString() {
		String s;
		if (getEditors() == null || getEditors().isEmpty()) {
			return s = "";
		} 	
		s = getEditors().get(0);
		for (int i = 1; i < getEditors().size(); i++) {
			s = s + " and " + getEditors().get(i);
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

	public void setWeight(int w) {
		weight = w;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void incrementWeight() {
		weight++;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Publication)) return false;
		if (((Publication) o).getId() == this.getId() 
				&& ((Publication) o).getType().equals(this.getType()))
			return true;
		return false;
		
	}
}
