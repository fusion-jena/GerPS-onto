package uni.jena.fim.OwnCodelist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CodelistCollumnSet {
	protected String RowTag = "gc:Row";
	
	ArrayList<CodeListRow> rows = new ArrayList<>();
	
	public CodelistCollumnSet(Element collumset) {
		NodeList items = collumset.getElementsByTagName("gc:Row");
		for(int i = 0; i < items.getLength(); i++) {
			Node row = items.item(i);
			
			this.rows.add(new CodeListRow(((Element)row)));
		}
		
	}
	
	public Collection<Collection<String>> getTable(){
		ArrayList<Collection<String>> result = new ArrayList<>();
		
		for (CodeListRow row : rows) {
			result.add(row.getAllVAlues());
		}
		
		return result;
	}
	
	public ArrayList<String> getAllValuesByID(String ID) {
		ArrayList<String> values = new ArrayList<>();
		
		for (CodeListRow row : rows) {
			values.add(row.getValueByID(ID));
		}
		
		return values;
	}
	
	public HashMap<String, String> getKeyValueHashMap(String key, String value){
		HashMap<String, String> result = new HashMap<>();
		
		for (CodeListRow row : rows) {
			String k = row.getValueByID(key);
			String v = row.getValueByID(value);
			
			result.put(k, v);
		}
		
		return result;
	}

}
