package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando{
	private IOConsole io;
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Comando non valido");
	}
	@Override
	public void setParametro(String parametro) {
	}
	@Override
	public String getNome() {
		return "ComandoNonValido";
	}
	@Override
	public String getParametro() {
		return null;
	}

}
