package de.tudresden.inf.tcs.ontocomp.ui.action;

import java.util.Iterator;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import de.tudresden.inf.tcs.oclib.change.AbstractContextModification;
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
 * The expert action for undoing the selected context modifications.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public class UndoSelectedContextModificationsUIAction  extends AbstractGUIAction {
	
	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(UndoSelectedContextModificationsUIAction.class);
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Undo selected context modifications");
		for (Iterator<AbstractContextModification> it = 
			getViewComponent().getContext().getHistory().iterator(); it.hasNext();) {
			AbstractContextModification modification = it.next();
			if (getViewComponent().getContextModificationsPanel().isSelected(modification)) {
				getViewComponent().getContext().getHistory().undo(modification);
				logger.debug("Undo: " + modification.getChange().getAxiom());
			}
		}
		getViewComponent().getContext().reClassifyOntology();
		// this is to refresh the context table. perhaps some 'new' counterexamples are removed
		// by the undo, they should not appear in the context table.
		// getViewComponent().getContext().clearObjects();
		// getViewComponent().getContext().getObjects();
		getViewComponent().getContext().updateObjects(de.tudresden.inf.tcs.oclib.Constants.AFTER_UNDO);
		getViewComponent().getContext().updateObjectDescriptions(de.tudresden.inf.tcs.oclib.Constants.AFTER_UNDO);
		getViewComponent().changeGUIState(Constants.SELECTED_CONTEXT_MODIFICATIONS_UNDONE);
	}

}
