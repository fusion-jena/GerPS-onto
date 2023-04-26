package uni.jena.fim.xdatenfelder;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Hashtable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import com.moandjiezana.toml.Toml;

import uni.jena.fim.xdatenfelder.v2.core.XdatenfelderStammdatenschema0102;
import uni.jena.fim.OwnCodelist.Codeliste;
import uni.jena.fim.xdatenfelder.Actions.Action;
import uni.jena.fim.xdatenfelder.Actions.CollectRuleAction;
import uni.jena.fim.xdatenfelder.Actions.RDFResourceStrukturAction;
import uni.jena.fim.xdatenfelder.v2.core.Datenfeld;
import uni.jena.fim.xdatenfelder.v2.core.Datenfeldgruppe;
import uni.jena.fim.xdatenfelder.v2.core.Regel;
import uni.jena.fim.xdatenfelder.v2.core.Struktur;

public class XDatenfeld {
	// ------------------------------- Fields ---------------------------------//
	private HashMap<String, Codeliste> clHashMap = null;
	private XdatenfelderStammdatenschema0102 sds = null; 
	private HashMap<String, Integer> wordCount = null;
	private XDatenfelderConfig config = null;
	
	
	// ---------------------------- Konstruktoren -----------------------------//
	public XDatenfeld() throws FileNotFoundException, IOException {
		this.clHashMap = new HashMap<>();
		this.wordCount = new HashMap<>();
		this.config = this.getConfig();
	}
	
	public XDatenfeld(String fileContent) throws FileNotFoundException, IOException {
		this();
		createXDatenfeld(fileContent);
	}
	
	public XDatenfeld(File f) throws FileNotFoundException, IOException {
		this(readFile(f));
	}
	
	
	// --------------------------- public Methoden ----------------------------//

	public void setXDatenfeld(File f) throws FileNotFoundException, IOException {
		createXDatenfeld(readFile(f));
	}
	
	public void addCodeliste(File f) throws IOException {
		Codeliste cl = new Codeliste(f);
		this.clHashMap.put(cl.getCanonicalUri().trim(), cl);
	}
	
	public Hashtable<String, String> getAllRulesFromDocument(){
		Hashtable<String, String> result = new Hashtable<>();
		
		for (Regel r : this.sds.getStammdatenschema().getRegel()) {
			String id = r.getIdentifikation().getId();
			String definition = r.getDefinition();
			
			if(!result.contains(id)) {
				result.put(id, definition);
			}
		}
		
		CollectRuleAction cra = new CollectRuleAction(result);
		
		
		for(Struktur s : this.sds.getStammdatenschema().getStruktur()) {
			traveserStruktur(s, cra);
		}
		
		return cra.getRules();
	}
	
	public Resource getRDFModel(Model m){
		String id = this.sds.getStammdatenschema().getIdentifikation().getId();
		String name = this.sds.getStammdatenschema().getName();
		
		Resource stammdatenSchemata = this.config.getIndividuals(m, id, this.config.getStammschemata(m));
		stammdatenSchemata.addProperty(RDFS.label, name);
		
		stammdatenSchemata.addLiteral(this.config.getName(m), name);
		stammdatenSchemata.addLiteral(this.config.getID(m), id);
		
		Property hatRegel = this.config.getHatRegel(m);
		for(Regel r : this.sds.getStammdatenschema().getRegel()) {
			stammdatenSchemata.addProperty(hatRegel, getRDFRegel(r, m));	
		}
				
		RDFResourceStrukturAction action = new RDFResourceStrukturAction(m,this.config);
		
		for(Struktur strukt : this.sds.getStammdatenschema().getStruktur()) {
			traveserStruktur(strukt, action);
			String struktId = getStruktID(strukt);
			if(struktId != null) {
				Resource res = this.config.getIndividuals(m, struktId);
				stammdatenSchemata.addProperty(this.config.getHat_enthaelt(m), res);
			}
		}
		
		return stammdatenSchemata;
	}
		
	public JSONObject getFormFlowJSON() throws ParseException {
		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject subObject = new JSONObject();
		
		subObject.put("title", this.sds.getStammdatenschema().getName());
		subObject.put("display", "form");
		subObject.put("type", "form");
		subObject.put("name", this.sds.getStammdatenschema().getIdentifikation().getId());
		subObject.put("path", this.sds.getStammdatenschema().getIdentifikation().getId());
		
		JSONArray tagsArray = new JSONArray();
		tagsArray.add("common");
		
		JSONArray componentsArray = new JSONArray();
		
		
		for (Struktur strukt : this.sds.getStammdatenschema().getStruktur()) {
			JSONObject childObject = traverseForJSON(strukt);
			if (childObject != null) componentsArray.add(childObject);
		}

		subObject.put("tags", tagsArray);
		subObject.put("components", componentsArray);
		
		array.add(subObject);
		object.put("forms", array);
		
		return object;
	}
	
	
	// --------------------------- private Methoden ---------------------------//

	private String getWordCount(String key) {
		if(this.wordCount.containsKey(key)) {
			String result = key + this.wordCount.get(key);
			this.wordCount.put(key, this.wordCount.get(key)+1);
			return result;
		}else {
			String result = key+"1";
			this.wordCount.put(key, 2);
			return result;
		}
	}
	
	private JSONObject traverseForJSON(Struktur struct) throws ParseException {
		JSONObject obj = null;
		
		if(struct.getEnthaelt()!= null) {
			if(struct.getEnthaelt().getDatenfeldgruppe() != null) {
				obj =  createDatenfeldGruppenJSONObj(struct.getEnthaelt().getDatenfeldgruppe());
			}else if(struct.getEnthaelt().getDatenfeld() != null) {
				obj = createDatenfeldJSONObj(struct.getEnthaelt().getDatenfeld());
			}
		}
		
		return obj;
	}
	
	private JSONObject createDatenfeldJSONObj(Datenfeld datenfeld) throws ParseException {
		JSONObject object = new JSONObject();
		
		object.put("label", datenfeld.getName());
		object.put("key", getWordCount(datenfeld.getName()));
		object.put("input", true);
		
		
		JSONObject propertyObject = new JSONObject();
		propertyObject.put("xdf:id", datenfeld.getIdentifikation().getId());
		object.put("properties", propertyObject);
		
		if(datenfeld.getHilfetextEingabe() != null) {
			if(!datenfeld.getHilfetextEingabe().isEmpty()) {
				object.put("tooltip", datenfeld.getHilfetextEingabe());
			}
		}


		
		// dropdown boxes
		if (datenfeld.getFeldart().getCode().equals("select")) {
						
			JSONArray values = new JSONArray();
			if(datenfeld.getCodelisteReferenz() != null) {
				HashMap<String, String> hashMap = this.clHashMap.get(datenfeld.getCodelisteReferenz().getGenericodeIdentification().getCanonicalIdentification().trim()).getHashMap("code", "name");
						
				for(String Key : hashMap.keySet()) { 
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("label", hashMap.get(Key));
					jsonObject.put("value", Key);
					values.add(jsonObject);
				}
						
				JSONObject selectValues = new JSONObject();
				selectValues.put("values", values);
			
			
				object.put("data", selectValues);
			}
			object.put("tableView", false); 
			object.put("type", "select");  
			object.put("input", true);  



		}		
		
		// if statements um verschiedene datenfelder darzustellen (textfelder, checkboxen, datum, etc)
		if (datenfeld.getFeldart().getCode().equals("input") && datenfeld.getDatentyp().getCode().equals("text"))
		{
			object.put("tableView", true);
			
			if(datenfeld.getPraezisierung() != null) {
				datenfeld.setPraezisierung(datenfeld.getPraezisierung().replace("Pattern=", ""));
				if(!datenfeld.getPraezisierung().isEmpty()) {
					JSONParser parser = new JSONParser();
					JSONObject präzisierung = (JSONObject) parser.parse(datenfeld.getPraezisierung());
					
					JSONObject validateObject = new JSONObject();
					if (präzisierung.get("minLength") != null) validateObject.put("minLength", präzisierung.get("minLength"));
					if (präzisierung.get("maxInput") != null) validateObject.put("maxLength", präzisierung.get("maxInput") );
					if (präzisierung.get("pattern")  != null) validateObject.put("pattern", präzisierung.get("pattern"));
						
					if(validateObject.size() > 0)
						object.put("validate", validateObject);
				}
			}
			
			object.put("type", "textfield");
		}

		// checkbox
		if (datenfeld.getFeldart().getCode().equals("input") && datenfeld.getDatentyp().getCode().equals("bool"))
		{
			object.put("tableView", false);
			object.put("type", "checkbox");
			object.put("defaultValue", false);
		}

		// number input
		if (datenfeld.getFeldart().getCode().equals("input") && (datenfeld.getDatentyp().getCode().equals("num_int")||datenfeld.getDatentyp().getCode().equals("num") || datenfeld.getDatentyp().getCode().equals("num_currency")))
		{
			object.put("mask", false);
			object.put("tableView", false);
			object.put("delimiter", false);
			object.put("requireDecimal", false);
			object.put("inputFormat", "plain");
			object.put("truncateMultipleSpaces", false);
			
			if(!datenfeld.getPraezisierung().isEmpty()) {
				datenfeld.setPraezisierung(datenfeld.getPraezisierung().replace("Pattern=", ""));				
				JSONParser parser = new JSONParser();
				JSONObject präzisierung = (JSONObject) parser.parse(datenfeld.getPraezisierung());
				
	
				JSONObject validateObject = new JSONObject();
				if (präzisierung.get("minLength") != null) validateObject.put("minLength", präzisierung.get("minLength"));
				if (präzisierung.get("maxInput") != null) validateObject.put("maxLength", präzisierung.get("maxInput") );
				if (präzisierung.get("pattern")  != null) validateObject.put("pattern", präzisierung.get("pattern"));
					
				if(validateObject.size() > 0)
					object.put("validate", validateObject);
			}
			
			object.put("type", "number");
		}

		// date input
		if (datenfeld.getFeldart().getCode().equals("input") && datenfeld.getDatentyp().getCode().equals("date"))
		{
			object.put("hideInputLabels", false);
			object.put("inputsLabelPosition", "top");
			object.put("useLocaleSettings", false);
			object.put("tableView", false);
			
			JSONObject fieldsObject = new JSONObject();
			
			JSONObject dayObject = new JSONObject();
			dayObject.put("hide", false);
			
			JSONObject monthObject = new JSONObject();
			monthObject.put("hide", false);
			
			JSONObject yearObject = new JSONObject();
			yearObject.put("hide", false);
			
			fieldsObject.put("day", dayObject);
			fieldsObject.put("month", monthObject);
			fieldsObject.put("year", yearObject);
				
			object.put("fields", fieldsObject);
			
			
			object.put("type", "day");
			object.put("defaultValue", "00/00/0000");
		}
		
		if(datenfeld.getFeldart().getCode().equals("input") && datenfeld.getDatentyp().getCode().equals("file")) {
			object.put("tableView", false);
			object.put("webcam", false);
			object.put("type", "file");
			
			JSONArray array = new JSONArray();
			JSONObject element = new JSONObject();
			element.put("label", "");
			element.put("value", "");
			array.add(element);
			
			object.put("fileTypes", array);
		}
		
		// besondere sachen nicht beachtet
		if(datenfeld.getFeldart().getCode().equals("input") && datenfeld.getDatentyp().getCode().equals("obj")) {
			return null;
		}
		
		// Label nicht beachhtet
		if(datenfeld.getFeldart().getCode().equals("label")){
			return null;
		}
		
		// fals kein Typ gesetzt wurde soll das Feld nicht übernommen werden
		if(!object.containsKey("type")) {
			return null;
		}
		
		return object;

	}

	private JSONObject createDatenfeldGruppenJSONObj(Datenfeldgruppe datenfeldgruppe) throws ParseException {
		JSONObject object = new JSONObject();
		
		object.put("title", datenfeldgruppe.getName());
		object.put("collapsible", false);
		object.put("key", getWordCount(datenfeldgruppe.getName()));
		object.put("type", "panel");
		object.put("label", "Panel");
		object.put("input", false);
		object.put("tableView", false);

		
		JSONObject propertyObject = new JSONObject();
		propertyObject.put("xdf:id", datenfeldgruppe.getIdentifikation().getId());
		object.put("properties", propertyObject);
		
		JSONArray componentsArray = new JSONArray();
		
		for(Struktur strukt : datenfeldgruppe.getStruktur()) {
			JSONObject childObject = traverseForJSON(strukt);
			if (childObject != null) componentsArray.add(childObject);
		}
		
		// hier fehlen noch die Regeln Ruleparser in Arbeit
		
		object.put("components", componentsArray);
		
		return object;
	}

	private String getStruktID(Struktur strukt) {
		String result = null;
		
		if(strukt.getEnthaelt() != null) {
			if(strukt.getEnthaelt().getDatenfeld() != null){
				result = strukt.getEnthaelt().getDatenfeld().getIdentifikation().getId();
			}else if(strukt.getEnthaelt().getDatenfeldgruppe() != null) {
				result = strukt.getEnthaelt().getDatenfeldgruppe().getIdentifikation().getId();
			}
		}
		
		return result;
	}

	private void traveserStruktur(Struktur struct, Action action) {
		action.act(struct);
		
		if(struct.getEnthaelt()!= null) {
			Datenfeldgruppe dfg = null;
			if((dfg = struct.getEnthaelt().getDatenfeldgruppe()) != null) {
				for(Struktur str : dfg.getStruktur()) {
					traveserStruktur(str, action);
				}
			}
		}
	}

	private Resource getRDFRegel(Regel regel, Model m) {
		String id = regel.getIdentifikation().getId();
		String name = regel.getName();
		
		Resource result = this.config.getIndividuals(m,id, this.config.getRegel(m));
		result.addProperty(RDFS.label, name);
		
		result.addLiteral(this.config.getName(m), name);
		result.addLiteral(this.config.getID(m), id);
		
		return result;
	}
	
	private XDatenfelderConfig getConfig() throws FileNotFoundException, IOException {
		File f = new File(this.getClass().getClassLoader().getResource("config.toml").getPath());
		
		Toml toml = new Toml().read(f).getTable("XDatenfeld");
		
		return toml.to(XDatenfelderConfig.class);
	}
	
	private void createXDatenfeld(String fileContent) {
		JAXBContext jaxbContext;
		XdatenfelderStammdatenschema0102 df = null;
		try
		{
		  jaxbContext = JAXBContext.newInstance(XdatenfelderStammdatenschema0102.class);        
		 
		  Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		  
		  		  
		  XdatenfelderStammdatenschema0102 ju = (XdatenfelderStammdatenschema0102) jaxbUnmarshaller.unmarshal(
				  																new ByteArrayInputStream(fileContent.getBytes()));
		  
		  this.sds = ju;
		}
		catch (JAXBException e)
		{
		  e.printStackTrace();
		}
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

}
