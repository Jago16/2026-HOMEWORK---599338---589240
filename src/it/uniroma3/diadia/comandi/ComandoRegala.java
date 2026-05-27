package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoRegala extends AbstractComando{

	public ComandoRegala(String nomeOggetto) {
		this.setParametro(nomeOggetto);
	}

	@Override
	public void esegui(Partita partita, IO console) {
		if(partita.getGiocatore().getBorsa().hasAttrezzo(this.getParametro())) {
			console.mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().
					riceviRegalo(partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro()), partita));
		}else
			console.mostraMessaggio("Non hai questo oggetto nella borsa...");
	}

	@Override
	public String getNome() {
		return "regala";
	}

}
