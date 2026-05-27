package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder extends Labirinto{

	private Stanza ultimaStanzaAggiornata;
	private Map<String, Stanza> mappaStanze;

	public LabirintoBuilder() {
		super(true);
		this.mappaStanze = new HashMap<>();
	}

	public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
		Stanza stanzaIniziale = new Stanza(nomeStanza);
		super.setStanzaIniziale(stanzaIniziale);
		this.mappaStanze.put(nomeStanza, stanzaIniziale);
		this.ultimaStanzaAggiornata = stanzaIniziale;
		return this;
	}

	public LabirintoBuilder addStanzaVincente(String nomeStanza) {
		Stanza stanzaVincente = new Stanza(nomeStanza);
		super.setStanzaVincente(stanzaVincente);
		this.mappaStanze.put(nomeStanza, stanzaVincente);
		this.ultimaStanzaAggiornata = stanzaVincente;
		return this;
	}

	public LabirintoBuilder addStanza(String nomeStanza) {
		Stanza nuovaStanza = new Stanza(nomeStanza);
		this.mappaStanze.put(nomeStanza, nuovaStanza);
		this.ultimaStanzaAggiornata = nuovaStanza;
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
		if(this.ultimaStanzaAggiornata != null) {
			this.ultimaStanzaAggiornata.addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
		}
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String nomeStanzaCorrente, String nomeStanzaAdiacente, String direzione) {
		Stanza stanzaCorrente = this.mappaStanze.get(nomeStanzaCorrente);
		Stanza stanzaAdiacente = this.mappaStanze.get(nomeStanzaAdiacente);
		if(stanzaCorrente != null && stanzaAdiacente != null)
			stanzaCorrente.impostaStanzaAdiacente(direzione, stanzaAdiacente);
		return this;
	}
	
	public LabirintoBuilder getLabirinto() {
		return this;
	}
	
	public Map<String, Stanza> getListaStanze(){
		return this.mappaStanze;
	}
	
	public LabirintoBuilder addStanzaBloccata(String nomeStanza, String direzione, String nomeOggettoSbloccante) {
		StanzaBloccata nuovaStanzaBloccata = new StanzaBloccata(nomeStanza, direzione, nomeOggettoSbloccante);
		this.mappaStanze.put(nomeStanza, nuovaStanzaBloccata);
		this.ultimaStanzaAggiornata = nuovaStanzaBloccata;
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nomeStanzaMagica, int sogliaMagica) {
		StanzaMagica nuovaStanzaMagica = new StanzaMagica(nomeStanzaMagica, sogliaMagica);
		this.mappaStanze.put(nomeStanzaMagica, nuovaStanzaMagica);
		this.ultimaStanzaAggiornata = nuovaStanzaMagica;
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String nomeStanzaBuia, String nomeOggettoLuminoso) {
		StanzaBuia nuovaStanzaBuia = new StanzaBuia(nomeStanzaBuia, nomeOggettoLuminoso);
		this.mappaStanze.put(nomeStanzaBuia, nuovaStanzaBuia);
		this.ultimaStanzaAggiornata = nuovaStanzaBuia;
		return this;
	}
}
