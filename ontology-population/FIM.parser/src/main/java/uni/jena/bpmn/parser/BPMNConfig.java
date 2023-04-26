package uni.jena.bpmn.parser;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;

public class BPMNConfig {
	public String Callactivity;
	public String SubProcess;
	public String task;
	private String name;
	private String id;
	private String attachedTo;
	private String baseURL;
	private String ExclusiveGateway ;
	private String InclusiveGateway ;
	private String ParallelGateway ;
	private String SequenceFlow;
	private String sourceRef ;
	private String targetRef ;
	
	public String getBaseURL() {
		return baseURL;
	}
	
	public String getAttachedTo() {
		return this.attachedTo;
	}
	
	public Property getName(Model model) {
		return model.getProperty(this.name);
	}


	public Property getID(Model model) {
		return model.getProperty(this.id);
	}

	public String getExclusiveGateway() {
		return ExclusiveGateway;
	}

	public String getInclusiveGateway() {
		return InclusiveGateway;
	}

	public String getParallelGateway() {
		return ParallelGateway;
	}

	public String getSequenzFLow() {
		return this.SequenceFlow;
	}

	public String getSourceRef() {
		return sourceRef;
	}

	public String getTargetRef() {
		return targetRef;
	}

}
