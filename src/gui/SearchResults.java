package gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pub.Publication;

public class SearchResults extends JPanel {
	
	JTable table;
	DefaultTableModel model;
    private static final long serialVersionUID = 1L;
	
	SearchResults(){
		super();
		setLayout(new GridLayout());
		String[] columnNames = {"Title", "Author(s)", "Year"};
		table = new JTable(new DefaultTableModel(columnNames, 0));
		((JLabel)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment( JLabel.LEFT );
		
		table.getColumnModel().getColumn(2).setMaxWidth(50);
		model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[] {"Algebrization: A New Barrier in Complexity Theory", "Scott Aaronson, Avi Wigderson", "2009"});
		model.addRow(new Object[] {"Reactive search, a history-sensitive heuristic for MAX-SAT", "Roberto Battiti, Marco Protasi", "1997"});
		
		table.setSize(1000, 400);
		JScrollPane sp = new JScrollPane(table);
		sp.setSize(new Dimension(1000, 400));
		
		add(sp);
	}
	
	public void addRow(Publication p) {
		String authors;
		if (p.getAuthors() == null || p.getAuthors().isEmpty()) {
			authors = "";
		} else {			
			authors = p.getAuthors().get(0);
			for (int i = 1; i < p.getAuthors().size(); i++) {
				authors = authors + " and " + p.getAuthors().get(i);
			}
		}
		model.addRow(new Object[] {p.getTitle(), authors, p.getYearString()});
		table.revalidate();
	}


}
