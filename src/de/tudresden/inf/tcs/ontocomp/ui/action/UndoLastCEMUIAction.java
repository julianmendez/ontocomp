package de.tudresden.inf.tcs.ontocomp.ui.action;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import de.tudresden.inf.tcs.ontocomp.Constants;

public class UndoLastCEMUIAction extends AbstractGUIAction {

	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(UndoLastCEMUIAction.class);
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Undo last counterexample candidate modification");
		getViewComponent().getContext().getHistory().undoLast();
		getViewComponent().getContext().reClassifyOntology();
		getViewComponent().getCounterExampleCandidatesTable().decrementActionCounter();
		if (!getViewComponent().getCounterExampleCandidatesTable().canUndo()) {
		 		getViewComponent().setUndoCEMButtons(false);
		}
		if (getViewComponent().isReadyButtonEnabled() && 
				!getViewComponent().getContext().refutes(getViewComponent().getContext().
						getCurrentQuestion())) {
			getViewComponent().enableReadyButton(false);
		}
		getViewComponent().getContext().getCounterExampleCandidates().update();
		getViewComponent().getCounterExampleCandidatesTable().getTableModel().fireTableDataChanged();
		getViewComponent().changeGUIState(Constants.LAST_COUNTEREXAMLE_MODIFICATION_UNDONE);
	}
}
