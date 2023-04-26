package uni.jena.fim.xdatenfelder.Actions;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import uni.jena.fim.xdatenfelder.XDatenfeld;
import uni.jena.fim.xdatenfelder.XDatenfelderConfig;
import uni.jena.fim.xdatenfelder.v2.core.Datenfeld;
import uni.jena.fim.xdatenfelder.v2.core.Datenfeldgruppe;
import uni.jena.fim.xdatenfelder.v2.core.Regel;
import uni.jena.fim.xdatenfelder.v2.core.Struktur;

public class RDFResourceStrukturAction implements Action {

	private Model model;
	private XDatenfelderConfig config;
	
	
	public RDFResourceStrukturAction(Model m, XDatenfelderConfig config) {
		this.config = config;
		this.model = m;
	}
	
	@Override
	public void act(Struktur struct) {
		if(struct.getEnthaelt().getDatenfeld() != null) {
			Resource df = getDatenfeld(struct);
			
		}else if(struct.getEnthaelt().getDatenfeldgruppe() != null) {
			Resource dfg = getDatenfeldGruppe(struct);
		}
	}
	
	public Model getModel() {
		return this.model;
	}
	
	private String getStruktID(Struktur strukt) {
		String result = null;
		
		if(strukt.getEnthaelt() != null) {
			if(strukt.getEnthaelt().getDatenfeld() != null){
				result = strukt.getEnthaelt().getDatenfeld().getIdentifikation().getId();
			}else if(strukt.getEnthaelt().getDatenfeldgruppe() != null) {
				result = strukt.getEnthaelt().getDatenfeldgruppe().getIdentifikation().getId();
			}
		}
		
		return result;
	}

	private Resource getDatenfeldGruppe(Struktur struct) {
		Resource result;
		Datenfeldgruppe field = struct.getEnthaelt().getDatenfeldgruppe();
		
		String name = field.getName();
		String id = field.getIdentifikation().getId();
		result = this.config.getIndividuals(model, id, this.config.getDatenfeldgruppe(model));
		
		onDatenfeldGruppen(field, result);

		result.addProperty(RDFS.label, name);

		result.addLiteral(this.config.getName(model), name);
		result.addLiteral(this.config.getID(model), id);
		
		for (Regel r : field.getRegel()) {
			result.addProperty(this.config.getHatRegel(model), getRDFRegel(r));
		}
		
		for(Struktur strukt : struct.getEnthaelt().getDatenfeldgruppe().getStruktur()) {
			String struktId = getStruktID(strukt);
			if(struktId != null) {
				Resource res = this.config.getIndividuals(model, struktId);
				result.addProperty(this.config.getHat_enthaelt(model), res);
			}
		}
		
		return result;
	}

	private Resource getDatenfeld(Struktur struct) {
		Resource result;
		Datenfeld field = struct.getEnthaelt().getDatenfeld();
		
		String name = field.getName();
		String id = field.getIdentifikation().getId();
		
		
		result = this.config.getIndividuals(model, id, this.config.getDatenfeld(model));
		
		onDatenfeld(field, result);
		
		result.addProperty(RDFS.label, name);

		result.addLiteral(this.config.getName(model), name);
		result.addLiteral(this.config.getID(model), id);
		
		for (Regel r : field.getRegel()) {
			result.addProperty(this.config.getHatRegel(model), getRDFRegel(r));
		}

		return result;
	}
	
	private Resource getRDFRegel(Regel regel) {
		
		String id = regel.getIdentifikation().getId();
		String name = regel.getName();
		
		Resource result = this.config.getIndividuals(model, id, this.config.getRegel(model));
		onRegel(regel, result);

		result.addProperty(RDFS.label, name);
		
		result.addLiteral(this.config.getName(model), name);
		result.addLiteral(this.config.getID(model), id);
		
		return result;
	}
	
	protected void onDatenfeld(Datenfeld datenfeld, Resource curResource) {
		
	}
	
	protected void onDatenfeldGruppen(Datenfeldgruppe datenfeldgruppe, Resource curResource) {
		
	}
	
	protected void onRegel(Regel regel, Resource curResource) {
		
	}
}
