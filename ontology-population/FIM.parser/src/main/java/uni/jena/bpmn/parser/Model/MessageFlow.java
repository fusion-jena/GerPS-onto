package uni.jena.bpmn.parser.Model;

public class MessageFlow extends SequenzFlow {
	// TODO als MessageFlowClass definieren / nicht in BBO enthalten
	private String resourceAddress = "http://BPMNbasedOntology#MessageFlow";

	public MessageFlow(String in, String out) {
		super(in, out);
	}
	
	public MessageFlow(String name, String id, String source, String target) {
		this(source,target);
		this.name = name;
		this.id = id;
	}
	
	public String getRessource() {
		return this.resourceAddress;
	}
	
	

}
