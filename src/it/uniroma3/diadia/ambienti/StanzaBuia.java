package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	
	private String oggettoLuminoso;
	
	public StanzaBuia(String nome, String oggettoLuminoso) {
		super(nome);
		this.oggettoLuminoso=oggettoLuminoso;
	}
	
	
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(oggettoLuminoso))
			return super.getDescrizione();
		else
			return "Qui c'e' un buio pesto";
	}
	
	

}
