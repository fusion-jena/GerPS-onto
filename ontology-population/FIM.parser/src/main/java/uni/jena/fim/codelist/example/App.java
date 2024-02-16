package uni.jena.fim.codelist.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import uni.jena.fim.codelist.Codelist;
import uni.jena.fim.codelist.XRepositoryDownloader;
import uni.jena.fim.codelist.*;

public class App {

	public static void main(String[] args) throws IOException {
		
		Codelist cl1 = XRepositoryDownloader.getCodelist("urn:xoev-de:xamtshilfe:codeliste:einnahmeart_1.1.1");
		Codelist cl2 = XRepositoryDownloader.getCodelist("urn:xoev-de:xauslaender:codeliste:ozg.daueraufenthaltsgrund_1");

		System.out.println(XRepositoryDownloader.getCodelist("urn:xoev-de:xamtshilfe:codeliste:einnahmeart_1.1.1", XRepositoryDownloader.JSON));
		
		//File f = new File("/home/raupach/Projects/prozessknowledgegraph/4_RDFParser/xml.files/Relation-1.0.xml");

		//String t = readFile(f.getAbsolutePath());
		  
		//Codelist cl = new Codelist(f);
		  
		//HashMap<String,String> hm = cl.getHashMap("Code","Beschreibung");
	}
	
	
	static String readFile(String path) throws IOException{
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded);
	}


}
