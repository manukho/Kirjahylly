package gui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import bibtex.BibTexExporter;
import pub.Bookstack;

public class Bookstacks extends JPanel {
	
	JList<Bookstack> list;
    private static final long serialVersionUID = 1L;
    private DefaultListModel<Bookstack> model;
    private int bsNum;
    private static Bookstacks bs;
    private SearchResults sr;
    
	public static Bookstacks getInstance() {
		if (bs == null) bs = new Bookstacks();
		return bs;
	}
	
	private Bookstacks(){
		super();
		this.setPreferredSize(new Dimension(150, 0));
		JButton newStack = new JButton("New Stack");
		newStack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addStack();
			}
		});
		model = new DefaultListModel<Bookstack>();
		list = new JList<Bookstack>(model);
		list.setFixedCellHeight(40);
		final JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem rename = new JMenuItem("rename");
		rename.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: implement rename
			}
		});
		popupMenu.add(rename);
		JMenuItem delete = new JMenuItem("delete");
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex() == 0) { // we won't delete 'default', just clear it
					list.getSelectedValue().clearStack();
				} else {
					model.removeElementAt(list.getSelectedIndex());
					list.setSelectedIndex(0);
				}
			}
		});
		popupMenu.add(delete);
		JMenuItem exportBibtex = new JMenuItem("export BibTeX");
		exportBibtex.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String bibtex = new BibTexExporter().toBibTex(list.getSelectedValue().getPublications());
				System.out.println("what should we do with a bibtex entry early in the morning?");
				System.out.println(bibtex);
			}
		});
		popupMenu.add(exportBibtex);
		popupMenu.addPopupMenuListener(new PopupMenuListener() {

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						int index = list.locationToIndex(SwingUtilities.convertPoint(popupMenu, new Point(0,0), list));
                        if (index > -1) {
                        	list.setSelectedIndex(index);
                        }
					}});
			}
		});
		
		list.setComponentPopupMenu(popupMenu);
		list.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					System.out.println("Left button on row " + list.locationToIndex(e.getPoint()));
					list.setSelectedIndex(list.locationToIndex(e.getPoint()));
					System.out.println("should show content of selected stack now");
					sr = SearchResults.getSR();
					sr.clear();
					sr.addRows(list.getSelectedValue().getPublications());
				}
			}
		});
		addStack("default");
		add(newStack);
		add(list);
		
		bsNum = 0;
	}
	
	private void addStack(String name) {
		Bookstack bs = new Bookstack(name);
		model.addElement(bs);
	}
	
	private void addStack() {
		Bookstack bs = new Bookstack("stack " + bsNum);
		bsNum++;
		model.addElement(bs);
	}

	public Bookstack getStackByName(String s) {
		Bookstack b = null;
		for (int i = 0; i < model.size(); i++) {
			if (model.elementAt(i).toString().equals(s)) {
				b = model.elementAt(i);
			}
		}
		return b;
	}
	
	public ArrayList<String> getStackNames(){
		ArrayList<String> s = new ArrayList<String>();
		for (int i = 0; i < model.size(); i++) {
			s.add(model.elementAt(i).toString());
		}
		return s;
	}
}
