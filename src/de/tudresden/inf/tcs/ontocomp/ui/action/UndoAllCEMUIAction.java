package de.tudresden.inf.tcs.ontocomp.ui.action;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import de.tudresden.inf.tcs.ontocomp.Constants;

public class UndoAllCEMUIAction extends AbstractGUIAction {

	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(UndoAllCEMUIAction.class);
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Undo all counterexample candidate modifications");
		getViewComponent().getContext().getHistory().undoLastN(
				getViewComponent().getCounterExampleCandidatesTable().getActionCounter());
		getViewComponent().getContext().reClassifyOntology();
		getViewComponent().getCounterExampleCandidatesTable().resetActionCounter();
		getViewComponent().setUndoCEMButtons(false);
		if (getViewComponent().isReadyButtonEnabled() && 
				!getViewComponent().getContext().refutes(getViewComponent().getContext().
						getCurrentQuestion())) {
			getViewComponent().enableReadyButton(false);
		}
		getViewComponent().getContext().getCounterExampleCandidates().update();
		getViewComponent().getCounterExampleCandidatesTable().getTableModel().fireTableDataChanged();
		getViewComponent().changeGUIState(Constants.ALL_COUNTEREXAMLE_MODIFICATIONS_UNDONE);
	}

}
