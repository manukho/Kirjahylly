package gui;

import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import lgc.Bookstack;

public class Bookstacks extends JPanel {
	
	JList<Bookstack> list;
	
	Bookstacks(){
		super();
		Vector<Bookstack> bkstk = new Vector<Bookstack>();
		list = new JList(bkstk);
		list.setModel(new DefaultListModel());
	}

}
