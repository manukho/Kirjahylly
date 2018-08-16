package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import pub.*;

/**
 * PubAdd is the Panel that gets used in a JOptionPane when adding or modifying a bibliographic item.
 * For adding an item, the constructor PubAdd() is used where the class of the item can be chosen.
 * For modifying an item, the constructor PubAdd(Publication p) is used where only the form for the 
 * class of p gets shown and the textfields are pre-filled with the fields of p. 
 *
 * @author Manuela Hopp
 */
public class PubAdd extends JPanel implements ActionListener{
	
    private static final long serialVersionUID = 1L;
	
    boolean modify = false;
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
	JLabel titleL;
	JLabel booktitleL;
	JLabel authorL;
	JLabel journalL;
	JLabel yearL;
	JLabel volumeL;
	JLabel numberL;
	JLabel pagesL;
	JLabel minus;
	JLabel monthL;
	JLabel keyL;
	JLabel noteL;
	JLabel schoolL;
	JLabel instL;
	JLabel orgL;
	JLabel addressL;
	JLabel publL;
	JLabel seriesL;
	JLabel editionL;
	JLabel urlL;
	JLabel hpL;
	JLabel typeL;
	JLabel edL;
	JLabel chapL;
	private String[] months = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	StringBuilder errMsg;
	private Publication b;
	/**
	 * constructor for the form for adding a new bibliographic item
	 */
	PubAdd(){
		modify = false;
		Dimension d = new Dimension(600,600);
		setPreferredSize(d);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		errMsg = new StringBuilder();
		
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
		
		createElements();
		
		showArticle();
	}
	
	
	/**
	 * constructor for the form for modifying an existing bibliographic item
	 * @param the publication to be modified
	 * @param boolean value that is true - if an item is being added
	 * 								false - if an item is being modified
	 */
	PubAdd(Publication p, boolean add){
		if (!add) modify = true;
		else modify = false;
		if (!add) id = p.getId();
		pubClassSel = p.getType();
		createElements();
		Dimension d = new Dimension(600,600);
		setPreferredSize(d);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		if (add) {
			JPanel classListPanel = new JPanel();
			pubClasses = new String[] {"article", "book", "booklet", "inbook", "incollection", "inproceedings", "manual", "mastersthesis", "misc", "phdthesis", "proceedings", "techreport", "unpublished"};
			pubClassList = new JComboBox<String>(pubClasses);
			pubClassList.setPreferredSize(new Dimension(150,25));
			setSelectedIndexOfPubClassList(p.getType());
			pubClassList.addActionListener(this);
			classListPanel.add(pubClassList);
			add(classListPanel);
			panel = new JPanel();
			panel.setLayout(new GridBagLayout());
			add(panel);
		} else {
			panel = this;
		}
		
		panel.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		dim = new Dimension(350,25);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTH;
		c.ipadx = 5;
		c.ipady = 5;
		
		errMsg = new StringBuilder();;
		
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
			if (a.getMonth() != null) monthBox.setSelectedIndex(getMonthIndex(a.getMonth()));
			if (a.getNote() != null) noteF.setText(a.getNote());
			if (a.getKey() != null) keyF.setText(a.getKey());
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
			if (b.getSeries() != null) seriesF.setText(b.getSeries());
			if (b.getAddress() != null) addressF.setText(b.getAddress());
			if (b.getEdition() != null) editionF.setText(b.getEdition());
			if (b.getMonth() != null) monthBox.setSelectedIndex(getMonthIndex(b.getMonth()));
			if (b.getURL() != null) urlF.setText(b.getURL());
			if (b.getNote() != null) noteF.setText(b.getNote());
			if (b.getKey() != null) keyF.setText(b.getKey());
		}
		if (p.getType().equals("booklet")) {
			Booklet b = (Booklet) p;
			showBooklet();
			titleF.setText(b.getTitle());
			authorF.setText(b.getAuthorString());
			if (b.getHowpublished() != null) hpF.setText(b.getHowpublished());
			if (b.getAddress() != null) addressF.setText(b.getAddress());
			if (b.getMonth() != null) monthBox.setSelectedIndex(getMonthIndex(b.getMonth()));
			if (b.getYear() != null) yearF.setText(b.getYearString());
			if (b.getNote() != null) noteF.setText(b.getNote());
			if (b.getKey() != null) keyF.setText(b.getKey());		
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
			if (ib.getSeries() != null) seriesF.setText(ib.getSeries());
			if (ib.getPType() != null) typeF.setText(ib.getPType());
			if (ib.getAddress() != null) addressF.setText(ib.getAddress());
			if (ib.getEdition() != null) editionF.setText(ib.getEdition());
			if (ib.getMonth() != null) monthBox.setSelectedIndex(getMonthIndex(ib.getMonth()));
			if (ib.getNote() != null) noteF.setText(ib.getNote());
			if (ib.getKey() != null) keyF.setText(ib.getKey());		
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
			if (ic.getPType() != null) typeF.setText(ic.getPType());
			if (ic.getChapter() != null) chapF.setText(ic.getChapter().toString());
			if (ic.getFirstPage() != null) pagesF1.setText(ic.getFirstPage().toString());
			if (ic.getFirstPage() != null) pagesF2.setText(ic.getLastPage().toString());
			if (ic.getAddress() != null) addressF.setText(ic.getAddress());
			if (ic.getEdition() != null) editionF.setText(ic.getEdition());
			if (ic.getMonth() != null) monthBox.setSelectedIndex(getMonthIndex(ic.getMonth()));
			if (ic.getNote() != null) noteF.setText(ic.getNote());
			if (ic.getKey() != null) keyF.setText(ic.getKey());		
		}
		if (p.getType().equals("inproceedings")) {
			Inproceedings ip = (Inproceedings) p;
			showInproceedings();
			titleF.setText(ip.getTitle());
			authorF.setText(ip.getAuthorString());
			booktitleF.setText(ip.getBooktitle());
			yearF.setText(ip.getYearString());
			edF.setText(ip.getEditorString());
			if (ip.getVolume() != null) volumeF.setText(ip.getVolume().toString());
			if (ip.getNumber() != null) numberF.setText(ip.getNumber().toString());
			if (ip.getSeries() != null) seriesF.setText(ip.getSeries());
			if (ip.getFirstPage() != null) pagesF1.setText(ip.getFirstPage().toString());
			if (ip.getFirstPage() != null) pagesF2.setText(ip.getLastPage().toString());
			if (ip.getAddress() != null) addressF.setText(ip.getAddress());
			if (ip.getMonth() != null) monthBox.setSelectedIndex(getMonthIndex(ip.getMonth()));
			if (ip.getOrganization() != null) orgF.setText(ip.getOrganization());
			if (ip.getPublisher() != null) publF.setText(ip.getPublisher());
			if (ip.getNote() != null) noteF.setText(ip.getNote());
			if (ip.getKey() != null) keyF.setText(ip.getKey());	
		}
		if (p.getType().equals("manual")) {
			Manual m = (Manual) p;
			showManual();
			titleF.setText(m.getTitle());
			authorF.setText(m.getAuthorString());
			if (m.getOrganization() != null) orgF.setText(m.getOrganization());
			if (m.getAddress() != null) addressF.setText(m.getAddress());
			if (m.getEdition() != null) editionF.setText(m.getEdition());
			if (m.getMonth() != null) monthBox.setSelectedIndex(getMonthIndex(m.getMonth()));
			yearF.setText(m.getYearString());
			if (m.getNote() != null) noteF.setText(m.getNote());
			if (m.getKey() != null) keyF.setText(m.getKey());	
		}
		if (p.getType().equals("mastersthesis")) {
			Mastersthesis m = (Mastersthesis) p;
			showMastersthesis();
			titleF.setText(m.getTitle());
			authorF.setText(m.getAuthorString());
			schoolF.setText(m.getSchool());
			yearF.setText(m.getYearString());
			if (m.getPType() != null) typeF.setText(m.getPType());
			if (m.getAddress() != null) addressF.setText(m.getAddress());
			if (m.getMonth() != null) monthBox.setSelectedIndex(getMonthIndex(m.getMonth()));
			if (m.getNote() != null) noteF.setText(m.getNote());
			if (m.getKey() != null) keyF.setText(m.getKey());	
		}
		if (p.getType().equals("misc")) {
			Misc m = (Misc) p;
			showMisc();
			titleF.setText(m.getTitle());
			authorF.setText(m.getAuthorString());
			if (m.getHowpublished() != null) hpF.setText(m.getHowpublished());
			if (m.getMonth() != null) monthBox.setSelectedIndex(getMonthIndex(m.getMonth()));
			yearF.setText(m.getYearString());
			if (m.getNote() != null) noteF.setText(m.getNote());
			if (m.getKey() != null) keyF.setText(m.getKey());	
		}
		if (p.getType().equals("phdthesis")) {
			Phdthesis pt = (Phdthesis) p;
			showPhdthesis();
			titleF.setText(pt.getTitle());
			authorF.setText(pt.getAuthorString());
			schoolF.setText(pt.getSchool());
			yearF.setText(pt.getYearString());
			if (pt.getPType() != null) typeF.setText(pt.getPType());
			if (pt.getAddress() != null) addressF.setText(pt.getAddress());
			if (pt.getMonth() != null) monthBox.setSelectedIndex(getMonthIndex(pt.getMonth()));
			if (pt.getNote() != null) noteF.setText(pt.getNote());
			if (pt.getKey() != null) keyF.setText(pt.getKey());	
		}
		if (p.getType().equals("proceedings")) {
			Proceedings pr = (Proceedings) p;
			showProceedings();
			titleF.setText(pr.getTitle());
			yearF.setText(pr.getYearString());
			edF.setText(pr.getEditorString());
			if (pr.getVolume() != null) volumeF.setText(pr.getVolume().toString());
			if (pr.getNumber() != null) numberF.setText(pr.getNumber().toString());
			if (pr.getSeries() != null) seriesF.setText(pr.getSeries());
			if (pr.getAddress() != null) addressF.setText(pr.getAddress());
			if (pr.getMonth() != null) monthBox.setSelectedIndex(getMonthIndex(pr.getMonth()));
			if (pr.getOrganization() != null) orgF.setText(pr.getOrganization());
			if (pr.getPublisher() != null) publF.setText(pr.getPublisher());
			if (pr.getNote() != null) noteF.setText(pr.getNote());
			if (pr.getKey() != null) keyF.setText(pr.getKey());	
		}
		if (p.getType().equals("techreport")) {
			Techreport tr = (Techreport) p;
			showTechreport();
			titleF.setText(tr.getTitle());
			authorF.setText(tr.getAuthorString());
			instF.setText(tr.getInstitution());
			yearF.setText(tr.getYearString());
			if (tr.getPType() != null && !tr.getPType().isEmpty()) typeF.setText(tr.getPType());
			if (tr.getNumber() != null) numberF.setText(tr.getNumber().toString());
			if (tr.getAddress() != null) addressF.setText(tr.getAddress());
			if (tr.getMonth() != null) monthBox.setSelectedIndex(getMonthIndex(tr.getMonth()));
			if (tr.getNote() != null && !tr.getNote().isEmpty()) noteF.setText(tr.getNote());
			if (tr.getKey() != null && !tr.getKey().isEmpty()) keyF.setText(tr.getKey());	
		}
		if (p.getType().equals("unpublished")) {
			Unpublished u = (Unpublished) p;
			showUnpublished();
			titleF.setText(u.getTitle());
			authorF.setText(u.getAuthorString());
			yearF.setText(u.getYearString());
			if (u.getMonth() != null) monthBox.setSelectedIndex(getMonthIndex(u.getMonth()));
			if (u.getNote() != null) noteF.setText(u.getNote());
			if (u.getKey() != null) keyF.setText(u.getKey());	
		}
	}

	private void setSelectedIndexOfPubClassList(String type) {
		System.out.println("setSelectedIndexOfPubClassList");
		pubClasses = new String[] {"article", "book", "booklet", "inbook", "incollection", "inproceedings", "manual", "mastersthesis", "misc", "phdthesis", "proceedings", "techreport", "unpublished"};
		for (int i = 0; i < pubClasses.length; i++) {
			if (type.equals(pubClasses[i])) {
				pubClassList.setSelectedIndex(i);
				
			}
		}
		System.out.println("Index selected: " + pubClassList.getSelectedIndex());
	}


	private int getMonthIndex(String month) {
		for (int i = 0; i < months.length; i++) {
			if (month.equals(months[i])) return i;
		}
		return 0;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (!modify) { 
			System.out.println("here! modify = " + modify);
			pubClassSel = (String) pubClassList.getSelectedItem();
		}
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
		if (req) titleL.setForeground(Color.RED);
	    c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(titleL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(titleF, c);
	}
	
	private void addAuthorFields(int row, boolean req) {
		if (req) authorL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(authorL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(authorF, c);
	}
	
	private void addJournalFields(int row, boolean req) {
		if (req) journalL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row; ;
		panel.add(journalL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(journalF, c);
	}
	
	private void addYearFields(int row, boolean req) {
		if (req) yearL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row; 
		panel.add(yearL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(yearF, c);
	}
	
	private void addVolumeFields(int row, boolean req) {
		if (req) volumeL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row; 
		panel.add(volumeL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(volumeF, c);
	}
	
	private void addNumberFields(int row, boolean req) {
		if (req) numberL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(numberL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(numberF, c);
	}
	
	private void addPagesFields(int row, boolean req) {
		if (req) pagesL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(pagesL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(pagesPanel, c);
	}
	
	private void addMonthFields(int row, boolean req) {
		if (req) monthL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row; 
		panel.add(monthL, c);
		monthBox.setSelectedIndex(0);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(monthBox, c);
	}
	
	private void addKeyFields(int row, boolean req) {
		if (req) keyL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row; 
		panel.add(keyL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(keyF, c);
	}
	
	private void addNoteFields(int row, boolean req) {
		if (req) noteL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row; c.insets = new Insets(5,0,0,0);
		panel.add(noteL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(noteF, c);
	}
	
	private void addSchoolFields(int row, boolean req) {
		if (req) schoolL.setForeground(Color.RED);
	    c.weightx = 0; c.gridx = 0; c.gridy = row; 
		panel.add(schoolL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(schoolF, c);
	}
	
	private void addAuthEdFields(int row, boolean req) {
		if (req) aeBox.setForeground(Color.RED);
		aeBox.setSelectedIndex(0);
	    c.weightx = 0; c.gridx = 0; c.gridy = row; c.insets = new Insets(0,0,0,5);
		panel.add(aeBox, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row; c.insets = new Insets(0,0,0,0);
		panel.add(authedF, c);
	}
	
	private void addAddressFields(int row, boolean req) {
		if (req) addressL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row; 
		panel.add(addressL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(addressF, c);
	}
	
	private void addPublisherFields(int row, boolean req) {
		if (req) publL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(publL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(publF, c);
	}
	
	private void addBooktitleFields(int row, boolean req) {
		if (req) booktitleL.setForeground(Color.RED);
	    c.weightx = 0; c.gridx = 0; c.gridy = row; 
		panel.add(booktitleL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(booktitleF, c);
	}
	
	private void addVolNumFields(int row, boolean req) {
		vnBox.setSelectedIndex(0);
	    c.weightx = 0; c.gridx = 0; c.gridy = row; c.insets = new Insets(0,0,0,5);
		panel.add(vnBox, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row; c.insets = new Insets(0,0,0,0);
		panel.add(volNumF, c);
	}
	
	private void addSeriesFields(int row, boolean req) {
		if (req) seriesL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(seriesL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(seriesF, c);
	}
	
	private void addEditionFields(int row, boolean req) {
		if (req) editionL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(editionL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(editionF, c);
	}
	
	private void addURLFields(int row, boolean req) {
		if (req) urlL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(urlL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(urlF, c);
	}
	
	private void addHowpublishedFields(int row, boolean req) {
		if (req) hpL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(hpL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(hpF, c);
	}
	
	private void addChaPaFields(int row, boolean req) {
		if (req) cpBox.setForeground(Color.RED);
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
		if (req) typeL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(typeL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(typeF, c);
	}
	
	private void addEditorFields(int row, boolean req) {
		if (req) edL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(edL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(edF, c);
	}
	
	private void addChapterFields(int row, boolean req) {
		if (req) chapL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(chapL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(chapF, c);
	}
	
	private void addOrgFields(int row, boolean req) {
		if (req) orgL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(orgL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(orgF, c);
	}
	
	private void addInstitutionFields(int row, boolean req) {
		if (req) instL.setForeground(Color.RED);
		c.weightx = 0; c.gridx = 0; c.gridy = row;
		panel.add(instL, c);
		c.weightx = 1; c.gridx = 1; c.gridy = row;
		panel.add(instF, c);
	}
	
	boolean validateInput() {
		boolean bool = true;
		if (!modify) pubClassSel = (String) pubClassList.getSelectedItem();
		
		if (pubClassSel.equals("article")) {
			b = new Article();
			if (modify) b.setId(id);
			bool &= setTitle(b, true);
			bool &= setAuthors(b, true);
			bool &= setJournal((Article) b, true);
			bool &= setYear(b, true);
			bool &= setVolume(b, true);
			bool &= setNumber(b, false);
			bool &= setPages(b, false);
			bool &= setMonth(b, false);
			bool &= setNote(b, false);
			bool &= setKey(b, false);
			return bool;
		}
		if (pubClassSel.equals("book")) {
			b = new Book();
			if (modify) b.setId(id);
			bool &= setTitle(b, true);
			bool &= setAuthEd(b, true);
			bool &= setPublisher(b, true);
			bool &= setYear(b, true);
			bool &= setVolume(b, false);
			bool &= setNumber(b, false);
			bool &= setSeries(b, false);
			bool &= setAddress(b, false);
			bool &= setEdition(b, false);
			bool &= setMonth(b, false);
			bool &= setUrl(b, false);
			bool &= setKey(b, false);
			bool &= setNote(b, false);	
			return bool;
		}
		if (pubClassSel.equals("booklet")) {
			b = new Booklet();
			if (modify) b.setId(id);
			bool &= setTitle(b, true);
			bool &= setAuthors(b, false);
			bool &= setHowpublished(b, false);
			bool &= setAddress(b, false);
			bool &= setMonth(b, false);
			bool &= setYear(b, false);
			bool &= setKey(b, false);
			bool &= setNote(b, false);
			return bool;
		}
		if (pubClassSel.equals("inbook")) {
			b = new Inbook();
			if (modify) b.setId(id);
			bool &= setTitle(b, true);
			bool &= setAuthEd(b, true);
			bool &= setChaPa(b, true);
			bool &= setPublisher(b, true);
			bool &= setYear(b, true);
			bool &= setVolNum(b, false);
			bool &= setSeries(b, false);
			bool &= setPType(b, false);
			bool &= setAddress(b, false);
			bool &= setEdition(b, false);
			bool &= setMonth(b, false);
			bool &= setKey(b, false);
			bool &= setNote(b, false);
			return bool;
		}
		if (pubClassSel.equals("incollection")) {
			b = new Incollection();
			if (modify) b.setId(id);
			bool &= setTitle(b, true);
			bool &= setBooktitle(b, true);
			bool &= setAuthors(b, true);
			bool &= setPublisher(b, true);
			bool &= setYear(b, true);
			bool &= setEditors(b, false);
			bool &= setVolNum(b, false);
			bool &= setSeries(b, false);
			bool &= setPType(b, false);
			bool &= setChapter(b, false);
			bool &= setPages(b, false);
			bool &= setAddress(b, false);
			bool &= setEdition(b, false);
			bool &= setMonth(b, false);
			bool &= setKey(b, false);
			bool &= setNote(b, false);
			return bool;
		}
		if (pubClassSel.equals("inproceedings")) {
			b = new Inproceedings();
			if (modify) b.setId(id);
			bool &= setTitle(b, true);
			bool &= setBooktitle(b, true);
			bool &= setAuthors(b, true);
			bool &= setYear(b, true);
			bool &= setEditors(b, false);
			bool &= setVolNum(b, false);
			bool &= setSeries(b, false);
			bool &= setPages(b, false);
			bool &= setAddress(b, false);
			bool &= setMonth(b, false);
			bool &= setOrganization(b, false);
			bool &= setPublisher(b, false);
			bool &= setKey(b, false);
			bool &= setNote(b, false);
			return bool;
		}
		if (pubClassSel.equals("manual")) {
			b = new Manual();
			if (modify) b.setId(id);
			bool &= setTitle(b, true);
			bool &= setAuthors(b, false);
			bool &= setOrganization(b, false);
			bool &= setAddress(b, false);
			bool &= setEdition(b, false);
			bool &= setMonth(b, false);
			bool &= setYear(b, false);
			bool &= setKey(b, false);
			bool &= setNote(b, false);
			return bool;
		}
		if (pubClassSel.equals("mastersthesis")) {
			b = new Mastersthesis();
			if (modify) b.setId(id);
			bool &= setTitle(b, true);
			bool &= setAuthors(b, true);
			bool &= setSchool(b, true);
			bool &= setYear(b, true);
			bool &= setPType(b, false);
			bool &= setAddress(b, false);
			bool &= setMonth(b, false);
			bool &= setKey(b, false);
			bool &= setNote(b, false);
			return bool;
		}
		if (pubClassSel.equals("misc")) {
			b = new Misc();
			if (modify) b.setId(id);
			bool &= setTitle(b, false);
			bool &= setAuthors(b, false);
			bool &= setHowpublished(b, false);
			bool &= setMonth(b, false);
			bool &= setYear(b, false);
			bool &= setKey(b, false);
			bool &= setNote(b, false);
			return bool;
		}
		if (pubClassSel.equals("phdthesis")) {
			b = new Phdthesis();
			if (modify) b.setId(id);
			bool &= setTitle(b, true);
			bool &= setAuthors(b, true);
			bool &= setSchool(b, true);
			bool &= setYear(b, true);
			bool &= setPType(b, false);
			bool &= setAddress(b, false);
			bool &= setMonth(b, false);
			bool &= setKey(b, false);
			bool &= setNote(b, false);
			return bool;
		}
		if (pubClassSel.equals("proceedings")) {
			b = new Proceedings();
			if (modify) b.setId(id);
			bool &= setTitle(b, true);
			bool &= setYear(b, true);
			bool &= setEditors(b, false);
			bool &= setVolNum(b, false);
			bool &= setSeries(b, false);
			bool &= setAddress(b, false);
			bool &= setMonth(b, false);
			bool &= setPublisher(b, false);
			bool &= setOrganization(b, false);
			bool &= setKey(b, false);
			bool &= setNote(b, false);
			return bool;
		}
		if (pubClassSel.equals("techreport")) {
			b = new Techreport();
			if (modify) b.setId(id);
			bool &= setTitle(b, true);
			bool &= setAuthors(b, true);
			bool &= setInstitution(b, true);
			bool &= setYear(b, true);
			bool &= setPType(b, false);
			bool &= setNumber(b, false);
			bool &= setAddress(b, false);
			bool &= setMonth(b, false);
			bool &= setKey(b, false);
			bool &= setNote(b, false);
			return bool;
		}
		if (pubClassSel.equals("unpublished")) {
			b = new Unpublished();
			if (modify) b.setId(id);
			bool &= setTitle(b, true);
			bool &= setAuthors(b, true);
			bool &= setNote(b, true);
			bool &= setMonth(b, false);
			bool &= setYear(b, false);
			bool &= setKey(b, false);
			return bool;
		}
		return true;
	}
	
	/* this assumes validateInput has been called before, thus b is not null */
	Publication getPublication() {
//		if (!modify) pubClassSel = (String) pubClassList.getSelectedItem();
		return b;
	}
	
	String getErrMsg() {
		return errMsg.toString();
	}
	
	void clearErrMsg() {
		errMsg.delete(0, errMsg.length());
	}
	
	private boolean setTitle(Publication p, boolean req) {
		String s = titleF.getText();
		if (req && (s == null || s.isEmpty())) {
			errMsg.append(p.getType() + " must have a title.\n");
			return false;
		}
		if (!req && (s == null || s.isEmpty())) {
			p.setTitle(null);
			return true;
		}
		p.setTitle(s);
		return true;
	}
	
	private boolean setBooktitle(Publication p, boolean req) {
		String s = booktitleF.getText();
		if (req && (s == null || s.isEmpty())) {
			errMsg.append(p.getType() + " must have a booktitle.\n");
			return false;
		}
		if (!req && (s == null || s.isEmpty())) {
			p.setBooktitle(null);
			return true;
		}
		p.setBooktitle(s);
		return true;
	}
	
	private boolean setAuthors(Publication p, boolean req) {
		String s = authorF.getText();
		if (req && (s == null || s.isEmpty())) {
			errMsg.append(p.getType() + " must have authors.\n");
			return false;
		}
		if (!req && (s == null || s.isEmpty())) {
			p.setAuthors(null);
			return true;
		}
		ArrayList<String> authors = constructList(s);
		p.setAuthors(authors);
		return true;
	}
	
	private boolean setEditors(Publication p, boolean req) {
		String s = edF.getText();
		if (req && (s == null || s.isEmpty())) {
			errMsg.append(p.getType() + " must have editors.\n");
			return false;
		}
		if (!req && (s == null || s.isEmpty())) {
			p.setEditors(null);
			return true;
		}
		ArrayList<String> authors = constructList(s);
		p.setEditors(authors);
		return true;
	}
	
	private boolean setJournal(Article p, boolean req) {
		String s = journalF.getText();
		if (s == null || s.isEmpty()) {
			errMsg.append("article must have a journal.\n");
			return false;
		} 
		p.setJournal(s);
		return true;
	}
	
	private boolean setYear(Publication p, boolean req) {
		String s = yearF.getText();
		boolean empty = (s == null || s.isEmpty());
		if (req && empty) {
			errMsg.append(p.getType() + "must have a year.\n");
			return false;
		}
		if (!req && empty) {
			p.setVolume(null);
			return true;
		}
		if (!empty && !isNumeric(s)) {
			errMsg.append("year must be a number.\n");
			return false;
		}
		p.setYear(Integer.valueOf(s));
		return true;
	}
	
	private boolean setVolume(Publication p, boolean req) {
		String s = volumeF.getText();
		boolean empty = (s == null || s.isEmpty());
		if (req && empty) {
			errMsg.append(p.getType() + " must have a volume.\n");
			return false;
		}
		if (!empty && !isNumeric(s)) {			
			errMsg.append("volume must be a number.\n");
			return false;
		}
		if (!req && empty) {
			p.setVolume(null);
			return true;
		}
		p.setVolume(Integer.valueOf(s));
		return true;
	}
	
	private boolean setNumber(Publication p, boolean req) {
		String s = numberF.getText();
		boolean empty = (s == null || s.isEmpty());
		if (req && empty) {
			errMsg.append(p.getType() + " must have a number.\n");
			return false;
		}
		if (!empty && !isNumeric(s)) {
			errMsg.append("number must be numeric.\n");
			return false;
		}
		if (!req && empty) {
			p.setNumber(null);
			return true;
		}
		p.setNumber(Integer.valueOf(s));
		return true;
	}
	
	private boolean setPages(Publication p, boolean req) {
		String s1 = pagesF1.getText();
		String s2 = pagesF2.getText();
		boolean p1empty = (s1 == null || s1.isEmpty());
		boolean p2empty = (s2 == null || s2.isEmpty());
		if (req && p1empty && p2empty) {
			errMsg.append(p.getType() + " must have pages.\n");
			return false;
		}
		if ((p1empty && !p2empty) || (!p1empty && p2empty)) {
			errMsg.append("there must be both a first and a last page.\n");
			return false;
		}
		if (!p1empty && !p2empty && (!isNumeric(s1) || !isNumeric(s2))) {
			errMsg.append("pages must be numeric.\n");
			return false;
		}
		if (!req && p1empty && p2empty) {
			p.setPages(null, null);
			return true;
		}
		p.setPages(Integer.valueOf(s1), Integer.valueOf(s2));
		return true;
	}
	
	private boolean setMonth(Publication p, boolean req) {
		if (req && monthBox.getSelectedIndex() == 0) {
			errMsg.append(p.getType() + " must have a month.\n");
			return false;
		}
		p.setMonth(months[monthBox.getSelectedIndex()]);
		return true;
	}
	
	private boolean setNote(Publication p, boolean req) {
		String s = noteF.getText();
		if (req && (s == null || s.isEmpty())) {
			errMsg.append(p.getType() + " must have a note.\n");
			return false;
		}
		p.setNote(s);
		return true;
	}
	
	private boolean setKey(Publication p, boolean req) {
		String s = keyF.getText();
		if (req && (s == null || s.isEmpty())) {
			errMsg.append(p.getType() + " must have a key.\n");
			return false;
		}
		p.setKey(s);
		return true;
	}
	
	private boolean setAuthEd(Publication p, boolean req) {
		String s = authedF.getText();
		if (req && s.isEmpty()) {
			errMsg.append(p.getType() + " must have either authors or editors.\n");
			return false;
		}
		if (!req && s.isEmpty()) return true;
		ArrayList<String> al = constructList(authedF.getText());
		if (aeBox.getSelectedIndex() == 0) { // authors
			p.setAuthors(al);
		} else { // editors
			p.setEditors(al);
		}
		return true;
	}
	
	private boolean setPublisher(Publication p, boolean req) {
		String s = publF.getText();
		if (req && (s == null || s.isEmpty())) {
			errMsg.append(p.getType() + " must have a publisher.\n");
			return false;
		}
		if (!req && (s == null || s.isEmpty())) {
			p.setPublisher(null);
			return true;
		}
		p.setPublisher(s);
		return true;
	}
	
	private boolean setSeries(Publication p, boolean req) {
		String s = seriesF.getText();
		if (req && (s == null || s.isEmpty())) {
			errMsg.append(p.getType() + " must have a series.\n");
			return false;
		}
		if (!req && (s == null || s.isEmpty())) {
			p.setSeries(null);
			return true;
		}
		p.setSeries(s);
		return true;
	}

	private boolean setAddress(Publication p, boolean req) {
		String s = addressF.getText();
		if (req && (s == null || s.isEmpty())) {
			errMsg.append(p.getType() + " must have an address.\n");
			return false;
		}
		if (!req && (s == null || s.isEmpty())) {
			p.setAddress(null);
			return true;
		}
		p.setAddress(s);
		return true;
	}
	
	private boolean setEdition(Publication p, boolean req) {
		String s = editionF.getText();
		if (req && (s == null || s.isEmpty())) {
			errMsg.append(p.getType() + " must have an edition.\n");
			return false;
		}
		if (!req && (s == null || s.isEmpty())) {
			p.setEdition(null);
			return true;
		}
		p.setEdition(s);
		return true;
	}
	
	private boolean setUrl(Publication p, boolean req) {
		String s = urlF.getText();
		if (req && (s == null || s.isEmpty())) {
			errMsg.append(p.getType() + " must have an URL.\n");
			return false;
		}
		if (!req && (s == null || s.isEmpty())) {
			p.setURL(null);
			return true;
		}
		p.setURL(s);
		return true;
	}
	
	private boolean setHowpublished(Publication p, boolean req) {
		String s = hpF.getText();
		if (req && (s == null || s.isEmpty())) {
			errMsg.append(p.getType() + " must have a howpublished.\n");
			return false;
		}
		if (!req && (s == null || s.isEmpty())) {
			p.setHowpublished(null);
			return true;
		}
		p.setHowpublished(s);
		return true;
	}
	
	private boolean setChaPa(Publication p, boolean req) {
		String s = chapF.getText();
		boolean empty = (s == null || s.isEmpty());
		String s1 = pagesF1.getText();
		String s2 = pagesF2.getText();
		boolean p1empty = (s1 == null || s1.isEmpty());
		boolean p2empty = (s2 == null || s2.isEmpty());
		if (empty && p1empty && p2empty) {
			errMsg.append(p.getType() + " must have chapter or pages.\n");
			return false;
		}
		if (cpBox.getSelectedIndex() == 0) { //chapter
			setChapter(p, req);
			return true;
		} else { // pages
			setPages(p, req);
			return true;
		}
	}
	
	private boolean setChapter(Publication p, boolean req) {
		String s = chapF.getText();
		boolean empty = (s == null || s.isEmpty());
		if (req && empty) {
			errMsg.append(p.getType() + " must have a chapter.\n");
			return false;
		}
		if (!empty && !isNumeric(s)) {
			errMsg.append("chapter must be numeric.\n");
			return false;
		}
		if (!req && empty) {
			p.setChapter(null);
			return true;
		}
		p.setChapter(Integer.valueOf(s));
		return true;
	}
	
	private boolean setVolNum(Publication p, boolean req) {
		String s = volNumF.getText();
		String t = (vnBox.getSelectedIndex() == 0) ? "volume" : "number";
		boolean empty = (s == null || s.isEmpty());
		if (req && empty) {
			errMsg.append(p.getType() + " must have a volume or a number.\n");
			return false;
		}
		if (!empty && !isNumeric(s)) {
			errMsg.append(t + " must be numeric.\n");
			return false;
		}
		if (!req && empty) {
			if (vnBox.getSelectedIndex() == 0) p.setVolume(null);
			else p.setNumber(null);
			return true;
		}
		if (vnBox.getSelectedIndex() == 0) {
			p.setVolume(Integer.valueOf(s));
			return true;
		} else {
			p.setNumber(Integer.valueOf(s));
			return true;
		}
	}
	
	private boolean setPType(Publication p, boolean req) {
		String s = typeF.getText();
		if (req && (s == null || s.isEmpty())) {
			errMsg.append(p.getType() + " must have a PType.\n");
			return false;
		}
		if (!req && (s == null || s.isEmpty())) {
			p.setPType(null);
			return true;
		}
		p.setPType(s);
		return true;
	}
	
	private boolean setOrganization(Publication p, boolean req) {
		String s = orgF.getText();
		if (req && (s == null || s.isEmpty())) {
			errMsg.append(p.getType() + " must have a organization.\n");
			return false;
		}
		if (!req && (s == null || s.isEmpty())) {
			p.setOrganization(null);
			return true;
		}
		p.setOrganization(s);
		return true;
	}
	
	private boolean setSchool(Publication p, boolean req) {
		String s = schoolF.getText();
		if (req && (s == null || s.isEmpty())) {
			errMsg.append(p.getType() + " must have a school.\n");
			return false;
		}
		if (!req && (s == null || s.isEmpty())) {
			p.setSchool(null);
			return true;
		}
		p.setSchool(s);
		return true;
	}
	
	private boolean setInstitution(Publication p, boolean req) {
		String s = instF.getText();
		if (req && (s == null || s.isEmpty())) {
			errMsg.append(p.getType() + " must have an institution.\n");
			return false;
		}
		if (!req && (s == null || s.isEmpty())) {
			p.setInstitution(null);
			return true;
		}
		p.setInstitution(s);
		return true;
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
	
	/* TODO: deal with more than one first name */
	ArrayList<String> constructList(String s){
		ArrayList<String> al = new ArrayList<String>();
		s.replaceAll("AND", "and");
		s.replaceAll(";", " and ");
		String[] sarr = s.split(" and ");
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
	
	private void createElements(){
		titleL = new JLabel("Title: ");
		titleF = new JTextField();
		titleF.setPreferredSize(dim);
		
		authorL = new JLabel("Author(s): ");
		authorF = new JTextField();
		authorF.setPreferredSize(dim);
		
		journalL = new JLabel("Journal: ");
		journalF = new JTextField();
		journalF.setPreferredSize(dim);

		yearL = new JLabel("Year: ");
		yearF = new JTextField();
		yearF.setPreferredSize(dim);
		
		volumeL = new JLabel("Volume: ");
		volumeF = new JTextField();
		volumeF.setPreferredSize(dim);
		
		numberL = new JLabel("Number: ");
		numberF = new JTextField();
		numberF.setPreferredSize(dim);
		
		pagesL = new JLabel("Pages: ");
		pagesPanel = new JPanel();
		pagesF1 = new JTextField();
		pagesF1.setPreferredSize(new Dimension(50,25));
		pagesPanel.add(pagesF1);
		minus = new JLabel(" - ");
		pagesPanel.add(minus);
		pagesF2 = new JTextField();
		pagesF2.setPreferredSize(new Dimension(50,25));
		pagesPanel.add(pagesF2);
		
		monthL = new JLabel("Month: ");
		monthBox = new JComboBox<String>(months);
		monthBox.setPreferredSize(dim);

		keyL = new JLabel("Key: ");
		keyF = new JTextField();
		keyF.setPreferredSize(dim);
		
		noteL = new JLabel("Note: ");
		noteF = new JTextArea();
		noteF.setPreferredSize(new Dimension(200,50));
	
		schoolL = new JLabel("School: ");
		schoolF = new JTextField();
		schoolF.setPreferredSize(dim);
		
		booktitleL = new JLabel("Booktitle: ");
		booktitleF = new JTextField();
		booktitleF.setPreferredSize(dim);
		
		String[] authed = {"Author(s)", "Editor(s)"};
		aeBox = new JComboBox<String>(authed);
		aeBox.setPreferredSize(new Dimension(90, 23));
		authedF = new JTextField();
		authedF.setPreferredSize(dim);
		
		addressL = new JLabel("Address: ");
		addressF = new JTextField();
		addressF.setPreferredSize(dim);
		
		publL = new JLabel("Publisher: ");
		publF = new JTextField();
		publF.setPreferredSize(dim);
		
		String[] volnum = {"Volume", "Number"};
		vnBox = new JComboBox<String>(volnum);
		vnBox.setPreferredSize(new Dimension(90, 23));
		volNumF = new JTextField();
		volNumF.setPreferredSize(dim);
		
		seriesL = new JLabel("Series: ");
		seriesF = new JTextField();
		seriesF.setPreferredSize(dim);
		
		editionL = new JLabel("Edition: ");
		editionF = new JTextField();
		editionF.setPreferredSize(dim);
		
		urlL = new JLabel("URL: ");
		urlF = new JTextField();
		urlF.setPreferredSize(dim);
		
		hpL = new JLabel("How published: ");
		hpF = new JTextField();
		hpF.setPreferredSize(dim);
		
		String[] chapa = {"Chapter", "Pages"};
		cpBox = new JComboBox<String>(chapa);
		cpBox.setPreferredSize(new Dimension(90, 23));
		chapF = new JTextField();
		chapF.setPreferredSize(dim);

		typeL = new JLabel("Type: ");
		typeF = new JTextField();
		typeF.setPreferredSize(dim);
		
		edL = new JLabel("Editor(s): ");
		edF = new JTextField();
		edF.setPreferredSize(dim);
		
		chapL = new JLabel("Chapter: ");
		chapF = new JTextField();
		chapF.setPreferredSize(dim);
		
		orgL = new JLabel("Organization: ");
		orgF = new JTextField();
		orgF.setPreferredSize(dim);
		
		instL = new JLabel("Institution: ");
		instF = new JTextField();
		instF.setPreferredSize(dim);
	}
}
