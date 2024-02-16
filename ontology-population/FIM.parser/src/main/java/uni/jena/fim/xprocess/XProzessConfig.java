package uni.jena.fim.xprocess;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class XProzessConfig {
	
	// base
	String baseURL;
	String dataFieldIndividual;
	
	// Classes
	String process;
	String sub_process;
	String service;
	String agent;
	String Hauptakteur;
	String Mitwirkende;
	String Ergebnissempfaenger;
	String rag;
	String data_fields;
	String basis;
	String provoRole;
	String provoAssociation;
	
	
	// Object Properties
	String has_process;
	String has_sub_process;
	String is_running;
	String based_on;
	String participates;
	String has_resource;
	String has_type;
	String tauscht_aus_mit;
	String trades_with;
	String has_process_participants;
	String provoQA;
	String provoHadRole;
	String provoAgent;
	
	// Data Properties
	String data_field_id;
	String leika_id;
	String id;
	String name;

	public Property getHasDataFieldID(Model m) {
		return m.getProperty(data_field_id);
	}
	
	public Resource createDataFieldIndividual(Model m, String ID) {
		return m.createResource(this.dataFieldIndividual + ID, this.getDatenfeldClass(m));
	}
	
	public Resource createAssociationIndividual(Model m, String ID) {
		return m.createResource(this.dataFieldIndividual + ID, this.getprovoAssociation(m));
	}
	
	public String getBaseURL() {
		return this.baseURL;
	}
	
	public Resource getProcessResource(Model m) {
		return m.getResource(this.process);
	}

	public Property getHasLeikaID(Model m) {
		return m.getProperty(this.leika_id);
	}

	public Property getName(Model m) {
		return m.getProperty(this.name);
	}

	public Property getHasProcess(Model m) {
		return m.getProperty(this.has_process);
	}

	public Resource getServiceResource(Model m) {
		return m.getResource(this.service);
	}

	public Property getID(Model m) {
		return m.getProperty(this.id);
	}
	
	public Resource getTeilnehmerClass(Model m, String id) {
		if(id != null) {
			if(id.equals("1")) {
				return m.getResource(this.Ergebnissempfaenger);
			}else if(id.equals("2")) {
				return m.getResource(this.Hauptakteur);
			}else if(id.equals("3")) {
				return m.getResource(this.Mitwirkende);
			}else {
				return m.getResource(this.agent);
			}
		}else {
			return m.getResource(this.agent);
		}
	}

	public Property getExecutes(Model m) {
		return m.getProperty(this.is_running);
	}

	public Resource getAktivitätengruppe(Model m, String codeActivityGroupe) {
		Resource activitygroupe = null;
		if((activitygroupe = m.getResource(this.baseURL+"RAG"+codeActivityGroupe))!= null) {
			return activitygroupe;
		}else {
			return m.createResource(this.baseURL+"RAG"+codeActivityGroupe, this.getRAGClass(m));
		}
	}

	private Resource getRAGClass(Model m) {
		return m.getResource(this.rag);
	}

	public Resource getSubProzess(Model m) {
		return m.getResource(this.sub_process);
	}

	public Property getHasType(Model m) {
		return m.getProperty(this.has_type);
	}

	public Property getHasSubProcess(Model m) {
		return m.getProperty(this.has_sub_process);
	}

	public Resource getBasis(Model m) {
		return m.getResource(this.basis);
	}

	public Property getHasBasis(Model m) {
		return m.getProperty(this.based_on);
	}

	public Property getHasSubProcessStep(Model m) {
		return m.getProperty(this.has_sub_process);
	}

	public Property participatesIn(Model m) {
		return m.getProperty(this.participates);
	}

	public Property hasID(Model m) {
		return m.getProperty(this.id);
	}
	public Property getHasResource(Model m) {
		return m.getProperty(this.has_resource);
	}

	public Resource getDatenfeldClass(Model m) {
		return m.getResource(this.data_fields);
	}
	
	public Property getprovoAgent(Model m) {
		return m.getProperty(this.provoAgent);
	}
	public Resource getprovoRole(Model m) {
		return m.getResource(this.provoRole);
	}
	public Resource getprovoAssociation(Model m) {
		return m.getResource(this.provoAssociation);
	}
	public Property getprovoQA(Model m) {
		return m.getProperty(this.provoQA);
	}
	public Property getprovoHadRole(Model m) {
		return m.getProperty(this.provoHadRole);
	}
}
