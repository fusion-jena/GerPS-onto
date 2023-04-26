package uni.jena.fim.xdatenfelder;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class XDatenfelderConfig {

	String baseURL;
	// Classes
	String Stammschemata ;
	String Struktur ;
	String Datenfeld ;
	String Datenfeldgruppe ;
	String Regel ;


	// Properties 
	String hat_Regel ;
	String hat_enthaelt ;

	
	// DataProperties
	String name;
	String id;
	
	public Resource getStammschemata(Model m) {
		return m.getResource(this.Stammschemata);
	}
	
	public Resource getStruktur(Model m) {
		return m.getResource(this.Struktur);
	}
	
	public Resource getDatenfeld(Model m) {
		return m.getResource(this.Datenfeld);
	}
	
	public Resource getDatenfeldgruppe(Model m) {
		return m.getResource(this.Datenfeldgruppe);
	}
	
	public Resource getRegel(Model m) {
		return m.getResource(this.Regel);
	}
	
	public Property getHat_enthaelt(Model m) {
		return m.getProperty(this.hat_enthaelt);
	}
	
	public Property getHatRegel(Model m) {
		return m.getProperty(this.hat_Regel);
	}
	
	public Property getName(Model m) {
		return m.getProperty(this.name);
	}
	
	public Property getID(Model m) {
		return m.getProperty(this.id);
	}
	
	public Resource getIndividuals(Model m,String id, Resource type) {
		return m.createResource(this.baseURL + id, type);
	}
	
	public Resource getIndividuals(Model m,String id) {
		return m.createResource(this.baseURL + id);
	}
	
	
}
