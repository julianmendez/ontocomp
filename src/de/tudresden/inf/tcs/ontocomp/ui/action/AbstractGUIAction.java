package de.tudresden.inf.tcs.ontocomp.ui.action;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import de.tudresden.inf.tcs.ontocomp.ui.OntoComPViewComponent;

/**
 * An abstract implementation of actions that the expert can perform.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public abstract class AbstractGUIAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private OntoComPViewComponent viewComponent;
	
	// public AbstractGUIAction(OCPViewComponent v) {
	// 	viewComponent = v;
	// }
	
	public void setViewComponent(OntoComPViewComponent v) {
		viewComponent = v;
	}
	
	public OntoComPViewComponent getViewComponent() {
		return viewComponent;
	}
	
	public abstract void actionPerformed(ActionEvent e);
}
