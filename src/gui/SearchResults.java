package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JMenu;
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

import db.DBManagement;
import pub.LitManagement;
import pub.Publication;

public class SearchResults extends JPanel {
	
	JTable table;
	DefaultTableModel model;
	ArrayList<Publication> tmp;
	DBManagement dbm;
	LitManagement lm;
    private static final long serialVersionUID = 1L;
	
	SearchResults(){
		super();
		
		dbm = DBManagement.getInstance();
		lm = LitManagement.getLitManagementInstance();
		tmp = new ArrayList<Publication>();
		
		setLayout(new GridLayout());
		String[] columnNames = {"Title", "Author(s)", "Year"};
		
		/* make cells non-editable */
		model = new DefaultTableModel(columnNames, 0){
			private static final long serialVersionUID = 1L;
			@Override 
		    public boolean isCellEditable(int row, int column){
		        return false;
		    }
		};

		table = new JTable(model);
		
		/* make header left aligned */
		((JLabel)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment( JLabel.LEFT );

		/* set maximum width for the year column */
		table.getColumnModel().getColumn(2).setMaxWidth(50);
		
//		model.addRow(new Object[] {0, "Algebrization: A New Barrier in Complexity Theory", "Scott Aaronson, Avi Wigderson", "2009"});
//		model.addRow(new Object[] {1, "Reactive search, a history-sensitive heuristic for MAX-SAT", "Roberto Battiti, Marco Protasi", "1997"});
		
		
		/* add popup menu on table */
        final JPopupMenu popupMenu = new JPopupMenu();
        
        JMenu addToStack = new JMenu("Add to Stack");
        
        ArrayList<String> stacklist = new ArrayList<String>();
        stacklist.addAll(lm.getBSNames());
        for (String s: stacklist) {
        	JMenuItem stackItem = new JMenuItem(s);
        	stackItem.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				int row = table.getSelectedRow();
                    //JOptionPane.showMessageDialog(table, "add item in row " + row + " to stack " + s);
                    lm.getBSByName(s).addPub(getById((Integer)table.getModel().getValueAt(row, 0)));;
    			}
        	});
        	addToStack.add(stackItem);
        }
        
        popupMenu.add(addToStack);
                
        JMenuItem modifyItem = new JMenuItem("Modify");
        modifyItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
                Publication p = tmp.get(row);
				PubAdd pa = new PubAdd(p);
				int option = JOptionPane.showConfirmDialog(null, pa, "modify " + p.getType(), JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) { // otherwise do nothing
					p = pa.getPublication();
					model.setValueAt(p.getTitle(), row, 0);
					model.setValueAt(p.getAuthorString(), row, 1);
					model.setValueAt(p.getYearString(), row, 2);
					dbm.updatePublication(p);
					tmp.set(row, p);
				}
			}
        });
        popupMenu.add(modifyItem);
        
        JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int row = table.getSelectedRow();
                Publication p = tmp.get(row);
                model.removeRow(row);
                dbm.deletePublication(p);
                tmp.remove(row);
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
		tmp.add(p);
		model.addRow(new Object[] {p.getTitle(), p.getAuthorString(), p.getYearString()});
	}
	
	public void addRows(ArrayList<Publication> list) {
		for (Publication pub : list)
			addRow(pub);
	}
	
	public void clear() {
		model.setRowCount(0);
		tmp.clear();
	}
	
	private Publication getById(int id) {
		for (Publication pub : tmp) {
			if (pub.getId() == id) return pub;
		}
		return null;
	}
}
