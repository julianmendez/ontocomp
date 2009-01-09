package de.tudresden.inf.tcs.ontocomp.ui.action;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import de.tudresden.inf.tcs.oclib.action.QuestionConfirmedAction;
import de.tudresden.inf.tcs.ontocomp.Constants;
import de.tudresden.inf.tcs.ontocomp.ui.OntoComPViewComponent;

/**
 * The expert fired when the user confirms a question.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public class ConfirmQuestionUIAction extends QuestionConfirmedAction {

	private static final long serialVersionUID = 1L;

	private OntoComPViewComponent viewComponent;
	
	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(ConfirmQuestionUIAction.class);
	
	public OntoComPViewComponent getViewComponent() {
		return viewComponent;
	}
	
	public void setViewComponent(OntoComPViewComponent v) {
		viewComponent = v;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		setContext(getViewComponent().getContext());
		setQuestion(getContext().getCurrentQuestion());
		logger.info("User confirmed question: " + getQuestion());
		getViewComponent().changeGUIState(Constants.QUESTION_CONFIRMED);
		super.actionPerformed(e);
	}

}
