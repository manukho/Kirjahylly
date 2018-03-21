package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JDialog;
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
				final JOptionPane optionPane = new JOptionPane(pa, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
				final JDialog dialog = new JDialog(kh, true);
				dialog.setContentPane(optionPane);
				dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				optionPane.addPropertyChangeListener(new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent e) {
						String prop = e.getPropertyName();
						if (dialog.isVisible() && (e.getSource() == optionPane) && (prop.equals(JOptionPane.VALUE_PROPERTY)) && (optionPane.getValue() != JOptionPane.UNINITIALIZED_VALUE)) {
							int value = ((Integer)optionPane.getValue()).intValue();
							if (value == JOptionPane.OK_OPTION) {
								if (!pa.validateInput()) {
									String errMsg = pa.getErrMsg();
									 JOptionPane.showMessageDialog(kh, errMsg, "Error", JOptionPane.ERROR_MESSAGE); 
									 pa.clearErrMsg();
									 optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);
								} else {
									Publication p = pa.getPublication();
									if (p != null) {
										defaultBS.addPub(p);
										dbm.insertPublication(p);
										sr.addRow(p);
									}
									dialog.dispose();
								}									
							} else {
								dialog.dispose();
							}
						}
					}
				});
				dialog.pack();
				dialog.setVisible(true);
			}
		});
		
		mfile.add(miaddbibit);
		
		JMenuItem miaddfbibtex = new JMenuItem("Add Bibliographic Item from BibTex");
		miaddfbibtex.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BibImport bi = new BibImport();
				int option = JOptionPane.showConfirmDialog(kh, bi, "", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					ArrayList<Publication> list = bi.getPublications();
					for (Publication p : list) {
						PubAdd pa = new PubAdd(p, true);
						final JOptionPane optionPane = new JOptionPane(pa, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
						final JDialog dialog = new JDialog(kh, true);
						dialog.setContentPane(optionPane);
						dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
						optionPane.addPropertyChangeListener(new PropertyChangeListener() {
							public void propertyChange(PropertyChangeEvent e) {
								String prop = e.getPropertyName();
								if (dialog.isVisible() && (e.getSource() == optionPane) && (prop.equals(JOptionPane.VALUE_PROPERTY)) && (optionPane.getValue() != JOptionPane.UNINITIALIZED_VALUE)) {
									int value = ((Integer)optionPane.getValue()).intValue();
									if (value == JOptionPane.OK_OPTION) {
										if (!pa.validateInput()) {
											String errMsg = pa.getErrMsg();
											 JOptionPane.showMessageDialog(kh, errMsg, "Error", JOptionPane.ERROR_MESSAGE); 
											 pa.clearErrMsg();
											 optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);
										} else {
											Publication p = pa.getPublication();
											if (p != null) {
												defaultBS.addPub(p);
												dbm.insertPublication(p);
												sr.addRow(p);
											}
											dialog.dispose();
										}									
									} else {
										dialog.dispose();
									}
								}
							}
						});
						dialog.pack();
						dialog.setVisible(true);
					}
				}
			}
		});
		
		mfile.add(miaddfbibtex);
		
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
		sr.validate();
		return sr;
	}
}
