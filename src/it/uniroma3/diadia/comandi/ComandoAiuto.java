package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando{

	static final private String[] elencoComandi = {"vai", "aiuto", "prendi", "posa", "fine"};

	
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) {
			System.out.println(elencoComandi[i]+" ");
		}
		System.out.println("");		
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}

}
