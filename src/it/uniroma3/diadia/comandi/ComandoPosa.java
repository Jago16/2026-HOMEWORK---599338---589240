package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoPosa implements Comando{
	
	private String nomeOggetto;
	private IOConsole console;
	
	public ComandoPosa(String nomeOggetto) {
		this.nomeOggetto = nomeOggetto;
		this.console = new IOConsole();
	}
	@Override
	public void esegui(Partita partita) {
		if(nomeOggetto==null) {
			this.console.mostraMessaggio("Che oggetto vuoi posare?");
			nomeOggetto = this.console.leggiRiga();
		}
		if(partita.getGiocatore().getBorsa().hasAttrezzo(nomeOggetto) == true) {
			partita.getStanzaCorrente().addAttrezzo(partita.getGiocatore().getBorsa().getAttrezzo(nomeOggetto));
			partita.getGiocatore().getBorsa().removeAttrezzo(nomeOggetto);
			this.console.mostraMessaggio("Hai posato l'oggetto " + nomeOggetto);
		} else {
			this.console.mostraMessaggio("L'oggetto non e' presente nella borsa");
		}		
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeOggetto = parametro;
		
	}

	@Override
	public String getNome() {
		return "posa";
	}

	@Override
	public String getParametro() {
		return nomeOggetto;
	}

}
