package gui;

import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import pub.Bookstack;

public class Bookstacks extends JPanel {
	
	JList<Bookstack> list;
    private static final long serialVersionUID = 1L;
	
	Bookstacks(){
		super();
		Vector<Bookstack> bkstk = new Vector<Bookstack>();
		list = new JList(bkstk);
		list.setModel(new DefaultListModel());
	}

}