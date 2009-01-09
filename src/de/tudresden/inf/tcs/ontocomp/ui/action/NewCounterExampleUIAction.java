package de.tudresden.inf.tcs.ontocomp.ui.action;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import org.semanticweb.owl.model.OWLIndividual;
import org.protege.editor.owl.model.entity.OWLEntityCreationSet;

import de.tudresden.inf.tcs.ontocomp.Constants;

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
		OWLEntityCreationSet<OWLIndividual> s = getViewComponent().getOWLWorkspace().createOWLIndividual();
		if (! (s == null)) {
			logger.info("New counterexample");
			OWLIndividual ind = s.getOWLEntity();
			getViewComponent().getContext().addObject(ind,
					getViewComponent().getContext().getCurrentQuestion().getPremise());
			getViewComponent().getCounterExampleCandidatesTable().incrementActionCounter();
			getViewComponent().getContext().getCounterExampleCandidates().update();
			getViewComponent().changeGUIState(Constants.NEW_COUNTEREXAMPLE_REQUEST);
			getViewComponent().getCounterExampleCandidatesTable().getTableModel().fireTableDataChanged();
		}
	}

}
