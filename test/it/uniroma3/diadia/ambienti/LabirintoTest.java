package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {
	private Labirinto labirinto;
	private Labirinto labirinto2;
	private Stanza stanzaIniziale2;
	
	@BeforeEach
	void setUp(){
		this.labirinto = new Labirinto();
		this.labirinto2 = new Labirinto();
		this.stanzaIniziale2 = new Stanza("N11");
		this.labirinto2.setStanzaIniziale(stanzaIniziale2);
	}

	@Test
	public void testStanzaInizialeCorretta_Lab() {
		assertEquals("Atrio", labirinto.getStanzaIniziale().getNome());
	}
	@Test
	public void testStanzaInizialeCorretta_Lab2() {
		assertEquals(stanzaIniziale2, labirinto2.getStanzaIniziale());
	}
	@Test
	public void testStanzaInizialeErrata_Lab() {
		assertNotEquals(stanzaIniziale2,labirinto.getStanzaIniziale());
	}
	@Test
	public void testStanzaVincente_Lab() {
		assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome());
	}
	@Test
	public void testStanzaVincente_Lab2() {
		assertEquals("Biblioteca", labirinto2.getStanzaVincente().getNome());
	}
	@Test
	public void testStanzaVincente_Lab_Errato() {
		assertNotEquals("N11", labirinto.getStanzaVincente().getNome());
	}
}
