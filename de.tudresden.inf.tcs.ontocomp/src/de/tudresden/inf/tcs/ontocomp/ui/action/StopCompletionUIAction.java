package de.tudresden.inf.tcs.ontocomp.ui.action;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import de.tudresden.inf.tcs.fcalib.action.StopExplorationAction;
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
 * The expert action that stops the completion.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public class StopCompletionUIAction extends StopExplorationAction<OWLClass,OWLNamedIndividual,IndividualObject> {

	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(StopCompletionUIAction.class);
	
	private OntoComPViewComponent viewComponent;
	
	public OntoComPViewComponent getViewComponent() {
		return viewComponent;
	}
	
	public void setViewComponent(OntoComPViewComponent v) {
		viewComponent = v;
	}
	
	public void actionPerformed(ActionEvent e) {
		logger.info("== Completion stopped by user ===");
		setContext(getViewComponent().getContext());
		super.actionPerformed(e);
		getViewComponent().changeGUIState(Constants.COMPLETION_STOPPED);
	}

}
