package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {


	public ComandoVai(String direzione) {
		super.setParametro(direzione);
	}

	public ComandoVai() {
		this(null);
	}
	@Override
	public void esegui(Partita partita, IO console) {

		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;

		if(super.getParametro() == null) {
			console.mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
			return;
		}try {
			prossimaStanza = stanzaCorrente.getStanzaAdiacente(Direzione.valueOf(super.getParametro().toUpperCase()));
		}catch(IllegalArgumentException e) {
			console.mostraMessaggio("Direzione inesistente");
			return;
		}
		if(prossimaStanza==null) {
			console.mostraMessaggio("Direzione inesistente");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		console.mostraMessaggio(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
	}


	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return "vai";
	}

}
