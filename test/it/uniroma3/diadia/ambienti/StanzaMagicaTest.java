package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {

	private StanzaMagica stanzaMagicaNonAttiva;
	private StanzaMagica stanzaMagicaAttiva;

	@BeforeEach
	void setUp() throws Exception {
		this.stanzaMagicaNonAttiva = new StanzaMagica("Atrio", 1);
		this.stanzaMagicaNonAttiva.addAttrezzo(new Attrezzo("Spada", 5));
		this.stanzaMagicaAttiva = new StanzaMagica("Atrio", 1);
		this.stanzaMagicaAttiva.addAttrezzo(new Attrezzo("Spada", 5));
		this.stanzaMagicaAttiva.addAttrezzo(new Attrezzo("Falce", 10));
		
		
	}

	@Test
	void testStanzaMagicaNonAttivaPresenzaAttrezzi() {
		assertTrue(this.stanzaMagicaNonAttiva.getAttrezzi().contains(new Attrezzo("Spada", 5)));
	}
	
	@Test
	void testStanzaMagicaNonAttivaPesiGiusti() {
		int index = this.stanzaMagicaNonAttiva.getAttrezzi().indexOf(new Attrezzo("Spada", 5));
		assertTrue(this.stanzaMagicaNonAttiva.getAttrezzi().get(index).getPeso() == 5);
	}
	
	@Test
	void testStanzaMagicaAttiva() {
		assertTrue(this.stanzaMagicaAttiva.getAttrezzi().contains(new Attrezzo("Spada", 5)) &&
				this.stanzaMagicaAttiva.getAttrezzi().contains(new Attrezzo("eclaF", 20)));
	}
	@Test
	void testStanzaMagicaAttivaPesiGiusti() {
		int index1 = this.stanzaMagicaAttiva.getAttrezzi().indexOf(new Attrezzo("Spada", 5));
		int index2 = this.stanzaMagicaAttiva.getAttrezzi().indexOf(new Attrezzo("eclaF", 20));
		assertTrue(this.stanzaMagicaAttiva.getAttrezzi().get(index1).getPeso() == 5 &&
				this.stanzaMagicaAttiva.getAttrezzi().get(index2).getPeso() == 20);
	}

}
