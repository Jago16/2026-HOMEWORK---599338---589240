package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{

	private String direzioneBloccata;
	private String nomeAttrezzoSbloccante;
	
	public StanzaBloccata(String nome, String direzioneBloccata, String nomeAttrezzo) {
		super(nome);
		this.direzioneBloccata=direzioneBloccata;
		this.nomeAttrezzoSbloccante=nomeAttrezzo;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(this.direzioneBloccata.equals(direzione) && !this.hasAttrezzo(this.nomeAttrezzoSbloccante)) {
			return this;
		}
		else {
			return super.getStanzaAdiacente(direzione);	
		}
	}
	
	@Override
	public String getDescrizione() {
		if(!this.hasAttrezzo(nomeAttrezzoSbloccante)) {
			return "La direzione " + this.direzioneBloccata + "sembra bloccata...\n" + super.getDescrizione();
		}
		else {
			return super.getDescrizione();
		}
	}
	
	public String getDirezioneBloccata() {
		return this.direzioneBloccata;
	}
	
	public String getNomeAttrezzoSbloccante() {
		return this.nomeAttrezzoSbloccante;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o==null || !this.getClass().equals(o.getClass()))
			return false;
		StanzaBloccata that = (StanzaBloccata) o;
		return this.getNome().equals(that.getNome()) && this.getDirezioneBloccata().equals(that.getDirezioneBloccata())
				&& this.getNomeAttrezzoSbloccante().equals(that.getNomeAttrezzoSbloccante());
	}
	
	@Override
	public int hashCode() {
		return this.getNome().hashCode() + this.getNomeAttrezzoSbloccante().hashCode() + 
				this.getDirezioneBloccata().hashCode();
	}
	
	
	
}
