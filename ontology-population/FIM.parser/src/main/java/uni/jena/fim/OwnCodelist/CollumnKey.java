package uni.jena.fim.OwnCodelist;

import org.w3c.dom.Element;

public class CollumnKey {
	protected String 	idTag= "Id",
						snTag="gc:ShortName",
						colRefTag="gc:ColumnRef",
						refTag="Ref";
	
	private String id, shortName, colRef;
	
	public CollumnKey(Element key) {
		this.id = key.getAttribute(idTag);
		if(key.getElementsByTagName(snTag).getLength() >0 )
			this.shortName = key.getElementsByTagName(snTag).item(0).getTextContent();
		if(key.getElementsByTagName(colRefTag).getLength() > 0)
			this.colRef = ((Element)key.getElementsByTagName(colRefTag).item(0)).getAttribute(refTag);
	}
	
	public String getId() {
		return id;
	}

	public String getShortName() {
		return shortName;
	}

	public String getColRef() {
		return colRef;
	}
}
