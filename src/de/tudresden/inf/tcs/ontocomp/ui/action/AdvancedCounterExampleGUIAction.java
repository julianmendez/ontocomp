package de.tudresden.inf.tcs.ontocomp.ui.action;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import de.tudresden.inf.tcs.ontocomp.Constants;

public class AdvancedCounterExampleGUIAction extends
		AbstractGUIAction {

	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(UndoLastCEMUIAction.class);
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Advanced counterexample generation");
		getViewComponent().changeGUIState(Constants.ADVANCED_COUNTEREXAMPLE_GENERATION);
	}

}
