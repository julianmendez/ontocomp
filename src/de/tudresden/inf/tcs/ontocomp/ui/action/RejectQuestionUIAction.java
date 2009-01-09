package de.tudresden.inf.tcs.ontocomp.ui.action;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import org.semanticweb.owl.model.OWLClass;
import org.semanticweb.owl.model.OWLIndividual;

import de.tudresden.inf.tcs.fcalib.action.QuestionRejectedAction;
import de.tudresden.inf.tcs.ontocomp.Constants;
import de.tudresden.inf.tcs.ontocomp.ui.OntoComPViewComponent;

/**
 * The expert action fired when the user rejects a question.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public class RejectQuestionUIAction extends QuestionRejectedAction<OWLClass,OWLIndividual> {

	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(RejectQuestionUIAction.class);
	
	private OntoComPViewComponent viewComponent;
	
	public void setViewComponent(OntoComPViewComponent v) {
		viewComponent = v;
	}
	
	public OntoComPViewComponent getViewComponent() {
		return viewComponent;
	}
	
	public void actionPerformed(ActionEvent e) {
		setContext(getViewComponent().getContext());
		setQuestion(getContext().getCurrentQuestion());
		logger.info("Expert rejected question: " + getQuestion());
		super.actionPerformed(e);
		getViewComponent().getContext().getCounterExampleCandidates().setQuestion(getQuestion());
		getViewComponent().getContext().getCounterExampleCandidates().update();
		getViewComponent().getCounterExampleCandidatesTable().getTableModel().fireTableStructureChanged();
		getViewComponent().changeGUIState(Constants.QUESTION_REJECTED);
	}
}
