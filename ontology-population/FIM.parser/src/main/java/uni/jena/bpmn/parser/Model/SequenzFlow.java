package uni.jena.bpmn.parser.Model;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import uni.jena.bpmn.parser.BPMNParser;
import uni.jena.bpmn.parser.OntologiesIRIs;

public class SequenzFlow extends FlowNode{
	private String resourceAddress = BPMNParser.config.getSequenzFLow();
	private String inComming;
	private String outGoing;
		
	public SequenzFlow(String in, String out) {
		this.inComming = in;
		this.outGoing = out;
	}
	
	public SequenzFlow(String name, String id, String source, String target) {
		this(source,target);
		this.name = name;
		this.id = id;
	}

	public void writeOnto(Model model) {
		Resource cs = this.getResource(getRessource(), model);
		Resource sf = model.createResource(this.getIRI());
		
		model.add(sf, RDF.type, cs);
		if(this.getName() != null) {
			model.add(sf, RDFS.label, this.getName());
			model.add(sf, BPMNParser.config.getName(model), this.getName());
		}
		model.add(sf, BPMNParser.config.getID(model), this.id);
		
		Property sourceRef = this.getProperty(BPMNParser.config.getSourceRef(), model);
		Property targetRef = this.getProperty(BPMNParser.config.getTargetRef(), model);
		
		model.add(sf, sourceRef, this.getResource(this.getSuffix()+ this.inComming, model));
		model.add(sf, targetRef, this.getResource(this.getSuffix()+ this.outGoing, model));	
	}
	
	public String getRessource() {
		return this.resourceAddress;
	}
	
}
