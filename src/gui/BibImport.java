package gui;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import bibtex.BibTexParser;
import pub.Publication;

public class BibImport extends JPanel {
	
	JTextArea textArea;
	
	public BibImport() {
		Dimension d = new Dimension(600,300);
		setPreferredSize(d);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		textArea = new JTextArea();
		textArea.setSize(580, 280);
		
		add(textArea);
	}

	public ArrayList<Publication> getPublications() {
		ArrayList<Publication> list = new ArrayList<Publication>();
		BibTexParser btp = new BibTexParser(textArea.getText());
		return list;
	}
}
