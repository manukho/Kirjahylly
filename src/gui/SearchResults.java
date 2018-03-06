package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;

import pub.Publication;

public class SearchResults extends JPanel {
	
	JTable table;
	DefaultTableModel model;
    private static final long serialVersionUID = 1L;
	
	SearchResults(){
		super();
		setLayout(new GridLayout());
		String[] columnNames = {"id", "Title", "Author(s)", "Year"};
		table = new JTable(new DefaultTableModel(columnNames, 0));
		((JLabel)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment( JLabel.LEFT );

		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(3).setMaxWidth(50);
		model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[] {0, "Algebrization: A New Barrier in Complexity Theory", "Scott Aaronson, Avi Wigderson", "2009"});
		model.addRow(new Object[] {1, "Reactive search, a history-sensitive heuristic for MAX-SAT", "Roberto Battiti, Marco Protasi", "1997"});
		
		
		/* add popup menu on table */
        final JPopupMenu popupMenu = new JPopupMenu();
        
        JMenuItem modifyItem = new JMenuItem("Modify");
        modifyItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
                JOptionPane.showMessageDialog(table, "Right-click performed on table and choose MODIFY on row " + row);
			}
        });
        popupMenu.add(modifyItem);
        
        JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int row = table.getSelectedRow();
                JOptionPane.showMessageDialog(table, "Right-click performed on table and choose DELETE on row " + row);
            }
        });
        popupMenu.add(deleteItem);
        
        popupMenu.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        int row = table.rowAtPoint(SwingUtilities.convertPoint(popupMenu, new Point(0, 0), table));
                        if (row > -1) {
                            table.setRowSelectionInterval(row, row);
                        }
                    }
                });
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {}
        });
        
        table.setComponentPopupMenu(popupMenu);
		
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
		model.addRow(new Object[] {p.getId(), p.getTitle(), authors, p.getYearString()});
		table.revalidate();
	}


}
