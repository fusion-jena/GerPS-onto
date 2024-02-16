package uni.jena.mapping.manuel;

import java.io.File;
import java.net.URL;
import java.util.Set;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDFS;

public class Mapper {
	private MappingConfig config;
	
	
	public Mapper() {
		URL resource = this.getClass().getClassLoader().getResource("config.toml");
		config = new MappingConfig(new File(resource.getPath()));
	}
	
	public void mapOntology(Model m) {
		Set<String> keyFromSameAs = this.config.getKeyFromSameAs();
		Set<String> keyFromSubClassOf = this.config.getKeyFromSubClassOf();
		Set<String> keyFromSuperClassOf = this.config.getKeyFromSuperClassOf();
		
		for(String s : keyFromSameAs) {
			Resource resource = m.getResource(s);
			resource.addProperty(OWL.sameAs, m.createResource(this.config.getSameAsValue(s)));
		}
		
		for(String s : keyFromSubClassOf) {
			Resource supeClass = m.createResource(this.config.getSubClassOf(s));
			Resource subclass = m.getResource(s);
			subclass.addProperty(RDFS.subClassOf, supeClass);
		}
		
		for(String s : keyFromSuperClassOf) {
			Resource subclass = m.createResource(this.config.getSuperClassOf(s));
			Resource superClass = m.getResource(s);
			subclass.addProperty(RDFS.subClassOf, superClass);
		}
	}
}
