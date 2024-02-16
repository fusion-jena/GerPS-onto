package uni.jena.fim.xprocess;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDFS;

import com.moandjiezana.toml.Toml;

import uni.jena.bpmn.parser.BPMNParser;
import uni.jena.fim.xprocess.v2.core.Aktivitaetengruppe;
import uni.jena.fim.xprocess.v2.core.AlleInhalteExport0303;
import uni.jena.fim.xprocess.v2.core.Datei;
import uni.jena.fim.xprocess.v2.core.Daten;
import uni.jena.fim.xprocess.v2.core.Handlungsgrundlage;
import uni.jena.fim.xprocess.v2.core.Prozess;
import uni.jena.fim.xprocess.v2.core.Prozessklasse;
import uni.jena.fim.xprocess.v2.core.Prozessteilnehmer;





public class XProzess {
	private AlleInhalteExport0303 xprocess = null;
	private XProzessConfig config = null;
	private String leikaID;
	private BPMNParser bpmnParser = null;
	
	
	// ---------------------------- Konstruktoren -----------------------------//
	
	public XProzess(String Content) throws FileNotFoundException, IOException {
		createInstance(Content);
	}
	
	public XProzess(URL path) throws IOException, FileNotFoundException {
		this(new File(path.getPath()));
	}
	
	public XProzess(File f) throws FileNotFoundException, IOException {
		createInstance(f);
	}
	
	// --------------------------- public Methoden ----------------------------//

	public String getLeikaID() {
		return this.xprocess.getProzessbibliothek().getProzess().get(0).getId();
	}
	
	public String getDatenfeldID(int i) {
		return this.xprocess.getProzessbibliothek().getProzess().get(i).getProzesssteckbrief().getAusloeser().get(0).getFormularID().replace("D", "S");
	}

	public Resource getRDFResource(URL modelPath) throws IOException {		
		Model m = ModelFactory.createDefaultModel();
		m.read(modelPath.getPath());
		
		return getRDFXProcess(m);
	}
	
	public Resource getRDFXProcess(Model m) throws IOException {
		leikaID = getLeikaID();
		File tmpFile = writeFile("test.xml", getProzessmodelldateiContent().toString());
		bpmnParser = new BPMNParser(m,tmpFile,leikaID);

		String name = this.xprocess.getProzessbibliothek().getProzess().get(0).getName();
	
		Resource service = m.createResource(this.config.baseURL+leikaID, this.config.getServiceResource(m));
	
		service.addLiteral(this.config.getHasLeikaID(m), leikaID);
		service.addLiteral(this.config.getName(m), name);
		service.addProperty(RDFS.label, name);
		
		Property hasProcess = this.config.getHasProcess(m);
		int i = 0;
		for(Prozess proz : this.xprocess.getProzessbibliothek().getProzess()) {
			Resource processes = getProcesses(m,proz,i);
			service.addProperty(hasProcess, processes);
			i++;
		}
		
		bpmnParser.parse();
		bpmnParser.writeOntology();
		
		tmpFile.delete();
		bpmnParser = null;
		
		return service;
	}

	public StringBuilder getProzessmodelldateiContent() throws IOException {
		if(this.xprocess.getProzessbibliothek().getProzess().size() > 0) {
			Prozess prozess = this.xprocess.getProzessbibliothek().getProzess().get(0);
			if(prozess.getProzessmodell() != null) {
				if(prozess.getProzessmodell().getProzessmodelldatei().size() > 0) {
					Datei datei = prozess.getProzessmodell().getProzessmodelldatei().get(0);
					byte[] inhalt = datei.getInhalt();
			
					return getContentFrom(inhalt);
				}
			}
		}
		return null;
	}
	
	
	// --------------------------- private Methoden ---------------------------//
	
	private static File writeFile(String filePath, String content) throws IOException {
		File  f = new File(filePath);
		FileOutputStream fos = new FileOutputStream(f);
		
		fos.write(content.getBytes());
		fos.close();
		
		return f;
	}
	
	private Resource getProcesses(Model m, Prozess prozess, int i) {
		String name = prozess.getName();		
	
		String processUUID = UUID.nameUUIDFromBytes((this.leikaID+name+i).getBytes()).toString();
		
		Resource process = m.createResource(this.config.getBaseURL() + "process_" + this.leikaID + "_" + processUUID, this.config.getProcessResource(m));
		process.addProperty(this.config.getName(m), name);
		process.addProperty(RDFS.label, name);
		process.addProperty(this.config.getID(m), leikaID);
		
		
		Property provoQA = this.config.getprovoQA(m);
		ArrayList<Resource> associations = bpmnParser.getTeilnehmer(this.config, m, prozess.getId());
		
		for(Resource association: associations) {
			process.addProperty(provoQA, association);
		}
		
		Property hasProcessStep = this.config.getHasSubProcessStep(m);
		for(Aktivitaetengruppe task : prozess.getProzessstrukturbeschreibung().getStrukturbeschreibungFIM().getAktivitaetengruppe()) {
			Resource taskResource = getSubProzess(m, task);
			process.addProperty(hasProcessStep, taskResource);
		}	
		
		return process;
	}
	
	private Resource getSubProzess(Model m, Aktivitaetengruppe task) {
		String nameTask = task.getName();
		String idTask = task.getSubProzessID();
		String codeActivityGroupe = task.getReferenzaktivitaetengruppeTyp().getCode();
		
		Resource rag = this.config.getAktivitätengruppe(m,codeActivityGroupe);
		rag.addProperty(this.config.hasID(m), codeActivityGroupe);
		
		Resource subProcess = m.createResource(this.config.baseURL + this.leikaID + idTask, this.config.getSubProzess(m));
		subProcess.addProperty(this.config.getHasType(m), rag);
		
		subProcess.addProperty(this.config.getName(m), nameTask);
		subProcess.addProperty(RDFS.label, nameTask);
		
		//result.addProperty(this.config.getID(m), idTask);
		if(task.getEingehendeDaten() != null) {
			for(Daten formularDaten : task.getEingehendeDaten()) {
				Resource usedFormular = getUsedFormular(m,formularDaten);
				
				subProcess.addProperty(this.config.getHasResource(m), usedFormular);
			}
		}
		
		for(Handlungsgrundlage handlungsgrundlage : task.getHandlungsgrundlage()) {
			Resource hglRessource = getHandlungsgrundlage(m, handlungsgrundlage);
			
			subProcess.addProperty(this.config.getHasBasis(m), hglRessource);
		}
		
		subProcess.addProperty(this.config.getHasResource(m), this.config.createDataFieldIndividual(m, this.getDatenfeldID(0)));
		
		return subProcess;
	}

	private Resource getUsedFormular(Model m, Daten daten) {
		String formularsteckbriefID = daten.getFormularverweis().getFormularsteckbriefID();
		Resource usedFormular = m.createResource(this.config.getBaseURL()+formularsteckbriefID, 
											  	  this.config.getDatenfeldClass(m));
		
		usedFormular.addLiteral(this.config.getHasDataFieldID(m), formularsteckbriefID);
		
		return usedFormular;
	}
	
	private Resource getHandlungsgrundlage(Model m, Handlungsgrundlage handlungsgrundlage) {
		String name = handlungsgrundlage.getName();
		String uri = handlungsgrundlage.getUri();
		
		Resource result = m.createResource(uri, this.config.getBasis(m));

		result.addProperty(this.config.getName(m), name);
		result.addProperty(RDFS.label, name);

		return result;
	}
	
	private StringBuilder getContentFrom(byte[] bytes) throws IOException {
		
        ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(bytes));
        
        ZipEntry nextEntry = zis.getNextEntry();

        BufferedReader reader = new BufferedReader(new InputStreamReader(zis));

        Stream<String> lines = reader.lines();
        
        StringBuilder sb = new StringBuilder(String.join("\r\n", lines.toList()));
		
		return sb;
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
	
	public void createInstance(File f) throws FileNotFoundException, IOException {
		String fileContent = readFile(f);
		
		createInstance(fileContent);
	}
	
	private void createInstance(String fileContent) throws FileNotFoundException, IOException {
		JAXBContext jaxbContext;
						
		try
		{
		  jaxbContext = JAXBContext.newInstance(AlleInhalteExport0303.class);        
		 
		  Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		  
		  		  
		  AlleInhalteExport0303 ju = (AlleInhalteExport0303) jaxbUnmarshaller.unmarshal(
				  							new ByteArrayInputStream(fileContent.getBytes()));
		  
		  this.xprocess = ju;
		  URL resource = XProzess.class.getClassLoader().getResource("config.toml");
		  this.config = getConfigFile(resource);
		}
		catch (JAXBException e)
		{
		  e.printStackTrace();
		}
	}

	private XProzessConfig getConfigFile(URL resource) throws IllegalStateException, FileNotFoundException, IOException {
		String tomlContent = readFile(new File(resource.getFile()));
		Toml toml = new Toml().read(tomlContent).getTable("XProzess");
		
		return toml.to(XProzessConfig.class);
	}
	
}
