# Change log GerPS-onto V1 -> V2

The quality of the first version of the ontology was enhanced by applying the following changes:
- Preserve both information about the actor of a process step and the corresponding role (main actor, result receiver, or contributor) for this specific process step/process. The previous version defined the different roles as subclasses of the actor class, which leads to losing the association of a role to an agent for a specific process step (agent roles change but they stay the same agents).
The performed modification consists of defining the different actors (main actor, result receiver, and contributors) as roles (as instances of the prov:Role class) by reusing some classes and relations of the [PROV-O](https://www.w3.org/TR/prov-o/) ontology,  this also has the advantage of adding new roles at the instance level if needed in the future without changing the ontology schema itself:
  - gerps:ProcessStep rdfs:subClassOf prov:Activity
  - gerps:Process rdfs:subClassOf prov:Activity (to be able to respond to the competency question [CQ10](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/SPARQL-queries/latest/SPARQL-queries.md?ref_type=heads#cq10-which-actorsmain-actorsresult-receiverscontributors-executes-which-processes) where all available roles and agents should be listed independently from the process steps e.g., in case data is missing and not all agents/roles are assigned with process steps)
  - prov:Activity prov:qualifiedAssociation prov:Association
  - prov:Association prov:agent prov:Agent
  - prov:Association prov:hadRole prov:Role
  - gerps:MainActor rdf:type prov:Role
  - gerps:ResultReceiver rdf:type prov:Role
  - gerps:Contributor rdf:type prov:Role
  - gerps:Actor rdfs:subClassOf prov:Agent
  - Remove properties gerps:executes und gerps_participates_in
- Adapt relation between the different roles (now instances) and the e-Gov Core Vocabularies classes
  - gerps:MainActor rdf:type m8g:PublicOrganisation
  - gerps:ResultReceiver rdf:type legal:LegalEntity
  - gerps:Contributor rdf:type org:FormalOrganization
- Switch to alphanumeric IRIs:
  - The new used IRIs for classes and properties are documented [here](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/mapping-iri.md?ref_type=heads)
- Avoid changing external ontologies
  - remove: bbo:Agent rdfs:subClassOf foaf:Agent
- Avoid usage of owl:equivalentClass it is in most of the cases too strict (e.g., in the previous version gerps:MainActor instances should always be a  m8g:PublicOrganisation and we are not sure about that)
   - replace owl:equivalentClass with rdfs:subClassOf everywhere
- Define German and English labels
