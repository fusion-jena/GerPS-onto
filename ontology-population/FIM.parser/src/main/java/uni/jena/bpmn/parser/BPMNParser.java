package uni.jena.bpmn.parser;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.moandjiezana.toml.Toml;

import uni.jena.bpmn.parser.Model.Activitiy;
import uni.jena.bpmn.parser.Model.Event;
import uni.jena.bpmn.parser.Model.Gateway;
import uni.jena.bpmn.parser.Model.SequenzFlow;
import uni.jena.fim.xprocess.XProzessConfig;

public class BPMNParser {
	ArrayList<Activitiy> activities;
	ArrayList<Gateway> gateways;
	ArrayList<SequenzFlow> sequenzFlows;
	ArrayList<Event> events;
	Model model;
	String LeikaID;
	File file;
	String path;
	private Document doc;
	public static BPMNConfig config = null;
	
	public BPMNParser(String ModelPath) throws IllegalStateException, FileNotFoundException, IOException {
		BPMNParser.config = this.getConfigFile(this.getClass().getClassLoader().getResource("config.toml"));

		this.activities = new ArrayList<Activitiy>();
		this.gateways = new ArrayList<Gateway>();
		this.sequenzFlows = new ArrayList<SequenzFlow>();
		this.events = new ArrayList<Event>();
		this.path = ModelPath;
		
		this.model = ModelFactory.createDefaultModel();
		this.model.read(this.path);	
	}
	
	public BPMNParser(Model model, File f, String leikaID) throws IllegalStateException, FileNotFoundException, IOException {
		BPMNParser.config = this.getConfigFile(this.getClass().getClassLoader().getResource("config.toml"));

		this.activities = new ArrayList<Activitiy>();
		this.gateways = new ArrayList<Gateway>();
		this.sequenzFlows = new ArrayList<SequenzFlow>();
		this.events = new ArrayList<Event>();
		
		this.model = model;
		this.doc = getDocument(f);
		
		this.LeikaID = leikaID;
		
		OntologiesIRIs.CreateIRIList();
	}
	
	
	public void parse() throws IOException{
		parseGateways(doc);
		parseActivities(doc);
		parseEvents(doc);
		parseSequenceFlows(doc);
	}
	
	
	



	public void writeOntology() throws FileNotFoundException {
		
		for (Activitiy act : this.activities){
			act.writeOnto(this.model);
		}
		
		for (Gateway gw: this.gateways){
			gw.writeOnto(this.model);
		}
		
		for (Event ev : this.events) {
			ev.writeOnto(this.model);
		}
		
		for (SequenzFlow seq: this.sequenzFlows){
			seq.writeOnto(this.model);
		}
		
		//this.model.write(new FileOutputStream(new File("../ontology/result.ttl")), "ttl");
		
		
		//model.write(System.out, "ttl");
	}
	
	
	private Document getDocument(InputStream in) {
		DocumentBuilder builder;
        
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(in);
            doc.getDocumentElement().normalize();
            return doc;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return null;
	}
	
    private Document getDocument(File docFile) throws IOException {
        DocumentBuilder builder;
        
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(docFile);
            doc.getDocumentElement().normalize();
            return doc;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    private void parseGateways(Document doc) {
    	NodeList list = doc.getElementsByTagName("process");
    	for (int i = 0; i < list.getLength(); i++ ) {
    		if(Node.ELEMENT_NODE == list.item(i).getNodeType()) {
    			Element process = (Element) list.item(i);
    			
    			NodeList gateways = process.getElementsByTagName("exclusiveGateway");   			
    			for (int j = 0; j < gateways.getLength(); j++) {
    				String id = this.LeikaID + ((Element)gateways.item(j)).getAttribute("id");
    				String name = ((Element)gateways.item(j)).getAttribute("name");
    				Gateway.GatewayType type = Gateway.GatewayType.exclusiv;
    				
    				this.gateways.add(new Gateway(type, name, id));   				
    			}	
    			
    
    			gateways = process.getElementsByTagName("parallelGateway");	
    			for (int j = 0; j < gateways.getLength(); j++) {
    				String id = this.LeikaID + ((Element)gateways.item(j)).getAttribute("id");
    				String name = ((Element)gateways.item(j)).getAttribute("name");
    				Gateway.GatewayType type = Gateway.GatewayType.parrallel;
    				
    				this.gateways.add(new Gateway(type, name, id));
    			}	
    			
    			gateways = process.getElementsByTagName("inclusiveGateway");	
    			for (int j = 0; j < gateways.getLength(); j++) {
    				String id = this.LeikaID +((Element)gateways.item(j)).getAttribute("id");
    				String name = ((Element)gateways.item(j)).getAttribute("name");
    				Gateway.GatewayType type = Gateway.GatewayType.inclusiv;
    				
    				this.gateways.add(new Gateway(type, name, id));
    			}	
    		}
    	}
    }
    
    private void parseActivities(Document doc) {
    	NodeList list = doc.getElementsByTagName("process");
    	for (int i = 0; i < list.getLength(); i++ ) {
    		if(Node.ELEMENT_NODE == list.item(i).getNodeType()) {
    			Element process = (Element) list.item(i);
    			
    			NodeList activities = process.getElementsByTagName("subProcess");   			
    			for (int j = 0; j < activities.getLength(); j++) {
    				Activitiy.ActivitiyType type = Activitiy.ActivitiyType.Subprocess;
    				String id = this.LeikaID + ((Element)activities.item(j)).getAttribute("id");
    				String name = ((Element)activities.item(j)).getAttribute("name");
    				
    				this.activities.add(new Activitiy(name, type, id));
    			}	
    			
    
    			activities = process.getElementsByTagName("callActivity");	
    			for (int j = 0; j < activities.getLength(); j++) {
    				Activitiy.ActivitiyType type = Activitiy.ActivitiyType.CallActivity;
    				String id = this.LeikaID + ((Element)activities.item(j)).getAttribute("id");
    				String name = ((Element)activities.item(j)).getAttribute("name");

    				this.activities.add(new Activitiy(name, type, id));
    			}	
    			
    			activities = process.getElementsByTagName("task");	
    			for (int j = 0; j < activities.getLength(); j++) {
    				Activitiy.ActivitiyType type = Activitiy.ActivitiyType.Task;
    				String id = this.LeikaID + ((Element)activities.item(j)).getAttribute("id");
    				String name = ((Element)activities.item(j)).getAttribute("name");

    				this.activities.add(new Activitiy(name, type, id));
    			}	
    		}
    	}
    }
    
    private void parseEvents(Document doc) {
    	NodeList list = doc.getElementsByTagName("process");
    	for (int i = 0; i < list.getLength(); i++ ) {
    		if(Node.ELEMENT_NODE == list.item(i).getNodeType()) {
    			Element process = (Element) list.item(i);
    			
    			NodeList events = process.getElementsByTagName("endEvent");   			
    			for (int j = 0; j < events.getLength(); j++) {
    				Event.EventType type = Event.EventType.EndEvent;
    				String id = this.LeikaID + ((Element)events.item(j)).getAttribute("id");
    				String name = ((Element)events.item(j)).getAttribute("name");
    				
    				this.events.add(new Event(name, id, type));
    			}	
    			
    
    			events = process.getElementsByTagName("boundaryEvent");	
    			for (int j = 0; j < events.getLength(); j++) {
    				Event.EventType type = Event.EventType.boundaryEvent;
    				String id = this.LeikaID + ((Element)events.item(j)).getAttribute("id");
    				String name = ((Element)events.item(j)).getAttribute("name");
    				String attachedTo = this.LeikaID + ((Element)events.item(j)).getAttribute("attachedToRef");

    				this.events.add(new Event(name, id, type, attachedTo));
    			}	
    			
    			events = process.getElementsByTagName("startEvent");	
    			for (int j = 0; j < events.getLength(); j++) {
    				Event.EventType type = Event.EventType.StartEvent;
    				String id = this.LeikaID + ((Element)events.item(j)).getAttribute("id");
    				String name = ((Element)events.item(j)).getAttribute("name");

    				this.events.add(new Event(name, id, type));
    			}	
    		}
    	}
    }
    
    private void parseSequenceFlows(Document doc) {
    	NodeList list = doc.getElementsByTagName("process");
    	for (int i = 0; i < list.getLength(); i++ ) {
    		if(Node.ELEMENT_NODE == list.item(i).getNodeType()) {
    			Element process = (Element) list.item(i);
    			
    			NodeList flows = process.getElementsByTagName("sequenceFlow");   			
    			for (int j = 0; j < flows.getLength(); j++) {
       				String id = this.LeikaID + ((Element)flows.item(j)).getAttribute("id");
       				String name = ((Element)flows.item(j)).getAttribute("name");
       				id = "sqf" + "_" + id;
       				
       				if(name.isEmpty()) {
       					name = "sqf"+ j;
       				}
    				
       				String source = this.LeikaID + ((Element)flows.item(j)).getAttribute("sourceRef");
       				String target = this.LeikaID + ((Element)flows.item(j)).getAttribute("targetRef");


    				this.sequenzFlows.add(new SequenzFlow(name, id, source,target));
    			}	
    		}
    	}
    	
    	list = doc.getElementsByTagName("collaboration");
    	for (int i = 0; i < list.getLength(); i++ ) {
    		if(Node.ELEMENT_NODE == list.item(i).getNodeType()) {
    			Element col = ((Element)list.item(i));
    			
    			NodeList messagesFlowsList = col.getElementsByTagName("messageFlow");
    			
    			for (int j = 0; j < messagesFlowsList.getLength(); j++) {
       				String id = "msgf" +"_" + this.LeikaID + ((Element)messagesFlowsList.item(j)).getAttribute("id");
       				String name = ((Element)messagesFlowsList.item(j)).getAttribute("name");

       				if(name.isEmpty()) {
       					name = "msgf"+ j;       				
       				}
    				
       				String source = this.LeikaID + ((Element)messagesFlowsList.item(j)).getAttribute("sourceRef");
       				String target = this.LeikaID + ((Element)messagesFlowsList.item(j)).getAttribute("targetRef");


    				//this.sequenzFlows.add(new SequenzFlow(name, id, source,target));
    			}
    		}
    	}
    }
    
    
	private BPMNConfig getConfigFile(URL resource) throws IllegalStateException, FileNotFoundException, IOException {
		String tomlContent = readFile(new File(resource.getFile()));
		Toml toml = new Toml().read(tomlContent).getTable("BPMN");
		
		return toml.to(BPMNConfig.class);
	}


	private static String readFile(File f) throws FileNotFoundException, IOException {
    	StringBuilder content = new StringBuilder();

    	try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(f)))) {
			for (String line; (line = reader.readLine()) != null; ) {
				content.append(line + "\n");
			}
		}
    	
    	return content.toString();
    }

	public ArrayList<Resource> getTeilnehmer(XProzessConfig config, Model m, String leika) {		
		ArrayList<Resource> tnList = new ArrayList<>();
		NodeList participants = doc.getElementsByTagName("participant");
		HashMap<String, Integer> countOfActions = new HashMap<>();
		HashMap<String, Element> processElements = new HashMap<>();

		
		NodeList processes = doc.getElementsByTagName("process");
		for(int j = 0 ; j < participants.getLength(); j++) {
			Element proc = (Element)processes.item(j);
			int length = proc.getElementsByTagName("subProcess").getLength() + proc.getElementsByTagName("callActivity").getLength() + proc.getElementsByTagName("task").getLength();
			String id = proc.getAttribute("id");
			
			countOfActions.put(id, length);
			processElements.put(id, proc);
		}
		
		for(int i = 0 ; i < participants.getLength(); i++) {
			Element participant = (Element)participants.item(i);
			
			String id = participant.getAttribute("id");
			String name = participant.getAttribute("name");
			String processRef = participant.getAttribute("processRef");
			
			Resource teilnehmer = m.createResource(config.getBaseURL()+id);
			teilnehmer.addProperty(RDFS.label, name);
			teilnehmer.addProperty(config.getName(m), name);
			//teilnehmer.addProperty(config.getExecutes(m), m.getResource(config.getBaseURL()+ leika + "_" + processRef));
			
			int length = countOfActions.get(processRef);
			
			if(length == 0) {
				teilnehmer.addProperty(RDF.type, config.getTeilnehmerClass(m, "1"));
			}else {
				boolean notBiggest = false;
				
				for(int value : countOfActions.values()) {
					if(length < value) notBiggest = true;
				}
				
				if(notBiggest) {
					teilnehmer.addProperty(RDF.type, config.getTeilnehmerClass(m, "3"));
				}else {
					teilnehmer.addProperty(RDF.type, config.getTeilnehmerClass(m, "2"));
				}
				
			}
			
			Element process = processElements.get(processRef);
			addElementsTeilnehmer(config, m, teilnehmer, process, "subProcess");
			addElementsTeilnehmer(config, m, teilnehmer, process, "task");
			addElementsTeilnehmer(config, m, teilnehmer, process, "callActivity");

			
			tnList.add(teilnehmer);
		}
		
		return tnList;
	}

	private void addElementsTeilnehmer(XProzessConfig config, Model m, Resource teilnehmer, Element process, String elementType) {
		NodeList childNodes = process.getElementsByTagName(elementType);
		for(int j = 0; j < childNodes.getLength(); j++) {
			Element child = (Element)childNodes.item(j);
			Resource action = null;
			if((action = m.getResource(config.getBaseURL() + this.LeikaID + child.getAttribute("id"))) == null) {
				action = m.createResource(config.getBaseURL() + this.LeikaID + child.getAttribute("id"));
			}
			
			teilnehmer.addProperty(config.participatesIn(m), action);
				
		}
	}
	
	

	public ArrayList<Resource> getProcesses(Model m, XProzessConfig config, Resource serviceResource, String leikaId) {
		ArrayList<Resource> result = new ArrayList<>();
		
		NodeList processes = doc.getElementsByTagName("process");
		for(int j = 0 ; j < processes.getLength(); j++) {
			Element process = (Element)processes.item(j);
			String name = process.getAttribute("name");
			String id = process.getAttribute("id");
			
			String url = config.getBaseURL() + leikaId + "_"+ id;
			
			Resource prozess = m.createResource(url, config.getProcessResource(m));
			prozess.addLiteral(config.getName(m), name);
			prozess.addLiteral(RDFS.label, name);
			prozess.addProperty(config.getID(m), id);
			
			result.add(prozess);
			
		}
		return result;
	}


    
}
