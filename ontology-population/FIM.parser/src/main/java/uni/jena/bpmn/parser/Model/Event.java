package uni.jena.bpmn.parser.Model;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import uni.jena.bpmn.parser.BPMNParser;
import uni.jena.bpmn.parser.OntologiesIRIs;

public class Event extends FlowNode{
	public enum EventType{
		boundaryEvent("http://BPMNbasedOntology#BoundaryEvent"),
		StartEvent("http://BPMNbasedOntology#StartEvent"),
		EndEvent("http://BPMNbasedOntology#EndEvent");
		
		String address;
		
		private EventType(String Address) {
			this.address = Address;
		}
		
		public String getAddress() {
			return this.address;
		}
		
	}
	
	private EventType type;
	private String attachedTo;

	
	public Event(String name, String id, EventType t) {
		this.name = name;
		this.id = id;
		this.type = t;
	}
	
	public Event(String Name, String id, EventType t, String AttachedTo) {
		this(Name, id, t);
		this.attachedTo = AttachedTo;
	}

	public void writeOnto(Model model) {
		Resource cs = this.getResource(this.type.getAddress(), model);
		Resource event = model.createResource(this.getIRI());
		
		model.add(event, RDF.type, cs);
		model.add(event, RDFS.label, this.getName());
		model.add(event,BPMNParser.config.getName(model) ,this.name);
		model.add(event,BPMNParser.config.getID(model) ,this.id);
		
		if(this.type == EventType.boundaryEvent) {
			Property at = getProperty(BPMNParser.config.getAttachedTo(), model);

			Resource atres = model.createResource(this.getSuffix()+this.attachedTo);
			
			model.add(event, at, atres);
		}
		
	}
	
}
