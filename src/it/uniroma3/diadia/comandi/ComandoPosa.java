package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoPosa extends AbstractComando{
	
	
	
	public ComandoPosa(String nomeOggetto) {
		super.setParametro(nomeOggetto);
	}
	
	public ComandoPosa() {
		this(null);
	}
	
	@Override
	public void esegui(Partita partita, IO console) {
		String nomeOggetto = super.getParametro();
		if(nomeOggetto==null) {
			console.mostraMessaggio("Che oggetto vuoi posare?");
			nomeOggetto = console.leggiRiga();
		}
		if(partita.getGiocatore().getBorsa().hasAttrezzo(nomeOggetto) == true) {
			partita.getStanzaCorrente().addAttrezzo(partita.getGiocatore().getBorsa().getAttrezzo(nomeOggetto));
			partita.getGiocatore().getBorsa().removeAttrezzo(nomeOggetto);
			console.mostraMessaggio("Hai posato l'oggetto " + nomeOggetto);
		} else {
			console.mostraMessaggio("L'oggetto non e' presente nella borsa");
		}		
	}


	@Override
	public String getNome() {
		return "posa";
	}

	

}
