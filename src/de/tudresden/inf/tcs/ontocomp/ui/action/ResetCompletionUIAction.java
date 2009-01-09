package de.tudresden.inf.tcs.ontocomp.ui.action;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import org.semanticweb.owl.model.OWLClass;
import org.semanticweb.owl.model.OWLIndividual;

import de.tudresden.inf.tcs.fcalib.action.ResetExplorationAction;
import de.tudresden.inf.tcs.ontocomp.Constants;
import de.tudresden.inf.tcs.ontocomp.ui.OntoComPViewComponent;

/**
 * The expert action that stops the completion.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */
// public class ResetCompletionAction extends AbstractGUIAction {
public class ResetCompletionUIAction extends ResetExplorationAction<OWLClass,OWLIndividual> {

	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(StopCompletionUIAction.class);
	
	private OntoComPViewComponent viewComponent;
	
	public ResetCompletionUIAction(OntoComPViewComponent v) {
		super(v.getContext());
		viewComponent = v;
	}
	
	public OntoComPViewComponent getViewComponent() {
		return viewComponent;
	}
	
	public void actionPerformed(ActionEvent e) {
		logger.info("== Completion reset by user ===");
		// ResetExplorationAction<OWLClass,OWLIndividual> action = 
		// 	new ResetExplorationAction<OWLClass,OWLIndividual>(getViewComponent().getContext());
		// getViewComponent().fireExpertAction(action);
		super.actionPerformed(e);
		getViewComponent().changeGUIState(Constants.COMPLETION_RESET);
	}

}
