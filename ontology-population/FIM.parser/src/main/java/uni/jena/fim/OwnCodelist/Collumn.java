package uni.jena.fim.OwnCodelist;

import org.w3c.dom.Element;

public class Collumn {
	protected String 	idTag="Id",
						snTag="gc:ShortName",
						dataTag="gc:Data",
						typeTag="Type";
	
	
	private String shortName, id, dataType;
	
	public Collumn(Element collumn) {
		this.id = collumn.getAttribute(idTag);
		this.shortName = collumn.getElementsByTagName(snTag).item(0).getTextContent();
		this.dataType = ((Element)collumn.getElementsByTagName(dataTag).item(0)).getAttribute(typeTag);
	}

	public String getShortName() {
		return this.shortName;
	}

	public String getID() {
		return this.id;
	}

	public String getDataType() {
		return dataType;
	}
	
}
