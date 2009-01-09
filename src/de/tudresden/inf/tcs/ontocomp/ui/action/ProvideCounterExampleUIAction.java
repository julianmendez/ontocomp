package de.tudresden.inf.tcs.ontocomp.ui.action;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import de.tudresden.inf.tcs.ontocomp.Constants;

/**
 * The expert action that is fired when the user provides a counterexample.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public class ProvideCounterExampleUIAction extends AbstractGUIAction {

	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(ProvideCounterExampleUIAction.class);
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("User provided counterexample");
		getViewComponent().changeGUIState(Constants.COUNTEREXAMPLE_PROVIDED);
		getViewComponent().getCounterExampleCandidatesTable().resetActionCounter();
		getViewComponent().getContext().continueExploration(
				getViewComponent().getContext().getCurrentQuestion().getPremise());
	}

}
