package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.io.StringReader;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;


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
		assertTrue(this.caricatoreMonolocale.getLabirintoBuilder().getListaStanze().get("Atrio").hasAttrezzo("Osso"));
	}
	
	@Test
	void testMonolocaPosizionamentoOggetto_nonPresente() {
		assertFalse(this.caricatoreMonolocale.getLabirintoBuilder().getListaStanze().get("Atrio").hasAttrezzo("Lanterna"));
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
		LabirintoBuilder labirintoExpected = Labirinto.newBuilder();
		labirintoExpected.addStanzaIniziale("N10").addAttrezzo("Osso", 5).addStanzaVincente("Biblioteca")
		.addAdiacenza("N10", "Biblioteca", Direzione.NORD).addAdiacenza("Biblioteca", "N10", Direzione.SUD);
		assertEquals(labirintoExpected, this.caricatoreBilocale.getLabirintoBuilder());
	}
	
	@Test
	void testInserimentoStanzaBuia() {
		StanzaBuia stanzaBuia = new StanzaBuia("N11", "Lanterna");
		assertEquals(stanzaBuia, this.caricatoreLabirintoCompleto.getLabirintoBuilder().getListaStanze().get("N11"));
	}
	
	@Test
	void testInserimentoStanzaBloccata() {
		StanzaBloccata stanzaBloccata = new StanzaBloccata("Campus", Direzione.SUD, "Chiave");
		assertEquals(stanzaBloccata, this.caricatoreLabirintoCompleto.getLabirintoBuilder().getListaStanze().get("Campus"));
	}
	
	@Test
	void testInserimentoStanzaMagica() {
		StanzaMagica stanzaMagica = new StanzaMagica("Atrio", 10);
		assertEquals(stanzaMagica, this.caricatoreLabirintoCompleto.getLabirintoBuilder().getListaStanze().get("Atrio"));
	}
	
	@Test
	void testInserimentoCane() {
		Cane caneTest = new Cane("Fido", new Attrezzo("Osso", 5));
		assertEquals(caneTest, this.caricatoreLabirintoCompleto.getLabirintoBuilder().getListaStanze().get("N10").getPersonaggio());
	}
	
	@Test
	void testInserimentoMago() {
		Mago magoTest = new Mago("Mario", new Attrezzo("Staffa", 5));
		assertEquals(magoTest, this.caricatoreLabirintoCompleto.getLabirintoBuilder().getListaStanze().get("Atrio").getPersonaggio());
	}
	
	@Test
	void testInserimentoStrega() {
		Strega stregaTest = new Strega("Caterina");
		assertEquals(stregaTest, this.caricatoreLabirintoCompleto.getLabirintoBuilder().getListaStanze().get("Biblioteca").getPersonaggio());
	}
	
}
