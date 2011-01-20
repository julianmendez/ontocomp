package de.tudresden.inf.tcs.ontocomp.ui;


import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import de.tudresden.inf.tcs.oclib.IndividualContext;

/* 
 * OntoComP: a Protégé plugin for completing OWL ontologies.
 * Copyright (C) 2009  Baris Sertkaya
 * 
 * This file is part of OntoComP.
 * OntoComP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OntoComP is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OntoComp.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Table showing the context.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public class ContextTable extends JTable {

	private static final long serialVersionUID = 1L;
	
	protected AbstractTableModel dataModel;
	
	// private static IndividualContext theContext = null;
	
	private IndividualContext context;
	
	public ContextTable(IndividualContext c) {
	// public ContextTable() {
		super();
		context = c;
		dataModel = new AbstractTableModel() {
			private static final long serialVersionUID = 1L;

			public int getColumnCount() {
				// return theContext.getAttributeCount() + 1;
				return getContext().getAttributeCount() + 1;
			}
			
			public int getRowCount() {
				// return theContext.getObjectCount();
				return getContext().getObjectCount();
			}
			
			public Object getValueAt(int row,int column) {
				if (column == 0) {
					// return theContext.getObjectAtIndex(row).getName();
					// return getContext().getObjectAtIndex(row).getName();
					return getContext().getObjectAtIndex(row).getIdentifier();
				}
				// if (theContext.objectHasAttribute(theContext.getObjectAtIndex(row),
				if (getContext().objectHasAttribute(getContext().getObjectAtIndex(row),
						// theContext.getAttributeAtIndex(column-1))) {
						getContext().getAttributeAtIndex(column-1))) {
					return GUIConstants.PLUS_SIGN;
				}
				// if (theContext.objectHasNegatedAttribute(theContext.getObjectAtIndex(row),
				// 		theContext.getAttributeAtIndex(column-1))) {
				// if (getContext().objectHasNegatedAttribute(theContext.getObjectAtIndex(row),
				if (getContext().objectHasNegatedAttribute(getContext().getObjectAtIndex(row),
						getContext().getAttributeAtIndex(column-1))) {
					return GUIConstants.MINUS_SIGN;
				}
				return GUIConstants.QUESTION_MARK;
			}
			
			public String getColumnName(int column) {
				if (column == 0) {
					return " ";
				}
				// return theContext.getAttributeAtIndex(column-1).getIRI().getFragment();
				return getContext().getAttributeAtIndex(column-1).getIRI().getFragment();
			}
			
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		setModel(dataModel);
	}
	
	public void setContext(IndividualContext c) {
		context = c;
	}
	
	public IndividualContext getContext() {
		return context;
	}

}
