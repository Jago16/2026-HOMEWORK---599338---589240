package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoPrendi implements Comando{
	private String parametro; //Oggetto da prendere
	private IOConsole console;

	public ComandoPrendi(String parametro) {
		this.parametro = parametro;
		this.console = new IOConsole();
	}
	@Override
	public void esegui(Partita partita, IO console) {

		if(parametro==null) {
			this.console.mostraMessaggio("Che oggetto vuoi prendere dalla stanza?");
			parametro = this.console.leggiRiga();
			if(partita.getStanzaCorrente().hasAttrezzo(parametro) == true) {
				partita.getGiocatore().getBorsa().addAttrezzo(partita.getStanzaCorrente().getAttrezzo(parametro));
				partita.getStanzaCorrente().removeAttrezzo(partita.getStanzaCorrente().getAttrezzo(parametro));
				console.mostraMessaggio("Messo nella borsa: " + parametro);
			} else {
				console.mostraMessaggio("L'oggetto non e' presente nella stanza");
			}
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	@Override
	public String getNome() {
		return "prendi";
	}

	@Override
	public String getParametro() {
		return parametro;
	}

}
