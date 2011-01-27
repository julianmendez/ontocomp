package de.tudresden.inf.tcs.ontocomp.ui.action;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import de.tudresden.inf.tcs.oclib.action.QuestionConfirmedAction;
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
 * The expert action fired when the user confirms a question.
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
		// TODO: why???
		setContext(getViewComponent().getContext());
		setQuestion(getContext().getCurrentQuestion());
		logger.info("User confirmed question: " + getQuestion());
		getViewComponent().changeGUIState(Constants.QUESTION_CONFIRMED);
		super.actionPerformed(e);
	}

}
