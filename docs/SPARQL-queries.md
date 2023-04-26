# SPARQL Queries

This is the list of sparql queries corresponding to the initially formulated competence questions

#### CQ1: Which *services* are offered?

```sparql

PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>

SELECT *
WHERE {
  ?Leistung rdf:type gerps:Leistung
}

```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/cq1.csv)

#### CQ2: Which *processes* are necessary to perform a *service*?

```sparql

PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>

SELECT ?Prozess
WHERE {
  FILTER(?Leistung=gerps:99006028261000)
  ?Leistung gerps:hat_prozess ?Prozess.
}


```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/cq2.csv)

#### CQ3: Which *process steps* are necessary to perform a *service*?

```sparql

PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>

SELECT ?Prozessschritt
WHERE {
  FILTER(?Leistung=gerps:99006028261000)
  ?Leistung gerps:hat_prozess ?Prozess.
  ?Prozess gerps:hat_prozessschritt ?Prozessschritt
}


```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/cq3.csv)

#### CQ4: What is the first/last *process step* of a *process*?

```sparql

PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX pro: <http://www.ProOnto.org/#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>

SELECT ?erster_Prozessschritt ?letzter_Prozessschritt
WHERE {
  ?StartEvent rdf:type bbo:StartEvent.
  ?EndEvent rdf:type bbo:EndEvent.
  ?StartEvent bbo:has_nextFlowNode ?erster_Prozessschritt.
  ?EndEvent bbo:has_previousFlowNode ?letzter_Prozessschritt
}    


```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/cq4.csv)

#### CQ5: Which *LeikaID* has a *service*?

```sparql

PREFIX pro: <http://purl.org/hpi/patchr#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>


SELECT ?ID
WHERE {
  FILTER(?Leistung=gerps:99006028261000)
  ?Leistung gerps:hat_leikaID ?ID
}        

```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/cq5.csv)

#### CQ6: What is the *processing deadline* for a *process*?

```sparql

PREFIX pro: <http://purl.org/hpi/patchr#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>


SELECT ?Bearbeitungsfrist
WHERE {
  FILTER(?Prozess=gerps:prozess_99006028261000)
  ?Prozess gerps:hat_bearbeitungsfrist ?Bearbeitungsfrist
}

```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/cq6.csv)

#### CQ7: Which *resources/data fields/documents* are required to perform a *service*?

```sparql

PREFIX pro: <http://purl.org/hpi/patchr#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>


SELECT DISTINCT ?Resource
WHERE {
  FILTER(?Leistung=gerps:99006028261000)
  ?Leistung gerps:hat_prozess ?Prozess.
  ?Prozess gerps:hat_prozessschritt ?Prozessschritt.
  ?Prozessschritt gerps:hat_ressource ?Resource
}    

```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/cq7.csv)

#### CQ8: What is the *submission deadline* of a *document*?

```sparql

PREFIX pro: <http://purl.org/hpi/patchr#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>


SELECT *
WHERE {
  ?Dokument gerps:hat_abgabefrist ?Abgabefrist
}


```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/cq8.csv)

#### CQ9: Which *data field ID* does a *data field* have?

```sparql

PREFIX pro: <http://purl.org/hpi/patchr#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>

SELECT *
WHERE {
  ?Datenfeld gerps:hat_datenfeldID ?ID.
}   


```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/cq9.csv)

#### CQ10: Which *actor(s)/main actor(s)/result receiver(s)/contributor(s)* execute(s) which *processes*?

```sparql

PREFIX pro: <http://purl.org/hpi/patchr#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>

SELECT DISTINCT *
WHERE {    
  ?Akteur gerps:fuehrt_aus ?Prozess
}          

```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/cq10.csv)

#### CQ11: Which *actor(s)/main actor(s) /result receiver(s) /contributor(s)* participate(s) in which *steps* of the *process*?

```sparql

PREFIX pro: <http://purl.org/hpi/patchr#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>

SELECT DISTINCT *
WHERE {    
  ?Akteur gerps:beteiligt_sich_an ?Prozessschritt
}   

```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/cq11.csv)

#### CQ12: On which *legal basis* is a *process* based?

```sparql

PREFIX pro: <http://purl.org/hpi/patchr#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>

SELECT DISTINCT ?Handlungsgrundlage
WHERE {
  ?Prozess gerps:hat_prozessschritt ?Prozessschritt.
  ?Prozess gerps:hat_leikaID "99006028261000".
  ?Prozessschritt gerps:basiert_auf ?Handlungsgrundlage
}  

```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/cq12.csv)

#### CQ13: On what *legal basis* is which *process step* based?

```sparql

PREFIX pro: <http://purl.org/hpi/patchr#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>

SELECT *
WHERE {
  ?Prozessschritt a gerps:Prozessschritt.   
  ?Prozessschritt gerps:basiert_auf ?Handlungsgrundlage
}

```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/cq13.csv)

#### CQ14: What *reference activity groups* does a *process* include?

```sparql

PREFIX pro: <http://purl.org/hpi/patchr#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>

SELECT DISTINCT ?RAG
WHERE {
  ?prozess a gerps:Prozess.
  ?prozess gerps:hat_leikaID "99006028261000".
 ?prozess gerps:hat_prozessschritt ?Prozessschritt .
  ?Prozessschritt gerps:hat_typ ?RAG
}

```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/cq14.csv)

#### CQ15: Which *reference activity group* corresponds to which *process step*?

```sparql

PREFIX pro: <http://purl.org/hpi/patchr#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX bbo: <http://BPMNbasedOntology#>
PREFIX gerps: <https://w3id.org/GerPS-onto/ontology#>

SELECT *
WHERE {
  ?Prozessschritt gerps:hat_typ ?RAG
}       

```

[Result](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-results/cq15.csv)
