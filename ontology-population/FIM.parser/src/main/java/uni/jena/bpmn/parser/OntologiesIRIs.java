package uni.jena.bpmn.parser;

import java.util.Hashtable;

public class OntologiesIRIs {
	private static Hashtable<String, String> IRIList;
	
	public static void CreateIRIList()
	{
		IRIList = new Hashtable<String, String>();
		
		IRIList.put("default", "http://www.semanticweb.org/#");
		
		// Classes
		IRIList.put("process", "http://www.semanticweb.org/#Prozess");
		IRIList.put("sub_process", "http://www.semanticweb.org/#Prozessschritt");
		IRIList.put("service", "http://www.semanticweb.org/#Leistung");
		IRIList.put("agent", "http://www.semanticweb.org/#Akteur");
		IRIList.put("rag", "http://www.semanticweb.org/#RAG");
		IRIList.put("data_fields", "http://www.semanticweb.org/#Datenfelder");
		IRIList.put("Hauptakteur", "http://www.semanticweb.org/#Hauptakteur");
		IRIList.put("Mitwirkende", "http://www.semanticweb.org/#Mitwirkende");
		IRIList.put("Ergebnissempfänger", "http://www.semanticweb.org/#Ergebnissempfänger");
		IRIList.put("basis", "http://www.semanticweb.org/#Handlungsgrundlage");
		
		// Object properties
		IRIList.put("has_process", "http://www.semanticweb.org/#hat_prozess");
		IRIList.put("has_sub_process", "http://www.semanticweb.org/#hat_prozessschritt");
		IRIList.put("is_running", "http://www.semanticweb.org/#fuehrt_aus");
		IRIList.put("based_on", "http://www.semanticweb.org/#basiert_auf");
		IRIList.put("participates", "http://www.semanticweb.org/#beteiligt_sich_an");
		IRIList.put("has_resource", "http://BPMNbasedOntology#has_resource");
		IRIList.put("has_types", "http://www.semanticweb.org/#hat_typ");
		IRIList.put("tauscht_aus_mit", "http://www.semanticweb.org/#tauscht_aus_mit");
				
		// Data properties
		IRIList.put("data_field_id", "http://www.semanticweb.org/#hat_datenfeldID");
		IRIList.put("leika_id", "http://www.semanticweb.org/#hat_leikaID");
		IRIList.put("id", "http://BPMNbasedOntology#id");
		IRIList.put("name", "http://BPMNbasedOntology#name");
		IRIList.put("attachedTo", "http://BPMNbasedOntology#is_attachedToRef");
		IRIList.put("sourceRef","http://BPMNbasedOntology#has_sourceRef");
		IRIList.put("targetRef","http://BPMNbasedOntology#has_targetRef");

		
		
	}
	
	public static String GetIRIByName(String name) {
		return IRIList.get(name);
	}
}
