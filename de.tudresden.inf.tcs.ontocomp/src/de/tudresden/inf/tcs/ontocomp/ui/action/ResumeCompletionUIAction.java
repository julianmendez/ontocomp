package de.tudresden.inf.tcs.ontocomp.ui.action;

import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.Set;

import org.apache.log4j.Logger;
import org.semanticweb.owlapi.model.OWLClass;

import de.tudresden.inf.tcs.ontocomp.Constants;

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
 * The expert action that resumes the completion after a stop action.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public class ResumeCompletionUIAction extends AbstractGUIAction {

	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(ResumeCompletionUIAction.class);
	
	// whether the completion should be resumed with the premise of the last question, or with an emtpy premise
	private boolean flag;
	
	public ResumeCompletionUIAction(boolean withTheLastQuestion) {
		flag = withTheLastQuestion;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("=== Completion resumed ===");
		getViewComponent().changeGUIState(Constants.COMPLETION_RESUMED);
		if (flag) {
			getViewComponent().getContext().continueExploration(getViewComponent().getContext().
					getCurrentQuestion().getPremise());
		}
		else {
			Set<OWLClass> initialPremise = Collections.emptySet();
			getViewComponent().getContext().continueExploration(initialPremise);
		}
	}

}
