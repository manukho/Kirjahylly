package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PubAdd extends JPanel implements ActionListener {
	
	String[] pubClasses;
	String pubClassSel;
	JPanel panel;
	GridBagConstraints c;
	
	private JComboBox<String> pubClassList;
	
	PubAdd(){
		Dimension dim = new Dimension(500,500);
		setPreferredSize(dim);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JPanel classListPanel = new JPanel();
		pubClasses = new String[] {"article", "book", "booklet", "inbook", "incollection", "inproceedings", "manual", "mastersthesis", "misc", "phdthesis", "proceedings", "techreport", "unpublished"};
		pubClassList = new JComboBox<String>(pubClasses);
		pubClassList.setPreferredSize(new Dimension(100,25));
		pubClassList.setSelectedIndex(0);
		pubClassList.addActionListener(this);
		classListPanel.add(pubClassList);
		add(classListPanel);
		
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTH;
		c.ipadx = 5;
		c.ipady = 5;
		
		add(panel);
		
		showArticle();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		pubClassSel = (String) pubClassList.getSelectedItem();
		System.out.println(pubClassSel);
		if (pubClassSel.equals("article")) showArticle();
		if (pubClassSel.equals("book")) showBook();
		if (pubClassSel.equals("booklet")) showBooklet();
	}
	
	private void showArticle() {
		panel.removeAll();
		panel.repaint();		
		
		Dimension dim = new Dimension(350,25);
		
		JLabel titleL = new JLabel("Title: ");
		JTextField titleF = new JTextField();
		titleF.setPreferredSize(dim);
	    c.weightx = 0; c.gridx = 0; c.gridy = 0; c.insets = new Insets(0,0,0,0);
		panel.add(titleL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = 0;
		panel.add(titleF, c);
		
		JLabel authorL = new JLabel("Author(s): ");
		JTextField authorF = new JTextField();
		authorF.setPreferredSize(dim);
		c.weightx = 0; c.gridx = 0; c.gridy = 1;
		panel.add(authorL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = 1;
		panel.add(authorF, c);
		
		JLabel journalL = new JLabel("Journal: ");
		JTextField journalF = new JTextField();
		journalF.setPreferredSize(dim);
		c.weightx = 0; c.gridx = 0; c.gridy = 2;
		panel.add(journalL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = 2;
		panel.add(journalF, c);
		
		JLabel yearL = new JLabel("Year: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 3;
		panel.add(yearL, c);
		JTextField yearF = new JTextField(Year.now().toString());
		yearF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 3;
		panel.add(yearF, c);
		
		JLabel volumeL = new JLabel("Volume: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 4;
		panel.add(volumeL, c);
		JTextField volumeF = new JTextField();
		volumeF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 4;
		panel.add(volumeF, c);
		
		// now the optional fields
		c.insets = new Insets(10,0,0,0);
		
		JLabel numberL = new JLabel("Number: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 5;
		panel.add(numberL, c);
		JTextField numberF = new JTextField();
		numberF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 5;
		panel.add(numberF, c);
		
		c.insets = new Insets(0,0,0,0);
		
		JLabel pagesL = new JLabel("Pages: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 6;
		panel.add(pagesL, c);
		JPanel pagesPanel = new JPanel();
		JTextField pagesF1 = new JTextField();
		pagesF1.setPreferredSize(new Dimension(50,25));
		pagesPanel.add(pagesF1);
		JLabel minus = new JLabel(" - ");
		pagesPanel.add(minus);
		JTextField pagesF2 = new JTextField();
		pagesF2.setPreferredSize(new Dimension(50,25));
		pagesPanel.add(pagesF2);
		c.weightx = 1; c.gridx = 1; c.gridy = 6;
		panel.add(pagesPanel, c);
		
		JLabel monthL = new JLabel("Month: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 7;
		panel.add(monthL, c);
		JTextField monthF = new JTextField();
		monthF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 7;
		panel.add(monthF, c);
		
		JLabel keyL = new JLabel("Key: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 8;
		panel.add(keyL, c);
		JTextField keyF = new JTextField();
		keyF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 8;
		panel.add(keyF, c);
		
		JLabel noteL = new JLabel("Note: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 9; c.insets = new Insets(5,0,0,0);
		panel.add(noteL, c);
		JTextArea noteF = new JTextArea();
		noteF.setPreferredSize(new Dimension(200,50));
		c.weightx = 1; c.gridx = 1; c.gridy = 9;
		panel.add(noteF, c);
		
		revalidate();
	}
	
	private void showBook() {
		panel.removeAll();
		panel.repaint();
		Dimension dim = new Dimension(200,25);
		
		JLabel titleL = new JLabel("Title: ");
		JTextField titleF = new JTextField();
		titleF.setPreferredSize(dim);
	    c.weightx = 0; c.gridx = 0; c.gridy = 0;
		panel.add(titleL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = 0;
		panel.add(titleF, c);
		
		String[] authed = {"Author(s)", "Editor(s)"};
		JComboBox<String> aeBox = new JComboBox<String>(authed);
		aeBox.setPreferredSize(new Dimension(90, 23));
		aeBox.setSelectedIndex(0);
	    c.weightx = 0; c.gridx = 0; c.gridy = 1; c.insets = new Insets(0,0,0,5);
		panel.add(aeBox, c);
		JTextField authedF = new JTextField();
		authedF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 1; c.insets = new Insets(0,0,0,0);
		panel.add(authedF, c);
		
		JLabel publL = new JLabel("Publisher: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 2;
		panel.add(publL, c);
		JTextField publF = new JTextField();
		publF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 2;
		panel.add(publF, c);
		
		JLabel yearL = new JLabel("Year: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 3;
		panel.add(yearL, c);
		JTextField yearF = new JTextField(Year.now().toString());
		yearF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 3;
		panel.add(yearF, c);
		
		// now the optional fields
		c.insets = new Insets(10,0,0,0);
		
		String[] volnum = {"Volume", "Number"};
		JComboBox<String> vnBox = new JComboBox<String>(volnum);
		vnBox.setPreferredSize(new Dimension(90, 23));
		vnBox.setSelectedIndex(0);
	    c.weightx = 0; c.gridx = 0; c.gridy = 4; c.insets = new Insets(0,0,0,5);
		panel.add(vnBox, c);
		JTextField volNumF = new JTextField();
		volNumF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 4; c.insets = new Insets(0,0,0,0);
		panel.add(volNumF, c);
		
		JLabel seriesL = new JLabel("Series: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 5;
		panel.add(seriesL, c);
		JTextField seriesF = new JTextField();
		seriesF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 5;
		panel.add(seriesF, c);
		
		JLabel addressL = new JLabel("Address: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 6;
		panel.add(addressL, c);
		JTextField addressF = new JTextField();
		addressF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 6;
		panel.add(addressF, c);
		
		JLabel editionL = new JLabel("Edition: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 7;
		panel.add(editionL, c);
		JTextField editionF = new JTextField();
		editionF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 7;
		panel.add(editionF, c);
		
		JLabel monthL = new JLabel("Month: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 8;
		panel.add(monthL, c);
		JTextField monthF = new JTextField();
		monthF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 8;
		panel.add(monthF, c);

		JLabel urlL = new JLabel("URL: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 9;
		panel.add(urlL, c);
		JTextField urlF = new JTextField();
		urlF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 9;
		panel.add(urlF, c);
		
		JLabel keyL = new JLabel("Key: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 10;
		panel.add(keyL, c);
		JTextField keyF = new JTextField();
		keyF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 10;
		panel.add(keyF, c);
		
		JLabel noteL = new JLabel("Note: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 11; c.insets = new Insets(5,0,0,0);
		panel.add(noteL, c);
		JTextArea noteF = new JTextArea();
		noteF.setPreferredSize(new Dimension(200,50));
		c.weightx = 1; c.gridx = 1; c.gridy = 11;
		panel.add(noteF, c);
		
		revalidate();
	}

	private void showBooklet() {
		panel.removeAll();
		panel.repaint();
		Dimension dim = new Dimension(200,25);
		
		JLabel titleL = new JLabel("Title: ");
		JTextField titleF = new JTextField();
		titleF.setPreferredSize(dim);
	    c.weightx = 0; c.gridx = 0; c.gridy = 0;
		panel.add(titleL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = 0;
		panel.add(titleF, c);
		
		// now the optional fields
		c.insets = new Insets(10,0,0,0);
		
		// note, key
		JLabel authorL = new JLabel("Author(s): ");
		JTextField authorF = new JTextField();
		authorF.setPreferredSize(dim);
		c.weightx = 0; c.gridx = 0; c.gridy = 1;
		panel.add(authorL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = 1;
		panel.add(authorF, c);
		
		JLabel hpL = new JLabel("How published: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 2; c.insets = new Insets(0,0,0,0);
		panel.add(hpL, c);
		JTextField hpF = new JTextField();
		hpF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 2;
		panel.add(hpF, c);
		
		JLabel addressL = new JLabel("Address: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 3;
		panel.add(addressL, c);
		JTextField addressF = new JTextField();
		addressF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 3;
		panel.add(addressF, c);
		
		JLabel monthL = new JLabel("Month: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 4;
		panel.add(monthL, c);
		JTextField monthF = new JTextField();
		monthF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 4;
		panel.add(monthF, c);
		
		JLabel yearL = new JLabel("Year: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 5;
		panel.add(yearL, c);
		JTextField yearF = new JTextField(Year.now().toString());
		yearF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 5;
		panel.add(yearF, c);
		
		JLabel keyL = new JLabel("Key: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 6;
		panel.add(keyL, c);
		JTextField keyF = new JTextField();
		keyF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 6;
		panel.add(keyF, c);
		
		JLabel noteL = new JLabel("Note: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 7; c.insets = new Insets(5,0,0,0);
		panel.add(noteL, c);
		JTextArea noteF = new JTextArea();
		noteF.setPreferredSize(new Dimension(200,50));
		c.weightx = 1; c.gridx = 1; c.gridy = 7;
		panel.add(noteF, c);
		
		revalidate();
	}
	
}
