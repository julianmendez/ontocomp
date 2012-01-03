package de.tudresden.inf.tcs.ontocomp.ui.action;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;
import org.protege.editor.owl.model.entity.OWLEntityCreationSet;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

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
 * The expert action fired when the user wants to create a new counterexample.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public class NewCounterExampleUIAction  extends AbstractGUIAction {
	
	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(NewCounterExampleUIAction.class);
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// getViewComponent().getContext().addObject(
		// 		getViewComponent().getOWLWorkspace().createOWLIndividual().getOWLEntity(),
		// 		getViewComponent().getContext().getCurrentQuestion().getPremise());
		OWLEntityCreationSet<OWLNamedIndividual> s = getViewComponent().getOWLWorkspace().createOWLIndividual();
		if (! (s == null)) {
			logger.info("New counterexample");
			OWLNamedIndividual ind = s.getOWLEntity();
			getViewComponent().getContext().addIndividualToOntology(ind,
					getViewComponent().getContext().getCurrentQuestion().getPremise());
			getViewComponent().getCounterExampleCandidatesTable().incrementActionCounter();
			getViewComponent().getContext().getCounterExampleCandidates().update();
			getViewComponent().changeGUIState(Constants.NEW_COUNTEREXAMPLE_REQUEST);
			getViewComponent().getCounterExampleCandidatesTable().getTableModel().fireTableDataChanged();
		}
	}

}
