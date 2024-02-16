# Ontology Population

## Running the Code
1. Download the project 
2. Install Maven
3. Run `mvn package` in FIM.parser-Folder
3. Run `Main.java`
4. The populated ontology will be generated in: `ontology/gerPS-Onto-populated.owl`

## Configuration
- The configuration is in `FIM.parser/src/main/resources/config.toml`. 
- There one can change the default parameters, e.g. the `outputPath` or the different input paths, e.g.,
    - the path for the input ontology `OntologyPath`, or 
    - for the [XProzess](https://www.xrepository.de/details/urn:xoev-de:mv:em:standard:xprozess), the [XDatenfeld](https://www.xrepository.de/details/urn:xoev-de:fim:standard:xdatenfelder) files, and the [Code lists](https://www.xoev.de/xoev/xoev-produkte/codelisten-handbuch-18762) (default in `xml.files`).

## Comment
* the `gerPS-Onto-populated.owl`-file is the result from the process with the standard configuration and the standards files.
