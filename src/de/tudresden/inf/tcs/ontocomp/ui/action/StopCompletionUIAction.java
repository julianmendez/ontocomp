package de.tudresden.inf.tcs.ontocomp.ui.action;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import org.semanticweb.owl.model.OWLClass;
import org.semanticweb.owl.model.OWLIndividual;

import de.tudresden.inf.tcs.fcalib.action.StopExplorationAction;
import de.tudresden.inf.tcs.ontocomp.Constants;
import de.tudresden.inf.tcs.ontocomp.ui.OntoComPViewComponent;

/**
 * The expert action that stops the completion.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

// public class StopCompletionAction extends AbstractGUIAction {
public class StopCompletionUIAction extends StopExplorationAction<OWLClass,OWLIndividual> {

	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(StopCompletionUIAction.class);
	
	private OntoComPViewComponent viewComponent;
	
	public OntoComPViewComponent getViewComponent() {
		return viewComponent;
	}
	
	public void setViewComponent(OntoComPViewComponent v) {
		viewComponent = v;
	}
	
	public void actionPerformed(ActionEvent e) {
		logger.info("== Completion stopped by user ===");
		setContext(getViewComponent().getContext());
		super.actionPerformed(e);
		getViewComponent().changeGUIState(Constants.COMPLETION_STOPPED);
	}

}
