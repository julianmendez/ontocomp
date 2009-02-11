package de.tudresden.inf.tcs.ontocomp.ui;

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
 * GUI constants.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public interface GUIConstants {

	final String CONTEXT_TAB_TITLE = "Context";
	final String MESSAGES_TAB_TITLE = "Messages";
	final String COUNTEREXAMLEEDITOR_TAB_TITLE = "Counterexample editor";
	final String REPAIR_TAB_TITLE = "Repair";
	
	static final int CONTEXT_TAB_INDEX = 0;
	static final int COUNTEREXAMPLEEDITOR_TAB_INDEX = 1;
	static final int REPAIR_TAB_INDEX = 2;
	
	final String START_BUTTON_TEXT = "Start";
	final String RESET_BUTTON_TEXT = "Reset";
	final String ACCEPT_BUTTON_TEXT = "Yes";
	final String REJECT_BUTTON_TEXT = "No";
	final String STOP_BUTTON_TEXT = "Stop";
	final String REPAIR_BUTTON_TEXT = "Repair";
	final String READY_BUTTON_TEXT = "Ready";
	final String RESUME_BUTTON_TEXT = "Resume";
	final String UNDO_LAST_CECHANGE_BUTTON_TEXT = "Undo";
	final String UNDO_ALL_CECHANGES_BUTTON_TEXT = "Undo all";
	final String NEW_COUNTEREXAMPLE_BUTTON_TEXT = "New";
	final String UNDO_LAST_CHANGE_BUTTON_TEXT = "Undo";
	final String UNDO_ALL_CHANGES_BUTTON_TEXT = "Undo all";
	final String ADVANCED_COUNTEREXAMPLE_BUTTON_TEXT = "Advanced";

	static final String START_BUTTON_TOOLTIP = "Start the completion";
	static final String RESET_BUTTON_TOOLTIP = "Reset the completion";
	static final String ACCEPT_BUTTON_TOOLTIP = "Yes, this is true in my application domain";
	static final String REJECT_BUTTON_TOOLTIP = "No, this is not true in my application domain";
	static final String STOP_BUTTON_TOOLTIP = "Stop the completion";
	static final String REPAIR_BUTTON_TOOLTIP = "Ooops, I did something wrong!";
	static final String READY_BUTTON_TOOLTIP = "Counterexample ready";
	static final String RESUME_BUTTON_TOOLTIP = "Resume the stopped completion";
	static final String UNDO_LAST_CECHANGE_BUTTON_TOOLTIP = "Undo last change";
	static final String UNDO_ALL_CECHANGES_BUTTON_TOOLTIP = "Undo all changes";
	static final String NEW_COUNTEREXAMPLE_BUTTON_TOOLTIP = "Add new counterexample";
	static final String UNDO_LAST_CHANGE_BUTTON_TOOLTIP = "Undo last change";
	static final String UNDO_ALL_CHANGES_BUTTON_TOOLTIP = "Undo all changes";
	static final String ADVANCED_COUNTEREXAMPLE_BUTTON_TOOLTIP = "Advanced counterexample generation";
	
	static final String INITIAL_MSG = "Welcome to the <b>Ontology Completion Plugin</b><br>" +
			"Drag & drop class names from the class hierarchy into the context, hit start when you are ready<hr>";
	static final String ATTRIBUTE_ADDED_MSG = "Added ";
	static final String ATTRIBUTE_ALREADY_ADDED_MSG = " has already been added";
	static final String COUNTEREXAMPLE_EXISTS_MSG = "An individual with the same name exists";
	static final String COUNTEREXAMPLE_INVALID_MSG = "The provided individual is not a proper counterexample";
	// static final String QUESTION_TEXT_EMPTY_PREMISE = "Is it true that every individual is an instance of ";
	static final String QUESTION_TEXT_EMPTY_PREMISE = "Is every individual an instance of ";
	// static final String QUESTION_TEXT_PART1 = "Is it true that instances of ";
	static final String QUESTION_TEXT_PART1 = "Are instances of ";
	// static final String QUESTION_TEXT_PART2 = " are also instances of ";
	static final String QUESTION_TEXT_PART2 = " also instances of ";
	static final String START_COMPLETION_MSG = "=== Completion starting ===";
	static final String COMPLETION_FINISHED_MSG = "=== Completion finished ===";
	static final String COMPLETION_STOPPED_MSG = "=== Completion stopped by user ===";
	static final String COMPLETION_RESET_MSG = "=== Completion reset by user ===";
	static final String COMPLETION_RESUMED_MSG = "=== Resuming completion ===";
	static final String CONFIRMED_QUESTION_MSG = "Expert confirmed question: ";
	static final String REJECTED_QUESTION_MSG = "Expert rejected question: ";
	static final String PROVIDED_COUNTEREXAMPLE_MSG = "Expert provided counterexample: ";
	static final String NEW_COUNTEREXAMPLE_MSG = "Please enter a name for the counterexample";
	static final String UNDO_LAST_CECHANGE_MSG = "Undo last counterexample description change";
	static final String GUI_ACTION_UNDEFINED_MSG = "Undefined GUI action!";
	static final String NEW_IMPLICATION_MODIFICATION_TEXT_PART1 = "Instances of ";
	static final String NEW_IMPLICATION_MODIFICATION_TEXT_PART2 = " are also instances of ";
	static final String NEW_IMPLICATION_MODIFICATION_TEXT_EMPTY_PREMISE = "Everything is an instance of ";
	static final String NEW_OBJECT_MODIFICATION_TEXT_PART1 = "New individual ";
	static final String NEW_OBJECT_MODIFICATION_TEXT_PART2 = " instance(s) of ";
	static final String CLASSASSERTION_MODIFICATION_TEXT_PART1 = "Individal ";
	static final String CLASSASSERTION_MODIFICATION_TEXT_PART2 = " is an instance of ";
	static final String REQUEST_COUNTEREXAMPLE_TEXT = "Provide a counterexample to the following: ";
	static final String REQUEST_COUNTEREXAMPLE_TEXT_EMPTY_PREMISE = "Everything is an instance of ";
	static final String REQUEST_COUNTEREXAMPLE_TEXT_PART1 = "Instances of ";
	static final String REQUEST_COUNTEREXAMPLE_TEXT_PART2 = " are also instances of ";
	
	static final char PLUS_SIGN = '+';
	static final char MINUS_SIGN = '-';
	static final char CROSS_SIGN = 'X';
	static final char QUESTION_MARK = '?';
}
