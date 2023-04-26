package uni.jena.bpmn.parser.Model;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import uni.jena.bpmn.parser.BPMNParser;
import uni.jena.bpmn.parser.OntologiesIRIs;

public class Activitiy extends FlowNode{
	public enum ActivitiyType{
		
		Subprocess(BPMNParser.config.SubProcess), 
		CallActivity(BPMNParser.config.Callactivity), 
		Task(BPMNParser.config.task);
		
		String address;
		
		private ActivitiyType(String Address) {
			this.address = Address;
		}
		
		public String getAddress() {
			return this.address;
		}
		
	}
	
	private ActivitiyType type;
	
	public Activitiy(String name, ActivitiyType t) {
		this.type = t;
		this.name = name;
	}
	
	public Activitiy(String name, ActivitiyType t, String id) {this(name,t); this.id=id;}


	public void writeOnto(Model model) {
		Resource cs = this.getResource(this.type.getAddress(), model);
		Resource actRes = model.createResource(this.getIRI());
		model.add(actRes, RDF.type, cs);
		
		model.add(actRes, RDFS.label, this.name);
		model.add(actRes, BPMNParser.config.getName(model), this.name);
		model.add(actRes, BPMNParser.config.getID(model), this.id);		
	}
	
}
