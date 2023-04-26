package uni.jena;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.moandjiezana.toml.Toml;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ParserConfig {

	private String XProzessPath;
	private String XDatenfeldPath;
	private String OntologyPath;
	private String outputPath;
	private String RDFLanguage;
	private ArrayList<String> codelisten;
	 
	public static ParserConfig getConfig(File f) throws FileNotFoundException {
		String content = readFile(f);
		ParserConfig parserConfig = new Toml().read(content).getTable("ParserConfig").to(ParserConfig.class);
		return parserConfig;
	}
	
	private static String readFile(File f) throws FileNotFoundException {
		String result = "";
		
		BufferedReader reader = new BufferedReader(new FileReader(f));
		List<String> list = reader.lines().toList();
		
		result = String.join("\r\n", list);
		
		return result;
	}
	
}
