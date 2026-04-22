package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IO;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;

class FabbricaDiComandiFisarmonicaTest {
	
	private Comando comando;
	private FabbricaDiComandiFisarmonica fabbrica;
	private IO console;

	@BeforeEach
	
	void setup() {
		this.fabbrica = new FabbricaDiComandiFisarmonica();
		this.console = new IOConsole();
	}
	
	@Test
	void testComandoVai() {
		this.comando = this.fabbrica.costruisciComando("vai sud", console);
		assertEquals("sud", this.comando.getParametro());
		assertEquals("vai", this.comando.getNome());
	}
	
	@Test
	void testComandoPrendi() {
	this.comando = this.fabbrica.costruisciComando("prendi osso", console);
	assertEquals("osso", this.comando.getParametro());
	assertEquals("prendi", this.comando.getNome());
	}
	
	@Test
	void testComandoPosa() {
	this.comando = this.fabbrica.costruisciComando("posa osso", console);
	assertEquals("osso", this.comando.getParametro());
	assertEquals("posa", this.comando.getNome());
	}
	
	@Test
	void testComandoAiuto() {
	this.comando = this.fabbrica.costruisciComando("aiuto", console);
	assertEquals(null, this.comando.getParametro());
	assertEquals("aiuto", this.comando.getNome());
	}
	
	@Test
	void testComandoFine() {
	this.comando = this.fabbrica.costruisciComando("fine", console);
	assertEquals(null, this.comando.getParametro());
	assertEquals("fine", this.comando.getNome());
	}
	
	@Test
	void testComandoGuarda() {
	this.comando = this.fabbrica.costruisciComando("guarda", console);
	assertEquals(null, this.comando.getParametro());
	assertEquals("guarda", this.comando.getNome());
	}
	
	@Test
	void testComandoNonValido() {
	this.comando = this.fabbrica.costruisciComando("NonValido", console);
	assertEquals(null, this.comando.getParametro());
	assertEquals("comandoNonValido", this.comando.getNome());
	}
	
	@Test
	void testComandoNonValido2() {
	this.comando = this.fabbrica.costruisciComando("ABC", console);
	assertEquals(null, this.comando.getParametro());
	assertEquals("comandoNonValido", this.comando.getNome());
	}
}
