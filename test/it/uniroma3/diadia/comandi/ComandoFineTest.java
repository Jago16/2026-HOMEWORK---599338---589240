package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;


class ComandoFineTest {

	private Labirinto monolocale;
	private Labirinto bilocale;
	private IO console;
	private Partita partita;
	private FabbricaDiComandiFisarmonica factory;
	
	@BeforeEach
	void setUp(){
		Scanner scannerTest = new Scanner("");
		this.console = new IOConsole(scannerTest);
		this.monolocale = Labirinto.newBuilder().addStanzaIniziale("Atrio").getLabirinto();
		this.bilocale = Labirinto.newBuilder().addStanzaIniziale("Inizio").addStanza("Fine").
				addAdiacenza("Inizio", "Fine", Direzione.NORD).getLabirinto();
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
