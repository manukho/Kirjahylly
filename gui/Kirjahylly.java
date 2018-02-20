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
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Kirjahylly extends JFrame {
	
	static Kirjahylly kh;
	String dir;
	JPanel searchBar;

	public static void main(String[] args) {
		new Kirjahylly();
	}
	
	public Kirjahylly() {
		super("Kirjahylly");
		kh = this;
		
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
		add(new SearchResults(), BorderLayout.CENTER);
		
		pack();
	}

	private void addMenuBar() {
		JMenuBar mb = new JMenuBar();
		JMenu mfile = new JMenu("File");
		JMenuItem miquit = new JMenuItem("Quit");
        miquit.setAccelerator(KeyStroke.getKeyStroke("control Q"));
		miquit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
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
	
	public String getDir() {
		return dir;
	}
	
	void setSearchBar(JPanel sb) {
		remove(searchBar);
		searchBar = sb;
		add(sb, BorderLayout.NORTH);
		pack();
	}
}
