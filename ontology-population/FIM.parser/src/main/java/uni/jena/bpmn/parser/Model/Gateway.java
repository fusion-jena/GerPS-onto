package uni.jena.bpmn.parser.Model;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import uni.jena.bpmn.parser.BPMNParser;
import uni.jena.bpmn.parser.OntologiesIRIs;

public class Gateway extends FlowNode{

	public enum GatewayType{
		exclusiv(BPMNParser.config.getExclusiveGateway()), // XOR
		inclusiv(BPMNParser.config.getInclusiveGateway()), // OR
		parrallel(BPMNParser.config.getParallelGateway()); // AND
		
		private String address;
		
		private GatewayType(String Address) {
			this.address = Address;
		}
		
		public String getAddress() {
			return this.address;
		}
	}
	
	private GatewayType type;
	
	public Gateway(GatewayType t, String name) {
		this.type = t;
		this.name = name;
	}
	
	public Gateway(GatewayType t, String name, String id) {
		this(t,name);
		this.id= id;
	}

	public void writeOnto(Model model) {
		Resource cs = this.getResource(this.type.getAddress(), model);
		Resource gateRes = model.createResource(this.getIRI());
		
		model.add(gateRes, RDF.type, cs);
		model.add(gateRes, RDFS.label, this.name);
		model.add(gateRes, BPMNParser.config.getName(model) ,this.name);
		model.add(gateRes, BPMNParser.config.getID(model) ,this.id);
	}
}
