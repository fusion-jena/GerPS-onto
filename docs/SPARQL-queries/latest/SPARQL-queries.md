# SPARQL Queries

This is the list of sparql queries corresponding to the initially formulated competence questions

#### CQ1: Which *services* are offered?

```sparql

PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>

SELECT *
WHERE {
  ?Leistung rdf:type gerps:GERPSE008
}

```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/latest/cq1.csv)

#### CQ2: Which *processes* are necessary to perform a *service*?

```sparql

PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>

SELECT ?Prozess
WHERE {
  FILTER(?Leistung=gerps:99006028261000)
  ?Leistung gerps:GERPSP005 ?Prozess.
}


```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/latest/cq2.csv)

#### CQ3: Which *process steps* are necessary to perform a *service*?

```sparql

PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>

SELECT ?Prozessschritt
WHERE {
  FILTER(?Leistung=gerps:99006028261000)
  ?Leistung gerps:GERPSP005 ?Prozess.
  ?Prozess gerps:GERPSP006 ?Prozessschritt
}


```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/latest/cq3.csv)

#### CQ4: What is the first/last *process step* of a *process*?

```sparql

PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>

SELECT ?erster_Prozessschritt ?letzter_Prozessschritt
WHERE {
  ?StartEvent rdf:type bbo:StartEvent.
  ?EndEvent rdf:type bbo:EndEvent.
  ?StartEvent bbo:has_nextFlowNode ?erster_Prozessschritt.
  ?EndEvent bbo:has_previousFlowNode ?letzter_Prozessschritt
}      


```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/latest/cq4.csv)

#### CQ5: Which *LeikaID* has a *service*?

```sparql

PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>


SELECT ?ID
WHERE {
  FILTER(?Leistung=gerps:99006028261000)
  ?Leistung gerps:GERPSP016 ?ID
}        

```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/latest/cq5.csv)

#### CQ6: What is the *processing deadline* for a *process*?

```sparql

PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>


SELECT *
WHERE {
  ?Prozess gerps:GERPSP011 ?Bearbeitungsfrist
}

```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/latest/cq6.csv)

#### CQ7: Which *resources/data fields/documents* are required to perform a *service*?

```sparql

PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>


SELECT DISTINCT ?Resource
WHERE {
  FILTER(?Leistung=gerps:99006028261000)
  ?Leistung gerps:GERPSP005 ?Prozess.
  ?Prozess gerps:GERPSP006 ?Prozessschritt.
  ?Prozessschritt gerps:GERPSP008 ?Resource
}   

```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/latest/cq7.csv)

#### CQ8: What is the *submission deadline* of a *document*?

```sparql

PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>


SELECT *
WHERE {
  ?Dokument gerps:GERPSP010 ?Abgabefrist
}


```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/latest/cq8.csv)

#### CQ9: Which *data field ID* does a *data field* have?

```sparql

PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>

SELECT *
WHERE {
  ?Datenfeld gerps:GERPSP012 ?ID.
}  


```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/latest/cq9.csv)

#### CQ10: Which *actor(s)/main actor(s)/result receiver(s)/contributor(s)* execute(s) which *processes*?

```sparql

PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX prov: <http://www.w3.org/ns/prov#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>

SELECT DISTINCT ?Agent ?Role
WHERE {
  ?Prozess rdf:type gerps:GERPSE010 .
  ?Prozess prov:qualifiedAssociation ?Association .
  ?Association prov:agent ?Agent .
  ?Association prov:hadRole ?Role .
}         

```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/latest/cq10.csv)

#### CQ11: Which *actor(s)/main actor(s) /result receiver(s) /contributor(s)* participate(s) in which *steps* of the *process*?

```sparql

PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX prov: <http://www.w3.org/ns/prov#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>

SELECT DISTINCT ?Agent ?Role ?Prozessschritt
WHERE {
    ?Prozess rdf:type gerps:GERPSE010 .
    ?Prozess gerps:GERPSP006 ?Prozessschritt .
    ?Prozessschritt prov:qualifiedAssociation ?Association .
    ?Association prov:agent ?Agent .
    ?Association prov:hadRole ?Role .
}   

```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/latest/cq11.csv)

#### CQ12: On which *legal basis* is a *process* based?

```sparql

PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>

SELECT DISTINCT ?Handlungsgrundlage
WHERE {
  ?Prozess gerps:GERPSP006 ?Prozessschritt.
  ?Prozess bbo:id "99006028261000".
  ?Prozessschritt gerps:GERPSP001 ?Handlungsgrundlage .
}  

```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/latest/cq12.csv)

#### CQ13: On what *legal basis* is which *process step* based?

```sparql

PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>

SELECT *
WHERE {
  ?Prozessschritt gerps:GERPSP001 ?Handlungsgrundlage .
}

```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/latest/cq13.csv)

#### CQ14: What *reference activity groups* does a *process* include?

```sparql

PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>

SELECT DISTINCT ?RAG
WHERE {
  ?Prozess bbo:id "99006028261000".
  ?Prozess gerps:GERPSP006 ?Prozessschritt .
  ?Prozessschritt gerps:GERPSP009 ?RAG
}

```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/latest/cq14.csv)

#### CQ15: Which *reference activity group* corresponds to which *process step*?

```sparql

PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>

SELECT *
WHERE {
  ?Prozessschritt gerps:GERPSP009 ?RAG
}       

```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/latest/cq15.csv)
