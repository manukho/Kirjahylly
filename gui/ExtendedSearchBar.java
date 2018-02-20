package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ExtendedSearchBar extends JPanel {
	
	Kirjahylly kh;

	public ExtendedSearchBar(Kirjahylly k) {
		super();
		kh = k;
		setPreferredSize(new Dimension(1000, 100));
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(3,3,3,3);
		
		JLabel titlel = new JLabel("Title:");
		c.gridx = 0; c.gridy = 0;
		add(titlel, c);
		
		JTextField titlef = new JTextField();
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
		
		JTextField authorf = new JTextField();
		authorf.setPreferredSize(new Dimension(600, 25));
		c.gridx = 1; c.gridy = 1;
		add(authorf, c);
		
		JLabel yearl = new JLabel("Year:");
		c.gridx = 0; c.gridy = 2;
		add(yearl, c);

		JTextField yearf = new JTextField();
		yearf.setPreferredSize(new Dimension(600, 25));
		c.gridx = 1; c.gridy = 2;
		add(yearf, c);
	}
	
	private class SearchButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
		}
	}
	
	private class NormalSearchButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			SearchBar sb = new SearchBar(kh);
			kh.setSearchBar(sb);
			
		} 
		
	}
}
