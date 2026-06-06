package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;

class FabbricaDiComandiRiflessivaTest {

	private FabbricaDiComandiRiflessiva fabbrica;
	private IOConsole console;
	private Comando comando;

	@BeforeEach
	void setUp() {
		Scanner scannerTest = new Scanner("");
		this.fabbrica = new FabbricaDiComandiRiflessiva();
		this.console = new IOConsole(scannerTest);
	}

	@Test
	void testComandoVai() throws Exception {
		this.comando = this.fabbrica.costruisciComando("vai sud", console);
		assertEquals(new ComandoVai(null).getClass(), comando.getClass());
	}

	@Test
	void testComandoPrendi() throws Exception {
		this.comando = this.fabbrica.costruisciComando("prendi osso", console);
		assertEquals(new ComandoPrendi(null).getClass(), comando.getClass());
	}

	@Test
	void testComandoPosa() throws Exception {
		this.comando = this.fabbrica.costruisciComando("posa osso", console);
		assertEquals(new ComandoPosa(null).getClass(), comando.getClass());
	}

	@Test
	void testComandoAiuto() throws Exception {
		this.comando = this.fabbrica.costruisciComando("aiuto", console);
		assertEquals(new ComandoAiuto().getClass(), comando.getClass());
	}

	@Test
	void testComandoFine() throws Exception {
		this.comando = this.fabbrica.costruisciComando("fine", console);
		assertEquals(new ComandoFine().getClass(), comando.getClass());
	}

	@Test
	void testComandoGuarda() throws Exception {
		this.comando = this.fabbrica.costruisciComando("guarda", console);
		assertEquals(new ComandoGuarda().getClass(), comando.getClass());
	}

	@Test
	void testComandoNonValido() throws Exception {
		this.comando = this.fabbrica.costruisciComando("NonValido", console);
		assertEquals(new ComandoNonValido().getClass(), comando.getClass());
	}

	@Test
	void testComandoNonValido2() throws Exception {
		this.comando = this.fabbrica.costruisciComando("ABC", console);
		assertEquals(new ComandoNonValido().getClass(), comando.getClass());
	}
}
