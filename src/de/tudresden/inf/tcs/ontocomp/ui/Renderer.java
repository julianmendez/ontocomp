package de.tudresden.inf.tcs.ontocomp.ui;

import java.util.Set;
import java.util.Iterator;

import org.semanticweb.owl.model.OWLClass;
import org.semanticweb.owl.model.OWLIndividual;

public class Renderer {

	public String render(Set<OWLClass> s) {
		String str = "<b>";
		for (Iterator<OWLClass> it = s.iterator(); it.hasNext();) {
			str += it.next().getURI().getFragment();
			if (it.hasNext()) {
				str += ", ";
			}
		}
		str += "</b>";
		return str;
	}
	
	public String render(OWLIndividual i) {
		return "<b>" + i.getURI().getFragment() + "</b>";
	}
	
	public String render(OWLClass c, boolean complement) {
		if (complement) {
			return "<b>not " + c + "</b>";
		}
		else {
			return "<b>" + c + "</b>";
		}
	}
	
}
