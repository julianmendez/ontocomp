**OntoComP** is a [Protégé 4](http://www.co-ode.org/downloads/protege-x/) plugin for completing OWL ontologies. It enables the user to check whether an OWL ontology contains "all
relevant information" about the application domain, and extend
the ontology appropriately if this is not the case. It achieves this by asking the user questions of the form
_"are instances of classes C1 and C2 also instances of the class C3?"_.
If the user confirms such a question, then a new axiom of the application domain
that does not follow from the ontology has been discovered, and it is added to the ontology.
If she rejects it, she is expected to give a counterexample to this question, i.e., an
individual that is an instance of _C1, C2_ and _not C3_. When all such questions (about
some initially given classes) have been answered, the ontology is complete w.r.t. the
application domain.

The approach uses the knowledge acquisition method called _attribute exploration_ developed in _Formal Concept Analysis (FCA)_. It has been introduced in `[1]` and described in detail in `[2]`. **OntoComP** is an implementation of the algorithm described in `[3]`. It uses the ontology completion library [OntoComPlib](http://code.google.com/p/ontocomplib/) which is based on the FCA library [FCAlib](http://code.google.com/p/fcalib/).


---


`[1]` F. Baader, B. Ganter, U. Sattler, and B. Sertkaya.  [Completing Description Logic Knowledge Bases using Formal Concept Analysis](http://lat.inf.tu-dresden.de/research/papers/2007/BGSS-IJCAI07.pdf). In  Proceedings of the Twentieth International Joint Conference on Artificial Intelligence (IJCAI-07). AAAI Press, 2007.

`[2]` F. Baader, B. Ganter, U. Sattler, and B. Sertkaya. [Completing Description Logic Knowledge Bases using Formal Concept Analysis](http://lat.inf.tu-dresden.de/research/reports/2006/BGSS-LTCS-06-02.pdf). LTCS-Report 06-02, Chair for Automata Theory, Institute for Theoretical Computer Science, Dresden University of Technology, Germany, 2006.

`[3]` F. Baader, and B. Sertkaya.  [Usability Issues in Description Logic Knowledge Base Completion](http://lat.inf.tu-dresden.de/research/papers/2009/BaSe09.pdf). In  Proceedings of the 7th International Conference on Formal Concept Analysis, (ICFCA 2009), volume 5548 of LNAI. Springer-Verlag, 2009.

`[4]` B. Sertkaya. OntoComP: [A Protege Plugin for Completing OWL Ontologies](http://lat.inf.tu-dresden.de/research/papers/2009/Sert09b.pdf). In Proceedings of the 6th European Semantic Web Conference, (ESWC 2009), volume 5554 of LNCS. Springer-Verlag, 2009