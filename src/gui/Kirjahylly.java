package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import db.DBManagement;
import pub.Bookstack;
import pub.Publication;

/**
 * Kirjahylly is the class that shows the main window and adds the panels.
 *
 * @author Manuela Hopp
 */
public class Kirjahylly extends JFrame {
	
	static Kirjahylly kh;
	String dir;
	JPanel searchBar;
	static DBManagement dbm;
	private Bookstack defaultBS;
	private SearchResults sr;
	
    private static final long serialVersionUID = 1L;

	public static void main(String[] args) {		
		dbm = new DBManagement();
		kh = new Kirjahylly();
	}
	
	public Kirjahylly() {
		super("Kirjahylly");
		kh = this;
		defaultBS = new Bookstack("unnamed");
		dir = System.getProperty("user.home") + File.separator + "Kirjahylly";
		File f = new File(dir);
		f.mkdirs();
		
		Dimension dim = new Dimension(1200, 600);
		setPreferredSize(dim);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setLayout(new BorderLayout());
		
		addMenuBar();
		
		searchBar = new SearchBar(this);
		add(searchBar, BorderLayout.NORTH);
		sr = new SearchResults();
		add(sr, BorderLayout.CENTER);
		
		pack();
	}

	private void addMenuBar() {
		JMenuBar mb = new JMenuBar();
		JMenu mfile = new JMenu("File");
		JMenuItem miaddbibit = new JMenuItem("Add Bibliographic Item");
		miaddbibit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PubAdd pa = new PubAdd();
				int option = JOptionPane.showConfirmDialog(kh, pa, "", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					Publication p = pa.getPublication();
					if (p != null) {
						defaultBS.addPub(p);
						dbm.insertPublication(p);
						sr.addRow(p);
					} else {
						System.out.println("publication was null.");
					}
				} else {
					System.out.println("option: " + option);
				}
			}
		});
		
		mfile.add(miaddbibit);
		JMenuItem miquit = new JMenuItem("Quit");
        miquit.setAccelerator(KeyStroke.getKeyStroke("control Q"));
		miquit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dbm.cleanUp();
				dispose();
			}
		});
		
		mfile.add(miquit);
		mb.add(mfile);
		setJMenuBar(mb);	
	}
	
	public static Kirjahylly getKH() {
		if (kh == null) kh = new Kirjahylly();
		return kh;
	}
	
	public DBManagement getDBM() {
		if (dbm == null) dbm = new DBManagement();
		return dbm;
	}
	
	public String getDir() {
		return dir;
	}
	
	void setSearchBar(JPanel sb) {
		remove(searchBar);
		searchBar = sb;
		add(sb, BorderLayout.NORTH);
		pack();
	}
	
	public void setBookstack(Bookstack bs) {
		defaultBS = bs;
	}
	
	public Bookstack getCurrBS() {
		return defaultBS;
	}
	
	public SearchResults getSR() {
		if (sr == null) sr = new SearchResults();
		return sr;
	}
}
