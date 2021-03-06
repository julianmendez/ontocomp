package de.tudresden.inf.tcs.ontocomp.ui.action;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

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
 * The expert action for undoing all context modifications so far.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public class UndoAllContextModificationsUIAction  extends AbstractGUIAction {
	
	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(UndoAllContextModificationsUIAction.class);
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Undo all context modifications");
		getViewComponent().getContext().getHistory().undoAll();
		getViewComponent().getContext().reClassifyOntology();
		// this is to refresh the context table. perhaps some 'new' counterexamples are removed
		// by the undo, they should not appear in the context table.
		// getViewComponent().getContext().clearObjects();
		// getViewComponent().getContext().getObjects();
		getViewComponent().getContext().updateObjects(de.tudresden.inf.tcs.oclib.Constants.AFTER_UNDO);
		getViewComponent().getContext().updateObjectDescriptions(de.tudresden.inf.tcs.oclib.Constants.AFTER_UNDO);
		getViewComponent().changeGUIState(Constants.ALL_CONTEXT_MODIFICATIONS_UNDONE);
	}

}
