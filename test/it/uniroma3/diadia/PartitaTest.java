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
	private Stanza stanzaVincente;
	
	
	@BeforeEach
	void setUp(){
		this.partitaIniziata = new Partita();
		this.partitaNuova = new Partita();
		this.partitaVinta = new Partita();
		this.partitaPersa = new Partita();
		this.stanzaCorrente = new Stanza("N11");
		this.stanzaVincente = new Stanza("Biblioteca");
		this.giocatoreW = new Giocatore();
		this.giocatoreL = new Giocatore();
		this.partitaIniziata.setStanzaCorrente(stanzaCorrente);
		this.giocatoreL.setCfu(0);
		this.partitaPersa.setGiocatore(giocatoreL);
		this.partitaVinta.setFinita();
		this.partitaPersa.setFinita();
		this.partitaVinta.setGiocatore(giocatoreW);
		this.partitaVinta.setStanzaCorrente(this.stanzaVincente);
	}

	@Test
	public void testVinta_PartitaNuova_Falso() {
		assertFalse(this.partitaNuova.vinta());
	}
	@Test
	public void testVinta_PartitaIniziata_Falso() {
		assertFalse(this.partitaIniziata.vinta());
	}
	@Test
	public void testVinta_PartitaVinta_Vero() {
		assertEquals("Biblioteca", this.partitaVinta.getStanzaCorrente().getNome());
		//da modificare quando potremo ottenere l'indirizzo della stanza vincente di Labirinto
	}
	@Test
	public void testVinta_PartitaPersa_Falso() {
		assertFalse(this.partitaPersa.vinta());
	}
	@Test
	public void testFinita_PartitaNuova_Falso() {
		assertFalse(this.partitaNuova.isFinita());
	}
	@Test
	public void testFinita_PartitaVints_Vero() {
		assertTrue(this.partitaVinta.isFinita());
	}
	@Test
	public void testFinita_PartitaPersa_Vero() {
		assertTrue(this.partitaPersa.isFinita());
	}
	@Test
	public void testGetStanzaCorrente_PartitsNuova() {
		assertEquals("Atrio", this.partitaNuova.getStanzaCorrente().getNome());
	}
	@Test
	public void testGetStanzaCorrente_PartitaIniziata() {
		assertEquals(this.stanzaCorrente, this.partitaIniziata.getStanzaCorrente());
	}
	@Test
	public void testGetStanzaCorrente_PartitaVinta() {
		assertEquals("Biblioteca", this.partitaVinta.getStanzaCorrente().getNome());
	}
	@Test
	public void testGetGiocatore_PartitaNuova() {
		assertNotNull(this.partitaNuova.getGiocatore());
	}
	@Test
	public void testGetGiocatore_PartitaVinta() {
		assertEquals(this.giocatoreW, this.partitaVinta.getGiocatore());
	}
	@Test
	public void testGetGiocatore_PartitaPersa() {
		assertEquals(this.giocatoreL, this.partitaPersa.getGiocatore());
	}
}
