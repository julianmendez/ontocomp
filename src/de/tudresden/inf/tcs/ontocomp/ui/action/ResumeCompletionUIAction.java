package de.tudresden.inf.tcs.ontocomp.ui.action;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import de.tudresden.inf.tcs.ontocomp.Constants;

public class ResumeCompletionUIAction extends AbstractGUIAction {

	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(ResumeCompletionUIAction.class);
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("=== Completion resumed ===");
		getViewComponent().changeGUIState(Constants.COMPLETION_RESUMED);
		getViewComponent().getContext().continueExploration(getViewComponent().getContext().
				getCurrentQuestion().getPremise());
	}

}
