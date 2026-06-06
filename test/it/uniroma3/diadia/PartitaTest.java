package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

class PartitaTest {
	private Partita partitaVuota;
	private Partita partitaIniziata;
	private Partita partitaVinta;
	private Partita partitaPersa;
	private Giocatore giocatoreW;
	private Giocatore giocatoreL;
	
	
	
	@BeforeEach
	void setUp(){
		
		Labirinto labirintoPartitaIniziata = Labirinto.newBuilder().addStanzaIniziale("StanzaCorrente")
				.addStanzaVincente("StanzaVincente").getLabirinto();
		Labirinto labirintoPartitaVinta = Labirinto.newBuilder().addStanzaIniziale("StanzaCorrente")
				.addStanzaVincente("StanzaCorrente").getLabirinto();
		Labirinto labirintoPartitaPersa = Labirinto.newBuilder().addStanzaIniziale("StanzaCorrente")
				.addStanzaVincente("StanzaVincente").getLabirinto();
		this.partitaIniziata = new Partita(labirintoPartitaIniziata);
		this.partitaVuota = new Partita(Labirinto.newBuilder().getLabirinto()); 
		this.partitaVinta = new Partita(labirintoPartitaVinta);
		this.partitaPersa = new Partita(labirintoPartitaPersa);
		this.giocatoreW = new Giocatore();
		this.giocatoreL = new Giocatore();
		this.giocatoreL.setCfu(0);
		this.partitaPersa.setGiocatore(giocatoreL);
		this.partitaVinta.setFinita();
		this.partitaPersa.setFinita();
		this.partitaVinta.setGiocatore(giocatoreW);
		this.partitaVinta.setStanzaCorrente(new Stanza("StanzaVincente"));
	}

	@Test
	public void testVinta_PartitaVuota_Vera() {
		assertTrue(this.partitaVuota.vinta());
	}
	@Test
	public void testVinta_PartitaIniziata_Falso() {
		assertFalse(this.partitaIniziata.vinta());
	}
	@Test
	public void testVinta_PartitaVinta_Vero() {
		assertEquals("StanzaVincente", this.partitaVinta.getStanzaCorrente().getNome());
		//da modificare quando potremo ottenere l'indirizzo della stanza vincente di Labirinto
	}
	@Test
	public void testVinta_PartitaPersa_Falso() {
		assertFalse(this.partitaPersa.vinta());
	}
	@Test
	public void testFinita_PartitaVuota_Vero() {
		assertTrue(this.partitaVuota.isFinita());
	}
	@Test
	public void testFinita_PartitaVinta_Vero() {
		assertTrue(this.partitaVinta.isFinita());
	}
	@Test
	public void testFinita_PartitaPersa_Vero() {
		assertTrue(this.partitaPersa.isFinita());
	}
	@Test
	public void testGetStanzaCorrente_PartitaNuova() {
		assertNull(this.partitaVuota.getStanzaCorrente());
	}
	@Test
	public void testGetStanzaCorrente_PartitaIniziata() {
		assertEquals(new Stanza("StanzaCorrente"), this.partitaIniziata.getStanzaCorrente());
	}
	@Test
	public void testGetStanzaCorrente_PartitaVinta() {
		assertEquals("StanzaVincente", this.partitaVinta.getStanzaCorrente().getNome());
	}
	@Test
	public void testGetGiocatore_PartitaNuova() {
		assertNotNull(this.partitaVuota.getGiocatore());
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
