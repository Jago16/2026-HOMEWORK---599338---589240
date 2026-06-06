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
	
	public String getOggettoLuminoso(){
		return this.oggettoLuminoso;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o==null || this.getClass()!=o.getClass())
			return false;
		StanzaBuia that = (StanzaBuia) o;
		return this.getNome().equals(that.getNome()) && this.getOggettoLuminoso().equals(that.getOggettoLuminoso());
	}
	
	@Override
	public int hashCode() {
		return this.getNome().hashCode() + this.getOggettoLuminoso().hashCode() + this.getClass().hashCode();
	}
	
	

}
