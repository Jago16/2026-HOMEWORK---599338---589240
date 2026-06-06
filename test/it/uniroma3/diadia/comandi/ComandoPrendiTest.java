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



class ComandoPrendiTest {

	private Labirinto monolocale;
	private Labirinto bilocale;
	private IO console;
	private Partita partita;
	private FabbricaDiComandiFisarmonica factory;
	
	@BeforeEach
	void setUp(){
		Scanner scannerTest = new Scanner("");
		this.console = new IOConsole(scannerTest);
		this.monolocale = Labirinto.newBuilder().addStanzaIniziale("Atrio").addAttrezzo("osso", 1).getLabirinto();
		this.bilocale = Labirinto.newBuilder().addStanzaIniziale("Inizio").addAttrezzo("osso", 1).addStanza("Fine").
				addAdiacenza("Inizio", "Fine", Direzione.NORD).getLabirinto();
		this.factory = new FabbricaDiComandiFisarmonica();
	}

	@Test
	void testMonolocalePrendi() {
		this.partita = new Partita(this.monolocale);
		Comando comandoAiuto;
		comandoAiuto = this.factory.costruisciComando("prendi osso", this.console);
		comandoAiuto.esegui(this.partita, this.console);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("osso"));
	}
	
	@Test
	void testBilocalePrendi() {
		this.partita = new Partita(this.bilocale);
		Comando comandoAiuto;
		comandoAiuto = this.factory.costruisciComando("prendi osso", this.console);
		comandoAiuto.esegui(this.partita, this.console);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("osso"));
	}

}
