package de.tudresden.inf.tcs.ontocomp.action;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import org.semanticweb.owl.model.OWLClass;

import de.tudresden.inf.tcs.fcaapi.FCAImplication;
import de.tudresden.inf.tcs.oclib.action.AbstractExpertAction;
import de.tudresden.inf.tcs.oclib.IndividualContext;

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
