package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import db.DBManagement;
import pub.Publication;

/**
 * Searchbar is the Panel showing the search bar.
 * clicking on "Extended Search" switches it for the ExtendedSearchBar panel.
 *
 * @author Manuela Hopp
 */
public class SearchBar extends JPanel {
	
    private static final long serialVersionUID = 1L;
	Kirjahylly kh;
	SearchBar sb;
	ExtendedSearchBar esb;
	private DBManagement dbm;
	private SearchResults sr;	
	JTextField searchField;
	
	SearchBar(Kirjahylly k){
		super();
		sb = this;
		kh = k;
		dbm = kh.getDBM();
		sr = kh.getSR();
		setSize(1000, 30);
		setLayout(new FlowLayout());
		searchField = new JTextField();
		searchField.setPreferredSize(new Dimension(600, 25));
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new SearchButtonListener());
		searchButton.setPreferredSize(new Dimension(90,25));
		JButton extendedSearchButton = new JButton("Extended Search");
		extendedSearchButton.setPreferredSize(new Dimension(160, 25));
		extendedSearchButton.addActionListener(new ExtendedSearchButtonListener());
		add(searchField);
		add(searchButton);
		add(extendedSearchButton);
	}
	
	private class SearchButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String text = searchField.getText();
			if (text.isEmpty()) {
				text = null;
			}
			
			ArrayList<Publication> list = dbm.searchAll(text);
			
			sr = kh.getSR();
			
			sr.clear();
			sr.addRows(list);
		}
	}
	
	private class ExtendedSearchButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			esb = new ExtendedSearchBar(kh);
			kh.setSearchBar(esb);
		}
	}
}
