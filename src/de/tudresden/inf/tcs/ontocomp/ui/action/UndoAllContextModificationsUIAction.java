package de.tudresden.inf.tcs.ontocomp.ui.action;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import de.tudresden.inf.tcs.ontocomp.Constants;

public class UndoAllContextModificationsUIAction  extends AbstractGUIAction {
	
	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(UndoAllContextModificationsUIAction.class);
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Undo all context modifications");
		getViewComponent().getContext().getHistory().undoAll();
		getViewComponent().getContext().reClassifyOntology();
		// this is to refresh the context table. perhaps some 'new' counterexamples are removed
		// by the undo, they should not appear in the context table.
		getViewComponent().getContext().clearObjects();
		getViewComponent().getContext().getObjects();
		getViewComponent().changeGUIState(Constants.ALL_CONTEXT_MODIFICATIONS_UNDONE);
	}

}