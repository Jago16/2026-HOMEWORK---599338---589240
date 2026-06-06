package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {


	private Labirinto monolocale;
	private Labirinto bilocale;
	private IO console;
	private Partita partita;
	private FabbricaDiComandiFisarmonica factory;
	private Attrezzo torcia;
	
	@BeforeEach
	void setUp(){
		Scanner scannerTest = new Scanner("");
		this.console = new IOConsole(scannerTest);
		this.monolocale = Labirinto.newBuilder().addStanzaIniziale("Atrio").addAttrezzo("osso", 1).getLabirinto();
		this.bilocale = Labirinto.newBuilder().addStanzaIniziale("Inizio").addAttrezzo("osso", 1).addStanza("Fine").
				addAdiacenza("Inizio", "Fine", Direzione.NORD).getLabirinto();
		this.factory = new FabbricaDiComandiFisarmonica();
		this.torcia = new Attrezzo("torcia", 1);
	}

	@Test
	void testMonolocalePosa() {
		this.partita = new Partita(this.monolocale);
		this.partita.getGiocatore().getBorsa().addAttrezzo(this.torcia);
		Comando comandoAiuto;
		comandoAiuto = this.factory.costruisciComando("posa torcia", this.console);
		comandoAiuto.esegui(this.partita, this.console);
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("torcia"));
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("torcia"));
	}
	
	@Test
	void testBilocalePosa() {
		this.partita = new Partita(this.bilocale);
		this.partita.getGiocatore().getBorsa().addAttrezzo(this.torcia);
		Comando comandoAiuto;
		comandoAiuto = this.factory.costruisciComando("posa torcia", this.console);
		comandoAiuto.esegui(this.partita, this.console);
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("torcia"));
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("torcia"));
	}
}
