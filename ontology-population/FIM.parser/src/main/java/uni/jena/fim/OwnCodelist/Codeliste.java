package uni.jena.fim.OwnCodelist;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Codeliste {
	protected String 	IDTag= "gc:Identification", 
				   		lnTag="gc:LongName", 
				   		shTag="gc:ShortName",
				   		vTag="gc:Version",
				   		cURITag="gc:CanonicalUri", 
				   		cURIvTag="gc:CanonicalVersionUri", 
				   		sclTag="gc:SimpleCodeList",
				   		colsTag="gc:ColumnSet";
	
	private String longName, Shortname, version, canonicalUri, canonicalVersionUri;
	
	private CodelistHeading heading;
	private CodelistCollumnSet collumnset;
	
	
	public Codeliste(Document doc) {
		createContent(doc);
	}
	
	public Codeliste(File f) throws IOException {
		this(getDocument(f));
	}
	
	public Codeliste(String content) throws IOException {
		this(getDocument(new ByteArrayInputStream(content.getBytes())));
	}
	
	public Codeliste(URL url) throws IOException {
		StringBuilder result = new StringBuilder();
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(conn.getInputStream()))) {
			for (String line; (line = reader.readLine()) != null; ) {
				result.append(line);
			}
		}
	     
		Document docFile = getDocument(new ByteArrayInputStream(result.toString().getBytes()));
		createContent(docFile);
	}

	public Collection<String> getIDs() {
		return this.heading.getAllIDs();
	}
	
	public ArrayList<String> getAllKeys(){
		return this.heading.getAllKeyRefs();
	}
	
	public Collection<String> getValuesByID(String ID){
		return this.collumnset.getAllValuesByID(ID);
	}
	
	public HashMap<String, String> getHashMap(String Key, String Value){
		return this.collumnset.getKeyValueHashMap(Key, Value);
	}
	
	public String getLongName() {
		return this.longName;
	}

	public String getShortname() {
		return Shortname;
	}

	public String getVersion() {
		return version;
	}

	public String getCanonicalUri() {
		return canonicalUri;
	}

	public String getCanonicalVersionUri() {
		return canonicalVersionUri;
	}
	
	public boolean checkCodeListByUri(String Uri) {
		return (this.canonicalVersionUri.equals(Uri) || this.canonicalUri.equals(Uri));
	}
	
	private void createContent(Document doc) {
		Element ids = (Element)doc.getElementsByTagName(IDTag).item(0);
		this.longName = ids.getElementsByTagName(lnTag).item(0).getTextContent();
		this.Shortname = ids.getElementsByTagName(shTag).item(0).getTextContent();
		this.version = ids.getElementsByTagName(vTag).item(0).getTextContent();
		this.canonicalUri = ids.getElementsByTagName(cURITag).item(0).getTextContent();
		this.canonicalVersionUri = ids.getElementsByTagName(cURIvTag).item(0).getTextContent();
		
		
		Element columnset = (Element)doc.getElementsByTagName(sclTag).item(0);
		Element heading = (Element)doc.getElementsByTagName(colsTag).item(0);
		
		
		this.heading = new CodelistHeading(heading);
		this.collumnset = new CodelistCollumnSet(columnset);
	}
	
	private static Document getDocument(File docFile) throws IOException {
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
	
	private static Document getDocument(InputStream contentStream) throws IOException {
        DocumentBuilder builder;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(contentStream);
            doc.getDocumentElement().normalize();
            return doc;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return null;
    }
}
