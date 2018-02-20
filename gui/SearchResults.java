package gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SearchResults extends JPanel {
	
	SearchResults(){
		super();
		setLayout(new GridLayout());
		String[] columnNames = {"Title", "Author(s)", "Year"};
		Object[][] data = {{"Algebrization: A New Barrier in Complexity Theory", "Scott Aaronson, Avi Wigderson", "2009"},
				{"Reactive search, a history-sensitive heuristic for MAX-SAT", "Roberto Battiti, Marco Protasi", "1997"}
		};
		JTable table = new JTable(data, columnNames);
		table.setSize(1000, 400);
		JScrollPane sp = new JScrollPane(table);
		sp.setSize(new Dimension(1000, 400));
		
		add(sp);
	}

}
