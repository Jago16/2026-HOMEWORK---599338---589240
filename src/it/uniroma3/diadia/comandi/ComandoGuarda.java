package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.IO;


public class ComandoGuarda implements Comando{

	@Override
	public void esegui(Partita partita, IO console) {
		
		console.mostraMessaggio("Stanza Corrente: " + partita.getStanzaCorrente().getDescrizione());
		if(partita.isFinita() == false) {
			console.mostraMessaggio("CFU rimasti: " + partita.getGiocatore().getCfu());
			console.mostraMessaggio("Info Borsa: " + partita.getGiocatore().getBorsa());
		}
	}

	@Override
	public void setParametro(String parametro) {
		
	}

	@Override
	public String getNome() {
		return "guarda";
	}

	@Override
	public String getParametro() {
		return null;
	}

}
