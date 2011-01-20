package de.tudresden.inf.tcs.ontocomp.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.semanticweb.owlapi.model.OWLClass;

import de.tudresden.inf.tcs.fcaapi.change.ContextChange;
import de.tudresden.inf.tcs.oclib.change.AbstractContextModification;

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
 * Panel showing the actions in the history in the repair tab.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public class ContextModificationsPanel extends JPanel implements ItemListener {

	private static final long serialVersionUID = 1L;
	
	private OntoComPViewComponent viewComponent;
	
	// number of checked context modifications for controlling undo buttons
	private int checkedCount;
	
	private HashMap<ContextChange<OWLClass>,JCheckBox> modificationsHash;
	
	/**
	 * The logger.
	 */
	private static final Logger logger = Logger.getLogger(ContextModificationsPanel.class);
	
	public ContextModificationsPanel(OntoComPViewComponent vc) {
		super();
		viewComponent = vc;
		// GridLayout myLayout = new GridLayout(0,2);
		 setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		 setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		 setBackground(Color.WHITE);
		 modificationsHash = new HashMap<ContextChange<OWLClass>,JCheckBox>();
		// setLayout(myLayout);
	}
	
	protected OntoComPViewComponent getViewComponent() {
		return viewComponent;
	}
	
	public void update() {
		removeAll();
		modificationsHash.clear();
		logger.debug("History size: " + getViewComponent().getContext().getHistory().size());
	 	// for (int i = getViewComponent().getContext().getHistory().size() - 1; i >= 0; --i) {
	 	for (Iterator<AbstractContextModification> it = getViewComponent().getContext().getHistory().iterator(); it.hasNext();) {
	 		// add(new ContextModificationRow(this,getViewComponent().getContext().getHistory().get(i)));
	 		AbstractContextModification modification = it.next();
	 		// if (modification.canUndo()) {
	 			JCheckBox checkBox = new JCheckBox();
	 			checkBox.addItemListener(this);
	 			modificationsHash.put(modification, checkBox);
	 			ContextModificationRow modificationRow = 
	 				new ContextModificationRow(modification,checkBox);
	 			add(modificationRow);
	 			add(Box.createRigidArea(new Dimension(0, 5)));
	 		// }
	 		// else {
	 		// 	// TODO: put a why button instead of the undo checkbox
	 		// 	ContextModificationRow modificationRow = 
	 		// 		new ContextModificationRow(modification);
	 		// 	add(modificationRow);
	 		// 	add(Box.createRigidArea(new Dimension(0, 5)));
	 		// }
	 	}
	 	revalidate();
	 	repaint();
	}
	
	public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
        	++checkedCount;
        	getViewComponent().setUndoSelectedModificationsButton(true);
        } else {
        	--checkedCount;
        	if (checkedCount == 0) {
        		getViewComponent().setUndoSelectedModificationsButton(false);
        	}
        }
	}
	
	public boolean isSelected(ContextChange<OWLClass> m) {
		if (!modificationsHash.containsKey(m)) {
			return false;
		}
		return modificationsHash.get(m).isSelected();
	}
	
//	public JEditorPane createEditorPane(ContextChange<OWLClass> c) {
//		JEditorPane editorPane = n//ew JEditorPane();
//		editorPane.setEditable(false);
// 		editorPane.setContentType("text/html");
// 		editorPane.setEditorKit(new HTMLEditorKit());
//		try {
// 			switch (c.getType()) {
// 			case ContextChange.NEW_IMPLICATION_MODIFICATION:
// 				System.out.println("new implication");
// 				editorPane.getEditorKit().read(new java.io.StringReader ("<html><body>" +
// 					GUIConstants.NEW_IMPLICATION_MODIFICATION_TEXT_PART1 + 
// 					renderer.render(((NewSubClassAxiomChange) c).getImplication().getPremise()) +
// 					GUIConstants.NEW_IMPLICATION_MODIFICATION_TEXT_PART2 + 
// 					renderer.render(((NewSubClassAxiomChange) c).getImplication().getConclusion()) +
// 					"</body></html>"),editorPane.getDocument(),
// 					editorPane.getDocument().getLength());
// 				break;
// 			case ContextChange.NEW_OBJECT_MODIFICATION:
// 				System.out.println("new object");
// 				editorPane.getEditorKit().read(new java.io.StringReader ("<html><body>" +
// 						GUIConstants.NEW_OBJECT_MODIFICATION_TEXT_PART1 +
// 						renderer.render(((NewIndividualChange) c).getObject()) +
// 						GUIConstants.NEW_OBJECT_MODIFICATION_TEXT_PART2 +
// 						renderer.render(((NewIndividualChange) c).getAttributes()) +
// 					"</body></html>"),editorPane.getDocument(),
// 					editorPane.getDocument().getLength());
// 				break;
// 			case ContextChange.OBJECT_HAS_ATTRIBUTE_MODIFICATION:
// 				System.out.println("object has attribute");
// 				editorPane.getEditorKit().read(new java.io.StringReader ("<html><body>" +
// 						GUIConstants.CLASSASSERTION_MODIFICATION_TEXT_PART1 +
// 						renderer.render(((ClassAssertionChange) c).getObject()) +
// 						GUIConstants.CLASSASSERTION_MODIFICATION_TEXT_PART2 +
// 						renderer.render(((ClassAssertionChange) c).getAttribute(),
// 								((ClassAssertionChange) c).isComplemented()) +
// 					"</body></html>"),editorPane.getDocument(),
// 					editorPane.getDocument().getLength());
// 				break;
// 			}
// 		}
// 		catch (BadLocationException e) {
// 			e.printStackTrace();
// 		}
// 		catch (IOException e) {
// 			e.printStackTrace();
// 		}
// 		
// 		return editorPane;
//	}
	// @Override
	// public void validate() {
	// 	System.out.println("update");
	// 	update();
	// }
	
	// public void validate() {
	// 	for (int i = history.size() - 1; i >= 0; --i) {
	// 		// System.out.println(history.get(i).getClass().getName());
	// 		// System.out.println(history.get(i).getChange().getAxiom());
	// 		// if (history.get(i).getClass().getName() == "ClassAssertionChange") {
	// 		// 	
	// 		// }
	// 		switch (history.get(i).getType()) {
	// 		case ContextChange.NEW_IMPLICATION_MODIFICATION:
	// 			System.out.println(((NewSubClassAxiomChange) history.get(i)).getImplication());
	// 			System.out.println();
	// 			break;
	// 		case ContextChange.NEW_OBJECT_MODIFICATION:
	// 			System.out.println(((NewIndividualChange) history.get(i)).getObject());
	// 			System.out.println(((NewIndividualChange) history.get(i)).getAttributes());
	// 			System.out.println();
	// 			break;
	// 		case ContextChange.OBJECT_HAS_ATTRIBUTE_MODIFICATION:
	// 			System.out.println(((ClassAssertionChange) history.get(i)).getObject());
	// 			System.out.println(((ClassAssertionChange) history.get(i)).getAttribute());
	// 			System.out.println(((ClassAssertionChange) history.get(i)).isComplemented());
	// 			System.out.println();
	// 			break;
	// 		}
	// 	}
	// }
}
