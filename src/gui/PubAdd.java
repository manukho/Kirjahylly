package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import db.DBManagement;
import pub.Article;
import pub.Book;
import pub.Booklet;
import pub.Inbook;
import pub.Incollection;
import pub.Publication;

/**
 * @author Manuela Hopp
 *
 */
public class PubAdd extends JPanel implements ActionListener {
	
    private static final long serialVersionUID = 1L;
	
    boolean modify;
    int id;
	String[] pubClasses;
	String pubClassSel;
	JPanel panel;
	GridBagConstraints c;
	private Dimension dim;
	private JComboBox<String> pubClassList;
	private JTextField titleF;
	private JTextField authorF;
	private JTextField journalF;
	private JTextField yearF;
	private JTextField volumeF;
	private JTextField numberF;
	private JPanel pagesPanel;
	private JTextField pagesF1;
	private JTextField pagesF2;
	private JTextField keyF;
	private JTextArea noteF;
	private JTextField schoolF;
	private JComboBox<String> aeBox;
	private JTextField authedF;
	private JTextField addressF;
	private JTextField publF;
	private JTextField booktitleF;
	private JComboBox<String> vnBox;
	private JTextField volNumF;
	private JTextField seriesF;
	private JTextField editionF;
	private JTextField urlF;
	private JTextField hpF;
	private JTextField chapF;
	private JComboBox<String> cpBox;
	private JTextField typeF;
	private JTextField edF;
	private JTextField orgF;
	private JTextField instF;
	private JComboBox<String> monthBox;
	private String[] months = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	
	/**
	 * constructor for the form for adding a new bibliographic item
	 */
	PubAdd(){
		modify = false;
		Dimension d = new Dimension(600,600);
		setPreferredSize(d);
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
		dim = new Dimension(350,25);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTH;
		c.ipadx = 5;
		c.ipady = 5;
		
		add(panel);
		
		showArticle();
	}
	
	
	/**
	 * constructor for the form for modifying an existing bibliographic item
	 * @param the publication to be modified
	 */
	PubAdd(Publication p){
		modify = true;
		id = p.getId();
		pubClassSel = p.getType();
		panel = this;		
		Dimension d = new Dimension(600,600);
		setPreferredSize(d);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		dim = new Dimension(350,25);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTH;
		c.ipadx = 5;
		c.ipady = 5;
		
		if (p.getType().equals("article")) {
			Article a = (Article) p;
			showArticle();
			titleF.setText(a.getTitle());
			authorF.setText(a.getAuthorString());
			journalF.setText(a.getJournal());
			yearF.setText(a.getYearString());
			volumeF.setText(a.getVolume().toString());
			if (a.getNumber() != null) numberF.setText(a.getNumber().toString());
			if (a.getFirstPage() != null) pagesF1.setText(a.getFirstPage().toString());
			if (a.getFirstPage() != null) pagesF2.setText(a.getLastPage().toString());
			if (a.getMonth() != null && !a.getMonth().isEmpty()) monthBox.setSelectedIndex(getMonthIndex(a.getMonth()));
			if (a.getNote() != null && !a.getNote().isEmpty()) noteF.setText(a.getNote());
			if (a.getKey() != null && !a.getKey().isEmpty()) keyF.setText(a.getKey());
		}
		if (p.getType().equals("book")) {
			Book b = (Book) p;
			showBook();
			titleF.setText(b.getTitle());
			if (!b.getAuthors().isEmpty()) {
				aeBox.setSelectedIndex(0);
				authedF.setText(b.getAuthorString());
			} else {
				aeBox.setSelectedIndex(1);
				authedF.setText(b.getEditorString());
			}
			publF.setText(b.getPublisher());
			yearF.setText(p.getYearString());
			if (b.getVolume() != null) volumeF.setText(b.getVolume().toString());
			if (b.getNumber() != null) numberF.setText(b.getNumber().toString());
			if (b.getSeries() != null && !b.getSeries().isEmpty()) seriesF.setText(b.getSeries());
			if (b.getAddress() != null && !b.getAddress().isEmpty()) addressF.setText(b.getAddress());
			if (b.getEdition() != null && !b.getEdition().isEmpty()) editionF.setText(b.getEdition());
			if (b.getMonth() != null && !b.getMonth().isEmpty()) monthBox.setSelectedIndex(getMonthIndex(b.getMonth()));
			if (b.getUrl() != null && !b.getUrl().isEmpty()) urlF.setText(b.getUrl());
			if (b.getNote() != null && !b.getNote().isEmpty()) noteF.setText(b.getNote());
			if (b.getKey() != null && !b.getKey().isEmpty()) keyF.setText(b.getKey());
		}
		if (p.getType().equals("booklet")) {
			Booklet b = (Booklet) p;
			showBooklet();
			titleF.setText(b.getTitle());
			authorF.setText(b.getAuthorString());
			if (b.getHowpublished() != null && !b.getHowpublished().isEmpty()) hpF.setText(b.getHowpublished());
			if (b.getAddress() != null && !b.getAddress().isEmpty()) addressF.setText(b.getAddress());
			if (b.getMonth() != null && !b.getMonth().isEmpty()) monthBox.setSelectedIndex(getMonthIndex(b.getMonth()));
			if (b.getYear() != null) yearF.setText(b.getYearString());
			if (b.getNote() != null && !b.getNote().isEmpty()) noteF.setText(b.getNote());
			if (b.getKey() != null && !b.getKey().isEmpty()) keyF.setText(b.getKey());		
		}
		if (p.getType().equals("inbook")) {
			Inbook ib = (Inbook) p;
			showInbook();
			titleF.setText(ib.getTitle());
			if (!ib.getAuthors().isEmpty()) {
				aeBox.setSelectedIndex(0);
				authedF.setText(ib.getAuthorString());
			} else {
				aeBox.setSelectedIndex(1);
				authedF.setText(ib.getEditorString());
			}
			chapF.setText(ib.getChapter().toString());
			pagesF1.setText(ib.getFirstPage().toString());
			pagesF2.setText(ib.getLastPage().toString());
			publF.setText(ib.getPublisher());
			yearF.setText(ib.getYearString());
			if (ib.getVolume() != null) volumeF.setText(ib.getVolume().toString());
			if (ib.getNumber() != null) numberF.setText(ib.getNumber().toString());
			if (ib.getSeries() != null && !ib.getSeries().isEmpty()) seriesF.setText(ib.getSeries());
			if (ib.getIBType() != null) typeF.setText(ib.getIBType());
			if (ib.getAddress() != null && !ib.getAddress().isEmpty()) addressF.setText(ib.getAddress());
			if (ib.getEdition() != null && !ib.getEdition().isEmpty()) editionF.setText(ib.getEdition());
			if (ib.getMonth() != null && !ib.getMonth().isEmpty()) monthBox.setSelectedIndex(getMonthIndex(ib.getMonth()));
			if (ib.getNote() != null && !ib.getNote().isEmpty()) noteF.setText(ib.getNote());
			if (ib.getKey() != null && !ib.getKey().isEmpty()) keyF.setText(ib.getKey());		
		}
		if (p.getType().equals("incollection")) {
			Incollection ic = (Incollection) p;
			showIncollection();
			titleF.setText(ic.getTitle());
			authorF.setText(ic.getAuthorString());
			booktitleF.setText(ic.getBooktitle());
			publF.setText(ic.getPublisher());
			yearF.setText(ic.getYearString());
			edF.setText(ic.getEditorString());
			if (ic.getVolume() != null) volumeF.setText(ic.getVolume().toString());
			if (ic.getNumber() != null) numberF.setText(ic.getNumber().toString());
			if (ic.getSeries() != null && !ic.getSeries().isEmpty()) seriesF.setText(ic.getSeries());
			if (ic.getICType() != null) typeF.setText(ic.getICType());
			if (ic.getChapter() != null) chapF.setText(ic.getChapter().toString());
			if (ic.getFirstPage() != null) pagesF1.setText(ic.getFirstPage().toString());
			if (ic.getFirstPage() != null) pagesF2.setText(ic.getLastPage().toString());
			if (ic.getAddress() != null && !ic.getAddress().isEmpty()) addressF.setText(ic.getAddress());
			if (ic.getEdition() != null && !ic.getEdition().isEmpty()) editionF.setText(ic.getEdition());
			if (ic.getMonth() != null && !ic.getMonth().isEmpty()) monthBox.setSelectedIndex(getMonthIndex(ic.getMonth()));
			if (ic.getNote() != null && !ic.getNote().isEmpty()) noteF.setText(ic.getNote());
			if (ic.getKey() != null && !ic.getKey().isEmpty()) keyF.setText(ic.getKey());		
		}
	}

	private int getMonthIndex(String month) {
		for (int i = 0; i < months.length; i++) {
			if (month.equals(months[i])) return i;
		}
		return 0;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (!modify) pubClassSel = (String) pubClassList.getSelectedItem();
		if (pubClassSel.equals("article")) showArticle();
		if (pubClassSel.equals("book")) showBook();
		if (pubClassSel.equals("booklet")) showBooklet();
		if (pubClassSel.equals("inbook")) showInbook();
		if (pubClassSel.equals("incollection")) showIncollection();
		if (pubClassSel.equals("inproceedings")) showInproceedings();
		if (pubClassSel.equals("manual")) showManual();
		if (pubClassSel.equals("mastersthesis")) showMastersthesis();
		if (pubClassSel.equals("misc")) showMisc();
		if (pubClassSel.equals("phdthesis")) showPhdthesis();
		if (pubClassSel.equals("proceedings")) showProceedings();
		if (pubClassSel.equals("techreport")) showTechreport();
		if (pubClassSel.equals("unpublished")) showUnpublished();	
		
	}
	
	private void showArticle() {
		panel.removeAll();
		panel.repaint();		
				
		addTitleFields(0, true);
		addAuthorFields(1, true);
		addJournalFields(2, true);
		addYearFields(3, true);
		addVolumeFields(4, true);
		
		// now the optional fields
		addNumberFields(5, false);
		addPagesFields(6, false);
		addMonthFields(7, false);
		addKeyFields(8, false);
		addNoteFields(9, false);
		
		panel.revalidate();
	}
	
	private void showBook() {
		panel.removeAll();
		panel.repaint();
		
		addTitleFields(0, true);
		addAuthEdFields(1, true);
		addPublisherFields(2, true);
		addYearFields(3, true);
		addVolNumFields(4, false);
		addSeriesFields(5, false);
		addAddressFields(6, false);
		addEditionFields(7, false);
		addMonthFields(8, false);
		addURLFields(9, false);
		addKeyFields(10, false);
		addNoteFields(11, false);
		
		panel.revalidate();
	}

	private void showBooklet() {
		panel.removeAll();
		panel.repaint();
		
		addTitleFields(0, true);
		addAuthorFields(1, false);
		addHowpublishedFields(2, false);
		addAddressFields(3, false);
		addMonthFields(4, false);
		addYearFields(5, false);
		addKeyFields(6, false);
		addNoteFields(7, false);
		
		panel.revalidate();
	}
	
	private void showInbook() {
		panel.removeAll();
		panel.repaint();
				
		addTitleFields(0, true);
		addAuthEdFields(1, true);
		addChaPaFields(2, true);
		addPublisherFields(3, true);
		addYearFields(4, true);	
		addVolNumFields(5, false);
		addSeriesFields(6, false);
		addTypeFields(7, false);
		addAddressFields(8, false);
		addEditionFields(9, false);
		addMonthFields(10, false);
		addKeyFields(11, false);
		addNoteFields(12, false);
		
		panel.revalidate();
	}
	
	private void showIncollection() {
		panel.removeAll();
		panel.repaint();
		
		addTitleFields(0, true);
		addBooktitleFields(1, true);
		addAuthorFields(2, true);
		addPublisherFields(3, true);
		addYearFields(4, true);
		addEditorFields(5, false);
		addVolNumFields(6, false);
		addSeriesFields(7, false);
		addTypeFields(8, false);
		addChapterFields(9, false);
		addPagesFields(10, false);
		addAddressFields(11, false);
		addEditionFields(12, false);
		addMonthFields(13, false);
		addKeyFields(14, false);
		addNoteFields(15, false);
		
		panel.revalidate();
	}
	
	private void showInproceedings() {
		panel.removeAll();
		panel.repaint();
		
		addTitleFields(0, true);
		addBooktitleFields(1, true);
		addAuthorFields(2, true);
		addYearFields(3, true);
		addEditorFields(4, false);
		addVolNumFields(5, false);
		addSeriesFields(6, false);
		addPagesFields(7, false);
		addAddressFields(8, false);
		addMonthFields(9, false);
		addOrgFields(10, false);
		addPublisherFields(11, false);
		addKeyFields(12, false);
		addNoteFields(13, false);
		
		panel.revalidate();
	}
	
	private void showManual() {
		panel.removeAll();
		panel.repaint();
		
		addTitleFields(0, true);	
		addAuthorFields(1, false);
		addOrgFields(2, false);
		addAddressFields(3, false);
		addEditionFields(4, false);
		addMonthFields(5, false);
		addYearFields(6, false);
		addKeyFields(7, false);
		addNoteFields(8, false);
		
		panel.revalidate();
	}
	
	private void showMastersthesis() {
		panel.removeAll();
		panel.repaint();
		
		addTitleFields(0, true);
		addAuthorFields(1, true);
		addSchoolFields(2, true);
		addYearFields(3, true);
		addTypeFields(4, false);
		addAddressFields(5, false);
		addMonthFields(6, false);
		addKeyFields(7, false);
		addNoteFields(8, false);
		
		panel.revalidate();
	}
	
	private void showMisc() {
		panel.removeAll();
		panel.repaint();
		
		addTitleFields(0, false);
		addAuthorFields(1, false);
		addHowpublishedFields(2, false);
		addMonthFields(3, false);
		addYearFields(4, false);
		addKeyFields(5, false);
		addNoteFields(6, false);
		
		panel.revalidate();
	}
	
	private void showPhdthesis() {
		panel.removeAll();
		panel.repaint();
		
		addTitleFields(0, true);
		addAuthorFields(1, true);
		addSchoolFields(2, true);
		addYearFields(3, true);
		addTypeFields(4, false);
		addAddressFields(5, false);
		addMonthFields(6, false);
		addKeyFields(7, false);
		addNoteFields(8, false);
		
		panel.revalidate();
	}
	
	private void showProceedings() {
		panel.removeAll();
		panel.repaint();
		
		addTitleFields(0, true);
		addYearFields(1, true);
		addEditorFields(2, false);
		addVolNumFields(3, false);
		addSeriesFields(4, false);
		addAddressFields(5, false);
		addMonthFields(6, false);
		addPublisherFields(7, false);
		addOrgFields(8, false);
		addKeyFields(9, false);
		addNoteFields(10, false);
		
		panel.revalidate();
	}
	
	private void showTechreport() {
		panel.removeAll();
		panel.repaint();
		
		addTitleFields(0, true);
		addAuthorFields(1, true);
		addInstitutionFields(2, true);
		addYearFields(3, true);
		addTypeFields(4, false);
		addNumberFields(5, false);
		addAddressFields(6, false);
		addMonthFields(7, false);
		addKeyFields(8, false);
		addNoteFields(9, false);
		
		panel.revalidate();
	}
	
	private void showUnpublished() {
		panel.removeAll();
		panel.repaint();
		
		addTitleFields(0, true);
		addAuthorFields(1, true);
		addNoteFields(2, true);
		addMonthFields(3, false);
		addYearFields(4, false);
		addKeyFields(5, false);
		
		panel.revalidate();
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
	
	private void addTitleFields(int row, boolean req) {
		JLabel titleL = new JLabel("Title: ");
		if (req) titleL.setForeground(Color.RED);
		titleF = new JTextField();
		titleF.setPreferredSize(dim);
	    c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(titleL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(titleF, c);
	}
	
	private void addAuthorFields(int row, boolean req) {
		JLabel authorL = new JLabel("Author(s): ");
		if (req) authorL.setForeground(Color.RED);
		authorF = new JTextField();
		authorF.setPreferredSize(dim);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(authorL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(authorF, c);
	}
	
	private void addJournalFields(int row, boolean req) {
		JLabel journalL = new JLabel("Journal: ");
		if (req) journalL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row; ;
		panel.add(journalL, c);
		journalF = new JTextField();
		journalF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(journalF, c);
	}
	
	private void addYearFields(int row, boolean req) {
		JLabel yearL = new JLabel("Year: ");
		if (req) yearL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row; 
		panel.add(yearL, c);
		yearF = new JTextField(Year.now().toString());
		yearF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(yearF, c);
	}
	
	private void addVolumeFields(int row, boolean req) {
		JLabel volumeL = new JLabel("Volume: ");
		if (req) volumeL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row; 
		panel.add(volumeL, c);
		volumeF = new JTextField();
		volumeF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(volumeF, c);
	}
	
	private void addNumberFields(int row, boolean req) {
		JLabel numberL = new JLabel("Number: ");
		if (req) numberL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(numberL, c);
		numberF = new JTextField();
		numberF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(numberF, c);
	}
	
	private void addPagesFields(int row, boolean req) {
		JLabel pagesL = new JLabel("Pages: ");
		if (req) pagesL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(pagesL, c);
		pagesPanel = new JPanel();
		pagesF1 = new JTextField();
		pagesF1.setPreferredSize(new Dimension(50,25));
		pagesPanel.add(pagesF1);
		JLabel minus = new JLabel(" - ");
		pagesPanel.add(minus);
		pagesF2 = new JTextField();
		pagesF2.setPreferredSize(new Dimension(50,25));
		pagesPanel.add(pagesF2);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(pagesPanel, c);
	}
	
	private void addMonthFields(int row, boolean req) {
		JLabel monthL = new JLabel("Month: ");
		if (req) monthL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row; 
		panel.add(monthL, c);
		monthBox = new JComboBox<String>(months);
		monthBox.setPreferredSize(dim);
		monthBox.setSelectedIndex(0);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(monthBox, c);
	}
	
	private void addKeyFields(int row, boolean req) {
		JLabel keyL = new JLabel("Key: ");
		if (req) keyL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row; 
		panel.add(keyL, c);
		keyF = new JTextField();
		keyF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(keyF, c);
	}
	
	private void addNoteFields(int row, boolean req) {
		JLabel noteL = new JLabel("Note: ");
		c.weightx = 0; c.gridx = 0; c.gridy = row; c.insets = new Insets(5,0,0,0);
		panel.add(noteL, c);
		noteF = new JTextArea();
		noteF.setPreferredSize(new Dimension(200,50));
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(noteF, c);
	}
	
	private void addSchoolFields(int row, boolean req) {
		JLabel schoolL = new JLabel("School: ");
		if (req) schoolL.setForeground(Color.RED);
	    c.weightx = 0; c.gridx = 0; c.gridy = row; 
		panel.add(schoolL, c);
		schoolF = new JTextField();
		schoolF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(schoolF, c);
	}
	
	private void addAuthEdFields(int row, boolean req) {
		String[] authed = {"Author(s)", "Editor(s)"};
		aeBox = new JComboBox<String>(authed);
		if (req) aeBox.setForeground(Color.RED);
		aeBox.setPreferredSize(new Dimension(90, 23));
		aeBox.setSelectedIndex(0);
	    c.weightx = 0; c.gridx = 0; c.gridy = row; c.insets = new Insets(0,0,0,5);
		panel.add(aeBox, c);
		authedF = new JTextField();
		authedF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = row; c.insets = new Insets(0,0,0,0);
		panel.add(authedF, c);
	}
	
	private void addAddressFields(int row, boolean req) {
		JLabel addressL = new JLabel("Address: ");
		if (req) addressL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row; 
		panel.add(addressL, c);
		addressF = new JTextField();
		addressF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(addressF, c);
	}
	
	private void addPublisherFields(int row, boolean req) {
		JLabel publL = new JLabel("Publisher: ");
		if (req) publL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(publL, c);
		publF = new JTextField();
		publF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(publF, c);
	}
	
	private void addBooktitleFields(int row, boolean req) {
		JLabel booktitleL = new JLabel("Booktitle: ");
		if (req) booktitleL.setForeground(Color.RED);
	    c.weightx = 0; c.gridx = 0; c.gridy = row; 
		panel.add(booktitleL, c);
		booktitleF = new JTextField();
		booktitleF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(booktitleF, c);
	}
	
	private void addVolNumFields(int row, boolean req) {
		String[] volnum = {"Volume", "Number"};
		vnBox = new JComboBox<String>(volnum);
		vnBox.setPreferredSize(new Dimension(90, 23));
		vnBox.setSelectedIndex(0);
	    c.weightx = 0; c.gridx = 0; c.gridy = row; c.insets = new Insets(0,0,0,5);
		panel.add(vnBox, c);
		volNumF = new JTextField();
		volNumF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = row; c.insets = new Insets(0,0,0,0);
		panel.add(volNumF, c);
	}
	
	private void addSeriesFields(int row, boolean req) {
		JLabel seriesL = new JLabel("Series: ");
		if (req) seriesL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(seriesL, c);
		seriesF = new JTextField();
		seriesF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(seriesF, c);
	}
	
	private void addEditionFields(int row, boolean req) {
		JLabel editionL = new JLabel("Edition: ");
		if (req) editionL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(editionL, c);
		editionF = new JTextField();
		editionF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(editionF, c);
	}
	
	private void addURLFields(int row, boolean req) {
		JLabel urlL = new JLabel("URL: ");
		if (req) urlL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(urlL, c);
		urlF = new JTextField();
		urlF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(urlF, c);
	}
	
	private void addHowpublishedFields(int row, boolean req) {
		JLabel hpL = new JLabel("How published: ");
		if (req) hpL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(hpL, c);
		hpF = new JTextField();
		hpF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(hpF, c);
	}
	
	private void addChaPaFields(int row, boolean req) {
		String[] chapa = {"Chapter", "Pages"};
		cpBox = new JComboBox<String>(chapa);
		if (req) cpBox.setForeground(Color.RED);
		pagesPanel = new JPanel();
		pagesPanel.setPreferredSize(dim);
		chapF = new JTextField();
		chapF.setPreferredSize(dim);
		pagesPanel.add(chapF);
		cpBox.setPreferredSize(new Dimension(90, 23));
		cpBox.setSelectedIndex(0);
		cpBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.weightx = 1; c.gridx = 1; c.gridy = row;
				int sel = cpBox.getSelectedIndex();
				if (containsComponent(panel, pagesPanel)) panel.remove(pagesPanel);
				if (containsComponent(panel, chapF)) panel.remove(chapF);
				if (sel == 0) { // chapter
					panel.add(chapF, c);
				} else { // pages
					if (pagesPanel.getComponentCount() == 0){
						JTextField pagesF1 = new JTextField();
						pagesF1.setPreferredSize(new Dimension(50,25));
						pagesPanel.add(pagesF1);
						JLabel minus = new JLabel(" - ");
						pagesPanel.add(minus);
						JTextField pagesF2 = new JTextField();
						pagesF2.setPreferredSize(new Dimension(50,25));
						pagesPanel.add(pagesF2);
						}
					panel.add(pagesPanel, c);
				}
				panel.revalidate();
				panel.repaint();
			}
		});
	    c.weightx = 0; c.gridx = 0; c.gridy = row; c.insets = new Insets(0,0,0,5);
		panel.add(cpBox, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row; c.insets = new Insets(0,0,0,0);
		panel.add(chapF, c);
	}
	
	private void addTypeFields(int row, boolean req) {
		JLabel typeL = new JLabel("Type: ");
		if (req) typeL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(typeL, c);
		typeF = new JTextField();
		typeF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(typeF, c);
	}
	
	private void addEditorFields(int row, boolean req) {
		JLabel edL = new JLabel("Editor: ");
		if (req) edL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(edL, c);
		edF = new JTextField();
		edF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(edF, c);
	}
	
	private void addChapterFields(int row, boolean req) {
		JLabel chapL = new JLabel("Chapter: ");
		if (req) chapL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(chapL, c);
		chapF = new JTextField();
		chapF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(chapF, c);
	}
	
	private void addOrgFields(int row, boolean req) {
		JLabel orgL = new JLabel("Organization: ");
		if (req) orgL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(orgL, c);
		orgF = new JTextField();
		orgF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(orgF, c);
	}
	
	private void addInstitutionFields(int row, boolean req) {
		JLabel instL = new JLabel("Institution: ");
		if (req) instL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(instL, c);
		instF = new JTextField();
		instF.setPreferredSize(dim);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(instF, c);
	}
	
	// TODO: error message if required fields are not set
	// TODO: check inputs
	Publication getPublication() {
		if (!modify) pubClassSel = (String) pubClassList.getSelectedItem();
		if (pubClassSel.equals("article")) {
			Article a = new Article();
			if (modify) a.setId(id);
			a.setTitle(titleF.getText());
			
			ArrayList<String> authors = constructList(authorF.getText());
			a.setAuthors(authors);
			
			a.setJournal(journalF.getText());
			
			if (!isNumeric(yearF.getText())) return null;
			a.setYear(Integer.parseInt(yearF.getText()));
			
			if (!isNumeric(volumeF.getText())) return null;
			a.setVolume(Integer.parseInt(volumeF.getText()));
						
			if (isNumeric(numberF.getText())) 
				a.setNumber(Integer.parseInt(numberF.getText()));
			
			if (isNumeric(pagesF1.getText()) && isNumeric(pagesF2.getText()))
				a.setPages(Integer.parseInt(pagesF1.getText()), Integer.parseInt(pagesF2.getText()));

			if (monthBox.getSelectedIndex() != 0) 
				a.setMonth(months[monthBox.getSelectedIndex()]);
			
			if (!keyF.getText().isEmpty()) 
				a.setKey(keyF.getText());
			
			if (!noteF.getText().isEmpty()) 
				a.setNote(noteF.getText());
			
			return a;
		}
		if (pubClassSel.equals("book")) {
			Book b = new Book();
			b.setTitle(titleF.getText());
			
			ArrayList<String> al = constructList(authedF.getText());
			if (aeBox.getSelectedIndex() == 0) { // author
				b.setAuthors(al);
			} else { // editors
				b.setEditors(al);
			}
			
			b.setPublisher(publF.getText());
			
			if (!isNumeric(yearF.getText())) return null;
			b.setYear(Integer.parseInt(yearF.getText()));	
			
			if (isNumeric(volumeF.getText()))
				b.setVolume(Integer.parseInt(volumeF.getText()));
						
			if (isNumeric(numberF.getText())) 
				b.setNumber(Integer.parseInt(numberF.getText()));
			
			if (!seriesF.getText().isEmpty())
				b.setSeries(seriesF.getText());
			
			if (!addressF.getText().isEmpty())
				b.setAddress(addressF.getText());
			
			if (!editionF.getText().isEmpty())
				b.setEdition(editionF.getText());
			
			if (monthBox.getSelectedIndex() != 0) 
				b.setMonth(months[monthBox.getSelectedIndex()]);
			
			if (!urlF.getText().isEmpty())
				b.setUrl(urlF.getText());
			
			if (!keyF.getText().isEmpty()) // TODO: check
				b.setKey(keyF.getText());
			
			if (!noteF.getText().isEmpty())
				b.setNote(noteF.getText());
			
			return b;
		}
		return null;
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
	
	ArrayList<String> constructList(String s){
		ArrayList<String> al = new ArrayList<String>();
		s.replaceAll("AND", "and");
		s.replaceAll(";", "and");
		String[] sarr = s.split("and");
		for (int i = 0; i < sarr.length; i++) {
			String[] name = sarr[i].split(",");
			if (name.length == 2) { 
				String p = sarr[i].trim();
				al.add(p);
			} 
			if (name.length == 1) {
				sarr[i] = sarr[i].trim();
				int index = sarr[i].lastIndexOf(" ");				
				if (index == -1) {
					String p = sarr[i].trim();
					al.add(p);
				} else {
					String[] ss = sarr[i].split(" ");
					String n = ss[ss.length - 1] + ", ";
					for (int j = 0; j < ss.length - 1; j++) {
						if (!ss[j].equals("")) n = n + ss[j];
					}
					String p = n.trim();
					al.add(p);
				}
				
			}
		}
		return al;
	}
}
