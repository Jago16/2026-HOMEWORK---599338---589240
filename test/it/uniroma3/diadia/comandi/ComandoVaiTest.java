package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;


class ComandoVaiTest {

	private Labirinto monolocale;
	private Labirinto bilocale;
	private IO console;
	private Partita partita;
	private FabbricaDiComandiFisarmonica factory;
	
	@BeforeEach
	void setUp() throws Exception {
		this.console = new IOConsole();
		this.monolocale = new LabirintoBuilder().addStanzaIniziale("Atrio");
		this.bilocale = new LabirintoBuilder().addStanzaIniziale("Inizio").addStanza("Fine").
				addAdiacenza("Inizio", "Fine", "nord");
		this.factory = new FabbricaDiComandiFisarmonica();
	}

	@Test
	void testVaiMonolocale() {
		this.partita = new Partita(this.monolocale);
		Comando comandoVai;
		comandoVai = this.factory.costruisciComando("vai nord", this.console);
		comandoVai.esegui(this.partita, this.console);
		assertEquals("Atrio", this.partita.getStanzaCorrente().getNome());
		assertEquals(20, this.partita.getGiocatore().getCfu());
	}
	
	@Test
	void testVaiBilocale() {
		this.partita = new Partita(this.bilocale);
		Comando comandoVai;
		comandoVai = this.factory.costruisciComando("vai nord", this.console);
		comandoVai.esegui(this.partita, this.console);
		assertEquals("Fine", this.partita.getStanzaCorrente().getNome());
		assertEquals(19, this.partita.getGiocatore().getCfu());
	}
}