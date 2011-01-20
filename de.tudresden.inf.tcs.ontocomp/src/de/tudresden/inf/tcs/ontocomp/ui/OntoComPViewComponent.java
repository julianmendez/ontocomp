package de.tudresden.inf.tcs.ontocomp.ui;

import java.awt.BorderLayout;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLEditorKit;

import org.apache.log4j.Logger;
import org.protege.editor.owl.model.event.OWLModelManagerChangeEvent;
import org.protege.editor.owl.model.event.OWLModelManagerListener;
import org.protege.editor.owl.ui.explanation.ExplanationManager;
import org.protege.editor.owl.ui.transfer.OWLObjectDataFlavor;
import org.protege.editor.owl.ui.view.AbstractOWLViewComponent;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObject;

import de.tudresden.inf.tcs.fcaapi.Expert;
import de.tudresden.inf.tcs.fcaapi.FCAImplication;
import de.tudresden.inf.tcs.fcaapi.action.ExpertAction;
import de.tudresden.inf.tcs.fcaapi.action.ExpertActionListener;
import de.tudresden.inf.tcs.fcaapi.exception.IllegalAttributeException;
import de.tudresden.inf.tcs.fcalib.PartialObjectDescription;
import de.tudresden.inf.tcs.oclib.DLExpert;
import de.tudresden.inf.tcs.oclib.ELIndividualContext;
import de.tudresden.inf.tcs.oclib.IndividualContext;
import de.tudresden.inf.tcs.oclib.IndividualObject;
import de.tudresden.inf.tcs.ontocomp.Constants;
import de.tudresden.inf.tcs.ontocomp.ui.action.AdvancedCounterExampleGUIAction;
import de.tudresden.inf.tcs.ontocomp.ui.action.ConfirmQuestionUIAction;
import de.tudresden.inf.tcs.ontocomp.ui.action.ExplainUIAction;
import de.tudresden.inf.tcs.ontocomp.ui.action.NewCounterExampleUIAction;
import de.tudresden.inf.tcs.ontocomp.ui.action.ProvideCounterExampleUIAction;
import de.tudresden.inf.tcs.ontocomp.ui.action.RejectQuestionUIAction;
import de.tudresden.inf.tcs.ontocomp.ui.action.RepairUIAction;
import de.tudresden.inf.tcs.ontocomp.ui.action.ResetCompletionUIAction;
import de.tudresden.inf.tcs.ontocomp.ui.action.ResumeCompletionUIAction;
import de.tudresden.inf.tcs.ontocomp.ui.action.SkipQuestionUIAction;
import de.tudresden.inf.tcs.ontocomp.ui.action.StartCompletionUIAction;
import de.tudresden.inf.tcs.ontocomp.ui.action.StopCompletionUIAction;
import de.tudresden.inf.tcs.ontocomp.ui.action.UndoAllCEMUIAction;
import de.tudresden.inf.tcs.ontocomp.ui.action.UndoAllContextModificationsUIAction;
import de.tudresden.inf.tcs.ontocomp.ui.action.UndoLastCEMUIAction;
import de.tudresden.inf.tcs.ontocomp.ui.action.UndoSelectedContextModificationsUIAction;

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
 * OntoComP tab.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */


public class OntoComPViewComponent extends AbstractOWLViewComponent implements DropTargetListener, 
	ActionListener, DLExpert, OWLModelManagerListener {
	// ActionListener, DLExpert<OWLClass,OWLIndividual,IndividualObject>, OWLModelManagerListener {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(OntoComPViewComponent.class);
	    

	// the split pane
	private JSplitPane splitPane;
	
	// the tabbed pane
	private JTabbedPane tabbedPane;
	
	//the exploration panel
	private JPanel explorationPanel,counterExampleEditorPanel, repairPanel;
	
	private JToolBar explorationToolBar,counterExampleEditorToolBar,debugToolBar;
	
	private JButton startButton, stopButton, repairButton, acceptButton, rejectButton,resetButton, skipButton,
		readyButton, resumeButton, undoCEMButton, undoAllCEMButton, newCounterExampleButton,
		undoSelectedModificationsButton, undoAllModificationsButton, advancedCounterExampleButton,explainButton;
	
	// the console tabbed pane
	private JTabbedPane consoleTabbedPane;
	
	// the context scrollpane
	private JScrollPane contextScrollPane;
	
	private JScrollPane counterExampleEditorScrollPane;
	
	private ContextModificationsPanel contextModificationsPanel;
	private JScrollPane contextModificationsScrollPane;
	
	// the messages scrollpane
	private JScrollPane messagesScrollPane;
	
	// messages textarea
	// private JTextArea messagesTextArea;
	private JEditorPane messagesTextArea;
	
	private IndividualContext context;
	
	private ContextTable contextTable;
	
	private CounterExampleCandidatesTable counterExampleCandidatesTable;
	
	private DropTarget dt;
	
	// explanation handler for automatically accepted or rejected questions
	private ExplanationManager explanationHandler;
	
	// The implication that already follows from the ontology and thus needs explanation
	private FCAImplication<OWLClass> implicationToBeExplained;
	
	/**
	 * The list of listeners
	 */
	private List<ExpertActionListener<OWLClass,OWLNamedIndividual>> listeners;
	
	// /**
	//  * The current question
	//  */
	// private FCAImplication<OWLClass> currentQuestion;
	
	private Renderer renderer;
	
	public OntoComPViewComponent() {
		super();
		listeners = new ArrayList<ExpertActionListener<OWLClass,OWLNamedIndividual>>();
	    renderer = new Renderer();
	}
	
	@Override
	protected void disposeOWLView() {
		removeExpertActionListeners();
		getOWLModelManager().removeListener(this);
	}

	protected OWLClass updateView(OWLClass selectedClass) {
		return null;
	}
	
	@Override
	protected void initialiseOWLView() throws Exception {
		setLayout(new BorderLayout(10,10));
		
		getOWLModelManager().addListener(this);
		
		if (getOWLModelManager().getOWLReasonerManager().getCurrentReasonerFactoryId().equals(Constants.CEL_REASONER_ID)) {
			context = new ELIndividualContext(getOWLModelManager().getOWLOntologyManager(),
					getOWLModelManager().getReasoner(), getOWLModelManager().getActiveOntology());
		}
		else {
			context = new IndividualContext(getOWLModelManager().getOWLOntologyManager(),
					getOWLModelManager().getReasoner(), getOWLModelManager().getActiveOntology());
		}
		
		getContext().setExpert(this);
		addExpertActionListener(getContext());
	
		add(prepareGUI(),BorderLayout.CENTER);
		contextTable.setContext(getContext());
		
	    dt = new DropTarget(this, this);
	    // dt.setActive(true);

	    // explanation handler. used for getting explanations for automatically accepted or rejected questions
	    explanationHandler = getOWLModelManager().getExplanationManager();
	    
	    changeGUIState(Constants.COMPLETION_INIT);
	    log.info("OntoComP View Component initialized");
	}
	
	public void handleChange(OWLModelManagerChangeEvent event) {
		switch (event.getType()) {
		case REASONER_CHANGED:
			log.info("reasoner ID:" + getOWLModelManager().getOWLReasonerManager().getCurrentReasonerFactoryId());
			if (getOWLModelManager().getOWLReasonerManager().getCurrentReasonerFactoryId().equals(Constants.CEL_REASONER_ID)) {
				log.info("using the CEL reasoner");
				context = new ELIndividualContext(getOWLModelManager().getOWLOntologyManager(),
						getOWLModelManager().getReasoner(), getOWLModelManager().getActiveOntology());
				getContext().setExpert(this);
				addExpertActionListener(getContext());
				contextTable.setContext(getContext());
			}
			// if Pellet is being used
			else if (getOWLModelManager().getOWLReasonerManager().getCurrentReasonerFactoryId().equals(Constants.PELLET_REASONER_ID)) {
				log.info("using the Pellet reasoner");
			    // Set flags for incremental consistency
			    // PelletOptions.USE_COMPLETION_QUEUE = true;
			    // PelletOptions.USE_INCREMENTAL_CONSISTENCY = true;
			    // PelletOptions.USE_SMART_RESTORE = false;
			}
			// else {
			// 	context = new IndividualContext(getOWLModelManager().getOWLOntologyManager(),
			// 			getOWLModelManager().getReasoner(), getOWLModelManager().getActiveOntology());
			// }
		
			
			getContext().setReasoner(getOWLModelManager().getReasoner());
			// getContext().setReasonerID(getOWLModelManager().getOWLReasonerManager().getCurrentReasonerFactoryId());
			log.debug("set reasoner of the context");
			break;
		}
	}
	
	/** Returns the ID of the current reasoner.
	 * @return the ID of the current reasoner
	 */
	public String getReasonerID() {
		return getOWLModelManager().getOWLReasonerManager().getCurrentReasonerFactoryId();
	}
	
	public IndividualContext getContext() {
		return context;
	}
	
	public CounterExampleCandidatesTable getCounterExampleCandidatesTable() {
		return counterExampleCandidatesTable;
	}
	
	public ContextModificationsPanel getContextModificationsPanel() {
		return contextModificationsPanel;
	}
	
	public void enableButton(JButton button, boolean enabled) {
		button.setEnabled(enabled);
	}
	
	private JButton prepareButton(AbstractAction action, String toolTipText, String text) {
		JButton button = new JButton(action);
		button.setText(text);
		// button.setAction(action);
		button.setToolTipText(toolTipText);
		button.addActionListener(this);
		button.setEnabled(false);
		return button;
	}

	public void changeGUIState(int state) {
		switch (state) {
		case Constants.COMPLETION_INIT:
			tabbedPane.setEnabledAt(GUIConstants.COUNTEREXAMPLEEDITOR_TAB_INDEX, false);
			tabbedPane.setEnabledAt(GUIConstants.REPAIR_TAB_INDEX, false);
			dt.setActive(true);
			break;
		case Constants.ATTRIBUTES_ADDED:
			startButton.setEnabled(true);
			stopButton.setEnabled(false);
			resumeButton.setEnabled(false);
			resetButton.setEnabled(true);
			acceptButton.setEnabled(false);
			rejectButton.setEnabled(false);
			skipButton.setEnabled(false);
			readyButton.setEnabled(false);
			undoCEMButton.setEnabled(false);
			undoAllCEMButton.setEnabled(false);
			newCounterExampleButton.setEnabled(false);
			repairButton.setEnabled(false);
			undoSelectedModificationsButton.setEnabled(false);
			undoAllModificationsButton.setEnabled(false);
			contextTable.dataModel.fireTableStructureChanged();
			break;
		case Constants.COMPLETION_STARTED:
			startButton.setEnabled(false);
			stopButton.setEnabled(true);
			resumeButton.setEnabled(false);
			resetButton.setEnabled(false);
			acceptButton.setEnabled(true);
			rejectButton.setEnabled(true);
			skipButton.setEnabled(true);
			readyButton.setEnabled(false);
			undoCEMButton.setEnabled(false);
			undoAllCEMButton.setEnabled(false);
			newCounterExampleButton.setEnabled(false);
			repairButton.setEnabled(false);
			undoSelectedModificationsButton.setEnabled(false);
			undoAllModificationsButton.setEnabled(false);
			dt.setActive(false);
			tabbedPane.setEnabledAt(GUIConstants.COUNTEREXAMPLEEDITOR_TAB_INDEX, false);
			tabbedPane.setEnabledAt(GUIConstants.REPAIR_TAB_INDEX,false);
			break;
		case Constants.COMPLETION_STOPPED:
			startButton.setEnabled(false);
			stopButton.setEnabled(false);
			resumeButton.setEnabled(true);
			resetButton.setEnabled(true);
			acceptButton.setEnabled(false);
			rejectButton.setEnabled(false);
			skipButton.setEnabled(false);
			readyButton.setEnabled(false);
			undoCEMButton.setEnabled(false);
			undoAllCEMButton.setEnabled(false);
			newCounterExampleButton.setEnabled(false);
			if (!getContext().getHistory().isEmpty()) {
				repairButton.setEnabled(true);
			}
			undoSelectedModificationsButton.setEnabled(false);
			undoAllModificationsButton.setEnabled(false);
			tabbedPane.setEnabledAt(GUIConstants.COUNTEREXAMPLEEDITOR_TAB_INDEX, false);
			tabbedPane.setEnabledAt(GUIConstants.REPAIR_TAB_INDEX,false);
			break;
		case Constants.COMPLETION_RESUMED:
			startButton.setEnabled(false);
			stopButton.setEnabled(true);
			resumeButton.setEnabled(false);
			resetButton.setEnabled(false);
			acceptButton.setEnabled(true);
			rejectButton.setEnabled(true);
			skipButton.setEnabled(true);
			readyButton.setEnabled(false);
			undoCEMButton.setEnabled(false);
			undoAllCEMButton.setEnabled(false);
			newCounterExampleButton.setEnabled(false);
			repairButton.setEnabled(false);
			explainButton.setEnabled(false);
			undoSelectedModificationsButton.setEnabled(false);
			undoAllModificationsButton.setEnabled(false);
			break;
		case Constants.COMPLETION_RESET:
			startButton.setEnabled(false);
			stopButton.setEnabled(false);
			resumeButton.setEnabled(false);
			resetButton.setEnabled(false);
			acceptButton.setEnabled(false);
			rejectButton.setEnabled(false);
			skipButton.setEnabled(false);
			readyButton.setEnabled(false);
			undoCEMButton.setEnabled(false);
			undoAllCEMButton.setEnabled(false);
			newCounterExampleButton.setEnabled(false);
			repairButton.setEnabled(false);
			undoSelectedModificationsButton.setEnabled(false);
			undoAllModificationsButton.setEnabled(false);
			dt.setActive(true);
			tabbedPane.setEnabledAt(GUIConstants.COUNTEREXAMPLEEDITOR_TAB_INDEX, false);
			tabbedPane.setEnabledAt(GUIConstants.REPAIR_TAB_INDEX,false);
			contextTable.dataModel.fireTableStructureChanged();
			messagesTextArea.setText(null);
			writeMessage(GUIConstants.INITIAL_MSG);
			break;
		case Constants.QUESTION_CONFIRMED:
			startButton.setEnabled(false);
			stopButton.setEnabled(true);
			resumeButton.setEnabled(false);
			resetButton.setEnabled(false);
			acceptButton.setEnabled(true);
			rejectButton.setEnabled(true);
			skipButton.setEnabled(true);
			readyButton.setEnabled(false);
			undoCEMButton.setEnabled(false);
			undoAllCEMButton.setEnabled(false);
			newCounterExampleButton.setEnabled(false);
			repairButton.setEnabled(false);
			undoSelectedModificationsButton.setEnabled(false);
			undoAllModificationsButton.setEnabled(false);
			contextTable.dataModel.fireTableStructureChanged();
			tabbedPane.setEnabledAt(GUIConstants.COUNTEREXAMPLEEDITOR_TAB_INDEX, false);
			break;
		case Constants.QUESTION_REJECTED:
			startButton.setEnabled(false);
			stopButton.setEnabled(true);
			resumeButton.setEnabled(false);
			resetButton.setEnabled(false);
			acceptButton.setEnabled(true);
			rejectButton.setEnabled(false);
			skipButton.setEnabled(true);
			readyButton.setEnabled(false);
			undoCEMButton.setEnabled(false);
			undoAllCEMButton.setEnabled(false);
			newCounterExampleButton.setEnabled(true);
			repairButton.setEnabled(false);
			undoSelectedModificationsButton.setEnabled(false);
			undoAllModificationsButton.setEnabled(false);
			advancedCounterExampleButton.setEnabled(true);
			tabbedPane.setEnabledAt(GUIConstants.COUNTEREXAMPLEEDITOR_TAB_INDEX, true);
			tabbedPane.setSelectedComponent(counterExampleEditorPanel);
			break;
		case Constants.COUNTEREXAMPLE_PROVIDED:
			startButton.setEnabled(false);
			stopButton.setEnabled(true);
			resumeButton.setEnabled(false);
			resetButton.setEnabled(false);
			acceptButton.setEnabled(true);
			rejectButton.setEnabled(true);
			skipButton.setEnabled(true);
			readyButton.setEnabled(false);
			undoCEMButton.setEnabled(false);
			undoAllCEMButton.setEnabled(false);
			newCounterExampleButton.setEnabled(false);
			repairButton.setEnabled(false);
			undoSelectedModificationsButton.setEnabled(false);
			undoAllModificationsButton.setEnabled(false);
			tabbedPane.setEnabledAt(GUIConstants.COUNTEREXAMPLEEDITOR_TAB_INDEX, false);
			tabbedPane.setSelectedComponent(explorationPanel);
			contextTable.dataModel.fireTableDataChanged();
			break;
		case Constants.COUNTEREXAMLE_CANDIDATE_MODIFIED:
			startButton.setEnabled(false);
			stopButton.setEnabled(true);
			resumeButton.setEnabled(false);
			resetButton.setEnabled(false);
			acceptButton.setEnabled(false);
			rejectButton.setEnabled(false);
			skipButton.setEnabled(false);
			// these should be set/reset from CounterExampleCandidatesTable
			readyButton.setEnabled(false);
			undoCEMButton.setEnabled(true);
			undoAllCEMButton.setEnabled(true);
			
			newCounterExampleButton.setEnabled(true);
			repairButton.setEnabled(false);
			undoSelectedModificationsButton.setEnabled(false);
			undoAllModificationsButton.setEnabled(false);
			break;
		case Constants.COUNTEREXAMPLE_READY:
			startButton.setEnabled(false);
			stopButton.setEnabled(true);
			resumeButton.setEnabled(false);
			resetButton.setEnabled(false);
			acceptButton.setEnabled(false);
			rejectButton.setEnabled(false);
			skipButton.setEnabled(false);
			readyButton.setEnabled(true);
			undoCEMButton.setEnabled(true);
			undoAllCEMButton.setEnabled(true);
			newCounterExampleButton.setEnabled(true);
			repairButton.setEnabled(false);
			undoSelectedModificationsButton.setEnabled(false);
			undoAllModificationsButton.setEnabled(false);
			break;
		case Constants.LAST_COUNTEREXAMLE_MODIFICATION_UNDONE:
			// TODO
			break;
		case Constants.ALL_COUNTEREXAMLE_MODIFICATIONS_UNDONE:
			// TODO
			break;
		case Constants.QUESTION_SKIPPED:
			// TODO
			contextTable.dataModel.fireTableStructureChanged();
			break;
		case Constants.SELECTED_CONTEXT_MODIFICATIONS_UNDONE:
			// TODO
			startButton.setEnabled(true);
			stopButton.setEnabled(false);
			resumeButton.setEnabled(false);
			resetButton.setEnabled(true);
			acceptButton.setEnabled(false);
			rejectButton.setEnabled(false);
			skipButton.setEnabled(false);
			readyButton.setEnabled(false);
			undoCEMButton.setEnabled(false);
			undoAllCEMButton.setEnabled(false);
			newCounterExampleButton.setEnabled(false);
			repairButton.setEnabled(false);
			undoSelectedModificationsButton.setEnabled(false);
			if (getContext().getHistory().isEmpty()) {
				undoAllModificationsButton.setEnabled(false);
				tabbedPane.setEnabledAt(GUIConstants.REPAIR_TAB_INDEX, false);
				tabbedPane.setSelectedComponent(explorationPanel);
			}
			else {
				undoAllModificationsButton.setEnabled(true);
				contextModificationsPanel.update();
			}
			contextTable.dataModel.fireTableStructureChanged();
			break;
		case Constants.ALL_CONTEXT_MODIFICATIONS_UNDONE:
			startButton.setEnabled(true);
			stopButton.setEnabled(false);
			resumeButton.setEnabled(false);
			resetButton.setEnabled(true);
			acceptButton.setEnabled(false);
			rejectButton.setEnabled(false);
			skipButton.setEnabled(false);
			readyButton.setEnabled(false);
			undoCEMButton.setEnabled(false);
			undoAllCEMButton.setEnabled(false);
			newCounterExampleButton.setEnabled(false);
			repairButton.setEnabled(false);
			undoSelectedModificationsButton.setEnabled(false);
			undoAllModificationsButton.setEnabled(false);
			// contextModificationsPanel.update();
			contextTable.dataModel.fireTableStructureChanged();
			tabbedPane.setEnabledAt(GUIConstants.REPAIR_TAB_INDEX, false);
			tabbedPane.setSelectedComponent(explorationPanel);
			break;
		case Constants.NEW_COUNTEREXAMPLE_REQUEST:
			// TODO
			undoCEMButton.setEnabled(true);
			undoAllCEMButton.setEnabled(true);
			break;
		case Constants.COMPLETION_FINISHED:
			startButton.setEnabled(false);
			stopButton.setEnabled(false);
			resumeButton.setEnabled(false);
			resetButton.setEnabled(true);
			acceptButton.setEnabled(false);
			rejectButton.setEnabled(false);
			skipButton.setEnabled(false);
			readyButton.setEnabled(false);
			undoCEMButton.setEnabled(false);
			undoAllCEMButton.setEnabled(false);
			newCounterExampleButton.setEnabled(false);
			repairButton.setEnabled(true);
			undoSelectedModificationsButton.setEnabled(false);
			undoAllModificationsButton.setEnabled(false);
			break;
		case Constants.REPAIR_REQUESTED:
			startButton.setEnabled(true);
			stopButton.setEnabled(false);
			resumeButton.setEnabled(false);
			resetButton.setEnabled(false);
			acceptButton.setEnabled(false);
			rejectButton.setEnabled(false);
			skipButton.setEnabled(false);
			readyButton.setEnabled(false);
			undoCEMButton.setEnabled(false);
			undoAllCEMButton.setEnabled(false);
			newCounterExampleButton.setEnabled(false);
			repairButton.setEnabled(false);
			undoSelectedModificationsButton.setEnabled(false);
			if (getContext().getHistory().isEmpty()) {
				undoAllModificationsButton.setEnabled(false);
			}
			else {
				undoAllModificationsButton.setEnabled(true);
			}
			tabbedPane.setEnabledAt(GUIConstants.REPAIR_TAB_INDEX, true);
			tabbedPane.setSelectedComponent(repairPanel);
			contextModificationsPanel.update();
			break;
		case Constants.IMPLICATION_MAKES_ONTOLOGY_INCONSISTENT:
			startButton.setEnabled(false);
			stopButton.setEnabled(true);
			resumeButton.setEnabled(false);
			resetButton.setEnabled(false);
			acceptButton.setEnabled(false);
			rejectButton.setEnabled(false);
			skipButton.setEnabled(false);
			readyButton.setEnabled(false);
			undoCEMButton.setEnabled(false);
			undoAllCEMButton.setEnabled(false);
			newCounterExampleButton.setEnabled(true);
			repairButton.setEnabled(false);
			undoSelectedModificationsButton.setEnabled(false);
			undoAllModificationsButton.setEnabled(false);
			tabbedPane.setEnabledAt(GUIConstants.COUNTEREXAMPLEEDITOR_TAB_INDEX, true);
			tabbedPane.setEnabledAt(GUIConstants.REPAIR_TAB_INDEX, false);
			tabbedPane.setSelectedComponent(counterExampleEditorPanel);
			break;
		case Constants.QUESTION_FOLLOWS_FROM_TBOX:
			startButton.setEnabled(false);
			stopButton.setEnabled(false);
			resumeButton.setEnabled(true);
			resetButton.setEnabled(false);
			acceptButton.setEnabled(false);
			rejectButton.setEnabled(false);
			skipButton.setEnabled(false);
			readyButton.setEnabled(false);
			undoCEMButton.setEnabled(false);
			undoAllCEMButton.setEnabled(false);
			newCounterExampleButton.setEnabled(false);
			repairButton.setEnabled(false);
			undoSelectedModificationsButton.setEnabled(false);
			undoAllModificationsButton.setEnabled(false);
			explainButton.setEnabled(true);
			tabbedPane.setEnabledAt(GUIConstants.COUNTEREXAMPLEEDITOR_TAB_INDEX, false);
			tabbedPane.setEnabledAt(GUIConstants.REPAIR_TAB_INDEX,false);
			break;
		default:
			log.fatal(GUIConstants.GUI_ACTION_UNDEFINED_MSG);
		}
	}
	
	
	private JToolBar prepareExplorationToolBar() {
		// create the exploration buttons
		StartCompletionUIAction startAction = new StartCompletionUIAction();
		startAction.setViewComponent(this);
		startButton = prepareButton(startAction, GUIConstants.START_BUTTON_TOOLTIP, 
				GUIConstants.START_BUTTON_TEXT);
		
		StopCompletionUIAction stopAction = new StopCompletionUIAction();
		stopAction.setViewComponent(this);
		stopButton = prepareButton(stopAction, GUIConstants.STOP_BUTTON_TOOLTIP, 
				GUIConstants.STOP_BUTTON_TEXT);
		
		RepairUIAction repairAction = new RepairUIAction();
		repairAction.setViewComponent(this);
		repairButton = prepareButton(repairAction, GUIConstants.REPAIR_BUTTON_TOOLTIP,
				GUIConstants.REPAIR_BUTTON_TEXT);
		
		resetButton = prepareButton(new ResetCompletionUIAction(this), GUIConstants.RESET_BUTTON_TOOLTIP, 
				GUIConstants.RESET_BUTTON_TEXT);
		
		ConfirmQuestionUIAction confirmAction = new ConfirmQuestionUIAction();
		confirmAction.setViewComponent(this);
		acceptButton = prepareButton(confirmAction, GUIConstants.ACCEPT_BUTTON_TOOLTIP, 
				GUIConstants.ACCEPT_BUTTON_TEXT);
		
		RejectQuestionUIAction rejectAction = new RejectQuestionUIAction();
		rejectAction.setViewComponent(this);
		rejectButton = prepareButton(rejectAction, GUIConstants.REJECT_BUTTON_TOOLTIP, 
				GUIConstants.REJECT_BUTTON_TEXT);
		
		SkipQuestionUIAction skipAction = new SkipQuestionUIAction();
		skipAction.setViewComponent(this);
		skipButton = prepareButton(skipAction, GUIConstants.SKIP_BUTTON_TOOLTIP,
				GUIConstants.SKIP_BUTTON_TEXT);
		
		// resume with the premise of the last question
		ResumeCompletionUIAction resumeCompletionAction = new ResumeCompletionUIAction(true);
		resumeCompletionAction.setViewComponent(this);
		resumeButton = prepareButton(resumeCompletionAction, GUIConstants.RESUME_BUTTON_TOOLTIP, 
				GUIConstants.RESUME_BUTTON_TEXT);
		
		ExplainUIAction explainAction = new ExplainUIAction();
		explainAction.setViewComponent(this);
		explainButton = prepareButton(explainAction, GUIConstants.EXPLAIN_BUTTON_TOOLTIP,
				GUIConstants.EXPLAIN_BUTTON_TEXT);
		
		// create the exploration toolbar
		explorationToolBar = new JToolBar();
		explorationToolBar.add(startButton);
		explorationToolBar.add(stopButton);
		explorationToolBar.add(repairButton);
		explorationToolBar.add(resetButton);
		explorationToolBar.add(resumeButton);
		explorationToolBar.add(acceptButton);
		explorationToolBar.add(rejectButton);
		explorationToolBar.add(skipButton);
		explorationToolBar.add(explainButton);
		return explorationToolBar;
	}
	
	private JPanel prepareExplorationPanel() {
		// create the context table
		contextTable = new ContextTable(context);
		// add the table to the contextScrollPane
		contextScrollPane = new JScrollPane(contextTable);
		// create the exploration panel
		explorationPanel = new JPanel(new BorderLayout());
		explorationPanel.add(contextScrollPane,BorderLayout.CENTER);
		explorationPanel.add(prepareExplorationToolBar(),BorderLayout.PAGE_END);
		return explorationPanel;
	}
	
	private JToolBar prepareCounterExampleEditorToolBar() {
		
		ProvideCounterExampleUIAction provideCounterExampleAction =
			new ProvideCounterExampleUIAction();
		provideCounterExampleAction.setViewComponent(this);
		readyButton = prepareButton(provideCounterExampleAction, GUIConstants.READY_BUTTON_TOOLTIP, 
				GUIConstants.READY_BUTTON_TEXT);
		
		UndoLastCEMUIAction undoCEMAction = new UndoLastCEMUIAction();
		undoCEMAction.setViewComponent(this);
		undoCEMButton = prepareButton(undoCEMAction, GUIConstants.UNDO_LAST_CECHANGE_BUTTON_TOOLTIP,
				GUIConstants.UNDO_LAST_CECHANGE_BUTTON_TEXT);
		
		UndoAllCEMUIAction undoAllCEMAction = new UndoAllCEMUIAction();
		undoAllCEMAction.setViewComponent(this);
		undoAllCEMButton = prepareButton(undoAllCEMAction, GUIConstants.UNDO_ALL_CECHANGES_BUTTON_TOOLTIP,
				GUIConstants.UNDO_ALL_CECHANGES_BUTTON_TEXT);
		
		NewCounterExampleUIAction newCounterExampleAction = new NewCounterExampleUIAction();
		newCounterExampleAction.setViewComponent(this);
		newCounterExampleButton = prepareButton(newCounterExampleAction, GUIConstants.NEW_COUNTEREXAMPLE_BUTTON_TOOLTIP,
				GUIConstants.NEW_COUNTEREXAMPLE_BUTTON_TEXT);
		
		AdvancedCounterExampleGUIAction advancedCounterExampleAction = new AdvancedCounterExampleGUIAction();
		advancedCounterExampleAction.setViewComponent(this);
		advancedCounterExampleButton = prepareButton(advancedCounterExampleAction, GUIConstants.ADVANCED_COUNTEREXAMPLE_BUTTON_TOOLTIP,
				GUIConstants.ADVANCED_COUNTEREXAMPLE_BUTTON_TEXT);
		
		counterExampleEditorToolBar = new JToolBar();
		counterExampleEditorToolBar.add(readyButton);
		counterExampleEditorToolBar.add(newCounterExampleButton);
		counterExampleEditorToolBar.add(undoCEMButton);
		counterExampleEditorToolBar.add(undoAllCEMButton);
		counterExampleEditorToolBar.add(advancedCounterExampleButton);
		return counterExampleEditorToolBar;
	}
	
	private JPanel prepareCounterExampleEditorPanel() {
		counterExampleCandidatesTable = new CounterExampleCandidatesTable(this);
		counterExampleEditorScrollPane = new JScrollPane(counterExampleCandidatesTable);
		counterExampleEditorPanel = new JPanel(new BorderLayout());
		counterExampleEditorPanel.add(counterExampleEditorScrollPane,BorderLayout.CENTER);
		counterExampleEditorPanel.add(prepareCounterExampleEditorToolBar(),
				BorderLayout.PAGE_END);
		return counterExampleEditorPanel;
	}
	
	private JToolBar prepareRepairToolBar() {
		UndoSelectedContextModificationsUIAction undoContextModificationAction =
			new UndoSelectedContextModificationsUIAction();
		undoContextModificationAction.setViewComponent(this);
		undoSelectedModificationsButton = prepareButton(undoContextModificationAction, GUIConstants.UNDO_LAST_CHANGE_BUTTON_TOOLTIP,
			GUIConstants.UNDO_LAST_CHANGE_BUTTON_TEXT);
		
		UndoAllContextModificationsUIAction undoAllContextModificationsAction =
			new UndoAllContextModificationsUIAction();
		undoAllContextModificationsAction.setViewComponent(this);
		undoAllModificationsButton = prepareButton(undoAllContextModificationsAction, GUIConstants.UNDO_ALL_CHANGES_BUTTON_TOOLTIP,
			GUIConstants.UNDO_ALL_CHANGES_BUTTON_TEXT);
		debugToolBar = new JToolBar();
		debugToolBar.add(undoSelectedModificationsButton);
		debugToolBar.add(undoAllModificationsButton);
		return debugToolBar;
	}
	
	private JPanel prepareRepairPanel() {
		contextModificationsPanel = new ContextModificationsPanel(this);
		contextModificationsScrollPane = new JScrollPane(contextModificationsPanel);
		repairPanel = new JPanel(new BorderLayout());
		repairPanel.add(contextModificationsScrollPane,BorderLayout.CENTER);
		repairPanel.add(prepareRepairToolBar(),BorderLayout.PAGE_END);
		log.debug("created repair panel");
		return repairPanel;
	}
	
	private JSplitPane prepareGUI() {
		
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab(GUIConstants.CONTEXT_TAB_TITLE, prepareExplorationPanel());
		tabbedPane.addTab(GUIConstants.COUNTEREXAMLEEDITOR_TAB_TITLE,
				prepareCounterExampleEditorPanel());
		tabbedPane.addTab(GUIConstants.REPAIR_TAB_TITLE, prepareRepairPanel());
		
		// messagesTextArea = new JTextArea();
		messagesTextArea = new JEditorPane();
		messagesTextArea.setEditable(false);
		messagesTextArea.setContentType("text/html");
		messagesTextArea.setEditorKit(new HTMLEditorKit());
		writeMessage(GUIConstants.INITIAL_MSG);
		
		messagesScrollPane = new JScrollPane(messagesTextArea);
		
		consoleTabbedPane = new JTabbedPane();
		consoleTabbedPane.addTab(GUIConstants.MESSAGES_TAB_TITLE, messagesScrollPane);
		
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,tabbedPane,
				consoleTabbedPane);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(350);
		
		return(splitPane);
	}
	
	private void writeMessage(String msg) {
		try {
			messagesTextArea.getEditorKit().read(new java.io.StringReader ("<html><body>" +
					msg + "</body></html>"),messagesTextArea.getDocument(),
					messagesTextArea.getDocument().getLength());
		}
		// messagesTextArea.append(msg + "\n");
		// try {
		// 	messagesTextArea.getDocument().insertString(messagesTextArea.getDocument().getLength(), msg + "\n", null);
		// }
		catch (BadLocationException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		// String cmd = e.getActionCommand();
		// if (Constants.ACCEPT_IMPLICATION.equals(cmd)) {
		// 	questionConfirmed();
		// } else if (Constants.REJECT_IMPLICATION.equals(cmd)) {
		// 	questionRejected();
		// } else if (Constants.START_EXPLORATION.equals(cmd)) {
		// 	startExploration();
		// } else if (Constants.RESET_EXPLORATION.equals(cmd)) {
		// 	resetCompletion();
		// } else if (Constants.STOP_EXPLORATION.equals(cmd)) {
		// 	stopCompletion();
		// } else if (Constants.DEBUG_EXPLORATION.equals(cmd)) {
		// 	debug();
		// } else if (Constants.COUNTEREXAMPLE_READY.equals(cmd)) {
		// 	counterExampleProvided();
		// } else if (Constants.RESUME_EXPLORATION.equals(cmd)) {
		// 	resumeCompletion();
		// } else if (Constants.UNDO_LAST_CECHANGE.equals(cmd)) {
		// 	undoLastCEChange();
		// } else if (Constants.UNDO_ALL_CECHANGES.equals(cmd)) {
		// 	undoAllCEChanges();
		// } else if (Constants.NEW_COUNTEREXAMPLE.equals(cmd)) {
		// 	newCounterExample();
		// } else {
		// 	// error
		// 	System.err.println("Unexpected action " + cmd);
		// 	// System.exit(-1);
		// }
	}

	// private synchronized void questionConfirmed() {
	// 	log.info(GUIConstants.CONFIRMED_QUESTION_MSG + currentQuestion);
	// 	ExpertAction<OWLClass,OWLIndividual> action = 
	// 		new ExpertAction<OWLClass,OWLIndividual>(this,Expert.CONFIRMED_QUESTION, currentQuestion);
	// 	fireExpertAction(action);
	// 	contextTable.dataModel.fireTableStructureChanged();
	// }
	
	// private synchronized void questionRejected() {
	// 	log.info(GUIConstants.REJECTED_QUESTION_MSG + currentQuestion);
	// 	ExpertAction<OWLClass,OWLIndividual> action = 
	// 		new ExpertAction<OWLClass,OWLIndividual>(this,Expert.REJECTED_QUESTION, currentQuestion);
	// 	fireExpertAction(action);
	// 	counterExampleEditorPanel.remove(counterExampleEditorScrollPane);
	// 	counterExampleCandidatesTable = new CounterExampleCandidatesTable(context,currentQuestion,this);
	// 	counterExampleEditorScrollPane = new JScrollPane(counterExampleCandidatesTable);
	// 	counterExampleEditorPanel.add(counterExampleEditorScrollPane);
	// 	tabbedPane.setEnabledAt(GUIConstants.COUNTEREXAMPLEEDITOR_TAB_INDEX, true);
	// 	tabbedPane.setSelectedComponent(counterExampleEditorPanel);
	// 	rejectButton.setEnabled(false);
	// 	readyButton.setEnabled(false);
	// 	newCounterExampleButton.setEnabled(true);
	// }
	
	// private synchronized void counterExampleProvided() {
	// 	log.info(GUIConstants.PROVIDED_COUNTEREXAMPLE_MSG);
	// 	context.counterExampleProvided(null, null);
	// 	tabbedPane.setEnabledAt(GUIConstants.COUNTEREXAMPLEEDITOR_TAB_INDEX, false);
	// 	tabbedPane.setSelectedComponent(explorationPanel);
	// 	rejectButton.setEnabled(true);
	// 	contextTable.dataModel.fireTableDataChanged();
	// }
	
	// private void undoLastCEChange() {
	// 	log.info(GUIConstants.UNDO_LAST_CECHANGE_MSG);
	// 	context.getHistory().undoLast();
	// 	counterExampleCandidatesTable.decrementActionCounter();
	// 	if (!counterExampleCandidatesTable.canUndo()) {
	// 		setUndoButtons(false);
	// 	}
	// 	if (readyButton.isEnabled() && !context.refutes(currentQuestion)) {
	// 		readyButton.setEnabled(false);
	// 	}
	// 	counterExampleCandidatesTable.getCandidates().update();
	// 	counterExampleCandidatesTable.dataModel.fireTableDataChanged();
	// }
	
	// private void undoAllCEChanges() {
	// 	context.getHistory().undoLastN(counterExampleCandidatesTable.getActionCounter());
	// 	counterExampleCandidatesTable.resetActionCounter();
	// 	setUndoButtons(false);
	// 	if (readyButton.isEnabled() && !context.refutes(currentQuestion)) {
	// 		readyButton.setEnabled(false);
	// 	}
	// 	counterExampleCandidatesTable.getCandidates().update();
	// 	counterExampleCandidatesTable.dataModel.fireTableDataChanged();
	// }
	
	// private void newCounterExample() {
	// 	context.addObject(getOWLWorkspace().createOWLIndividual().getOWLEntity());
	// 	counterExampleCandidatesTable.incrementActionCounter();
	// 	counterExampleCandidatesTable.getCandidates().update();
	// 	counterExampleCandidatesTable.dataModel.fireTableDataChanged();
	// }
	
	public void setUndoCEMButtons(boolean enabled) {
		undoCEMButton.setEnabled(enabled);
		undoAllCEMButton.setEnabled(enabled);
	}
	
	public void setUndoSelectedModificationsButton(boolean enabled) {
		undoSelectedModificationsButton.setEnabled(enabled);
	}
	
	public boolean isReadyButtonEnabled() {
		return readyButton.isEnabled();
	}
	
	public void enableReadyButton(boolean enabled) {
		readyButton.setEnabled(enabled);
	}
	
	// public void setReadyButton(boolean enabled) {
	// 	readyButton.setEnabled(enabled);
	// }
	
	public void dragEnter(DropTargetDragEvent dtde) {
	}

	public void dragOver(DropTargetDragEvent dtde) {
	}

	public void dropActionChanged(DropTargetDragEvent dtde) {
	}

	public void dragExit(DropTargetEvent dte) {
	}

	public void drop(DropTargetDropEvent dtde) {
		log.info("Drop: " + dtde);
		if (dtde.isDataFlavorSupported(OWLObjectDataFlavor.OWL_OBJECT_DATA_FLAVOR)) {
			try {
				List<OWLObject> objects = (List<OWLObject>) dtde
					.getTransferable().getTransferData(
							OWLObjectDataFlavor.OWL_OBJECT_DATA_FLAVOR);
				List<OWLClass> clses = new ArrayList<OWLClass>();
				for (OWLObject obj : objects) {
					if (obj instanceof OWLClass) {
						clses.add((OWLClass) obj);
					}
				}
				if (!clses.isEmpty()) {
					for (OWLClass cls : clses) {
						try {
							getContext().addAttribute(cls);
							// writeMessage(GUIConstants.ATTRIBUTE_ADDED_MSG + cls.getURI().getFragment());
							changeGUIState(Constants.ATTRIBUTES_ADDED);
						}
						catch (IllegalAttributeException e) {
							writeMessage(cls.getIRI().getFragment() + GUIConstants.ATTRIBUTE_ALREADY_ADDED_MSG);
						}
					}
					dtde.dropComplete(true);
				}
			} catch (UnsupportedFlavorException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} 

	// public String render(Set<OWLClass> s) {
	// 	String str = "<b>";
	// 	for (Iterator<OWLClass> it = s.iterator(); it.hasNext();) {
	// 		str += it.next().getURI().getFragment();
	// 		if (it.hasNext()) {
	// 			str += ", ";
	// 		}
	// 	}
	// 	str += "</b>";
	// 	return str;
	// }

	public void askQuestion(FCAImplication<OWLClass> question) {
		// currentQuestion = question;
		if (question.getPremise().isEmpty()) {
			writeMessage("<hr>" + GUIConstants.QUESTION_TEXT_EMPTY_PREMISE + 
					renderer.render(question.getConclusion()) + "?");
		}
		else {
			writeMessage("<hr>" + GUIConstants.QUESTION_TEXT_PART1 + 
					renderer.render(question.getPremise()) + "<br>" +
				GUIConstants.QUESTION_TEXT_PART2  + 
				renderer.render(question.getConclusion()) + "?");
		}
	}
	
	/**
	 * Called to notify the expert that a counterexample is required. Here it does not need
	 * to perform any operation.
	 */
	public void requestCounterExample(FCAImplication<OWLClass> question) {
	}
	
	public void implicationFollowsFromBackgroundKnowledge(FCAImplication<OWLClass> imp) {
		writeMessage(GUIConstants.QUESTION_FOLLOWS_FROM_TBOX_MSG);
		changeGUIState(Constants.QUESTION_FOLLOWS_FROM_TBOX);
		implicationToBeExplained = imp;
		// explanationHandler.handleExplain(getContext().toOWLSubClassAxiom(imp));
	}
	
	// public void popUpExplanationWorkbench(FCAImplication<OWLClass> question) {
	public void popUpExplanationWorkbench() {
		explanationHandler.handleExplain(null, getContext().toOWLSubClassAxiom(implicationToBeExplained));
	}
	
	public void forceToCounterExample(FCAImplication<OWLClass> question) {
		if (question.getPremise().isEmpty()) {
			writeMessage("<hr>" + GUIConstants.REQUEST_COUNTEREXAMPLE_TEXT + "<br>" +
					GUIConstants.REQUEST_COUNTEREXAMPLE_TEXT_EMPTY_PREMISE +
					renderer.render(question.getConclusion()));
		}
		else {
			writeMessage("<hr>" + GUIConstants.REQUEST_COUNTEREXAMPLE_TEXT + "<br>" +
					GUIConstants.REQUEST_COUNTEREXAMPLE_TEXT_PART1 +
					renderer.render(question.getPremise()) + "<br>" +
					GUIConstants.REQUEST_COUNTEREXAMPLE_TEXT_PART2 +
					renderer.render(question.getConclusion()));
					
		}
		// explanationHandler.handleExplain(getContext().toOWLSubClassAxiom(question));
		getContext().getCounterExampleCandidates().setQuestion(question);
		getContext().getCounterExampleCandidates().update();
		getCounterExampleCandidatesTable().getTableModel().fireTableStructureChanged();
		changeGUIState(Constants.IMPLICATION_MAKES_ONTOLOGY_INCONSISTENT);
	}
	
	// @Override
	public synchronized void addExpertActionListener(ExpertActionListener<OWLClass,OWLNamedIndividual> listener) {
		listeners.add(listener);
	}
	
	public synchronized void removeExpertActionListeners() {
		listeners.clear();
	}
	
	public synchronized void fireExpertAction(ExpertAction action) {
		for (ExpertActionListener<OWLClass,OWLNamedIndividual> listener : listeners) {
			listener.expertPerformedAction(action);
		}
	}
	
	public void counterExampleInvalid(IndividualObject counterExample, int reason) {
		switch (reason) {
		case Expert.COUNTEREXAMPLE_EXISTS: 
			writeMessage(GUIConstants.COUNTEREXAMPLE_EXISTS_MSG);
			break;
		case Expert.COUNTEREXAMPLE_INVALID:
			writeMessage(GUIConstants.COUNTEREXAMPLE_INVALID_MSG);
			break;
		}
	}
	
	// private synchronized void startExploration() {
	// 	log.info(GUIConstants.START_COMPLETION_MSG);
	// 	startButton.setEnabled(false);
	// 	acceptButton.setEnabled(true);
	// 	rejectButton.setEnabled(true);
	// 	stopButton.setEnabled(true);
	// 	resetButton.setEnabled(false);
	// 	dt.setActive(false);
	// 	ExpertAction<OWLClass,OWLIndividual> action = 
	// 		new ExpertAction<OWLClass,OWLIndividual>(this,Expert.STARTED_EXPLORATION);
	// 	fireExpertAction(action);
	// 	// try {
	// 	// 	context.startExploration();
	// 	// }
	// 	// catch (IllegalExpertException e) {
	// 	// 	e.printStackTrace();
	// 	// 	System.err.println("Expert not set");
	// 	// }
	// 	// catch (IllegalObjectException e) {
	// 	// 	e.printStackTrace();
	// 	// 	System.err.println("Illegal object?");
	// 	// }
	// }
	
	public void explorationFinished() {
		writeMessage(GUIConstants.COMPLETION_FINISHED_MSG);
		log.info(GUIConstants.COMPLETION_FINISHED_MSG);
		changeGUIState(Constants.COMPLETION_FINISHED);
	}
	
	// public synchronized void resetCompletion() {
	// 	// writeMessage(GUIConstants.COMPLETION_RESET_MSG);
	// 	// context.resetExploration();
	// 	dt.setActive(true);
	// 	startButton.setEnabled(false);
	// 	stopButton.setEnabled(false);
	// 	acceptButton.setEnabled(false);
	// 	rejectButton.setEnabled(false);
	// 	repairButton.setEnabled(false);
	// 	resetButton.setEnabled(false);
	// 	resumeButton.setEnabled(false);
	// 	messagesTextArea.setText(null);
	// 	writeMessage(GUIConstants.INITIAL_MSG);
	// 	ExpertAction<OWLClass,OWLIndividual> action = 
	// 		new ExpertAction<OWLClass,OWLIndividual>(this,Expert.RESET_EXPLORATION);
	// 	fireExpertAction(action);
	// 	log.info(GUIConstants.COMPLETION_RESET_MSG);
	// 	contextTable.dataModel.fireTableStructureChanged();
	// }
	
	// public synchronized void stopCompletion() {
	// 	writeMessage(GUIConstants.COMPLETION_STOPPED_MSG);
	// 	startButton.setEnabled(false);
	// 	stopButton.setEnabled(false);
	// 	acceptButton.setEnabled(false);
	// 	rejectButton.setEnabled(false);
	// 	repairButton.setEnabled(true);
	// 	resetButton.setEnabled(true);
	// 	resumeButton.setEnabled(true);
	// 	ExpertAction<OWLClass,OWLIndividual> action = 
	// 		new ExpertAction<OWLClass,OWLIndividual>(this,Expert.STOPPED_EXPLORATION);
	// 	fireExpertAction(action);
	// 	log.info(GUIConstants.COMPLETION_STOPPED_MSG);
	// }
	
	// public void debug() {
	// 	tabbedPane.add(GUIConstants.DEBUG_TAB_TITLE,
	// 			prepareRepairPanel());
	// 	tabbedPane.setSelectedComponent(repairPanel);
	// }
	
	// public synchronized void resumeCompletion() {
	// 	writeMessage(GUIConstants.COMPLETION_RESUMED_MSG);
	// 	log.info(GUIConstants.COMPLETION_RESUMED_MSG);
	// 	// context.resumeExploration();
	// 	startButton.setEnabled(false);
	// 	stopButton.setEnabled(true);
	// 	acceptButton.setEnabled(true);
	// 	rejectButton.setEnabled(true);
	// 	repairButton.setEnabled(false);
	// 	resetButton.setEnabled(false);
	// 	resumeButton.setEnabled(false);
	// 	ExpertAction<OWLClass,OWLIndividual> action = 
	// 		new ExpertAction<OWLClass,OWLIndividual>(this,Expert.RESUMED_EXPLORATION);
	// 	fireExpertAction(action);
	// }
	
	
	public PartialObjectDescription<OWLClass> getCounterExampleDescription() {
		// TODO:
		return null;
	}
	
}
