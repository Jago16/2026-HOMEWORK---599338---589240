package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

class ComandoFineTest {

	private Labirinto monolocale;
	private Labirinto bilocale;
	private IO console;
	private Partita partita;
	private FabbricaDiComandiFisarmonica factory;
	
	@BeforeEach
	void setUp(){
		this.console = new IOConsole();
		this.monolocale = new LabirintoBuilder().addStanzaIniziale("Atrio");
		this.bilocale = new LabirintoBuilder().addStanzaIniziale("Inizio").addStanza("Fine").
				addAdiacenza("Inizio", "Fine", "nord");
		this.factory = new FabbricaDiComandiFisarmonica();
	}

	@Test
	void testFineMonolocale() {
		this.partita = new Partita(this.monolocale);
		Comando comandoFine;
		comandoFine = this.factory.costruisciComando("fine", this.console);
		comandoFine.esegui(this.partita, this.console);
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	void testFineBilocale() {
		this.partita = new Partita(this.bilocale);
		Comando comandoFine;
		comandoFine = this.factory.costruisciComando("fine", this.console);
		comandoFine.esegui(this.partita, this.console);
		assertTrue(this.partita.isFinita());
	}

}
