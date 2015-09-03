package de.tudresden.inf.tcs.ontocomp.action;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;
import org.semanticweb.owlapi.model.OWLClass;

import de.tudresden.inf.tcs.fcaapi.FCAImplication;
import de.tudresden.inf.tcs.oclib.IndividualContext;
import de.tudresden.inf.tcs.oclib.action.AbstractExpertAction;

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
 * The expert action fired when a counterexample is provided.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public class CounterExampleProvidedAction extends AbstractExpertAction {

	private static final long serialVersionUID = 1L;
	
	private FCAImplication<OWLClass> question;
	
	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(CounterExampleProvidedAction.class);
	
	public CounterExampleProvidedAction(IndividualContext c,FCAImplication<OWLClass> q) {
		// super(c);
		question = q;
	}
	
	/**
	 * Returns the question rejected to which the counterexample in this action is provided.
	 * @return the question rejected to which the counterexample in this action is provided.
	 */
	public FCAImplication<OWLClass> getQuestion() {
		return question;
	}
	
	/**
	 * Continues completion with the same premise. The check whether the counterexample is valid,
	 * i.e., whether the extended ABox refutes the question, are done in the action that is fired
	 * when the description of a counterexample candidate is modified. Here it is assumed that 
	 * these checks have been performed, and a valid counterexample has been provided.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Counterexample provided");
		getContext().continueExploration(getQuestion().getPremise());
	}
}
