package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchBar extends JPanel {
	
	Kirjahylly kh;
	SearchBar sb;
	ExtendedSearchBar esb;
	
	SearchBar(Kirjahylly k){
		super();
		sb = this;
		kh = k;
		setSize(1000, 30);
		setLayout(new FlowLayout());
		JTextField searchField = new JTextField();
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
			/* TODO: do something */
			System.out.println("Search button clicked");
		}
	}
	
	private class ExtendedSearchButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			/* TODO: do something */
			System.out.println("Extended Search button clicked");
			esb = new ExtendedSearchBar(kh);
			kh.setSearchBar(esb);
		}
	}
}
