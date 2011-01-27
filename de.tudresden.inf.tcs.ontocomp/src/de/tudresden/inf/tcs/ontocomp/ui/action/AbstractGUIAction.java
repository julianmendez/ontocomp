package de.tudresden.inf.tcs.ontocomp.ui.action;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import de.tudresden.inf.tcs.ontocomp.ui.OntoComPViewComponent;

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
