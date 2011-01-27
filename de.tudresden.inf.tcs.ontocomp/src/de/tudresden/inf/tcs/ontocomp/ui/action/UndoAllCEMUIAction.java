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
 * The expert action for undoing modifications in the counterexample editor.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public class UndoAllCEMUIAction extends AbstractGUIAction {

	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(UndoAllCEMUIAction.class);
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Undo all counterexample candidate modifications");
		getViewComponent().getContext().getHistory().undoLastN(
				getViewComponent().getCounterExampleCandidatesTable().getActionCounter());
		getViewComponent().getContext().reClassifyOntology();
		getViewComponent().getContext().updateObjects(de.tudresden.inf.tcs.oclib.Constants.AFTER_UNDO);
		getViewComponent().getContext().updateObjectDescriptions(de.tudresden.inf.tcs.oclib.Constants.AFTER_UNDO);
		getViewComponent().getCounterExampleCandidatesTable().resetActionCounter();
		getViewComponent().setUndoCEMButtons(false);
		if (getViewComponent().isReadyButtonEnabled() && 
				!getViewComponent().getContext().refutes(getViewComponent().getContext().
						getCurrentQuestion())) {
			getViewComponent().enableReadyButton(false);
		}
		getViewComponent().getContext().getCounterExampleCandidates().update();
		getViewComponent().getCounterExampleCandidatesTable().getTableModel().fireTableDataChanged();
		getViewComponent().changeGUIState(Constants.ALL_COUNTEREXAMLE_MODIFICATIONS_UNDONE);
	}

}
