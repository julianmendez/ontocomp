# [OntoComP](https://julianmendez.github.io/ontocomp/)

*A Protégé plugin for completing OWL ontologies*

[![build](https://github.com/julianmendez/ontocomp/workflows/Java%20CI/badge.svg)](https://github.com/julianmendez/ontocomp/actions)
[![license](https://img.shields.io/badge/license-LGPL%203.0-blue.svg)](https://www.gnu.org/licenses/lgpl-3.0.txt)

**OntoComP** is a [Protégé 4](https://protege.stanford.edu/) plugin for completing OWL ontologies. It enables the user to check whether an OWL ontology contains "all relevant information" about the application domain, and extend the ontology appropriately if this is not the case. It achieves this by asking the user questions of the form *"are instances of classes C1 and C2 also instances of the class C3?"*. If the user confirms such a question, then a new axiom of the application domain that does not follow from the ontology has been discovered, and it is added to the ontology. If she rejects it, she is expected to give a counterexample to this question, i.e., an individual that is an instance of *C1*, *C2* and *not C3*. When all such questions (about some initially given classes) have been answered, the ontology is complete w.r.t. the application domain.

The approach uses the knowledge acquisition method called *attribute exploration* developed in *Formal Concept Analysis (FCA)*. It has been introduced in [1] and described in detail in [2]. **OntoComP** is an implementation of the algorithm described in [3]. It uses the ontology completion library [OntoComPlib](https://github.com/julianmendez/ontocomplib/) which is based on the FCA library [FCAlib](https://github.com/julianmendez/fcalib/).


## Developers

Original Developer: [Barış Sertkaya](https://sites.google.com/site/sertkayabaris/)

Additional Developer: [Julian Mendez](https://julianmendez.github.io)


## License

[GNU GPL v3](http://www.gnu.org/licenses/gpl-3.0.txt)


## References

[1] F. Baader, B. Ganter, U. Sattler, and B. Sertkaya. [Completing Description Logic Knowledge Bases using Formal Concept Analysis](https://lat.inf.tu-dresden.de/research/papers/2007/BGSS-IJCAI07.pdf). In Proceedings of the Twentieth International Joint Conference on Artificial Intelligence (IJCAI-07). AAAI Press, 2007.

[2] F. Baader, B. Ganter, U. Sattler, and B. Sertkaya. [Completing Description Logic Knowledge Bases using Formal Concept Analysis](https://lat.inf.tu-dresden.de/research/reports/2006/BGSS-LTCS-06-02.pdf). LTCS-Report 06-02, Chair for Automata Theory, Institute for Theoretical Computer Science, Dresden University of Technology, Germany, 2006.

[3] F. Baader, and B. Sertkaya. [Usability Issues in Description Logic Knowledge Base Completion](https://lat.inf.tu-dresden.de/research/papers/2009/BaSe09.pdf). In Proceedings of the 7th International Conference on Formal Concept Analysis, (ICFCA 2009), volume 5548 of LNAI. Springer-Verlag, 2009.

[4] B. Sertkaya. [OntoComP: A Protege Plugin for Completing OWL Ontologies](https://lat.inf.tu-dresden.de/research/papers/2009/Sert09b.pdf). In Proceedings of the 6th European Semantic Web Conference, (ESWC 2009), volume 5554 of LNCS. Springer-Verlag, 2009


