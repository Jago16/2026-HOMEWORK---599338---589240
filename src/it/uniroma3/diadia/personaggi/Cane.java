package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{

	private static final String MESSAGGIO_PRESENTAZIONE = "Bau";
	private static final String MESSAGGIO_MORSO = "WOF!";
	
	public Cane(String nome, Attrezzo attrezzo) {
		super(nome, MESSAGGIO_PRESENTAZIONE);
		this.setAttrezzo(attrezzo);
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
		return MESSAGGIO_MORSO;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		//Il cibo preferito è l'osso
		if(attrezzo.getNome().equals("osso")){
			partita.getStanzaCorrente().addAttrezzo(getAttrezzo());
			setAttrezzo(partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome()));
			return MESSAGGIO_PRESENTAZIONE + "-Il cane ha posato un attrezzo a terra-";
		}
		else {
			//partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
			return agisci(partita) + " -sei stato morso, -1CFU -";
		}
	}
	
	@Override
	public String saluta() {
		return MESSAGGIO_PRESENTAZIONE;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o==null || this.getClass() != o.getClass())
			return false;
		Cane that = (Cane) o;
		return this.getNome().equals(that.getNome()) && this.getAttrezzo().equals(that.getAttrezzo());
	}
	
	@Override
	public int hashCode() {
		return this.getClass().hashCode() + this.getNome().hashCode() + this.getAttrezzo().hashCode();
	}
	

}
