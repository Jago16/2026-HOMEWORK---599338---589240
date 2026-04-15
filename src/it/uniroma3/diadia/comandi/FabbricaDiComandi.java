package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandi implements Comando{
	private String parametro;
	private Object istruzione;
	private String nome;
	public Comando costruisciComando(String istruzione) {
		String nome = null;
		String parametro = null;
		Scanner scannerDiParole = new Scanner(istruzione);

		// prima parola: nome del comando
		if (scannerDiParole.hasNext())
			this.nome = scannerDiParole.next(); 

		// seconda parola: eventuale parametro
		if (scannerDiParole.hasNext())
			this.parametro = scannerDiParole.next();
	}
	Comando comandoDaEseguire = Comando(istruzione);
	if ("fine".equals(nome)) {
		return new ComandoFine(); 
		
	} else if (comandoDaEseguire.getNome()!=null && comandoDaEseguire.getNome().equals("vai"))
		this.vai(comandoDaEseguire.getParametro());
	else if (comandoDaEseguire.getNome()!=null && comandoDaEseguire.getNome().equals("aiuto"))
		this.aiuto();
	else if (comandoDaEseguire.getNome()!=null && comandoDaEseguire.getNome().equals("prendi"))
		this.prendi(comandoDaEseguire.getParametro());
	else if (comandoDaEseguire.getNome()!=null && comandoDaEseguire.getNome().equals("posa"))
		this.posa(comandoDaEseguire.getParametro());
	else
		this.console.mostraMessaggio("Comando sconosciuto");
	/*
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next(); // prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next(); // seconda parola: eventuale parametro
		if (nomeComando == null)
			comando = new ComandoNonValido();
		else if (nomeComando.equals("vai"))
			comando = new ComandoVai();
		else if (nomeComando.equals("prendi"))
			comando = new ComandoPrendi();
		else if (nomeComando.equals("posa"))
			comando = new ComandoPosa();
		else if (nomeComando.equals("aiuto"))
			comando = new ComandoAiuto();
		else if (nomeComando.equals("fine"))
			comando = new ComandoFine();
		else if (nomeComando.equals("guarda"))
			comando = new ComandoGuarda();
		else comando = new ComandoNonValido();
		comando.setParametro(parametro);
		return comando;
	 */
}
}
