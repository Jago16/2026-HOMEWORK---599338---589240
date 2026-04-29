package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {

	private Stanza generica;
	private Stanza buia;
	private Stanza buiaConOggetto;


	
	@BeforeEach
	void setUp() {
		this.generica = new Stanza("atrio");
		this.buia = new StanzaBuia("n11", "lume");
		this.buiaConOggetto = new StanzaBuia("n12", "lume");
		Attrezzo lume = new Attrezzo("lume", 1);
		this.buiaConOggetto.addAttrezzo(lume);
	}

	@Test
	void testGetDescrizioneBuia() {
		assertEquals("Qui c'e' un buio pesto", this.buia.getDescrizione());
	}

	@Test
	void testGetDescrizioneBuiaConOggetto() {
		assertEquals(this.buiaConOggetto.toString(), this.buiaConOggetto.getDescrizione());
	}
	
	@Test
	void testGetDescrizioneGenerica() {
		assertEquals(this.generica.toString(), this.generica.getDescrizione());
	}
}
