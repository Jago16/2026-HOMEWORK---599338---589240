package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

class IOSimulatorTest {

	private Labirinto labirintoAttuale;

	@BeforeEach

	void setUp() {
		this.labirintoAttuale = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("osso", 1)
				.addStanzaVincente("Biblioteca")
				.addStanza("Aula N11")
				.addStanza("Aula N10")
				.addAttrezzo("lanterna", 3)
				.addStanza("Laboratorio Campus")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.addAdiacenza("Atrio", "Aula N11", "est")
				.addAdiacenza("Atrio", "Aula N10", "sud")
				.addAdiacenza("Atrio", "Laboratorio Campus", "ovest")
				.addAdiacenza("Aula N11", "Laboratorio Campus", "est")
				.addAdiacenza("Aula N11", "Atrio", "ovest")
				.addAdiacenza("Aula N10", "Atrio", "nord")
				.addAdiacenza("Aula N10", "Aula N11", "est")
				.addAdiacenza("Aula N10", "Laboratorio Campus", "ovest")
				.addAdiacenza("Laboratorio Campus", "Atrio", "est")
				.addAdiacenza("Laboratorio Campus", "Aula N11", "ovest")
				.addAdiacenza("Biblioteca", "Atrio", "sud")
				.getLabirinto();
	}

	@Test
	void testPartitaAiutoFine() {
		List<String> comandi = new ArrayList<>();
		comandi.add("aiuto");
		comandi.add("fine");
		IOSimulator simulatore = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(simulatore, this.labirintoAttuale);
		gioco.gioca();
		assertEquals(""+
				"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
				"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
				"I locali sono popolati da strani personaggi, " +
				"alcuni amici, altri... chissa!\n"+
				"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
				"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
				"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
				"Per conoscere le istruzioni usa il comando 'aiuto'.", simulatore.getMessaggio(0)); 
		assertEquals("vai ", simulatore.getMessaggio(1));
		assertEquals("aiuto ", simulatore.getMessaggio(2));
		assertEquals("prendi ", simulatore.getMessaggio(3));
		assertEquals("posa ", simulatore.getMessaggio(4));
		assertEquals("fine ", simulatore.getMessaggio(5));
		assertEquals("guarda ", simulatore.getMessaggio(6));
		assertEquals("saluta ", simulatore.getMessaggio(7));
		assertEquals("interagisci ", simulatore.getMessaggio(8));
		assertEquals("regala ", simulatore.getMessaggio(9));
		assertEquals("", simulatore.getMessaggio(10));
		assertEquals("Grazie di aver giocato!", simulatore.getMessaggio(11));
	}

	@Test

	void testPartitaVinta() {
		List<String> comandi = new ArrayList<>();
		comandi.add("vai nord");
		IOSimulator simulatore = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(simulatore, this.labirintoAttuale);
		gioco.gioca();
		assertEquals(""+
				"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
				"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
				"I locali sono popolati da strani personaggi, " +
				"alcuni amici, altri... chissa!\n"+
				"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
				"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
				"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
				"Per conoscere le istruzioni usa il comando 'aiuto'.", simulatore.getMessaggio(0)); 
		assertEquals("Biblioteca", simulatore.getMessaggio(1));
		assertEquals("Hai vinto!", simulatore.getMessaggio(2));

	}
	@Test

	void testPartitaGuardaFinita() {
		List<String> comandi = new ArrayList<>();
		comandi.add("guarda");
		comandi.add("fine");
		IOSimulator simulatore = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(simulatore, this.labirintoAttuale);
		gioco.gioca();
		assertEquals(""+
				"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
				"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
				"I locali sono popolati da strani personaggi, " +
				"alcuni amici, altri... chissa!\n"+
				"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
				"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
				"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
				"Per conoscere le istruzioni usa il comando 'aiuto'.", simulatore.getMessaggio(0)); 
		assertEquals("Stanza Corrente: Atrio\n"
				+ "Uscite:  nord sud est ovest\n"
				+ "Attrezzi nella stanza: osso (1kg) ", simulatore.getMessaggio(1));

		assertEquals("CFU rimasti: 20", simulatore.getMessaggio(2));
		assertEquals("Info Borsa: Borsa vuota", simulatore.getMessaggio(3));
		assertEquals("Grazie di aver giocato!", simulatore.getMessaggio(4));
	}

	@Test

	void testPartitaComandoNonValidoFinita() {
		List<String> comandi = new ArrayList<>();
		comandi.add("comandoNonValido");
		comandi.add("fine");
		IOSimulator simulatore = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(simulatore, this.labirintoAttuale);
		gioco.gioca();
		assertEquals(""+
				"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
				"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
				"I locali sono popolati da strani personaggi, " +
				"alcuni amici, altri... chissa!\n"+
				"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
				"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
				"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
				"Per conoscere le istruzioni usa il comando 'aiuto'.", simulatore.getMessaggio(0));
		assertEquals("Comando non valido", simulatore.getMessaggio(1));
		assertEquals("Grazie di aver giocato!", simulatore.getMessaggio(2));
	}

	@Test

	void testPartitaVaiVaiDirezioneInesistenteFine() {
		List<String> comandi = new ArrayList<>();
		comandi.add("vai");
		comandi.add("vai 4");
		comandi.add("fine");
		IOSimulator simulatore = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(simulatore, this.labirintoAttuale);
		gioco.gioca();
		assertEquals(""+
				"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
				"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
				"I locali sono popolati da strani personaggi, " +
				"alcuni amici, altri... chissa!\n"+
				"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
				"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
				"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
				"Per conoscere le istruzioni usa il comando 'aiuto'.", simulatore.getMessaggio(0));
		assertEquals("Dove vuoi andare? Devi specificare una direzione", simulatore.getMessaggio(1));
		assertEquals("Direzione inesistente", simulatore.getMessaggio(2));
		assertEquals("Grazie di aver giocato!", simulatore.getMessaggio(3));
	}

	@Test

	void testPartitaPrendiPosaFine_Riuscito() {
		List<String> comandi = new ArrayList<>();
		comandi.add("prendi osso");
		comandi.add("posa osso");
		comandi.add("fine");
		IOSimulator simulatore = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(simulatore, this.labirintoAttuale);
		gioco.gioca();
		assertEquals(""+
				"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
				"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
				"I locali sono popolati da strani personaggi, " +
				"alcuni amici, altri... chissa!\n"+
				"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
				"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
				"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
				"Per conoscere le istruzioni usa il comando 'aiuto'.", simulatore.getMessaggio(0));
		assertEquals("Messo nella borsa: osso", simulatore.getMessaggio(1));
		assertEquals("Hai posato l'oggetto osso", simulatore.getMessaggio(2));
		assertEquals("Grazie di aver giocato!", simulatore.getMessaggio(3));
	}

	@Test

	void testPartitaPrendiPosaFine_NonRiuscito() {
		List<String> comandi = new ArrayList<>();
		comandi.add("prendi");
		comandi.add("lanterna");
		comandi.add("posa");
		comandi.add("lanterna");
		comandi.add("fine");
		IOSimulator simulatore = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(simulatore, this.labirintoAttuale);
		gioco.gioca();
		assertEquals(""+
				"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
				"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
				"I locali sono popolati da strani personaggi, " +
				"alcuni amici, altri... chissa!\n"+
				"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
				"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
				"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
				"Per conoscere le istruzioni usa il comando 'aiuto'.", simulatore.getMessaggio(0));
		assertEquals("Che oggetto vuoi prendere dalla stanza?", simulatore.getMessaggio(1));
		assertEquals("L'oggetto non e' presente nella stanza", simulatore.getMessaggio(2));
		assertEquals("Che oggetto vuoi posare?", simulatore.getMessaggio(3));
		assertEquals("L'oggetto non e' presente nella borsa", simulatore.getMessaggio(4));
		assertEquals("Grazie di aver giocato!", simulatore.getMessaggio(5));
	}

}
