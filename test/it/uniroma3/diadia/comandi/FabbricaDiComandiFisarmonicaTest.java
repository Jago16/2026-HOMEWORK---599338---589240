package it.uniroma3.diadia.comandi;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FabbricaDiComandiFisarmonicaTest {
	
	private Comando comando;
	private FabbricaDiComandiFisarmonica fabbrica;

	@BeforeEach
	
	void setup() {
		this.fabbrica = new FabbricaDiComandiFisarmonica();
	}
	
	@Test
	void testComandoVai() {
		this.comando = this.fabbrica.costruisciComando("vai sud");
		assertEquals("sud", this.comando.getParametro());
		assertEquals("vai", this.comando.getNome());
	}
	
	@Test
	void testComandoPrendi() {
	this.comando = this.fabbrica.costruisciComando("prendi osso");
	assertEquals("osso", this.comando.getParametro());
	assertEquals("prendi", this.comando.getNome());
	}
	
	@Test
	void testComandoPosa() {
	this.comando = this.fabbrica.costruisciComando("posa osso");
	assertEquals("osso", this.comando.getParametro());
	assertEquals("posa", this.comando.getNome());
	}
	
	@Test
	void testComandoAiuto() {
	this.comando = this.fabbrica.costruisciComando("aiuto");
	assertEquals(null, this.comando.getParametro());
	assertEquals("aiuto", this.comando.getNome());
	}
	
	@Test
	void testComandoFine() {
	this.comando = this.fabbrica.costruisciComando("fine");
	assertEquals(null, this.comando.getParametro());
	assertEquals("fine", this.comando.getNome());
	}
	
	@Test
	void testComandoGuarda() {
	this.comando = this.fabbrica.costruisciComando("guarda");
	assertEquals(null, this.comando.getParametro());
	assertEquals("guarda", this.comando.getNome());
	}
	
	@Test
	void testComandoNonValido() {
	this.comando = this.fabbrica.costruisciComando("NonValido");
	assertEquals(null, this.comando.getParametro());
	assertEquals("comandoNonValido", this.comando.getNome());
	}
	
	@Test
	void testComandoNonValido2() {
	this.comando = this.fabbrica.costruisciComando("ABC");
	assertEquals(null, this.comando.getParametro());
	assertEquals("comandoNonValido", this.comando.getNome());
	}
}
