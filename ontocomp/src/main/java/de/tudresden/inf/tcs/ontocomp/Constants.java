package de.tudresden.inf.tcs.ontocomp;

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
 * Constants.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public interface Constants {
	static final String START_EXPLORATION = "start";
	static final String RESET_EXPLORATION = "reset";
	static final String ACCEPT_IMPLICATION = "accept";
	static final String REJECT_IMPLICATION = "reject";
	static final String STOP_EXPLORATION = "stop";
	static final String DEBUG_EXPLORATION = "debug";
	static final String RESUME_EXPLORATION = "resume";
	// static final String COUNTEREXAMPLE_READY = "counterexample_ready";
	static final String UNDO_LAST_CECHANGE = "undoLastCEChange";
	static final String UNDO_ALL_CECHANGES = "undoAllCEChanges";
	static final String NEW_COUNTEREXAMPLE = "newCounterExample";
	static final String UNDO_LAST_CHANGE = "undoLastChange";
	static final String UNDO_ALL_CHANGES = "undoAllChanges";
	
	// GUI state codes
	static final int COMPLETION_INIT = 0;
	static final int COMPLETION_STARTED = 1;
	static final int COMPLETION_STOPPED = 2;
	static final int COMPLETION_RESUMED = 3;
	static final int COMPLETION_RESET = 4;
	static final int QUESTION_CONFIRMED = 5;
	static final int QUESTION_REJECTED = 6;
	static final int COUNTEREXAMPLE_PROVIDED = 7;
	static final int REPAIR_REQUESTED = 8;
	static final int COUNTEREXAMLE_CANDIDATE_MODIFIED = 9;
	static final int LAST_COUNTEREXAMLE_MODIFICATION_UNDONE = 10;
	static final int ALL_COUNTEREXAMLE_MODIFICATIONS_UNDONE = 11;
	static final int SELECTED_CONTEXT_MODIFICATIONS_UNDONE = 12;
	static final int ALL_CONTEXT_MODIFICATIONS_UNDONE = 13;
	static final int ATTRIBUTES_ADDED = 14;
	static final int EXPLORATION_FINISHED = 15;
	static final int COUNTEREXAMPLE_READY = 16;
	static final int NEW_COUNTEREXAMPLE_REQUEST = 17;
	static final int COMPLETION_FINISHED = 18;
	static final int IMPLICATION_MAKES_ONTOLOGY_INCONSISTENT = 19;
	static final int ADVANCED_COUNTEREXAMPLE_GENERATION = 20;
	static final int QUESTION_SKIPPED = 21;
	static final int QUESTION_FOLLOWS_FROM_TBOX = 22;
	
	static final String CEL_REASONER_ID="de.tudresden.inf.lat.cel.de.tudresden.inf.lat.cel.protege.CelProtegeReasonerFactory";
	static final String JCEL_REASONER_ID="de.tudresden.inf.lat.jcel.de.tudresden.inf.lat.jcel.protege.main.JcelProtegeReasonerFactory";
	static final String PELLET_REASONER_ID="com.owldl.pellet.pellet.reasoner.factory";
}
