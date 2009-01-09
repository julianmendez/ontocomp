package de.tudresden.inf.tcs.ontocomp.ui;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultCellEditor;

import org.apache.log4j.Logger;

import de.tudresden.inf.tcs.ontocomp.Constants;

public class CounterExampleCandidatesTable extends JTable {

	private static final long serialVersionUID = 1L;
	
	protected AbstractTableModel dataModel;
	
	private JComboBox choicesComboBox = new JComboBox();
	
	private boolean ontologyConsistent = true;
	
	private OntoComPViewComponent viewComponent;
	
	private int actionCounter = 0;
	
	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(CounterExampleCandidatesTable.class);
	
	public CounterExampleCandidatesTable(OntoComPViewComponent vc) {
		super();
		viewComponent = vc;
		dataModel = new AbstractTableModel() {
			private static final long serialVersionUID = 1L;

			public int getColumnCount() {
				return getViewComponent().getContext().getAttributeCount() + 1;
			}
			
			public int getRowCount() {
				return getViewComponent().getContext().getCounterExampleCandidates().size();
			}
			
			public Object getValueAt(int row,int column) {
				if (column == 0) {
					return getViewComponent().getContext().getCounterExampleCandidates().get(row).getURI().getFragment();
				}
				if (getViewComponent().getContext().objectHasAttribute(
						getViewComponent().getContext().getCounterExampleCandidates().get(row),
						getViewComponent().getContext().getAttributeAtIndex(column-1))) {
					return GUIConstants.PLUS_SIGN;
				}
				if (getViewComponent().getContext().objectHasNegatedAttribute(
						getViewComponent().getContext().getCounterExampleCandidates().get(row),
						getViewComponent().getContext().getAttributeAtIndex(column-1))) {
					return GUIConstants.MINUS_SIGN;
				}
				return GUIConstants.QUESTION_MARK;
			}
			
			public String getColumnName(int column) {
				if (column == 0) {
					return " ";
				}
				return getViewComponent().getContext().getAttributeAtIndex(column-1).getURI().getFragment();
			}
			
			public boolean isCellEditable(int row,int column) {
				return ontologyConsistent && getValueAt(row,column).equals(GUIConstants.QUESTION_MARK);
			}
			
			public void setValueAt(Object value, int row, int column) {
				if (value.equals(GUIConstants.PLUS_SIGN)) {
					incrementActionCounter();
					getViewComponent().getContext().addAttributeToObject(
							getViewComponent().getContext().getAttributeAtIndex(column-1),
							getViewComponent().getContext().getCounterExampleCandidates().get(row));
				}
				else if (value.equals(GUIConstants.MINUS_SIGN)) {
					incrementActionCounter();
					getViewComponent().getContext().addNegatedAttributeToObject(
							getViewComponent().getContext().getAttributeAtIndex(column-1),
							getViewComponent().getContext().getCounterExampleCandidates().get(row));
				}
				else {
					return;
				}
				// getViewComponent().setUndoButtons(true);
				getViewComponent().changeGUIState(Constants.COUNTEREXAMLE_CANDIDATE_MODIFIED);
				if (getViewComponent().getContext().isOntologyConsistent()) {
					logger.debug("Ontology stayed consistent");
					getViewComponent().getContext().getCounterExampleCandidates().update();
					dataModel.fireTableDataChanged();
					if (getViewComponent().getContext().refutes(getViewComponent().getContext().getCurrentQuestion())) {
						logger.info("Ontology refutes the question " + 
								getViewComponent().getContext().getCurrentQuestion());
						// getViewComponent().setReadyButton(true);
						getViewComponent().changeGUIState(Constants.COUNTEREXAMPLE_READY);
					}
				}
				else {
					logger.debug("Ontology became inconsistent");
					// TODO: disallow everything but Undo!
				}
			}
		};
		setModel(dataModel);
	    choicesComboBox.addItem(GUIConstants.PLUS_SIGN);
	    choicesComboBox.addItem(GUIConstants.MINUS_SIGN);
	    choicesComboBox.addItem(GUIConstants.QUESTION_MARK);
	    setDefaultEditor(Character.class, new DefaultCellEditor(choicesComboBox));
	}
	
	public AbstractTableModel getTableModel() {
		return dataModel;
	}
	
	public OntoComPViewComponent getViewComponent() {
		return viewComponent;
	}
	
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
	// public CounterExampleCandidates getCandidates() {
	// 	return candidates;
	// }
	
	public boolean canUndo() {
		return actionCounter != 0;
	}
	
	public void decrementActionCounter() {
		--actionCounter;
	}

	public void incrementActionCounter() {
		++actionCounter;
	}
	
	public int getActionCounter() {
		return actionCounter;
	}
	
	public void resetActionCounter() {
		actionCounter = 0;
	}
}
