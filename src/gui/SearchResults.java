package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import pub.Publication;

public class SearchResults extends JPanel {
	
	JTable table;
	DefaultTableModel model;
	ArrayList<Publication> tmp;
	DBManagement dbm;
	Bookstacks bs;
    private static final long serialVersionUID = 1L;
    private JMenu addToStack;
    private static SearchResults sr;
	
    public static SearchResults getSR() {
    	if (sr == null) sr = new SearchResults();
    	return sr;
    }
    
	private SearchResults(){
		super();
		
		dbm = DBManagement.getInstance();
		tmp = new ArrayList<Publication>();
		bs = Bookstacks.getInstance();
		
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
		
		table.getTableHeader().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {
		        int col = table.columnAtPoint(e.getPoint());
		        ArrayList<Publication> list = new ArrayList<Publication>();
		        switch (col){
		        case 0: // sort by title
		        	list.addAll(dbm.sortByTitle(tmp));
		        	clear();
		        	addRows(list);
		        	break;
		        case 1: // sort by author
		        	list.addAll(dbm.sortByAuthors(tmp));
		        	clear();
		        	addRows(list);
		        	break;
		        case 2: // sort by year
		        	list.addAll(dbm.sortByYear(tmp));
		        	clear();
		        	addRows(list);
		        	break;
		        }
			}
		});
		
		/* add popup menu on table */
        final JPopupMenu popupMenu = new JPopupMenu();
        
        addToStack = new JMenu("Add to Stack");
        
        reloadStackMenu();
        
        popupMenu.add(addToStack);
                
        JMenuItem modifyItem = new JMenuItem("Modify");
        modifyItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
                Publication p = tmp.get(row);
				PubAdd pa = new PubAdd(p, false);
				int option = JOptionPane.showConfirmDialog(null, pa, "modify " + p.getType(), JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) { // otherwise do nothing
					if (!pa.validateInput()) {
						String errMsg = pa.getErrMsg();
						JOptionPane.showMessageDialog(sr, errMsg, "Error", JOptionPane.ERROR_MESSAGE);
						pa.clearErrMsg();
					} else {
						p = pa.getPublication();//						
						if (p != null) {
							replaceRow(row, p);
							dbm.updatePublication(p);
							tmp.set(row, p);
						}
					}	
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
                    	reloadStackMenu();
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
	
	private void reloadStackMenu() {
    	addToStack.removeAll();
        ArrayList<String> stacklist = bs.getStackNames();
        for (String s: stacklist) {
        	JMenuItem stackItem = new JMenuItem(s);
        	stackItem.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				int row = table.getSelectedRow();
                    bs.getStackByName(s).addPub(tmp.get(row));
    			}
        	});
        	addToStack.add(stackItem);
        }		
	}

	public void addRow(Publication p) {
		tmp.add(p);
		Object[] obj = new Object[] {p.getTitle(), p.getAuthorString(), p.getYearString()};
		model.addRow(obj);
	}
	
	public void replaceRow(int row, Publication p) {
		tmp.set(row, p);
		model.setValueAt(p.getTitle(), row, 0);
		model.setValueAt(p.getAuthorString(), row, 1);
		model.setValueAt(p.getYearString(), row, 2);
	}
	
	public void addRows(ArrayList<Publication> list) {
		for (Publication pub : list) {
			addRow(pub);
		}
		 model.fireTableDataChanged();
		 table.repaint();
	}
	
	public void clear() {
		model.setRowCount(0);
		tmp.clear();
	}
}
