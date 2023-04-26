package uni.jena.fim.OwnCodelist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CodelistHeading {
	protected String 	colTag="gc:Column",
						keyTag="gc:Key";
	
	
	private ArrayList<Collumn> collumns = new ArrayList<>();
	private ArrayList<CollumnKey> keys = new ArrayList<>();
	
	public CodelistHeading(Element heading) {
		NodeList collumn = heading.getElementsByTagName(colTag);		
		for (int i = 0; i < collumn.getLength(); i++) {
			Node item = collumn.item(i);
			this.collumns.add(new Collumn(((Element)item)));
		}
		
		NodeList keys = heading.getElementsByTagName(keyTag);		
		for (int i = 0; i < keys.getLength(); i++) {
			Element item = (Element)keys.item(i);		
			this.keys.add(new CollumnKey(item)); 
		}
	}
	
	public Collection<String> getAllKeyRefs(){
		ArrayList<String> result = new ArrayList<>(); 
		
		for (CollumnKey key : this.keys) {
			result.add(key.getColRef());
		}
		
		return result;
	}
	
	public ArrayList<String> getAllNames() {
		ArrayList<String> result = new ArrayList<>();
		
		for (Collumn c : this.collumns) {
			result.add(c.getShortName());
		}
		
		return result;
	}
	
	public ArrayList<String> getAllIDs() {
		ArrayList<String> result = new ArrayList<>();
		
		for (Collumn c : this.collumns) {
			result.add(c.getID());
		}
		
		return result;
	}
	
	public HashMap<String, String> getAllKeyValuePairs(){
		HashMap<String, String> result = new HashMap<>();
		
		for (Collumn c : this.collumns) {
			result.put(c.getID(), c.getShortName());
		}
		
		return result;
	}

}
