package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {
	
	Borsa borsaPiena;
	Borsa borsaVuota;
	Borsa borsaConUnAttrezzo;
	Attrezzo martello;
	Attrezzo attrezzoNumerato;

	@BeforeEach
	void setUp(){
		this.borsaConUnAttrezzo = new Borsa();
		this.borsaPiena= new Borsa();
		this.borsaVuota = new Borsa();
		this.martello = new Attrezzo("Martello", 5);
		for(int i = 0; i < 10; i++) {
			this.attrezzoNumerato = new Attrezzo("Attrezzo " + i, 1);
			this.borsaPiena.addAttrezzo(attrezzoNumerato);
		}
		this.borsaConUnAttrezzo.addAttrezzo(this.martello);
	}

	@Test
	void testAddAttrezzo_BorsaVuota_NonRiuscito() {
		Attrezzo attrezzoTroppoPesante = new Attrezzo("AttrezzoTroppoPesante", 11);
		assertFalse(this.borsaVuota.addAttrezzo(attrezzoTroppoPesante));
	}
	@Test
	void testAddAttrezzo_BorsaVuota_Riuscito() {
		Attrezzo attrezzoNonTroppoPesante = new Attrezzo("AttrezzoNonTroppoPesante", 1);
		assertTrue(this.borsaVuota.addAttrezzo(attrezzoNonTroppoPesante));
	}
	@Test
	void testAddAttrezzo_BorsaPiena(){
		Attrezzo attrezzoDiPeso1 = new Attrezzo("AttrezzoDiPeso1", 1);
		assertFalse(this.borsaPiena.addAttrezzo(attrezzoDiPeso1));
	}
	@Test
	void testGetAttrezzo_BorsaVuota() {
		assertNull(this.borsaVuota.getAttrezzo("Martello"));
	}
	@Test
	void testGetAttrezzo_BorsaConUnAttrezzo_Presente() {
		assertEquals(this.martello, this.borsaConUnAttrezzo.getAttrezzo("Martello"));
	}
	@Test
	void testGetAttrezzo_BorsaConUnAttrezzo_Assente() {
		assertNull(this.borsaConUnAttrezzo.getAttrezzo("Spada"));
	}
	@Test
	void testGetAttrezzo_BorsaPiena_Assente() {
		assertNull(this.borsaPiena.getAttrezzo("Martello"));
	}
	@Test
	void testGetAttrezzo_BorsaPiena_Presente() {
		assertEquals(this.attrezzoNumerato, this.borsaPiena.getAttrezzo("Attrezzo " + 9));
	}
	@Test
	void testGetPeso_BorsaVuota() {
		assertEquals(0, this.borsaVuota.getPeso());
	}
	@Test
	void testGetPeso_BorsaPiena() {
		assertEquals(10, this.borsaPiena.getPeso());
	}
	@Test
	void testGetPeso_BorsaConUnOggetto() {
		assertEquals(5, this.borsaConUnAttrezzo.getPeso());
	}
	@Test
	void  testIsEmpty_BorsaVuota() {
		assertTrue(this.borsaVuota.isEmpty());
	}
	@Test
	void testIsEmpty_BorsaConUnAttrezzo() {
		assertFalse(this.borsaConUnAttrezzo.isEmpty());
	}
	@Test
	void testIsEmpty_BorsaPiena() {
		assertFalse(this.borsaPiena.isEmpty());
	}
	@Test
	void testHasAttrezzo_BorsaVuota() {
		assertFalse(this.borsaVuota.hasAttrezzo("Martello"));
	}
	@Test
	void testHasAttrezzo_BorsaConUnAttrezzo_Presente() {
		assertTrue(this.borsaConUnAttrezzo.hasAttrezzo("Martello"));
	}
	@Test
	void testHasAttrezzo_BorsaConUnAttrezzo_Assente() {
		assertFalse(this.borsaConUnAttrezzo.hasAttrezzo("Spada"));
	}
	@Test
	void testRemoveAttrezzo_BorsaVuota() {
		assertNull(this.borsaVuota.removeAttrezzo("Martello")); 
	}
	@Test
	void testRemoveAttrezzo_BorsaConUnOggetto_Presente() {
		assertEquals(this.martello, this.borsaConUnAttrezzo.removeAttrezzo("Martello"));
	}
	@Test
	void testRemoveAttrezzo_BorsaConUnOggetto_Assente() {
		assertNull(this.borsaConUnAttrezzo.removeAttrezzo("Spada"));
	}

}
