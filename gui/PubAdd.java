package gui;

import java.awt.Component;
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
		Dimension dim = new Dimension(600,600);
		setPreferredSize(dim);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JPanel classListPanel = new JPanel();
		pubClasses = new String[] {"article", "book", "booklet", "inbook", "incollection", "inproceedings", "manual", "mastersthesis", "misc", "phdthesis", "proceedings", "techreport", "unpublished"};
		pubClassList = new JComboBox<String>(pubClasses);
		pubClassList.setPreferredSize(new Dimension(150,25));
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
		if (pubClassSel.equals("article")) showArticle();
		if (pubClassSel.equals("book")) showBook();
		if (pubClassSel.equals("booklet")) showBooklet();
		if (pubClassSel.equals("inbook")) showInbook();
		if (pubClassSel.equals("incollection")) showIncollection();
		if (pubClassSel.equals("inproceedings")) showInproceedings();
		if (pubClassSel.equals("manual")) showManual();
		if (pubClassSel.equals("mastersthesis")) showMastersthesis();
		if (pubClassSel.equals("misc")) showMisc();
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
	
	private void showInbook() {
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
		
		String[] chapa = {"Chapter", "Pages"};
		JComboBox<String> cpBox = new JComboBox<String>(chapa);
		JPanel cpPanel = new JPanel();
		cpPanel.setPreferredSize(dim);
		JTextField chapF = new JTextField();
		chapF.setPreferredSize(dim);
		cpPanel.add(chapF);
		cpBox.setPreferredSize(new Dimension(90, 23));
		cpBox.setSelectedIndex(0);
		cpBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.weightx = 1; c.gridx = 1; c.gridy = 2; c.insets = new Insets(0,0,0,0);
				int sel = cpBox.getSelectedIndex();
				if (containsComponent(panel, cpPanel)) panel.remove(cpPanel);
				if (containsComponent(panel, chapF)) panel.remove(chapF);
				if (sel == 0) { // chapter
					panel.add(chapF, c);
				} else { // pages
					if (cpPanel.getComponentCount() == 0){
						JTextField pagesF1 = new JTextField();
						pagesF1.setPreferredSize(new Dimension(50,25));
						cpPanel.add(pagesF1);
						JLabel minus = new JLabel(" - ");
						cpPanel.add(minus);
						JTextField pagesF2 = new JTextField();
						pagesF2.setPreferredSize(new Dimension(50,25));
						cpPanel.add(pagesF2);
						}
					panel.add(cpPanel, c);
				}
				panel.revalidate();
				panel.repaint();
			}
		});
	    c.weightx = 0; c.gridx = 0; c.gridy = 2; c.insets = new Insets(0,0,0,5);
		panel.add(cpBox, c);
		c.weightx = 1; c.gridx = 1; c.gridy = 2; c.insets = new Insets(0,0,0,0);
		panel.add(chapF, c);
		
		JLabel publL = new JLabel("Publisher: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 3;
		panel.add(publL, c);
		JTextField publF = new JTextField();
		publF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 3;
		panel.add(publF, c);
		
		JLabel yearL = new JLabel("Year: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 4;
		panel.add(yearL, c);
		JTextField yearF = new JTextField(Year.now().toString());
		yearF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 4;
		panel.add(yearF, c);
		
		// now the optional fields
		c.insets = new Insets(10,0,0,0);
		
		String[] volnum = {"Volume", "Number"};
		JComboBox<String> vnBox = new JComboBox<String>(volnum);
		vnBox.setPreferredSize(new Dimension(90, 23));
		vnBox.setSelectedIndex(0);
	    c.weightx = 0; c.gridx = 0; c.gridy = 5; c.insets = new Insets(0,0,0,5);
		panel.add(vnBox, c);
		JTextField volNumF = new JTextField();
		volNumF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 5; c.insets = new Insets(0,0,0,0);
		panel.add(volNumF, c);
		
		JLabel seriesL = new JLabel("Series: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 6;
		panel.add(seriesL, c);
		JTextField seriesF = new JTextField();
		seriesF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 6;
		panel.add(seriesF, c);
		
		JLabel typeL = new JLabel("Type: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 7;
		panel.add(typeL, c);
		JTextField typeF = new JTextField();
		typeF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 7;
		panel.add(typeF, c);
		
		JLabel addressL = new JLabel("Address: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 8;
		panel.add(addressL, c);
		JTextField addressF = new JTextField();
		addressF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 8;
		panel.add(addressF, c);

		JLabel editionL = new JLabel("Edition: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 9;
		panel.add(editionL, c);
		JTextField editionF = new JTextField();
		editionF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 9;
		panel.add(editionF, c);
		
		JLabel monthL = new JLabel("Month: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 10;
		panel.add(monthL, c);
		JTextField monthF = new JTextField();
		monthF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 10;
		panel.add(monthF, c);
		
		JLabel keyL = new JLabel("Key: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 11;
		panel.add(keyL, c);
		JTextField keyF = new JTextField();
		keyF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 11;
		panel.add(keyF, c);
		
		JLabel noteL = new JLabel("Note: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 12; c.insets = new Insets(5,0,0,0);
		panel.add(noteL, c);
		JTextArea noteF = new JTextArea();
		noteF.setPreferredSize(new Dimension(200,50));
		c.weightx = 1; c.gridx = 1; c.gridy = 12;
		panel.add(noteF, c);
		
		revalidate();
	}
	
	private void showIncollection() {
		panel.removeAll();
		panel.repaint();
		Dimension dim = new Dimension(200,25);
		
		JLabel titleL = new JLabel("Title: ");
		JTextField titleF = new JTextField();
		titleF.setPreferredSize(dim);
	    c.weightx = 0; c.gridx = 0; c.gridy = 0; c.insets = new Insets(0,0,0,0);
		panel.add(titleL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = 0;
		panel.add(titleF, c);
		
		JLabel booktitleL = new JLabel("Booktitle: ");
	    c.weightx = 0; c.gridx = 0; c.gridy = 1;
		panel.add(booktitleL, c);
		JTextField booktitleF = new JTextField();
		booktitleF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 1;
		panel.add(booktitleF, c);
		
		JLabel authorL = new JLabel("Author(s): ");
		JTextField authorF = new JTextField();
		authorF.setPreferredSize(dim);
		c.weightx = 0; c.gridx = 0; c.gridy = 2;
		panel.add(authorL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = 2;
		panel.add(authorF, c);
		
		JLabel publL = new JLabel("Publisher: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 3;
		panel.add(publL, c);
		JTextField publF = new JTextField();
		publF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 3;
		panel.add(publF, c);
		
		JLabel yearL = new JLabel("Year: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 4;
		panel.add(yearL, c);
		JTextField yearF = new JTextField(Year.now().toString());
		yearF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 4;
		panel.add(yearF, c);
		
		// now the optional fields
		c.insets = new Insets(10,0,0,0);
		
		JLabel edL = new JLabel("Editor: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 5;
		panel.add(edL, c);
		JTextField edF = new JTextField();
		edF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 5;
		panel.add(edF, c);
		
		String[] volnum = {"Volume", "Number"};
		JComboBox<String> vnBox = new JComboBox<String>(volnum);
		vnBox.setPreferredSize(new Dimension(90, 23));
		vnBox.setSelectedIndex(0);
	    c.weightx = 0; c.gridx = 0; c.gridy = 6; c.insets = new Insets(0,0,0,5);
		panel.add(vnBox, c);
		JTextField volNumF = new JTextField();
		volNumF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 6; c.insets = new Insets(0,0,0,0);
		panel.add(volNumF, c);
		
		JLabel seriesL = new JLabel("Series: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 7;
		panel.add(seriesL, c);
		JTextField seriesF = new JTextField();
		seriesF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 7;
		panel.add(seriesF, c);
		
		JLabel typeL = new JLabel("Type: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 8;
		panel.add(typeL, c);
		JTextField typeF = new JTextField();
		typeF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 8;
		panel.add(typeF, c);
		
		JLabel chapL = new JLabel("Chapter: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 9;
		panel.add(chapL, c);
		JTextField chapF = new JTextField();
		chapF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 9;
		panel.add(chapF, c);
		
		JLabel pagesL = new JLabel("Pages: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 10;
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
		c.weightx = 1; c.gridx = 1; c.gridy = 10;
		panel.add(pagesPanel, c);
		
		JLabel addressL = new JLabel("Address: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 11;
		panel.add(addressL, c);
		JTextField addressF = new JTextField();
		addressF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 11;
		panel.add(addressF, c);
		
		JLabel editionL = new JLabel("Edition: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 12;
		panel.add(editionL, c);
		JTextField editionF = new JTextField();
		editionF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 12;
		panel.add(editionF, c);
		
		JLabel monthL = new JLabel("Month: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 13;
		panel.add(monthL, c);
		JTextField monthF = new JTextField();
		monthF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 13;
		panel.add(monthF, c);
		
		JLabel keyL = new JLabel("Key: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 14;
		panel.add(keyL, c);
		JTextField keyF = new JTextField();
		keyF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 14;
		panel.add(keyF, c);
		
		JLabel noteL = new JLabel("Note: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 15; c.insets = new Insets(5,0,0,0);
		panel.add(noteL, c);
		JTextArea noteF = new JTextArea();
		noteF.setPreferredSize(new Dimension(200,50));
		c.weightx = 1; c.gridx = 1; c.gridy = 15;
		panel.add(noteF, c);
		
		revalidate();
	}
	
	private void showInproceedings() {
		panel.removeAll();
		panel.repaint();
		Dimension dim = new Dimension(200,25);
		
		JLabel titleL = new JLabel("Title: ");
		JTextField titleF = new JTextField();
		titleF.setPreferredSize(dim);
	    c.weightx = 0; c.gridx = 0; c.gridy = 0; c.insets = new Insets(0,0,0,0);
		panel.add(titleL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = 0;
		panel.add(titleF, c);
		
		JLabel booktitleL = new JLabel("Booktitle: ");
	    c.weightx = 0; c.gridx = 0; c.gridy = 1;
		panel.add(booktitleL, c);
		JTextField booktitleF = new JTextField();
		booktitleF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 1;
		panel.add(booktitleF, c);
		
		JLabel authorL = new JLabel("Author(s): ");
		JTextField authorF = new JTextField();
		authorF.setPreferredSize(dim);
		c.weightx = 0; c.gridx = 0; c.gridy = 2;
		panel.add(authorL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = 2;
		panel.add(authorF, c);
		
		JLabel yearL = new JLabel("Year: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 3;
		panel.add(yearL, c);
		JTextField yearF = new JTextField(Year.now().toString());
		yearF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 3;
		panel.add(yearF, c);
		

		// now the optional fields
		c.insets = new Insets(10,0,0,0);
		
		JLabel edL = new JLabel("Editor: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 4;
		panel.add(edL, c);
		JTextField edF = new JTextField();
		edF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 4;
		panel.add(edF, c);
		
		String[] volnum = {"Volume", "Number"};
		JComboBox<String> vnBox = new JComboBox<String>(volnum);
		vnBox.setPreferredSize(new Dimension(90, 23));
		vnBox.setSelectedIndex(0);
	    c.weightx = 0; c.gridx = 0; c.gridy = 5; c.insets = new Insets(0,0,0,5);
		panel.add(vnBox, c);
		JTextField volNumF = new JTextField();
		volNumF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 5; c.insets = new Insets(0,0,0,0);
		panel.add(volNumF, c);
		
		JLabel seriesL = new JLabel("Series: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 6;
		panel.add(seriesL, c);
		JTextField seriesF = new JTextField();
		seriesF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 6;
		panel.add(seriesF, c);
		
		JLabel pagesL = new JLabel("Pages: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 7;
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
		c.weightx = 1; c.gridx = 1; c.gridy = 7;
		panel.add(pagesPanel, c);

		JLabel addressL = new JLabel("Address: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 8;
		panel.add(addressL, c);
		JTextField addressF = new JTextField();
		addressF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 8;
		panel.add(addressF, c);
		
		JLabel monthL = new JLabel("Month: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 9;
		panel.add(monthL, c);
		JTextField monthF = new JTextField();
		monthF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 9;
		panel.add(monthF, c);
		
		JLabel orgL = new JLabel("Organization: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 10;
		panel.add(orgL, c);
		JTextField orgF = new JTextField();
		orgF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 10;
		panel.add(orgF, c);
		
		JLabel publL = new JLabel("Publisher: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 11;
		panel.add(publL, c);
		JTextField publF = new JTextField();
		publF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 11;
		panel.add(publF, c);
		
		JLabel keyL = new JLabel("Key: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 12;
		panel.add(keyL, c);
		JTextField keyF = new JTextField();
		keyF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 12;
		panel.add(keyF, c);
		
		JLabel noteL = new JLabel("Note: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 13; c.insets = new Insets(5,0,0,0);
		panel.add(noteL, c);
		JTextArea noteF = new JTextArea();
		noteF.setPreferredSize(new Dimension(200,50));
		c.weightx = 1; c.gridx = 1; c.gridy = 13;
		panel.add(noteF, c);
		
		panel.revalidate();
	}
	
	private void showManual() {
		panel.removeAll();
		panel.repaint();
		Dimension dim = new Dimension(200,25);
		
		JLabel titleL = new JLabel("Title: ");
		JTextField titleF = new JTextField();
		titleF.setPreferredSize(dim);
	    c.weightx = 0; c.gridx = 0; c.gridy = 0; c.insets = new Insets(0,0,0,0);
		panel.add(titleL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = 0;
		panel.add(titleF, c);
		
		// now the optional fields
		c.insets = new Insets(10,0,0,0);
		
		JLabel authorL = new JLabel("Author(s): ");
		JTextField authorF = new JTextField();
		authorF.setPreferredSize(dim);
		c.weightx = 0; c.gridx = 0; c.gridy = 1;
		panel.add(authorL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = 1;
		panel.add(authorF, c);
		
		JLabel orgL = new JLabel("Organization: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 2; c.insets = new Insets(0,0,0,0);
		panel.add(orgL, c);
		JTextField orgF = new JTextField();
		orgF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 2;
		panel.add(orgF, c);
		
		JLabel addressL = new JLabel("Address: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 3;
		panel.add(addressL, c);
		JTextField addressF = new JTextField();
		addressF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 3;
		panel.add(addressF, c);
		
		JLabel editionL = new JLabel("Edition: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 4;
		panel.add(editionL, c);
		JTextField editionF = new JTextField();
		editionF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 4;
		panel.add(editionF, c);
		
		JLabel monthL = new JLabel("Month: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 5;
		panel.add(monthL, c);
		JTextField monthF = new JTextField();
		monthF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 5;
		panel.add(monthF, c);
		
		JLabel yearL = new JLabel("Year: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 6;
		panel.add(yearL, c);
		JTextField yearF = new JTextField(Year.now().toString());
		yearF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 6;
		panel.add(yearF, c);
		
		JLabel keyL = new JLabel("Key: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 7;
		panel.add(keyL, c);
		JTextField keyF = new JTextField();
		keyF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 7;
		panel.add(keyF, c);
		
		JLabel noteL = new JLabel("Note: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 8; c.insets = new Insets(5,0,0,0);
		panel.add(noteL, c);
		JTextArea noteF = new JTextArea();
		noteF.setPreferredSize(new Dimension(200,50));
		c.weightx = 1; c.gridx = 1; c.gridy = 8;
		panel.add(noteF, c);
		
		panel.revalidate();
	}
	
	private void showMastersthesis() {
		panel.removeAll();
		panel.repaint();
		Dimension dim = new Dimension(200,25);
		
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
		
		JLabel schoolL = new JLabel("School: ");
	    c.weightx = 0; c.gridx = 0; c.gridy = 2;
		panel.add(schoolL, c);
		JTextField schoolF = new JTextField();
		schoolF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 2;
		panel.add(schoolF, c);
		
		JLabel yearL = new JLabel("Year: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 3;
		panel.add(yearL, c);
		JTextField yearF = new JTextField(Year.now().toString());
		yearF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 3;
		panel.add(yearF, c);
		
		// now the optional fields
		c.insets = new Insets(10,0,0,0);
		
		JLabel typeL = new JLabel("Type: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 4;
		panel.add(typeL, c);
		JTextField typeF = new JTextField();
		typeF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 4;
		panel.add(typeF, c);
		
		JLabel addressL = new JLabel("Address: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 5; c.insets = new Insets(0,0,0,0);
		panel.add(addressL, c);
		JTextField addressF = new JTextField();
		addressF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 5;
		panel.add(addressF, c);

		JLabel monthL = new JLabel("Month: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 6;
		panel.add(monthL, c);
		JTextField monthF = new JTextField();
		monthF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 6;
		panel.add(monthF, c);
		
		JLabel keyL = new JLabel("Key: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 7;
		panel.add(keyL, c);
		JTextField keyF = new JTextField();
		keyF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 7;
		panel.add(keyF, c);
		
		JLabel noteL = new JLabel("Note: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 8; c.insets = new Insets(5,0,0,0);
		panel.add(noteL, c);
		JTextArea noteF = new JTextArea();
		noteF.setPreferredSize(new Dimension(200,50));
		c.weightx = 1; c.gridx = 1; c.gridy = 8;
		panel.add(noteF, c);
		
		revalidate();
	}
	
	private void showMisc() {
		panel.removeAll();
		panel.repaint();
		Dimension dim = new Dimension(200,25);
		
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
		
		JLabel hpL = new JLabel("How published: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 2;
		panel.add(hpL, c);
		JTextField hpF = new JTextField();
		hpF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 2;
		panel.add(hpF, c);
		
		JLabel monthL = new JLabel("Month: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 3;
		panel.add(monthL, c);
		JTextField monthF = new JTextField();
		monthF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 3;
		panel.add(monthF, c);
		
		JLabel yearL = new JLabel("Year: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 4;
		panel.add(yearL, c);
		JTextField yearF = new JTextField(Year.now().toString());
		yearF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 4;
		panel.add(yearF, c);
		
		JLabel keyL = new JLabel("Key: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 5;
		panel.add(keyL, c);
		JTextField keyF = new JTextField();
		keyF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = 5;
		panel.add(keyF, c);
		
		JLabel noteL = new JLabel("Note: ");
		c.weightx = 0; c.gridx = 0; c.gridy = 6; c.insets = new Insets(5,0,0,0);
		panel.add(noteL, c);
		JTextArea noteF = new JTextArea();
		noteF.setPreferredSize(new Dimension(200,50));
		c.weightx = 1; c.gridx = 1; c.gridy = 6;
		panel.add(noteF, c);
		
		revalidate();
	}
	
	private boolean containsComponent(JPanel p, Component c) {
		Component[] comps = p.getComponents();
		for (Component comp : comps) {
			if (c == comp) {
				return true;
			}
		}
		return false;
	}
	
}
