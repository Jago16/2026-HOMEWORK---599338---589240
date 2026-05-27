package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoInteragisci extends AbstractComando{

	@Override
	public void esegui(Partita partita, IO console) {
		console.mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().agisci(partita));
	}

	@Override
	public String getNome() {
		return "interagisci";
	}

}
