package de.tudresden.inf.tcs.ontocomp.ui;


import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import de.tudresden.inf.tcs.oclib.IndividualContext;

public class ContextTable extends JTable {

	private static final long serialVersionUID = 1L;
	
	protected AbstractTableModel dataModel;
	
	private static IndividualContext theContext = null;
	
	public ContextTable(IndividualContext context) {
		super();
		theContext = context;
		dataModel = new AbstractTableModel() {
			private static final long serialVersionUID = 1L;

			public int getColumnCount() {
				return theContext.getAttributeCount() + 1;
			}
			
			public int getRowCount() {
				return theContext.getObjectCount();
			}
			
			public Object getValueAt(int row,int column) {
				if (column == 0) {
					return theContext.getObjectAtIndex(row).getURI().getFragment();
				}
				if (theContext.objectHasAttribute(theContext.getObjectAtIndex(row),
						theContext.getAttributeAtIndex(column-1))) {
					return GUIConstants.PLUS_SIGN;
				}
				if (theContext.objectHasNegatedAttribute(theContext.getObjectAtIndex(row),
						theContext.getAttributeAtIndex(column-1))) {
					return GUIConstants.MINUS_SIGN;
				}
				return GUIConstants.QUESTION_MARK;
			}
			
			public String getColumnName(int column) {
				if (column == 0) {
					return " ";
				}
				return theContext.getAttributeAtIndex(column-1).getURI().getFragment();
			}
			
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		setModel(dataModel);
	}

}
