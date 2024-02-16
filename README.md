[![DOI](https://zenodo.org/badge/632821036.svg)](https://zenodo.org/badge/latestdoi/632821036)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://github.com/fusion-jena/GerPS-onto/blob/master/LICENSE)

# GerPS-onto: An ontology for German public service processes

- :books: [Documentation](https://w3id.org/GerPS-onto/ontology/)
- :hourglass_flowing_sand: [Download latest](https://zenodo.org/doi/10.5281/zenodo.7845887)
- :page_facing_up: [SPARQL Queries](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/latest/SPARQL-queries.md)
- :globe_with_meridians: [SPARQL Endpoint](https://fuseki.simplex.fmi.uni-jena.de/#/dataset/GerPS-Onto_V2.0/query)
- :repeat: [Change Log](https://github.com/fusion-jena/GerPS-onto/tree/main/docs/change-log.md)

GerPS-onto models the process of a German public service.
It was created by extending the [BBO](https://hal.science/hal-02365012/document) with some domain specific concepts.
In addition, some concepts are also mapped to the [e-Government Core Vocabularies](https://joinup.ec.europa.eu/collection/semantic-interoperability-community-semic/solution/e-government-core-vocabularies/about).
The ontology was automatically populated with an exemplary German public service by parsing XML-based ([XProzess](https://www.xrepository.de/details/urn:xoev-de:mv:em:standard:xprozess), [XDatenfelder](https://www.xrepository.de/details/urn:xoev-de:fim:standard:xdatenfelder)) files used for describing administrative BPMN-processes and form fields involved in executing the specific service.
The code for population with instances is available [here](https://github.com/fusion-jena/GerPS-onto/tree/main/ontology-population).
The ontology is exemplary populated with instances from a specific [German public service](https://fimportal.de/detail/L/99006028261000) and publicly available on [Zenodo](https://zenodo.org/doi/10.5281/zenodo.7866313).

## Citation

### GerPS-onto V1
```
@incollection{incollection,
author = "Feddoul, Leila and Raupach, Maximilian and Löffler, Felicitas and Babalou, Samira and Hoyer, Jonas and Mauch, Marianne and König-Ries, Birgitta",
title = "On which legal regulations is a public service based? Fostering transparency in public administration by using knowledge graphs",
year = 2023,
doi = "10.18420/inf2023_115",
booktitle = "INFORMATIK 2023 - Designing Futures: Zukünfte gestalten",
publisher = "Gesellschaft für Informatik e.V.",
address = "Bonn",
pissn = "1617-5468",
isbn = "978-3-88579-731-9",
pages = "1035--1040",
}
```

## License

This project is licensed under the [MIT License](https://github.com/fusion-jena/GerPS-onto/blob/master/LICENSE).
