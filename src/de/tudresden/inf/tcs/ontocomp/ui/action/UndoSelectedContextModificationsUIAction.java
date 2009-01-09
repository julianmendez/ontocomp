package de.tudresden.inf.tcs.ontocomp.ui.action;

import java.util.Iterator;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import de.tudresden.inf.tcs.oclib.change.AbstractContextModification;
import de.tudresden.inf.tcs.ontocomp.Constants;


public class UndoSelectedContextModificationsUIAction  extends AbstractGUIAction {
	
	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(UndoSelectedContextModificationsUIAction.class);
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Undo selected context modifications");
		for (Iterator<AbstractContextModification> it = 
			getViewComponent().getContext().getHistory().iterator(); it.hasNext();) {
			AbstractContextModification modification = it.next();
			if (getViewComponent().getContextModificationsPanel().isSelected(modification)) {
				getViewComponent().getContext().getHistory().undo(modification);
				logger.debug("Undo: " + modification.getChange().getAxiom());
			}
		}
		getViewComponent().getContext().reClassifyOntology();
		// this is to refresh the context table. perhaps some 'new' counterexamples are removed
		// by the undo, they should not appear in the context table.
		getViewComponent().getContext().clearObjects();
		getViewComponent().getContext().getObjects();
		getViewComponent().changeGUIState(Constants.SELECTED_CONTEXT_MODIFICATIONS_UNDONE);
	}

}
