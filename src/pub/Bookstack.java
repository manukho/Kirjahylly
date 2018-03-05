package pub;

import java.io.File;
import java.util.ArrayList;

import gui.Kirjahylly;

public class Bookstack {
	
	String name;
	ArrayList<Publication> pubs;
	File f;
	
	public Bookstack(String n) {
		name = n;
		pubs = new ArrayList<Publication>();
		String dir = Kirjahylly.getKH().getDir() + File.separator + "stacks";
		new File(dir).mkdirs();		
		f = new File(dir + File.separator + name);
	}
	
	public void addPub(Publication p) {
		pubs.add(p);
	}

}
