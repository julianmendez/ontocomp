package de.tudresden.inf.tcs.ontocomp.ui.action;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import org.semanticweb.owl.model.OWLClass;
import org.semanticweb.owl.model.OWLIndividual;

import de.tudresden.inf.tcs.fcalib.action.StartExplorationAction;
import de.tudresden.inf.tcs.ontocomp.Constants;
import de.tudresden.inf.tcs.ontocomp.ui.OntoComPViewComponent;

/**
 * The expert action that is fired when the user wants to go to repair mode.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public class RepairUIAction extends AbstractGUIAction {

	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(RepairUIAction.class);
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("=== Going to repair mode ===");
		getViewComponent().changeGUIState(Constants.REPAIR_REQUESTED);
		// TODO Auto-generated method stub
	}
}
