package de.tudresden.inf.tcs.ontocomp.ui;

import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.BadLocationException;
import javax.swing.BorderFactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import org.semanticweb.owl.model.OWLClass;

import de.tudresden.inf.tcs.fcaapi.change.ContextChange;
import de.tudresden.inf.tcs.oclib.change.ClassAssertionChange;
import de.tudresden.inf.tcs.oclib.change.NewIndividualChange;
import de.tudresden.inf.tcs.oclib.change.NewSubClassAxiomChange;

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
 * Row showing an action in the history in the repair tab.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public class ContextModificationRow extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JEditorPane editorPane;
	// private JCheckBox checkBox;
	// private ContextModificationsPanel modificationsPanel;
	
	// public ContextModificationRow(ContextModificationsPanel mp, ContextChange<OWLClass> c,
	// 		JCheckBox checkBox) {
	public ContextModificationRow(ContextChange<OWLClass> c, JCheckBox checkBox) {
		super();
		// modificationsPanel = mp;
		setBackground(Color.WHITE);
		// setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		setBorder(BorderFactory.createLineBorder(Color.black));
		// setLayout(new BorderLayout());
		setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
 		Renderer renderer = new Renderer();
 		editorPane = new JEditorPane();
 		editorPane.setEditable(false);
 		editorPane.setContentType("text/html");
 		editorPane.setEditorKit(new HTMLEditorKit());
 		editorPane.setAlignmentX(LEFT_ALIGNMENT);
 		editorPane.setAlignmentY(CENTER_ALIGNMENT);
 		// editorPane.setPreferredSize(new Dimension(650,50));
 		editorPane.setPreferredSize(new Dimension(650,80));
 		editorPane.setMaximumSize(editorPane.getPreferredSize());
 		
 		// checkBox = new JCheckBox();
 		// checkBox.addItemListener(getModificationsPanel());
 		checkBox.setAlignmentX(RIGHT_ALIGNMENT);
 		checkBox.setAlignmentY(CENTER_ALIGNMENT);
 		
 		try {
 			switch (c.getType()) {
 			case ContextChange.NEW_IMPLICATION_MODIFICATION:
 				if (((NewSubClassAxiomChange) c).getImplication().getPremise().isEmpty()) {
 					editorPane.getEditorKit().read(new java.io.StringReader ("<html><body>" +
 							GUIConstants.NEW_IMPLICATION_MODIFICATION_TEXT_EMPTY_PREMISE + "<br>" +
 						renderer.render(((NewSubClassAxiomChange) c).getImplication().getConclusion()) +
 						"</body></html>"),editorPane.getDocument(),
 						editorPane.getDocument().getLength());
 				}
 				else {
 					editorPane.getEditorKit().read(new java.io.StringReader ("<html><body>" +
 							GUIConstants.NEW_IMPLICATION_MODIFICATION_TEXT_PART1 + 
 						renderer.render(((NewSubClassAxiomChange) c).getImplication().getPremise()) + "<br>" +
 						GUIConstants.NEW_IMPLICATION_MODIFICATION_TEXT_PART2 + 
 						renderer.render(((NewSubClassAxiomChange) c).getImplication().getConclusion()) +
 						"</body></html>"),editorPane.getDocument(),
 						editorPane.getDocument().getLength());
 				}
 				break;
 			case ContextChange.NEW_OBJECT_MODIFICATION:
 				editorPane.getEditorKit().read(new java.io.StringReader ("<html><body>" +
 						GUIConstants.NEW_OBJECT_MODIFICATION_TEXT_PART1 +
 						renderer.render(((NewIndividualChange) c).getObject()) + "<br>" +
 						GUIConstants.NEW_OBJECT_MODIFICATION_TEXT_PART2 +
 						renderer.render(((NewIndividualChange) c).getAttributes()) +
 					"</body></html>"),editorPane.getDocument(),
 					editorPane.getDocument().getLength());
 				break;
 			case ContextChange.OBJECT_HAS_ATTRIBUTE_MODIFICATION:
 				editorPane.getEditorKit().read(new java.io.StringReader ("<html><body>" +
 						GUIConstants.CLASSASSERTION_MODIFICATION_TEXT_PART1 +
 						renderer.render(((ClassAssertionChange) c).getObject()) + "<br>" +
 						GUIConstants.CLASSASSERTION_MODIFICATION_TEXT_PART2 +
 						renderer.render(((ClassAssertionChange) c).getAttribute(),
 								((ClassAssertionChange) c).isComplemented()) +
 					"</body></html>"),editorPane.getDocument(),
 					editorPane.getDocument().getLength());
 				break;
 			}
 		}
 		catch (BadLocationException e) {
 			e.printStackTrace();
 		}
 		catch (IOException e) {
 			e.printStackTrace();
 		}
 		
 		add(editorPane,BorderLayout.WEST);
 		add(checkBox,BorderLayout.EAST);
	}
	
    // public Dimension getMinimumSize() {
    //     return getPreferredSize();
    // }
    
    // public Dimension getPreferredSize() {
    //     return new Dimension(700,30);
    // }
    
    // public Dimension getMaximumSize() {
    //     return getPreferredSize();
    // }
	// protected ContextModificationsPanel getModificationsPanel() {
	// 	return modificationsPanel;
	// }
	
	// public boolean isSelected() {
	// 	return checkBox.isSelected();
	// }

}
