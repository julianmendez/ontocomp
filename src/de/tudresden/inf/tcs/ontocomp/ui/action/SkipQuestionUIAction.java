package de.tudresden.inf.tcs.ontocomp.ui.action;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import de.tudresden.inf.tcs.ontocomp.Constants;
import de.tudresden.inf.tcs.fcalib.action.ChangeAttributeOrderAction;

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
 * The expert action for skipping a question.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public class SkipQuestionUIAction extends AbstractGUIAction {

	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(SkipQuestionUIAction.class);
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Attributes before: " + getViewComponent().getContext().getAttributes());
		logger.info("Skipping question: " + getViewComponent().getContext().getCurrentQuestion());
		// stop completion
		StopCompletionUIAction stop = new StopCompletionUIAction();
		stop.setViewComponent(getViewComponent());
		stop.actionPerformed(e);
		// change the order of attributes
		ChangeAttributeOrderAction changeOrder = new ChangeAttributeOrderAction();
		changeOrder.setContext(getViewComponent().getContext());
		changeOrder.actionPerformed(e);
		getViewComponent().changeGUIState(Constants.QUESTION_SKIPPED);
		logger.info("Attributes after: " + getViewComponent().getContext().getAttributes());
		// restart completion
		StartCompletionUIAction start = new StartCompletionUIAction();
		start.setViewComponent(getViewComponent());
		start.actionPerformed(e);
	}

}
