package pub;

import java.io.File;
import java.util.Vector;

import gui.Kirjahylly;

public class Bookstack {
	
	String name;
	Vector<Publication> pubs;
	File f;
	
	public Bookstack(String n) {
		name = n;
		pubs = new Vector<Publication>();
		String dir = Kirjahylly.getKH().getDir() + File.separator + "stacks";
		new File(dir).mkdirs();		
		f = new File(dir + File.separator + name);
	}

}