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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import pub.*;

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
	StringBuilder errMsg;
	/**
	 * constructor for the form for adding a new bibliographic item
	 */
	PubAdd(){
		modify = false;
		Dimension d = new Dimension(600,600);
		setPreferredSize(d);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		errMsg = new StringBuilder();;
		
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
			if (ib.getPType() != null) typeF.setText(ib.getPType());
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
			if (ic.getPType() != null) typeF.setText(ic.getPType());
			if (ic.getChapter() != null) chapF.setText(ic.getChapter().toString());
			if (ic.getFirstPage() != null) pagesF1.setText(ic.getFirstPage().toString());
			if (ic.getFirstPage() != null) pagesF2.setText(ic.getLastPage().toString());
			if (ic.getAddress() != null && !ic.getAddress().isEmpty()) addressF.setText(ic.getAddress());
			if (ic.getEdition() != null && !ic.getEdition().isEmpty()) editionF.setText(ic.getEdition());
			if (ic.getMonth() != null && !ic.getMonth().isEmpty()) monthBox.setSelectedIndex(getMonthIndex(ic.getMonth()));
			if (ic.getNote() != null && !ic.getNote().isEmpty()) noteF.setText(ic.getNote());
			if (ic.getKey() != null && !ic.getKey().isEmpty()) keyF.setText(ic.getKey());		
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
			if (ip.getSeries() != null && !ip.getSeries().isEmpty()) seriesF.setText(ip.getSeries());
			if (ip.getFirstPage() != null) pagesF1.setText(ip.getFirstPage().toString());
			if (ip.getFirstPage() != null) pagesF2.setText(ip.getLastPage().toString());
			if (ip.getAddress() != null && !ip.getAddress().isEmpty()) addressF.setText(ip.getAddress());
			if (ip.getMonth() != null && !ip.getMonth().isEmpty()) monthBox.setSelectedIndex(getMonthIndex(ip.getMonth()));
			if (ip.getOrganization() != null && !ip.getOrganization().isEmpty()) orgF.setText(ip.getOrganization());
			if (ip.getPublisher() != null && !ip.getPublisher().isEmpty()) publF.setText(ip.getPublisher());
			if (ip.getNote() != null && !ip.getNote().isEmpty()) noteF.setText(ip.getNote());
			if (ip.getKey() != null && !ip.getKey().isEmpty()) keyF.setText(ip.getKey());	
		}
		if (p.getType().equals("manual")) {
			Manual m = (Manual) p;
			showManual();
			titleF.setText(m.getTitle());
			authorF.setText(m.getAuthorString());
			if (m.getOrganization() != null && !m.getOrganization().isEmpty()) orgF.setText(m.getOrganization());
			if (m.getAddress() != null && !m.getAddress().isEmpty()) addressF.setText(m.getAddress());
			if (m.getEdition() != null && !m.getEdition().isEmpty()) editionF.setText(m.getEdition());
			if (m.getMonth() != null && !m.getMonth().isEmpty()) monthBox.setSelectedIndex(getMonthIndex(m.getMonth()));
			yearF.setText(m.getYearString());
			if (m.getNote() != null && !m.getNote().isEmpty()) noteF.setText(m.getNote());
			if (m.getKey() != null && !m.getKey().isEmpty()) keyF.setText(m.getKey());	
		}
		if (p.getType().equals("mastersthesis")) {
			Mastersthesis m = (Mastersthesis) p;
			showMastersthesis();
			titleF.setText(m.getTitle());
			authorF.setText(m.getAuthorString());
			schoolF.setText(m.getSchool());
			yearF.setText(m.getYearString());
			if (m.getPType() != null && !m.getPType().isEmpty()) typeF.setText(m.getPType());
			if (m.getAddress() != null && !m.getAddress().isEmpty()) addressF.setText(m.getAddress());
			if (m.getMonth() != null && !m.getMonth().isEmpty()) monthBox.setSelectedIndex(getMonthIndex(m.getMonth()));
			if (m.getNote() != null && !m.getNote().isEmpty()) noteF.setText(m.getNote());
			if (m.getKey() != null && !m.getKey().isEmpty()) keyF.setText(m.getKey());	
		}
		if (p.getType().equals("misc")) {
			Misc m = (Misc) p;
			showMisc();
			titleF.setText(m.getTitle());
			authorF.setText(m.getAuthorString());
			if (m.getHowpublished() != null && !m.getHowpublished().isEmpty()) hpF.setText(m.getHowpublished());
			if (m.getMonth() != null && !m.getMonth().isEmpty()) monthBox.setSelectedIndex(getMonthIndex(m.getMonth()));
			yearF.setText(m.getYearString());
			if (m.getNote() != null && !m.getNote().isEmpty()) noteF.setText(m.getNote());
			if (m.getKey() != null && !m.getKey().isEmpty()) keyF.setText(m.getKey());	
		}
		if (p.getType().equals("phdthesis")) {
			Phdthesis pt = (Phdthesis) p;
			showPhdthesis();
			titleF.setText(pt.getTitle());
			authorF.setText(pt.getAuthorString());
			schoolF.setText(pt.getSchool());
			yearF.setText(pt.getYearString());
			if (pt.getPType() != null && !pt.getPType().isEmpty()) typeF.setText(pt.getPType());
			if (pt.getAddress() != null && !pt.getAddress().isEmpty()) addressF.setText(pt.getAddress());
			if (pt.getMonth() != null && !pt.getMonth().isEmpty()) monthBox.setSelectedIndex(getMonthIndex(pt.getMonth()));
			if (pt.getNote() != null && !pt.getNote().isEmpty()) noteF.setText(pt.getNote());
			if (pt.getKey() != null && !pt.getKey().isEmpty()) keyF.setText(pt.getKey());	
		}
		if (p.getType().equals("proceedings")) {
			Proceedings pr = (Proceedings) p;
			showProceedings();
			titleF.setText(pr.getTitle());
			yearF.setText(pr.getYearString());
			edF.setText(pr.getEditorString());
			if (pr.getVolume() != null) volumeF.setText(pr.getVolume().toString());
			if (pr.getNumber() != null) numberF.setText(pr.getNumber().toString());
			if (pr.getSeries() != null && !pr.getSeries().isEmpty()) seriesF.setText(pr.getSeries());
			if (pr.getAddress() != null && !pr.getAddress().isEmpty()) addressF.setText(pr.getAddress());
			if (pr.getMonth() != null && !pr.getMonth().isEmpty()) monthBox.setSelectedIndex(getMonthIndex(pr.getMonth()));
			if (pr.getOrganization() != null && !pr.getOrganization().isEmpty()) orgF.setText(pr.getOrganization());
			if (pr.getPublisher() != null && !pr.getPublisher().isEmpty()) publF.setText(pr.getPublisher());
			if (pr.getNote() != null && !pr.getNote().isEmpty()) noteF.setText(pr.getNote());
			if (pr.getKey() != null && !pr.getKey().isEmpty()) keyF.setText(pr.getKey());	
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
			if (tr.getAddress() != null && !tr.getAddress().isEmpty()) addressF.setText(tr.getAddress());
			if (tr.getMonth() != null && !tr.getMonth().isEmpty()) monthBox.setSelectedIndex(getMonthIndex(tr.getMonth()));
			if (tr.getNote() != null && !tr.getNote().isEmpty()) noteF.setText(tr.getNote());
			if (tr.getKey() != null && !tr.getKey().isEmpty()) keyF.setText(tr.getKey());	
		}
		if (p.getType().equals("unpublished")) {
			Unpublished u = (Unpublished) p;
			showUnpublished();
			titleF.setText(u.getTitle());
			authorF.setText(u.getAuthorString());
			yearF.setText(u.getYearString());
			if (u.getMonth() != null && !u.getMonth().isEmpty()) monthBox.setSelectedIndex(getMonthIndex(u.getMonth()));
			if (u.getNote() != null && !u.getNote().isEmpty()) noteF.setText(u.getNote());
			if (u.getKey() != null && !u.getKey().isEmpty()) keyF.setText(u.getKey());	
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
		if (req) noteL.setForeground(Color.RED);
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
						pagesF1 = new JTextField();
						pagesF1.setPreferredSize(new Dimension(50,25));
						pagesPanel.add(pagesF1);
						JLabel minus = new JLabel(" - ");
						pagesPanel.add(minus);
						pagesF2 = new JTextField();
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
		JLabel edL = new JLabel("Editor(s): ");
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
	
	Publication getPublication() {
		if (!modify) pubClassSel = (String) pubClassList.getSelectedItem();
		boolean bool = true;
		if (pubClassSel.equals("article")) {
			Article a = new Article();
			if (modify) a.setId(id);
			bool &= setTitle(a, true);
			bool &= setAuthors(a, true);
			bool &= setJournal(a, true);
			bool &= setYear(a, true);
			bool &= setVolume(a, true);
			bool &= setNumber(a, false);
			bool &= setPages(a, false);
			bool &= setMonth(a, false);
			bool &= setNote(a, false);
			bool &= setKey(a, false);
			if (bool) {
				return a;
			}
		}
		if (pubClassSel.equals("book")) {
			Book b = new Book();
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
			if (bool) return b;
			
		}
		if (pubClassSel.equals("booklet")) {
			Booklet b = new Booklet();
			bool &= setTitle(b, true);
			bool &= setAuthors(b, false);
			bool &= setHowpublished(b, false);
			bool &= setAddress(b, false);
			bool &= setMonth(b, false);
			bool &= setYear(b, false);
			bool &= setKey(b, false);
			bool &= setNote(b, false);
			if (bool) return b;
		}
		if (pubClassSel.equals("inbook")) {
			Inbook b = new Inbook();
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
			if (bool) return b;
		}
		if (pubClassSel.equals("incollection")) {
			Incollection b = new Incollection();
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
			if (bool) return b;
		}
		if (pubClassSel.equals("inproceedings")) {
			Inproceedings b = new Inproceedings();
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
			if (bool) return b;
		}
		if (pubClassSel.equals("manual")) {
			Manual b = new Manual();
			bool &= setTitle(b, true);
			bool &= setAuthors(b, false);
			bool &= setOrganization(b, false);
			bool &= setAddress(b, false);
			bool &= setEdition(b, false);
			bool &= setMonth(b, false);
			bool &= setYear(b, false);
			bool &= setKey(b, false);
			bool &= setNote(b, false);
			if (bool) return b;
		}
		if (pubClassSel.equals("mastersthesis")) {
			Mastersthesis b = new Mastersthesis();
			bool &= setTitle(b, true);
			bool &= setAuthors(b, true);
			bool &= setSchool(b, true);
			bool &= setYear(b, true);
			bool &= setPType(b, false);
			bool &= setAddress(b, false);
			bool &= setMonth(b, false);
			bool &= setKey(b, false);
			bool &= setNote(b, false);
			if (bool) return b;
		}
		if (pubClassSel.equals("misc")) {
			Misc b = new Misc();
			bool &= setTitle(b, false);
			bool &= setAuthors(b, false);
			bool &= setHowpublished(b, false);
			bool &= setMonth(b, false);
			bool &= setYear(b, false);
			bool &= setKey(b, false);
			bool &= setNote(b, false);
			if (bool) return b;
		}
		if (pubClassSel.equals("phdthesis")) {
			Phdthesis b = new Phdthesis();
			bool &= setTitle(b, true);
			bool &= setAuthors(b, true);
			bool &= setSchool(b, true);
			bool &= setYear(b, true);
			bool &= setPType(b, false);
			bool &= setAddress(b, false);
			bool &= setMonth(b, false);
			bool &= setKey(b, false);
			bool &= setNote(b, false);
			if (bool) return b;
		}
		if (pubClassSel.equals("proceedings")) {
			Proceedings b = new Proceedings();
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
			if (bool) return b;
		}
		if (pubClassSel.equals("techreport")) {
			Techreport b = new Techreport();
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
			if (bool) return b;
		}
		if (pubClassSel.equals("unpublished")) {
			Unpublished b = new Unpublished();
			bool &= setTitle(b, true);
			bool &= setAuthors(b, true);
			bool &= setNote(b, true);
			bool &= setMonth(b, false);
			bool &= setYear(b, false);
			bool &= setKey(b, false);
			if (bool) return b;
		}		
		
		/* TODO: keep window open if there are errors */
		int option = JOptionPane.showConfirmDialog(this, errMsg.toString());
		return null;
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
			System.out.println("!req && empty");
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
