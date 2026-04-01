package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {

	private Stanza vuota;
	private Stanza piena;
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private Attrezzo attrezzo3;
	private Stanza stanzaConAttrezzi;
	
	@BeforeEach
	void setUp(){
		this.vuota = new Stanza("Vuota");
		this.piena = new Stanza("Piena");
		this.stanzaConAttrezzi = new Stanza("StanzaConAttrezzi");
		this.attrezzo1 = new Attrezzo("Attrezzo1", 1);
		this.attrezzo2 = new Attrezzo("Attrezzo2", 2);
		this.attrezzo3 = new Attrezzo("Attrezzo3", 3);
		this.stanzaConAttrezzi.addAttrezzo(attrezzo1);
		this.stanzaConAttrezzi.addAttrezzo(attrezzo2);
		this.stanzaConAttrezzi.addAttrezzo(attrezzo3);
		this.piena.addAttrezzo(attrezzo1);
	}

	@Test
	public void testGetAttrezzo_StanzaVuota() {
		assertNull(this.vuota.getAttrezzo("Attrezzo1"));
	}
	
	@Test
	public void testGetAttrezzo_StanzaConAttrezzi() {
		assertNotNull(this.stanzaConAttrezzi.getAttrezzo("Attrezzo1"));
		assertNotNull(this.stanzaConAttrezzi.getAttrezzo("Attrezzo2"));
		assertNotNull(this.stanzaConAttrezzi.getAttrezzo("Attrezzo3"));
		assertNull(this.stanzaConAttrezzi.getAttrezzo("Attrezzo4"));
	}
	@Test
	public void testGetAttrezzo_StanzaPiena_Assente() {
		assertNull(this.piena.getAttrezzo("Attrezzo4"));
	}
	@Test
	public void removeAttrezzo_StanzaVuota() {
		assertFalse(this.vuota.removeAttrezzo(attrezzo3));
	}
	@Test
	public void removeAttrezzo_StanzaConAttrezzi() {
		assertTrue(this.stanzaConAttrezzi.removeAttrezzo(attrezzo2));
	}
	@Test
	public void removeAttrezzo_StanzaPiena_PresenteAssente() {
		assertTrue(this.piena.removeAttrezzo(attrezzo1));
		assertFalse(this.piena.removeAttrezzo(attrezzo3));
	}
	
	

}
