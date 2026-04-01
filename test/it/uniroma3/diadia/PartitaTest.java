package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

class PartitaTest {
	private Partita partitaNuova;
	private Partita partitaIniziata;
	private Partita partitaVinta;
	private Partita partitaPersa;
	private Stanza stanzaCorrente;
	private Giocatore giocatoreW;
	private Giocatore giocatoreL;
	
	
	@BeforeEach
	void setUp(){
		this.partitaIniziata = new Partita();
		this.partitaNuova = new Partita();
		this.partitaVinta = new Partita();
		this.partitaPersa = new Partita();
		this.stanzaCorrente = new Stanza("N11");
		this.giocatoreW = new Giocatore();
		this.giocatoreL = new Giocatore();
		this.partitaIniziata.setStanzaCorrente(stanzaCorrente);
		this.giocatoreL.setCfu(0);
		this.partitaPersa.setGiocatore(giocatoreL);
		this.partitaVinta.setStanzaCorrente(this.partitaVinta.getLabirinto.);
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
