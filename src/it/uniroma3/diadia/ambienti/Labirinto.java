package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	public Labirinto(String nomeFile) {
		try {
			CaricatoreLabirinto c = new CaricatoreLabirinto(nomeFile);
			c.carica();
			this.stanzaIniziale = c.getStanzaIniziale();
			this.stanzaVincente = c.getStanzaVincente();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private Labirinto() {
	}
	

//	private void init() {
//
//		/* crea gli attrezzi */
//		Attrezzo lanterna = new Attrezzo("lanterna",3);
//		Attrezzo osso = new Attrezzo("osso",1);
//
//		/* crea stanze del labirinto */
//		Stanza atrio = new Stanza("Atrio");
//		Stanza aulaN11 = new Stanza("Aula N11");
//		Stanza aulaN10 = new Stanza("Aula N10");
//		Stanza laboratorio = new Stanza("Laboratorio Campus");
//		Stanza biblioteca = new Stanza("Biblioteca");
//
//		/* collega le stanze */
//		atrio.impostaStanzaAdiacente(Direzione.NORD, biblioteca);
//		atrio.impostaStanzaAdiacente(Direzione.EST, aulaN11);
//		atrio.impostaStanzaAdiacente(Direzione.SUD, aulaN10);
//		atrio.impostaStanzaAdiacente(Direzione.OVEST, laboratorio);
//		aulaN11.impostaStanzaAdiacente(Direzione.EST, laboratorio);
//		aulaN11.impostaStanzaAdiacente(Direzione.OVEST, atrio);
//		aulaN10.impostaStanzaAdiacente(Direzione.NORD, atrio);
//		aulaN10.impostaStanzaAdiacente(Direzione.EST, aulaN11);
//		aulaN10.impostaStanzaAdiacente(Direzione.OVEST, laboratorio);
//		laboratorio.impostaStanzaAdiacente(Direzione.EST, atrio);
//		laboratorio.impostaStanzaAdiacente(Direzione.OVEST, aulaN11);
//		biblioteca.impostaStanzaAdiacente(Direzione.SUD, atrio);
//
//		/* pone gli attrezzi nelle stanze */
//		aulaN10.addAttrezzo(lanterna);
//		atrio.addAttrezzo(osso);
//
//		// il gioco comincia nell'atrio
//		stanzaIniziale = atrio;
//		stanzaVincente = biblioteca;
//	}

	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}
	
	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale=stanzaIniziale;
	}

	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}
	
	public static class LabirintoBuilder{

		private Labirinto labirinto;
		private Stanza ultimaStanzaAggiornata;
		private Map<String, Stanza> mappaStanze;

		public LabirintoBuilder() {
			this.labirinto = new Labirinto();
			this.mappaStanze = new HashMap<>();
		}

		public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
			Stanza stanzaIniziale = new Stanza(nomeStanza);
			this.labirinto.setStanzaIniziale(stanzaIniziale);
			this.mappaStanze.put(nomeStanza, stanzaIniziale);
			this.ultimaStanzaAggiornata = stanzaIniziale;
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String nomeStanza) {
			Stanza stanzaVincente = new Stanza(nomeStanza);
			this.labirinto.setStanzaVincente(stanzaVincente);
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
		
		public LabirintoBuilder addAttrezzoAStanza(String nomeAttrezzo, int peso, String nomeStanza) {
			if(this.mappaStanze.containsKey(nomeStanza))
				this.mappaStanze.get(nomeStanza).addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
			return this;
		}
		
		public LabirintoBuilder addAdiacenza(String nomeStanzaCorrente, String nomeStanzaAdiacente, Direzione direzione) {
			Stanza stanzaCorrente = this.mappaStanze.get(nomeStanzaCorrente);
			Stanza stanzaAdiacente = this.mappaStanze.get(nomeStanzaAdiacente);
			if(stanzaCorrente != null && stanzaAdiacente != null)
				stanzaCorrente.impostaStanzaAdiacente(direzione, stanzaAdiacente);
			return this;
		}
		
		public Labirinto getLabirinto() {
			return this.labirinto;
		}
		
		public Map<String, Stanza> getListaStanze(){
			return this.mappaStanze;
		}
		
		public LabirintoBuilder addStanzaBloccata(String nomeStanza, String nomeOggettoSbloccante,  Direzione direzione) {
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
		
		@Override
		public boolean equals(Object o) {
			if(o==null)
				return false;
			LabirintoBuilder that = (LabirintoBuilder) o;
			return this.getListaStanze().equals(that.getListaStanze());
		}
		
		@Override
		public int hashCode() {
			return this.getListaStanze().hashCode();
		}
	}
}
