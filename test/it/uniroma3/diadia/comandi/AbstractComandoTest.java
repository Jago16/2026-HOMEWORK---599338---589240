package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

class AbstractComandoTest {

	private AbstractComando comando;
	
	private class fakeComando extends AbstractComando{

		@Override
		public void esegui(Partita partita, IO console) {		
		}

		@Override
		public String getNome() {
			return "fake";
		}
		
	}
	
	@BeforeEach
	void setUp(){
		this.comando = new fakeComando();
	}

	@Test
	void testGetParametro() {
		assertNull(comando.getParametro());
	}
	

	@Test
	void testSetParametro() {
		this.comando.setParametro("fake");
		assertEquals("fake", this.comando.getParametro());
	}
	
	@Test
	void testSetParametroConCambio() {
		this.comando.setParametro("fake");
		assertEquals("fake", this.comando.getParametro());
		this.comando.setParametro("fake1");
		assertEquals("fake1", this.comando.getParametro());
	}
	

}
