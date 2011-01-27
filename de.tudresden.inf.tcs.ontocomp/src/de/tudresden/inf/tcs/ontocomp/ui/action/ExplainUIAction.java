package de.tudresden.inf.tcs.ontocomp.ui.action;

import org.apache.log4j.Logger;

import java.awt.event.ActionEvent;

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
 * The expert action for explaining a question that already follows from the ontology.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public class ExplainUIAction extends AbstractGUIAction {

	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(ExplainUIAction.class);
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// getViewComponent().popUpExplanationWorkbench(getViewComponent().getContext().getCurrentQuestion());
		getViewComponent().popUpExplanationWorkbench();
	}

}
