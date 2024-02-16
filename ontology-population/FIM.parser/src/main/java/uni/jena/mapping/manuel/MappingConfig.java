package uni.jena.mapping.manuel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.moandjiezana.toml.Toml;

public class MappingConfig {
	
	 private Map<String, String> sameAs;
	 private Map<String, String> subClassOf;
	 private Map<String, String> superClassOf;
	
	public MappingConfig(File f) {
		Toml configFile = new Toml().read(f);
		
		sameAs = createMap(configFile.getTable("Mapping.sameAs").getTables("entry"));
		
		subClassOf = createMap(configFile.getTable("Mapping.subClassOf").getTables("entry"));

		superClassOf = createMap(configFile.getTable("Mapping.superClassOf").getTables("entry"));
	}
	
	private Map<String, String> createMap(List<Toml> tomls){
		HashMap<String, String> result = new HashMap<>();
		
		if(tomls != null) {
			for(Toml toml : tomls) {
				String key = toml.getString("key");
				String value = toml.getString("value");
				
				result.put(key,value);
			}
		}
		
		return result;
	}
	
	public Set<String> getKeyFromSameAs(){
		return this.sameAs.keySet();
	}
	
	public Set<String> getKeyFromSubClassOf(){
		return this.subClassOf.keySet();
	}
	
	public Set<String> getKeyFromSuperClassOf(){
		return this.superClassOf.keySet();
	}
	
	public String getSameAsValue(String key) {
		return this.sameAs.get(key).toString();
	}
	
	public String getSubClassOf(String key) {
		return this.subClassOf.get(key).toString();
	}
	
	public String getSuperClassOf(String key) {
		return this.superClassOf.get(key).toString();
	}
}
