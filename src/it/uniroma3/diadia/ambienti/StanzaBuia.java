package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	
	static final private String[] oggettiLuminosi = {"lanterna", "torcia"};
	
	public StanzaBuia(String nome) {
		super(nome);
	}
	
	
	@Override
	public String getDescrizione() {
		boolean illuminata = false;
		for(int i = 0; i < this.oggettiLuminosi.length && !illuminata; i++) {
			if(super.hasAttrezzo(oggettiLuminosi[i]));
				illuminata = true;	
		}
		if(illuminata)
			return super.getDescrizione();
		else
			return "Qui c'e' un buio pesto";
	}
	
	

}
