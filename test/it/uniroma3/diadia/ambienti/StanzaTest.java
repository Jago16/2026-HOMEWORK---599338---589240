package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {

	private Stanza vuota;
	private Stanza stanzaConTreAttrezzi;
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private Attrezzo attrezzo3;
	private Stanza stanzaConUnAttrezzo;
	private Stanza piena;
	
	@BeforeEach
	void setUp(){
		this.vuota = new Stanza("Vuota");
		this.stanzaConTreAttrezzi = new Stanza("stanzaConTreAttrezzi");
		this.stanzaConUnAttrezzo = new Stanza("StanzaConAttrezzi");
		this.attrezzo1 = new Attrezzo("Attrezzo1", 1);
		this.attrezzo2 = new Attrezzo("Attrezzo2", 2);
		this.attrezzo3 = new Attrezzo("Attrezzo3", 3);
		this.stanzaConTreAttrezzi.addAttrezzo(attrezzo1);
		this.stanzaConTreAttrezzi.addAttrezzo(attrezzo2);
		this.stanzaConTreAttrezzi.addAttrezzo(attrezzo3);
		this.stanzaConUnAttrezzo.addAttrezzo(attrezzo1);
		this.piena = new Stanza("Piena");
		for(int i = 0; i < 10; i++) {
			Attrezzo attrezzo = new Attrezzo("Attrezzo " + i, i);
			this.piena.addAttrezzo(attrezzo);
		}
	}

	@Test
	public void testGetAttrezzo_StanzaVuota() {
		assertNull(this.vuota.getAttrezzo("Attrezzo1"));
	}
	
	@Test
	public void testGetAttrezzo_StanzaConTreAttrezzi() {
		assertNotNull(this.stanzaConTreAttrezzi.getAttrezzo("Attrezzo1"));
		assertNotNull(this.stanzaConTreAttrezzi.getAttrezzo("Attrezzo2"));
		assertNotNull(this.stanzaConTreAttrezzi.getAttrezzo("Attrezzo3"));
		assertNull(this.stanzaConTreAttrezzi.getAttrezzo("Attrezzo4"));
	}
	@Test
	public void testGetAttrezzo_StanzaConTreAttrezzi_Assente() {
		assertNull(this.stanzaConTreAttrezzi.getAttrezzo("Attrezzo4"));
	}
	@Test
	public void testGetAttrezzo_StanzaPiena_Assente() {
		assertNull(piena.getAttrezzo("AttrezzoMancante"));
	}
	@Test
	public void testRemoveAttrezzo_StanzaVuota() {
		assertFalse(this.vuota.removeAttrezzo(attrezzo3));
	}
	@Test
	public void testRemoveAttrezzo_StanzaConTreAttrezzi_Presente() {
		assertTrue(this.stanzaConTreAttrezzi.removeAttrezzo(attrezzo2));
	}
	@Test
	public void testRemoveAttrezzo_StanzaConUnAttrezzo_PresenteAssente() {
		assertTrue(this.stanzaConUnAttrezzo.removeAttrezzo(attrezzo1));
		assertFalse(this.stanzaConUnAttrezzo.removeAttrezzo(attrezzo3));
	}
	@Test
	public void testHasAttrezzo_StanzaVuota() {
		assertFalse(this.vuota.hasAttrezzo("Attrezzo1"));
	}
	@Test
	public void testHasAttrezzo_StanzaConTreAttrezzi_Presente() {
		assertTrue(this.stanzaConTreAttrezzi.hasAttrezzo("Attrezzo3"));
	}
	@Test
	public void testHasAttrezzo_StanzaConUnAttrezzo_Assente() {
		assertFalse(this.stanzaConUnAttrezzo.hasAttrezzo("Attrezzo2"));
	}
	@Test
	public void testHasAttrezzo_StanzaPiena_Assente() {
		assertFalse(this.piena.hasAttrezzo("AttrezzoNonPresente"));
	}
	
	@Test
	public void testGetStanzaAdiacente_StanzaVuota() {
		assertNull(this.vuota.getStanzaAdiacente("sud"));
	}
	@Test
	public void testGetStanzaAdiacenteConDirezione_Presente() {
		Stanza stanzaConUnaDirezione = new Stanza("stanzaConUnaDirezione");
		stanzaConUnaDirezione.impostaStanzaAdiacente("sud", vuota);
		assertEquals(vuota, stanzaConUnaDirezione.getStanzaAdiacente("sud")); 
	}
	@Test
	public void testGetStanzaAdiacenteConDirezione_Assente() {
		Stanza stanzaConUnaDirezione = new Stanza("stanzaConUnaDirezione");
		stanzaConUnaDirezione.impostaStanzaAdiacente("sud", vuota);
		assertNotEquals(vuota, stanzaConUnaDirezione.getStanzaAdiacente("nord"));
	}
	@Test
	public void testImpostaStanzaAdiacente_StanzaSenzaStanzaAdiacenti() {
		Stanza stanzaSenzaAdiacenti = new Stanza("stanzaSenzaAdiacenti");
		assertNull(stanzaSenzaAdiacenti.getStanzaAdiacente("sud"));
	}
	@Test
	public void testImpostaStanzaAdiacente_Presente() {
		Stanza stanzaConAdiacenteSud = new Stanza("stanzaConAdiacenteSud");
		stanzaConAdiacenteSud.impostaStanzaAdiacente("sud", vuota);
		assertEquals(vuota, stanzaConAdiacenteSud.getStanzaAdiacente("sud"));
	}
	@Test
	public void testImpostaStanzaAdiacente_Assente() {
		Stanza stanzaConAdiacenteSud = new Stanza("stanzaConAdiacenteSud");
		stanzaConAdiacenteSud.impostaStanzaAdiacente("sud", vuota);
		assertNotEquals(vuota, stanzaConAdiacenteSud.getStanzaAdiacente("nord"));
	}
	
	

}
