package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatorePerPesoENome;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	//	private Attrezzo[] attrezzi;
	//	private int numeroAttrezzi;
	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		//		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		//		this.numeroAttrezzi = 0;
		this.attrezzi = new HashMap<>();
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax() || this.attrezzi.containsKey(attrezzo.getNome()))
			//se ho un oggetto nella borsa con quel nome l'attrezzo verrebbe sovrascritto e avrei
			//perso l'oggetto
			return false;
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
		//		if (this.numeroAttrezzi==10)
		//			return false;
		//		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		//		this.numeroAttrezzi++;
		//		return true;


	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	public int getPeso() {
		int peso = 0;
		//		for (int i= 0; i<this.numeroAttrezzi; i++)
		//			peso += this.attrezzi[i].getPeso();
		//
		//		return peso;
		for(Attrezzo attrezzoCorrente : this.attrezzi.values()) {
			peso += attrezzoCorrente.getPeso();
		}
		return peso;

	}

	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		//		Attrezzo a = null;
		//		if(hasAttrezzo(nomeAttrezzo)) {
		//			boolean trovato=false;
		//			for(int i = 0; i < this.numeroAttrezzi && trovato==false;i++) {
		//				if(nomeAttrezzo.equals(this.attrezzi[i].getNome())) {
		//					a=this.attrezzi[i];
		//					this.attrezzi[i] = this.attrezzi[this.numeroAttrezzi];
		//					this.numeroAttrezzi--;
		//					trovato=true;
		//				}
		//			}
		//		}
		//		return a;
		return this.attrezzi.remove(nomeAttrezzo);
	}

	List<Attrezzo> getContenutoOrdinatoPerPeso(){
		ComparatorePerPesoENome comparatore = new ComparatorePerPesoENome();
		List<Attrezzo> listaOrdinataPerPeso = new ArrayList<>(this.attrezzi.values());
		Collections.sort(listaOrdinataPerPeso, comparatore);
		return listaOrdinataPerPeso;
	}

	SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		SortedSet<Attrezzo> ordinatoPerNome = new TreeSet<>(this.attrezzi.values());
		return ordinatoPerNome;
	}

	Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer, Set<Attrezzo>> mappaRaggruppataPerPeso = new HashMap<Integer, Set<Attrezzo>>();
		for (Attrezzo attrezzoCorrente : this.attrezzi.values()) {
			if(mappaRaggruppataPerPeso.containsKey(attrezzoCorrente.getPeso())) {
				mappaRaggruppataPerPeso.get(attrezzoCorrente.getPeso()).add(attrezzoCorrente);
			}else {
				Set<Attrezzo> setPerOggettiDiPesoAncoraNonConosciuto = new HashSet<Attrezzo>();
				setPerOggettiDiPesoAncoraNonConosciuto.add(attrezzoCorrente);
				mappaRaggruppataPerPeso.put(attrezzoCorrente.getPeso(), setPerOggettiDiPesoAncoraNonConosciuto);
			}
		}	
		return mappaRaggruppataPerPeso;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			//			for (int i= 0; i<this.numeroAttrezzi; i++)
			//				s.append(attrezzi[i].toString()+" ");
			for(Attrezzo attrezzoCorrente : this.attrezzi.values())
				s.append(attrezzoCorrente.toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}