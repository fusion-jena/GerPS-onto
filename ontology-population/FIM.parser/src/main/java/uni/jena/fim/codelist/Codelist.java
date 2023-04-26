package uni.jena.fim.codelist;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import uni.jena.fim.codelist.core.CodeListDocument;
import uni.jena.fim.codelist.core.Column;
import uni.jena.fim.codelist.core.ColumnRef;
import uni.jena.fim.codelist.core.Key;
import uni.jena.fim.codelist.core.Row;
import uni.jena.fim.codelist.core.Value;



public class Codelist {
	private CodeListDocument cld = null;
	
	//-------------------- Konstruktoren ----------------------
	
	public Codelist(String content) {
		JAXBContext jaxbContext;
		CodeListDocument cl = null;
		
		try
		{
		  jaxbContext = JAXBContext.newInstance(CodeListDocument.class);        
		 
		  Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		  @SuppressWarnings("unchecked")
		  JAXBElement<CodeListDocument> ju = (JAXBElement<CodeListDocument>)jaxbUnmarshaller.unmarshal(
				  												new ByteArrayInputStream(content.getBytes()));
		  
		  cl = ju.getValue();
		  		  
		}
		catch (JAXBException e) 
		{
		  e.printStackTrace();
		}

		this.cld = cl;
	}
	
	public Codelist(URL url) throws IOException {
		StringBuilder result = new StringBuilder();
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(conn.getInputStream()))) {
			for (String line; (line = reader.readLine()) != null; ) {
				result.append(line);
			}
		}
		
		JAXBContext jaxbContext;
		CodeListDocument cl = null;
		 
		try
		{
		  jaxbContext = JAXBContext.newInstance(CodeListDocument.class);        
		 
		  Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		  @SuppressWarnings("unchecked")
		  JAXBElement<CodeListDocument> ju = (JAXBElement<CodeListDocument>)jaxbUnmarshaller.unmarshal(
				  																	new ByteArrayInputStream(result.toString().getBytes()));
		  
		  cl = ju.getValue();
		  		  
		}
		catch (JAXBException e) 
		{
		  e.printStackTrace();
		}

		this.cld = cl;
	}
	
	public Codelist(File file) throws IOException {
		this(readFile(file.getAbsolutePath()));
	}
	
	
	
	//-------------------- Public Methods----------------------
	
	public boolean checkCodeListByUri(String Uri) {
		return (this.cld.getIdentification().getCanonicalUri().equals(Uri) || this.cld.getIdentification().getCanonicalVersionUri().equals(Uri));
	} 
	
	public String getLongName() {
		return this.cld.getIdentification().getLongName().get(0).getValue();
	}

	public String getShortname() {
		return this.cld.getIdentification().getShortName().getValue();
	}

	public String getVersion() {
		return this.cld.getIdentification().getVersion();
	}

	public String getCanonicalUri() {
		return this.cld.getIdentification().getCanonicalUri();
	}

	public String getCanonicalVersionUri() {
		return this.cld.getIdentification().getCanonicalVersionUri();
	}

	public Collection<String> getIDs() {
		ArrayList<String> result = new ArrayList<>();
		
		for (Object obj : this.cld.getColumnSet().getColumnChoice()) {
			if(obj.getClass() == Column.class) {
				Column c = (Column) obj;
				
				result.add(c.getId());
			}else if(obj.getClass() == ColumnRef.class) {
				ColumnRef cr = (ColumnRef) obj;
				
				result.add(cr.getId());
			}
			
		}
		
		return result;	
	}
	
	public Collection<String> getKeys(){
		ArrayList<String> result = new ArrayList<>();
		
		for (Object obj : this.cld.getColumnSet().getKeyChoice()) {
			if(obj.getClass() == Key.class) {
				Key c = (Key) obj;
				
				// lese aus der ColRef, die Column raus --> hole ID von Column
				Column col = (Column)c.getColumnRef().get(0).getRef();
				
				result.add(col.getId());
			}
		}
		
		return result;	
	}
	
	public Collection<String> getValuesByID(String ID){
		ArrayList<String> result = new ArrayList<>();

		for(Row row : this.cld.getSimpleCodeList().getRow()) {
			
			for(Value v : row.getValue()) {
				if(v.getColumnRef().getClass() == Column.class) {
					Column c = (Column) v.getColumnRef();
					if(c.getId().equals(ID)) {
						result.add(v.getSimpleValue().getValue());
					}				
				}else if(v.getColumnRef().getClass() == ColumnRef.class) {
					ColumnRef cr = (ColumnRef) v.getColumnRef();
					if(cr.getId().equals(ID)) {
						result.add(v.getSimpleValue().getValue());
					}
				}
			}
		}

		return result;	
	}
	
	public HashMap<String, String> getHashMap(String Key, String Value){
		HashMap<String,String> result = new HashMap<>();

		if(this.cld.getSimpleCodeList() != null) {
			if(this.cld.getSimpleCodeList().getRow()!= null) {
				for(Row row : this.cld.getSimpleCodeList().getRow()) {
					String k="", va="";
					if(row.getValue() != null) {
						for(Value v : row.getValue()) {
							if(v.getColumnRef() != null) {
								if(v.getColumnRef().getClass() == Column.class) {
									Column c = (Column) v.getColumnRef();
									if(c.getId()!= null) {
										if(c.getId().equals(Key)) {
											if(v.getSimpleValue() != null) {						
												k = v.getSimpleValue().getValue();
											}
										}
										if(c.getId().equals(Value)) {
											if(v.getSimpleValue()!= null) {
											va = v.getSimpleValue().getValue(); 
											}
										}
									}
									
								}else if(v.getColumnRef().getClass() == ColumnRef.class) {
									ColumnRef cr = (ColumnRef) v.getColumnRef();
									if(cr.getId() != null) {
										if(cr.getId().equals(Key)) {
											if(v.getSimpleValue() != null) {
												k = v.getSimpleValue().getValue();
											}
										}
										if(cr.getId().equals(Value)) {
											if(v.getSimpleValue() != null) {
												va = v.getSimpleValue().getValue(); 
											}
										}
									}									
								}
							}
						}
					}
					if(!result.containsKey(k))
						result.put(k, va);
				}
			}
		}
		if(result.containsKey(""))
			result.remove("");
		
		return result;
	}
	
	public Collection<String> get(int i){
		ArrayList<String> result = new ArrayList<>();
		
		if(this.cld.getSimpleCodeList() != null) {
			if(this.cld.getSimpleCodeList().getRow() != null) {
				Row row = this.cld.getSimpleCodeList().getRow().get(i);
				for (Value v : row.getValue()) {
					if(v.getSimpleValue() != null) {
						result.add(v.getSimpleValue().getValue());
					}
				}
			}
		}
		
		
		
		return result;
	}
	
	public int getRowSize() {
		if (this.cld.getSimpleCodeList() != null)
			if(this.cld.getSimpleCodeList().getRow() != null)
				return this.cld.getSimpleCodeList().getRow().size();
		
		return 0;
	}
	
	public int getHeaderSize() {
		if (this.cld.getColumnSet() != null)
			if(this.cld.getColumnSet().getColumnChoice() != null)
				return this.cld.getColumnSet().getColumnChoice().size();
		
		return 0;
	}
	
	public int getKeySize() {
		if (this.cld.getColumnSet() != null)
			if(this.cld.getColumnSet().getKeyChoice() != null)
				return this.cld.getColumnSet().getKeyChoice().size();
		
		return 0;
	}
	

	
	//-------------------- Private Methods ----------------------

	private static String readFile(String path) throws IOException{
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded);
	}
}
