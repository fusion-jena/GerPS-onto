package uni.jena.fim.OwnCodelist;

import java.util.Collection;
import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CodeListRow {
	protected String 	valTag="gc:Value",
						colRefTag="ColumnRef",
						svTag="gc:SimpleValue";
	
	
	
	private HashMap<String, String> row = new HashMap<>();
	
	public CodeListRow(Element element) {
		NodeList items = element.getElementsByTagName(valTag);
		for(int i = 0; i < items.getLength(); i++) {
			Element value = (Element)items.item(i);
			
			String id = value.getAttribute(colRefTag);
			String v = value.getElementsByTagName(svTag).item(0).getTextContent();
			row.put(id, v);
		}
	}
	
	public String getValueByID(String ID) {
		return this.row.get(ID);
	}
	
	public Collection<String> getAllVAlues() {
		return this.row.values();
	}
	

}
