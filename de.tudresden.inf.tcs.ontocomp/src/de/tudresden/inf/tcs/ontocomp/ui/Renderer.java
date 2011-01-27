package de.tudresden.inf.tcs.ontocomp.ui;

import java.util.Iterator;
import java.util.Set;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

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
 * Renderer.
 * @author Baris Sertkaya
 * Technische Universtaet Dresden
 * sertkaya@tcs.inf.tu-dresden.de
 */

public class Renderer {

	public String render(Set<OWLClass> s) {
		if (s.isEmpty()) {
			return "<b>Thing</b>";
		}
		String str = "<b>";
		for (Iterator<OWLClass> it = s.iterator(); it.hasNext();) {
			str += toString(it.next().getIRI());
			if (it.hasNext()) {
				str += ", ";
			}
		}
		str += "</b>";
		return str;
	}
	
	public String render(OWLNamedIndividual i) {
		return "<b>" + toString(i.getIRI()) + "</b>";
	}
	
	public String render(OWLClass c, boolean complement) {
		if (complement) {
			return "<b>not " + c + "</b>";
		}
		else {
			return "<b>" + c + "</b>";
		}
	}
	
	private String toString(IRI id) {
		return (id.getFragment() == null) ?  id.toString(): id.getFragment().toString();
	}

}
