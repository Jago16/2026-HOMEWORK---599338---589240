package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IOSimulatorTest {

	@Test
	void testPartitaAiutoFine() {
		String [] comandi = {"aiuto", "fine"};
		IOSimulator simulatore = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(simulatore);
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
		assertEquals("", simulatore.getMessaggio(7));
		assertEquals("Grazie di aver giocato!", simulatore.getMessaggio(8));
		assertNull(simulatore.getMessaggio(9));
	}
	
	@Test

	void testPartitaVinta() {
		String [] comandi = {"vai nord"};
		IOSimulator simulatore = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(simulatore);
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
		assertNull(simulatore.getMessaggio(3));
		
	}
	@Test
	
	void testPartitaGuardaFinita() {
		String [] comandi = {"guarda", "fine"};
		IOSimulator simulatore = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(simulatore);
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
				+ "Uscite:  nord est sud ovest\n"
				+ "Attrezzi nella stanza: osso (1kg) ", simulatore.getMessaggio(1));
	
		assertEquals("CFU rimasti: 20", simulatore.getMessaggio(2));
		assertEquals("Info Borsa: Borsa vuota", simulatore.getMessaggio(3));
		assertEquals("Grazie di aver giocato!", simulatore.getMessaggio(4));
		assertNull(simulatore.getMessaggio(5));
	}
	
	@Test
	
	void testPartitaComandoNonValidoFinita() {
		String [] comandi = {"comandoNonValido", "fine"};
		IOSimulator simulatore = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(simulatore);
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
		assertNull(simulatore.getMessaggio(3));
	}
	
	@Test
	
	void testPartitaVaiVaiDirezioneInesistenteFine() {
		String [] comandi = {"vai", "vai 4", "fine"};
		IOSimulator simulatore = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(simulatore);
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
		assertNull(simulatore.getMessaggio(4));
	}
	
	@Test
	
	void testPartitaPrendiPosaFine_Riuscito() {
		String [] comandi = {"prendi osso", "posa osso", "fine"};
		IOSimulator simulatore = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(simulatore);
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
		assertNull(simulatore.getMessaggio(4));
	}
	
	@Test
	
	void testPartitaPrendiPosaFine_NonRiuscito() {
		String [] comandi = {"prendi","lanterna", "posa", "lanterna", "fine"};
		IOSimulator simulatore = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(simulatore);
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
		assertNull(simulatore.getMessaggio(6));
	}

}
