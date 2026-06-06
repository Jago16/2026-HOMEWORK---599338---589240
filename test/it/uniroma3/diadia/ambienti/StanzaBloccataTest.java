package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

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
		this.bloccata = new StanzaBloccata("n11", Direzione.SUD, "chiave");
		this.conChiave = new StanzaBloccata("n12", Direzione.NORD, "chiave");
		Attrezzo chiave = new Attrezzo("chiave", 1);
		this.conChiave.addAttrezzo(chiave);
		this.stanzaAd = new Stanza("Ad");
		this.bloccata.impostaStanzaAdiacente(Direzione.SUD, this.stanzaAd);
		this.conChiave.impostaStanzaAdiacente(Direzione.NORD, this.stanzaAd);
		this.generica.impostaStanzaAdiacente(Direzione.EST, this.stanzaAd);
		
	}

	@Test
	void testGetStanzaAdiacenteBloccata() {
		assertSame(this.bloccata, this.bloccata.getStanzaAdiacente(Direzione.SUD));
	}
	@Test
	void testGetStanzaAdiacenteConChiave() {
		assertSame(stanzaAd, this.conChiave.getStanzaAdiacente(Direzione.NORD));
	}
	@Test
	void testGetStanzaAdiacenteGenerica() {
		assertSame(this.stanzaAd, this.generica.getStanzaAdiacente(Direzione.EST));
	}
	
	@Test
	void testGetDescrizioneBloccata() {
		assertEquals("La direzione " + "sud " + "sembra bloccata...\n" + this.bloccata.toString(), 
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
