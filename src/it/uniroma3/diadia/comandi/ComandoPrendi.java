package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoPrendi extends AbstractComando{



	public ComandoPrendi(String parametro) {
		super.setParametro(parametro);;
	}

	public ComandoPrendi() {
		this(null);
	}

	@Override
	public void esegui(Partita partita, IO console) {
		String parametro = super.getParametro();
		if(parametro==null) {
			console.mostraMessaggio("Che oggetto vuoi prendere dalla stanza?");
			parametro = console.leggiRiga();
		}	
		if(partita.getStanzaCorrente().hasAttrezzo(parametro) == true) {
			partita.getGiocatore().getBorsa().addAttrezzo(partita.getStanzaCorrente().getAttrezzo(parametro));
			partita.getStanzaCorrente().removeAttrezzo(partita.getStanzaCorrente().getAttrezzo(parametro));
			console.mostraMessaggio("Messo nella borsa: " + parametro);
		} else {
			console.mostraMessaggio("L'oggetto non e' presente nella stanza");
		}
	}


	@Override
	public String getNome() {
		return "prendi";
	}

}
