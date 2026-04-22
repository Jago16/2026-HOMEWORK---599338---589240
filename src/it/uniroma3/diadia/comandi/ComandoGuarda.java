package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando{

	@Override
	public void esegui(Partita partita) {
		System.out.println("Stanza Corrente: " + partita.getStanzaCorrente());
		if(partita.isFinita() == false) {
			System.out.println("CFU rimasti: " + partita.getGiocatore().getCfu());
			System.out.println("Info Borsa: " + partita.getGiocatore().getBorsa());
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
