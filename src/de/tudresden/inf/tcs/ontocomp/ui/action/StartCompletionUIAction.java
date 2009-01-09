package de.tudresden.inf.tcs.ontocomp.ui.action;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import org.semanticweb.owl.model.OWLClass;
import org.semanticweb.owl.model.OWLIndividual;

import de.tudresden.inf.tcs.fcalib.action.StartExplorationAction;
import de.tudresden.inf.tcs.ontocomp.Constants;
import de.tudresden.inf.tcs.ontocomp.ui.OntoComPViewComponent;

/**
 * The expert action that starts the completion.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

// public class StartCompletionAction extends AbstractGUIAction {
public class StartCompletionUIAction extends StartExplorationAction<OWLClass,OWLIndividual> {

	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(StartCompletionUIAction.class);
	
	private OntoComPViewComponent viewComponent;
	
	public void setViewComponent(OntoComPViewComponent v) {
		viewComponent = v;
	}
	
	public OntoComPViewComponent getViewComponent() {
		return viewComponent;
	}
	
	public void actionPerformed(ActionEvent e) {
		logger.info("== Completion starting ===");
		setContext(getViewComponent().getContext());
		getViewComponent().changeGUIState(Constants.COMPLETION_STARTED);
		super.actionPerformed(e);
	}
}
