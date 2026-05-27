package it.uniroma3.diadia.comandi;

import java.util.ArrayList;
import java.util.List;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando{

	private String parametro;
	
	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	@Override
	public String getParametro() {
		return this.parametro;
	}
	
	public List<String> getElencoCOmandi(){
		List<String> elencoComandiDisponibili = new ArrayList<>();
		for(ElencoComandi c : ElencoComandi.values())
			elencoComandiDisponibili.add(c.getNome());
		return elencoComandiDisponibili;
	}
	
	@Override
	public abstract void esegui(Partita partita, IO console);
	
	@Override
	public abstract String getNome();
	
}
