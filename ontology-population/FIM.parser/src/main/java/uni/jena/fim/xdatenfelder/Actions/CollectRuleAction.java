package uni.jena.fim.xdatenfelder.Actions;

import java.util.HashMap;
import java.util.Hashtable;

import uni.jena.fim.xdatenfelder.v2.core.Datenfeld;
import uni.jena.fim.xdatenfelder.v2.core.Datenfeldgruppe;
import uni.jena.fim.xdatenfelder.v2.core.Regel;
import uni.jena.fim.xdatenfelder.v2.core.Struktur;

public class CollectRuleAction implements Action{

	private Hashtable<String, String> rules = null;
	
	public CollectRuleAction() {
		rules = new Hashtable<>();
	}
	
	public CollectRuleAction(Hashtable<String, String> result) {
		this();
		this.rules = result;
	}
	
	public Hashtable<String, String> getRules() {
		return rules;
	}
		
	@Override
	public void act(Struktur strukt) {
		if(strukt != null) {
			if(strukt.getEnthaelt() != null) {
				if(strukt.getEnthaelt().getDatenfeld() != null) {
					getRulesFromDatenfeld(strukt.getEnthaelt().getDatenfeld());
				}else if(strukt.getEnthaelt().getDatenfeldgruppe() != null){
					getRulesFromDatenfeldgruppe(strukt.getEnthaelt().getDatenfeldgruppe());
				}
			}
		}
	}

	private void getRulesFromDatenfeldgruppe(Datenfeldgruppe datenfeldgruppe) {
		for(Regel r : datenfeldgruppe.getRegel()) {
			if(!this.rules.contains(r.getIdentifikation().getId())) {
				this.rules.put(r.getIdentifikation().getId(), r.getDefinition());
			}
		}
	}

	private void getRulesFromDatenfeld(Datenfeld datenfeld) {
		for(Regel r : datenfeld.getRegel()) {
			if(!this.rules.contains(r.getIdentifikation().getId())) {
				this.rules.put(r.getIdentifikation().getId(), r.getDefinition());
			}
		}
	}


	
}
