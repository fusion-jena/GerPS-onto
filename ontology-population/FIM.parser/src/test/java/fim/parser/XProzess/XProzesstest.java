package fim.parser.XProzess;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.junit.jupiter.api.Test;

import uni.jena.ParserConfig;
import uni.jena.fim.xprocess.XProzess;

public class XProzesstest {
	
	@Test public void  createRDF() throws IOException{
		URL resource = ParserConfig.class.getClassLoader().getResource("config.toml");
		ParserConfig config = ParserConfig.getConfig(new File(resource.getPath()));
		
		String outputPath = config.getOutputPath();
		
		XProzess xp = createXProzess(config);
		Model model = createModel(config);
		
		Resource rdfxProcess = xp.getRDFXProcess(model);
		
		Model write;
		if(config.getRDFLanguage().equals("")) {
			write = model.write(new FileOutputStream(new File(outputPath)));
		}else {
			File f = new File(outputPath);
			write = model.write(new FileOutputStream(f), config.getRDFLanguage());	
		}
	}
	
	private XProzess createXProzess(ParserConfig config) throws IOException {
		String outputPath = config.getOutputPath();

		String xprozesspath = config.getXProzessPath();

		File xprocessFile = new File(xprozesspath);

		XProzess xp = new XProzess(xprocessFile);
		
		return xp;
	}
	
	private Model createModel(ParserConfig config) {
		System.out.println(config.getOntologyPath());
		File f = new File(config.getOntologyPath());
		Model model = ModelFactory.createDefaultModel();
		model.read(f.getAbsolutePath());	
		
		return model;
	}
}
