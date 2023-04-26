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
		
		Resource result = m.createResource(this.config.baseURL+leikaID, this.config.getServiceResource(m));
		
		result.addLiteral(this.config.getHasLeikaID(m), leikaID);
		result.addLiteral(this.config.getName(m), name);
		result.addProperty(RDFS.label, name);
		
		Property hasProcess = this.config.getHasProcess(m);
		
		for(Prozess proz : this.xprocess.getProzessbibliothek().getProzess()) {
			Resource processes = getProcesses(m,result,proz);
			result.addProperty(hasProcess, processes);
		}
		
		bpmnParser.parse();
		bpmnParser.writeOntology();
		
		tmpFile.delete();
		bpmnParser = null;
		
		return result;
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
	
	private Resource getProcesses(Model m,Resource serviceResource, Prozess prozess) {
		String name = prozess.getName();
		
		Resource result = m.createResource(this.config.getBaseURL() + "process_" + this.leikaID+"_"+UUID.randomUUID(), this.config.getProcessResource(m));
		result.addProperty(this.config.getName(m), name);
		result.addProperty(RDFS.label, name);
		result.addProperty(this.config.getHasLeikaID(m), leikaID);
		
		Property executes = this.config.getExecutes(m);
		
		ArrayList<Resource> teilnehmer = bpmnParser.getTeilnehmer(this.config, m, prozess.getId());
		
		for(Resource tn: teilnehmer) {
			tn.addProperty(executes, result);
		}
		
		Property hasProcessStep = this.config.getHasSubProcessStep(m);
		for(Aktivitaetengruppe task : prozess.getProzessstrukturbeschreibung().getStrukturbeschreibungFIM().getAktivitaetengruppe()) {
			Resource taskResource = getSubProzess(m, task);
			result.addProperty(hasProcessStep, taskResource);
		}	
		
		return result;
	}
	
	private Resource getSubProzess(Model m, Aktivitaetengruppe task) {
		String nameTask = task.getName();
		String idTask = task.getSubProzessID();
		String codeActivityGroupe = task.getReferenzaktivitaetengruppeTyp().getCode();
		
		Resource rag = this.config.getAktivitätengruppe(m,codeActivityGroupe);
		rag.addProperty(this.config.hasID(m), codeActivityGroupe);
		
		Resource result = m.createResource(this.config.baseURL + this.leikaID + idTask, this.config.getSubProzess(m));
		result.addProperty(this.config.getHasType(m), rag);
		
		result.addProperty(this.config.getName(m), nameTask);
		result.addProperty(RDFS.label, nameTask);
		
		//result.addProperty(this.config.getID(m), idTask);
		
		for(Handlungsgrundlage handlungsgrundlage : task.getHandlungsgrundlage()) {
			Resource hglRessource = getHandlungsgrundlage(m, handlungsgrundlage);
			
			result.addProperty(this.config.getHasBasis(m), hglRessource);
		}
		
		result.addProperty(this.config.getHasResource(m), this.config.createDataFieldIndividual(m, this.getDatenfeldID(0)));
		
		return result;
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
