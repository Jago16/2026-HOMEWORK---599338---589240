package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;


class ComandoPrendiTest {

	private Labirinto monolocale;
	private Labirinto bilocale;
	private IO console;
	private Partita partita;
	private FabbricaDiComandiFisarmonica factory;
	
	@BeforeEach
	void setUp(){
		this.console = new IOConsole();
		this.monolocale = new LabirintoBuilder().addStanzaIniziale("Atrio").addAttrezzo("osso", 1);
		this.bilocale = new LabirintoBuilder().addStanzaIniziale("Inizio").addAttrezzo("osso", 1).addStanza("Fine").
				addAdiacenza("Inizio", "Fine", "nord");
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
