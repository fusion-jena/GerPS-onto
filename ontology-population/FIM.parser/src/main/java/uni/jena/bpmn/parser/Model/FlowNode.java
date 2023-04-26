package uni.jena.bpmn.parser.Model;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

import uni.jena.bpmn.parser.BPMNParser;

public abstract class FlowNode implements IFlowNode {
	
	protected String name;
	protected String id;
	
	public String getName() {
		return this.name;
	}
	
	public String getIRI() {
		return this.getSuffix() + this.id;
	}
	
	public String getSuffix() {
		return BPMNParser.config.getBaseURL();
	}
	
	public Property getProperty(String IRI, Model model) {
		Property p = model.getProperty(IRI);
		if(p == null) {
			p = model.createProperty(IRI);
		}
		
		return p;
	}
	
	public Resource getResource(String IRI, Model model) {
		Resource p = model.getResource(IRI);
		if(p == null) {
			p = model.createResource(IRI);
		}
		
		return p;
	}
}
