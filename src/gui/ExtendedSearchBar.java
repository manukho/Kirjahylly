package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import db.DBManagement;
import pub.Publication;

public class ExtendedSearchBar extends JPanel {
	
    private static final long serialVersionUID = 1L;
	private Kirjahylly kh;
	private DBManagement dbm;
	private SearchResults sr;
	private JTextField titlef;
	private JTextField authorf;
	private JTextField yearf;
	

	public ExtendedSearchBar(Kirjahylly k) {
		super();
		kh = k;
		dbm = kh.getDBM();
		sr = SearchResults.getSR();
		setPreferredSize(new Dimension(1000, 100));
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(3,3,3,3);
		
		JLabel titlel = new JLabel("Title:");
		c.gridx = 0; c.gridy = 0;
		add(titlel, c);
		
		titlef = new JTextField();
		titlef.setPreferredSize(new Dimension(600, 25));
		c.gridx = 1; c.gridy = 0;
		add(titlef, c);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new SearchButtonListener());
		searchButton.setPreferredSize(new Dimension(90,25));
		c.gridx = 2; c.gridy = 0;
		add(searchButton, c);
		
		JButton normalSearchButton = new JButton("Normal Search");
		normalSearchButton.setPreferredSize(new Dimension(160, 25));
		normalSearchButton.addActionListener(new NormalSearchButtonListener());
		c.gridx = 3; c.gridy = 0;
		add(normalSearchButton, c);
		
		JLabel authorl = new JLabel("Author(s):");
		c.gridx = 0; c.gridy = 1;
		add(authorl, c);
		
		authorf = new JTextField();
		authorf.setPreferredSize(new Dimension(600, 25));
		c.gridx = 1; c.gridy = 1;
		add(authorf, c);
		
		JLabel yearl = new JLabel("Year:");
		c.gridx = 0; c.gridy = 2;
		add(yearl, c);

		yearf = new JTextField();
		yearf.setPreferredSize(new Dimension(600, 25));
		c.gridx = 1; c.gridy = 2;
		add(yearf, c);
	}
	
	private class SearchButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String auth_s = authorf.getText();
			String year_s = yearf.getText();
			String title_s = titlef.getText();
			String title = null;
			String author = null;
			Integer year = null;
			if (!title_s.isEmpty()) title = title_s;
			if (!auth_s.isEmpty()) author = auth_s;
			if (!year_s.isEmpty() && isNumeric(year_s)) year = Integer.parseInt(year_s);
			
			ArrayList<Publication> list = dbm.search(title, author, year);
			
			sr = SearchResults.getSR();
			
			sr.clear();
			sr.addRows(list);
		}
	}
	
	private class NormalSearchButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			SearchBar sb = new SearchBar(kh);
			kh.setSearchBar(sb);
			
		} 
		
	}
	
	@SuppressWarnings("unused")
	public static boolean isNumeric(String s){  
	  try {  
	    int i = Integer.parseInt(s);  
	  }  
	  catch(NumberFormatException e) {  
	    return false;  
	  }  
	  return true;  
	}
}
