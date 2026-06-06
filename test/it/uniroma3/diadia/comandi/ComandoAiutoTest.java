package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;


class ComandoAiutoTest {

	private Labirinto monolocale;
	private Labirinto bilocale;
	private IOSimulator console;
	private Partita partita;
	private FabbricaDiComandiFisarmonica factory;
	
	@BeforeEach
	void setUp(){
		List <String> comandi = new ArrayList<>();
		comandi.add("aiuto");
		this.console = new IOSimulator(comandi);
		this.monolocale = Labirinto.newBuilder().addStanzaIniziale("Atrio").getLabirinto();
		this.bilocale =Labirinto.newBuilder().addStanzaIniziale("Inizio").addStanza("Fine").
				addAdiacenza("Inizio", "Fine", Direzione.SUD).getLabirinto();
		this.factory = new FabbricaDiComandiFisarmonica();
	}

	@Test
	void testAiutoMonolocale() {
		this.partita = new Partita(this.monolocale);
		Comando comandoAiuto;
		comandoAiuto = this.factory.costruisciComando("aiuto", this.console);
		comandoAiuto.esegui(this.partita, this.console);
		assertEquals("vai ", this.console.getMessaggio(0));
		assertEquals("aiuto ", this.console.getMessaggio(1));
		assertEquals("prendi ", this.console.getMessaggio(2));
		assertEquals("posa ", this.console.getMessaggio(3));
		assertEquals("fine ", this.console.getMessaggio(4));
		assertEquals("guarda ", this.console.getMessaggio(5));
	}
	
	@Test
	void testAiutoBilocale() {
		this.partita = new Partita(this.bilocale);
		Comando comandoAiuto;
		comandoAiuto = this.factory.costruisciComando("aiuto", this.console);
		comandoAiuto.esegui(this.partita, this.console);
		assertEquals("vai ", this.console.getMessaggio(0));
		assertEquals("aiuto ", this.console.getMessaggio(1));
		assertEquals("prendi ", this.console.getMessaggio(2));
		assertEquals("posa ", this.console.getMessaggio(3));
		assertEquals("fine ", this.console.getMessaggio(4));
		assertEquals("guarda ", this.console.getMessaggio(5));
	}


}
