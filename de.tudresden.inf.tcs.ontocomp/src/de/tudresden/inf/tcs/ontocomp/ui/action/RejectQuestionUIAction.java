package de.tudresden.inf.tcs.ontocomp.ui.action;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import de.tudresden.inf.tcs.fcalib.action.QuestionRejectedAction;
import de.tudresden.inf.tcs.oclib.IndividualObject;
import de.tudresden.inf.tcs.ontocomp.Constants;
import de.tudresden.inf.tcs.ontocomp.ui.OntoComPViewComponent;

/* 
 * OntoComP: a Protégé plugin for completing OWL ontologies.
 * Copyright (C) 2009  Baris Sertkaya
 * 
 * This file is part of OntoComP.
 * OntoComP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OntoComP is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OntoComp.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * The expert action fired when the user rejects a question.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public class RejectQuestionUIAction extends QuestionRejectedAction<OWLClass,OWLNamedIndividual,IndividualObject> {

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
