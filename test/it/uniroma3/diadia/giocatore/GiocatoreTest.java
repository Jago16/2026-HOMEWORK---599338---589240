package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class GiocatoreTest {
	
	private Giocatore giocantoreNuovo;
	private Giocatore giocatoreCambiato;
	private Attrezzo attrezzo;
	
	
	
	@BeforeEach
	void setUp() {
		this.giocantoreNuovo = new Giocatore();
		this.giocatoreCambiato =new Giocatore();
		this.attrezzo = new Attrezzo("Attrezzo1", 1);
		this.giocatoreCambiato.setCfu(5);
		this.giocatoreCambiato.getBorsa().addAttrezzo(attrezzo);
	}

	@Test
	public void testGiocatoreNuovoHasAttrezzo_Negativo() {
		assertFalse(this.giocantoreNuovo.getBorsa().hasAttrezzo("Attrezzo1"));
	}
	@Test
	public void testGiocatoreNuovoCFU() {
		assertEquals(20, this.giocantoreNuovo.getCfu());
	}
	@Test
	public void testGiocatoreCambiatoCFU() {
		assertEquals(5, this.giocatoreCambiato.getCfu());
	}
	@Test
	public void testGiocatoreCambiatoAttrezzo() {
		assertTrue(this.giocatoreCambiato.getBorsa().hasAttrezzo("Attrezzo1"));
	}

}
