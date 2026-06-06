package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;


class ComandoGuardaTest {

	private Labirinto monolocale;
	private Labirinto bilocale;
	private IOSimulator console;
	private Partita partita;
	private FabbricaDiComandiFisarmonica factory;
	
	@BeforeEach
	void setUp(){
		List <String> comandi = new ArrayList<>();
		comandi.add("guarda");
		this.console = new IOSimulator(comandi);
		this.monolocale = Labirinto.newBuilder().addStanzaIniziale("Atrio").addAttrezzo("osso", 1).getLabirinto();
		this.bilocale = Labirinto.newBuilder().addStanzaIniziale("Inizio").addAttrezzo("osso", 1).addStanza("Fine").
				addAdiacenza("Inizio", "Fine", Direzione.NORD).getLabirinto();
		this.factory = new FabbricaDiComandiFisarmonica();
	}

	@Test
	void testMonolocaleGuarda() {
		this.partita = new Partita(this.monolocale);
		Comando comandoAiuto;
		comandoAiuto = this.factory.costruisciComando("guarda", this.console);
		comandoAiuto.esegui(this.partita, this.console);
		assertEquals("Stanza Corrente: Atrio\n"
				+ "Uscite: \n"
				+ "Attrezzi nella stanza: osso (1kg) ", console.getMessaggio(0));
	}
	
	@Test
	void testBilocaleGuarda() {
		this.partita = new Partita(this.bilocale);
		Comando comandoAiuto;
		comandoAiuto = this.factory.costruisciComando("guarda", this.console);
		comandoAiuto.esegui(this.partita, this.console);
		assertEquals("Stanza Corrente: Inizio\n"
				+ "Uscite:  nord\n"
				+ "Attrezzi nella stanza: osso (1kg) ", console.getMessaggio(0));
	}

}
