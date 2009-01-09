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
 		editorPane.setPreferredSize(new Dimension(650,50));
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
