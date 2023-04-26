package uni.jena.fim.xdatenfelder.Actions;


import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

import uni.jena.fim.xdatenfelder.XDatenfelderConfig;
import uni.jena.fim.xdatenfelder.v2.core.Datenfeld;
import uni.jena.fim.xdatenfelder.v2.core.Datenfeldgruppe;
import uni.jena.fim.xdatenfelder.v2.core.Regel;

public class RDFResourceInformationAction extends RDFResourceStrukturAction {

	public RDFResourceInformationAction(Model m , XDatenfelderConfig config) {
		super(m, config);
	}
	
	@Override
	protected void onDatenfeld(Datenfeld datenfeld, Resource curResource) {		
		//TODO Informationen in RDF Parsen, Ontologie fehlt aber noch
	}
	
	@Override
	protected void onRegel(Regel regel, Resource curResource) {
		//TODO Informationen in RDF Parsen, Ontologie fehlt aber noch
	}
	
	@Override
	protected void onDatenfeldGruppen(Datenfeldgruppe datenfeldgruppe, Resource curResource) {
		//TODO Informationen in RDF Parsen, Ontologie fehlt aber noch
	}

}
