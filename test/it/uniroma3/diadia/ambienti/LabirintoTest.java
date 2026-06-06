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
		this.labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.getLabirinto();
		this.labirinto2 = Labirinto.newBuilder()
				.addStanzaIniziale("N11")
				.addStanzaVincente("Biblioteca")
				.getLabirinto();
		this.stanzaIniziale2 = this.labirinto2.getStanzaIniziale();
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
