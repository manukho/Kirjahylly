package pub;

public class Person {
	
	int id;
	String name;
	
	Person(String s){
		name = s;
		id = LitManagement.getLitManagementInstance().getPersID();
	}
	
	Person(String s, int persID){
		name = s;
		id = persID;
	}
	
	void setPersID(int persID) {
		id = persID;
	}

}
