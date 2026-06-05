package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.io.StringReader;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CaricatoreLabirintoTest {

	private static final String MONOLOCALE = "Stanze:\n" +
			"Atrio\n" +
			"Estremi:\n" +
			"Atrio\n" +
			"Atrio\n" +
			"Attrezzi:\n" +
			"Osso 5 Atrio\n" +
			"StanzeBloccate:\n" +
			"StanzeBuie:\n" +
			"StanzeMagiche:\n" +
			"Uscite:\n" +
			"Cane:\n" +
			"Mago:\n" +
			"Strega:\n";
	
	private static final String BILOCALE = "Stanze:\n" +
			"N10\n" +
			"Biblioteca\n" +
			"Estremi:\n" +
			"N10\n" +
			"Biblioteca\n" +
			"Attrezzi:\n" +
			"Osso 5 N10\n" +
			"StanzeBloccate:\n" +
			"StanzeBuie:\n" +
			"StanzeMagiche:\n" +
			"Uscite:\n" +
			"N10 nord Biblioteca\n" +
			"Biblioteca sud N10\n" +
			"Cane:\n" +
			"Mago:\n" +
			"Strega:\n";
	
	private static final String LABIRINTO_COMPLETO = "Stanze:\n" +
			"N10\n" +
			"Biblioteca\n" +
			"Estremi:\n" +
			"N10\n" +
			"Biblioteca\n" +
			"Attrezzi:\n" +
			"Osso 5 N10\n" +
			"StanzeBloccate:\n" +
			"Campus Chiave sud\n" +
			"StanzeBuie:\n" +
			"N11 Lanterna\n" +
			"StanzeMagiche:\n" +
			"Atrio 10\n" +
			"Uscite:\n" +
			"N10 nord Biblioteca\n" +
			"Biblioteca sud N10\n" +
			"Cane:\n" +
			"Fido Osso 5 N10\n" +
			"Mago:\n" +
			"Mario Staffa 5 Atrio\n" +
			"Strega:\n" +
			"Caterina Biblioteca\n";
	
	
	
	private CaricatoreLabirinto caricatoreMonolocale;
	private CaricatoreLabirinto caricatoreBilocale;

	private CaricatoreLabirinto caricatoreLabirintoCompleto;
	
	@BeforeEach
	void setUp() throws Exception {
		this.caricatoreMonolocale = new CaricatoreLabirinto(new StringReader(MONOLOCALE));
		this.caricatoreBilocale = new CaricatoreLabirinto(new StringReader(BILOCALE));
		this.caricatoreLabirintoCompleto = new CaricatoreLabirinto(new StringReader(LABIRINTO_COMPLETO));
		this.caricatoreLabirintoCompleto.carica();
		this.caricatoreMonolocale.carica();
		this.caricatoreBilocale.carica();
	}

	@Test
	void testMonolocaleStanzaVincente() {
		assertEquals(new Stanza("Atrio"), this.caricatoreMonolocale.getStanzaVincente());
	}
	
	@Test
	void testMonolocaleStanzaInziale() {
		assertEquals(new Stanza("Atrio"), this.caricatoreMonolocale.getStanzaIniziale());
	}
	
	@Test
	void testMonolocaPosizionamentoOggetto_presente() {
		assertTrue(this.caricatoreMonolocale.getListaStanze().get("Atrio").hasAttrezzo("Osso"));
	}
	
	@Test
	void testMonolocaPosizionamentoOggetto_nonPresente() {
		assertFalse(this.caricatoreMonolocale.getListaStanze().get("Atrio").hasAttrezzo("Lanterna"));
	}

	@Test
	void testMonolocaleNienteUscite() {
		assertEquals(Collections.emptyMap() ,this.caricatoreMonolocale.getStanzaVincente().getMapStanzeAdiacenti());
	}
	
	@Test
	void testBilocaleStanzaVincente() {
		assertEquals(new Stanza("Biblioteca"), this.caricatoreBilocale.getStanzaVincente());
	}
	
	@Test
	void testBilocaleStanzaIniziale() {
		assertEquals(new Stanza("N10"), this.caricatoreBilocale.getStanzaIniziale());
	}
	
	@Test
	void testBilocalePosizionamentoOggetto_presente() {
		assertTrue(this.caricatoreBilocale.getStanzaIniziale().hasAttrezzo("Osso"));
		assertEquals(5, this.caricatoreBilocale.getStanzaIniziale().getAttrezzo("Osso").getPeso());
	}
	
	@Test
	void testBilocalePosizionamentoOggetto_nonPresente() {
		assertFalse(this.caricatoreBilocale.getStanzaVincente().hasAttrezzo("Osso"));
	}
	
	@Test
	void testBilocaleInteroLabirinto() {
		LabirintoBuilder labirintoExpected = new LabirintoBuilder();
		labirintoExpected.addStanzaIniziale("N10").addAttrezzo("Osso", 5).addStanzaVincente("Biblioteca")
		.addAdiacenza("N10", "Biblioteca", "nord").addAdiacenza("Biblioteca", "N10", "sud");
		assertEquals(labirintoExpected, this.caricatoreBilocale.getLabirinto());
	}
	
	@Test
	void testInserimentoStanzaBuia() {
		StanzaBuia stanzaBuia = new StanzaBuia("N11", "Lanterna");
		assertEquals(stanzaBuia, this.caricatoreLabirintoCompleto.getListaStanze().get("N11"));
	}
	
	@Test
	void testInserimentoStanzaBloccata() {
		StanzaBloccata stanzaBloccata = new StanzaBloccata("Campus", "Chiave", "sud");
		assertEquals(stanzaBloccata, this.caricatoreLabirintoCompleto.getListaStanze().get("Campus"));
	}
}
