package uni.jena;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


import org.apache.commons.csv.CSVRecord;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.json.simple.parser.ParseException;

import uni.jena.fim.xprocess.XProzess;
import uni.jena.mapping.manuel.Mapper;

import uni.jena.bpmn.parser.BPMNParser;

public class main {

	public void main(String[] args) throws IOException{
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
		System.out.println("done.");
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
