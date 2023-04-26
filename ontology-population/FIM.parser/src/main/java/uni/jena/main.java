package uni.jena;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.json.simple.parser.ParseException;

import uni.jena.fim.xprocess.XProzess;
import uni.jena.fim.xdatenfelder.XDatenfeld;

public class main {
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		createXProzess();
		 
		System.out.println("done");
	} 
	
	private static void createXProzess() throws FileNotFoundException, IOException {
		URL resource = ParserConfig.class.getClassLoader().getResource("config.toml");
		ParserConfig config = ParserConfig.getConfig(new File(resource.getPath()));
		
		String outputPath = config.getOutputPath();
		String xdatenfeldPath = config.getXDatenfeldPath();


		String xprozesspath = config.getXProzessPath();

		String modelPath = config.getOntologyPath();

		File xprocessFile = new File(xprozesspath);
		
		Model model = ModelFactory.createDefaultModel();
		model.read(modelPath);	
		
		XProzess xp = new XProzess(xprocessFile);
		
		Resource rdfxProcess = xp.getRDFXProcess(model);
				
		XDatenfeld xd = new XDatenfeld(new File(xdatenfeldPath));
		for(String path : config.getCodelisten()) {
			xd.addCodeliste(new File(path));
		}
		
		Resource rdfModel = xd.getRDFModel(model);
		
		Model write;
		if(config.getRDFLanguage().equals("")) {
			write = model.write(new FileOutputStream(new File(outputPath)));
		}else {
			write = model.write(new FileOutputStream(new File(outputPath)), config.getRDFLanguage());	
		}
	}
}
