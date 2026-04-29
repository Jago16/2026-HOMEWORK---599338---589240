package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {

	private Stanza generica;
	private Stanza bloccata;
	private Stanza conChiave;
	private Stanza stanzaAd;

	@BeforeEach
	void setUp() {
		this.generica = new Stanza("atrio");
		this.bloccata = new StanzaBloccata("n11", "sud", "chiave");
		this.conChiave = new StanzaBloccata("n12", "nord", "chiave");
		Attrezzo chiave = new Attrezzo("chiave", 1);
		this.conChiave.addAttrezzo(chiave);
		this.stanzaAd = new Stanza("Ad");
		this.bloccata.impostaStanzaAdiacente("sud", this.stanzaAd);
		this.conChiave.impostaStanzaAdiacente("nord", this.stanzaAd);
		this.generica.impostaStanzaAdiacente("est", this.stanzaAd);
		
	}

	@Test
	void testGetStanzaAdiacenteBloccata() {
		assertSame(this.bloccata, this.bloccata.getStanzaAdiacente("sud"));
	}
	@Test
	void testGetStanzaAdiacenteConChiave() {
		assertSame(stanzaAd, this.conChiave.getStanzaAdiacente("nord"));
	}
	@Test
	void testGetStanzaAdiacenteGenerica() {
		assertSame(this.stanzaAd, this.generica.getStanzaAdiacente("est"));
	}
	
	@Test
	void testGetDescrizioneBloccata() {
		assertEquals("La direzione " + "sud" + "sembra bloccata...\n" + this.bloccata.toString(), 
				this.bloccata.getDescrizione());
		
	}
	
	@Test
	void testGetDescrizioneConChiave() {
		assertEquals(this.conChiave.toString(), this.conChiave.getDescrizione());
		
	}
	
	@Test
	void testGetDescrizioneGenerica() {
		assertEquals(this.generica.toString(), this.generica.getDescrizione());
		
	}

}
