package uni.jena.bpmn.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class main {
	public static void main(String[] args) throws IllegalStateException, FileNotFoundException, IOException {
		OntologiesIRIs.CreateIRIList();
		
		BPMNParser p = new BPMNParser("../ontology/processRDF.xml");
		
		try {
			//p.parse(new File("../xml.files/BPMN DI-Export.bpmn"), "99006028261000");
			p.writeOntology();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
