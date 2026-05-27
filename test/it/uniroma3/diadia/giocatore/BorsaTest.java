package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;


class BorsaTest {
	
	Borsa borsaPiena;
	Borsa borsaVuota;
	Borsa borsaConUnAttrezzo;
	Attrezzo martello;
	Attrezzo attrezzoNumerato;

	@BeforeEach
	void setUp(){
		this.borsaConUnAttrezzo = new Borsa();
		this.borsaPiena= new Borsa();
		this.borsaVuota = new Borsa();
		this.martello = new Attrezzo("Martello", 5);
		for(int i = 0; i < 10; i++) {
			this.attrezzoNumerato = new Attrezzo("Attrezzo " + i, 1);
			this.borsaPiena.addAttrezzo(attrezzoNumerato);
		}
		this.borsaConUnAttrezzo.addAttrezzo(this.martello);
		this.borsaPiena.addAttrezzo(new Attrezzo("AttrezzoDiPeso0", 0));
	}

	@Test
	void testAddAttrezzo_BorsaVuota_NonRiuscito() {
		Attrezzo attrezzoTroppoPesante = new Attrezzo("AttrezzoTroppoPesante", 11);
		assertFalse(this.borsaVuota.addAttrezzo(attrezzoTroppoPesante));
	}
	@Test
	void testAddAttrezzo_BorsaVuota_Riuscito() {
		Attrezzo attrezzoNonTroppoPesante = new Attrezzo("AttrezzoNonTroppoPesante", 1);
		assertTrue(this.borsaVuota.addAttrezzo(attrezzoNonTroppoPesante));
	}
	@Test
	void testAddAttrezzo_BorsaPiena(){
		Attrezzo attrezzoDiPeso1 = new Attrezzo("AttrezzoDiPeso1", 1);
		assertFalse(this.borsaPiena.addAttrezzo(attrezzoDiPeso1));
	}
	@Test
	void testGetAttrezzo_BorsaVuota() {
		assertNull(this.borsaVuota.getAttrezzo("Martello"));
	}
	@Test
	void testGetAttrezzo_BorsaConUnAttrezzo_Presente() {
		assertEquals(this.martello, this.borsaConUnAttrezzo.getAttrezzo("Martello"));
	}
	@Test
	void testGetAttrezzo_BorsaConUnAttrezzo_Assente() {
		assertNull(this.borsaConUnAttrezzo.getAttrezzo("Spada"));
	}
	@Test
	void testGetAttrezzo_BorsaPiena_Assente() {
		assertNull(this.borsaPiena.getAttrezzo("Martello"));
	}
	@Test
	void testGetAttrezzo_BorsaPiena_Presente() {
		assertEquals(this.attrezzoNumerato, this.borsaPiena.getAttrezzo("Attrezzo " + 9));
	}
	@Test
	void testGetPeso_BorsaVuota() {
		assertEquals(0, this.borsaVuota.getPeso());
	}
	@Test
	void testGetPeso_BorsaPiena() {
		assertEquals(10, this.borsaPiena.getPeso());
	}
	@Test
	void testGetPeso_BorsaConUnOggetto() {
		assertEquals(5, this.borsaConUnAttrezzo.getPeso());
	}
	@Test
	void  testIsEmpty_BorsaVuota() {
		assertTrue(this.borsaVuota.isEmpty());
	}
	@Test
	void testIsEmpty_BorsaConUnAttrezzo() {
		assertFalse(this.borsaConUnAttrezzo.isEmpty());
	}
	@Test
	void testIsEmpty_BorsaPiena() {
		assertFalse(this.borsaPiena.isEmpty());
	}
	@Test
	void testHasAttrezzo_BorsaVuota() {
		assertFalse(this.borsaVuota.hasAttrezzo("Martello"));
	}
	@Test
	void testHasAttrezzo_BorsaConUnAttrezzo_Presente() {
		assertTrue(this.borsaConUnAttrezzo.hasAttrezzo("Martello"));
	}
	@Test
	void testHasAttrezzo_BorsaConUnAttrezzo_Assente() {
		assertFalse(this.borsaConUnAttrezzo.hasAttrezzo("Spada"));
	}
	@Test
	void testRemoveAttrezzo_BorsaVuota() {
		assertNull(this.borsaVuota.removeAttrezzo("Martello")); 
	}
	@Test
	void testRemoveAttrezzo_BorsaConUnOggetto_Presente() {
		assertEquals(this.martello, this.borsaConUnAttrezzo.removeAttrezzo("Martello"));
	}
	@Test
	void testRemoveAttrezzo_BorsaConUnOggetto_Assente() {
		assertNull(this.borsaConUnAttrezzo.removeAttrezzo("Spada"));
	}
	
	@Test
	void testGetContenutoOrdinatoPerPeso_borsaVuota() {
		assertTrue(this.borsaVuota.getContenutoOrdinatoPerPeso().isEmpty());
	}
	
	@Test
	void testGetContenutoOrdinatoPerPeso_borsaConUnSoloElemento() {
		assertEquals(Collections.singletonList(this.martello), this.borsaConUnAttrezzo.getContenutoOrdinatoPerPeso());
	}
	
	@Test
	void testGetContenutoOrdinatoPerPeso_borsaPiena() {
		List <Attrezzo> ListaAttesa = new ArrayList<>();
		ListaAttesa.add(new Attrezzo("AttrezzoDiPeso0", 0));
		for(int i = 0; i < 10; i++) {
			Attrezzo attrezzoNumerato = new Attrezzo("Attrezzo " + i, 1);
			ListaAttesa.add(attrezzoNumerato);
		}
		assertEquals(ListaAttesa, this.borsaPiena.getContenutoOrdinatoPerPeso());
	}
	
	@Test
	void testGetContenutoOrdinatoPerNome_borsaVuota() {
		assertTrue(this.borsaVuota.getContenutoOrdinatoPerNome().isEmpty());
	}
	
	@Test
	void testGetContenutoOrdinatoPerNome_borsaConUnSoloElemento() {
		assertEquals(Collections.singleton(this.martello), this.borsaConUnAttrezzo.getContenutoOrdinatoPerNome());
	}
	
	@Test
	void testGetContenutoOrdinatoPerNome_borsaPiena() {
		Set <Attrezzo> setAtteso = new TreeSet<>();
		for(int i = 0; i < 10; i++) {
			Attrezzo attrezzoNumerato = new Attrezzo("Attrezzo " + i, 1);
			setAtteso.add(attrezzoNumerato);
		}
		setAtteso.add(new Attrezzo("AttrezzoDiPeso0", 0));
		assertEquals(setAtteso, this.borsaPiena.getContenutoOrdinatoPerNome());
	}
	
	@Test 
	void testGetContenutoRaggruppatoPerPeso_borsaVuota(){
		assertEquals(Collections.emptyMap(), this.borsaVuota.getContenutoRaggruppatoPerPeso());
	}
	
	@Test 
	void testGetContenutoRaggruppatoPerPeso_borsaConUnAttrezzo(){
		Set<Attrezzo> setAtteso = new HashSet<Attrezzo>();
		setAtteso.add(this.martello);
		assertEquals(Collections.singletonMap(5, setAtteso), 
				this.borsaConUnAttrezzo.getContenutoRaggruppatoPerPeso());
	}
	
	@Test 
	void testGetContenutoRaggruppatoPerPeso_borsaPiena(){
		Map<Integer,Set<Attrezzo>> mappaAttesa = new HashMap<>();
		Set<Attrezzo> setAttesoDiPeso0 = new HashSet<Attrezzo>();
		setAttesoDiPeso0.add(new Attrezzo("AttrezzoDiPeso0", 0));
		Set<Attrezzo> setAttesoDiPeso1 = new HashSet<Attrezzo>();
		for(int i = 0; i < 10; i++) {
			Attrezzo attrezzoNumerato = new Attrezzo("Attrezzo " + i, 1);
			setAttesoDiPeso1.add(attrezzoNumerato);
		}
		mappaAttesa.put(0, setAttesoDiPeso0);
		mappaAttesa.put(1, setAttesoDiPeso1);
		assertEquals(mappaAttesa, this.borsaPiena.getContenutoRaggruppatoPerPeso());
	}
	
	@Test
	void testGetSortedSetOrdinatoPerPeso_borsaVuota() {
		assertEquals(Collections.emptySortedSet(), this.borsaVuota.getSortedSetOrdinatoPerPeso());
	}
	
	@Test
	void testGetSortedSetOrdinatoPerPeso_borsaConUnAttrezzo() {
		assertEquals(Collections.singleton(this.martello), this.borsaConUnAttrezzo.getSortedSetOrdinatoPerPeso());
	}
	
	@Test
	void testGetSortedSetOrdinatoPerPeso_borsaPiena() {
		SortedSet <Attrezzo> setAtteso = new TreeSet<>(new Comparator<Attrezzo>() {
			@Override
			public int compare(Attrezzo a1, Attrezzo a2) {
				if(a1.getPeso()!= a2.getPeso())
					return a1.getPeso() - a2.getPeso();
				return a1.getNome().compareTo(a2.getNome());
			}
		});
		for(int i = 0; i < 10; i++) {
			Attrezzo attrezzoNumerato = new Attrezzo("Attrezzo " + i, 1);
			setAtteso.add(attrezzoNumerato);
		}
		setAtteso.add(new Attrezzo("AttrezzoDiPeso0", 0));
		assertEquals(setAtteso, this.borsaPiena.getSortedSetOrdinatoPerPeso());
	}
	
}
