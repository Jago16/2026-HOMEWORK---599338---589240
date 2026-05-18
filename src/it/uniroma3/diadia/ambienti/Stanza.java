package it.uniroma3.diadia.ambienti;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	static final private int NUMERO_MASSIMO_DIREZIONI = 4;

	private String nome;
	//	private Attrezzo[] attrezzi;
	//	private int numeroAttrezzi;
	//	private Stanza[] stanzeAdiacenti;
	//	private int numeroStanzeAdiacenti;
	//	private String[] direzioni;
	private Map<String, Attrezzo> attrezzi;
	private Map<String, Stanza> stanzeAdiacenti;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		//		this.numeroStanzeAdiacenti = 0;
		//		this.numeroAttrezzi = 0;
		//		this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
		//		this.stanzeAdiacenti = new Stanza[NUMERO_MASSIMO_DIREZIONI];
		//		this.attrezzi = new Attrezzo[NUMERO_MASSIMO_ATTREZZI];
		this.attrezzi = new HashMap<>();
		this.stanzeAdiacenti = new HashMap<String, Stanza>();
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		//		boolean aggiornato = false;
		//		for(int i=0; i<this.direzioni.length; i++)
		//			if (direzione.equals(this.direzioni[i])) {
		//				this.stanzeAdiacenti[i] = stanza;
		//				aggiornato = true;
		//			}
		//		if (!aggiornato)
		//			if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
		//				this.direzioni[numeroStanzeAdiacenti] = direzione;
		//				this.stanzeAdiacenti[numeroStanzeAdiacenti] = stanza;
		//				this.numeroStanzeAdiacenti++;
		//			}
		if(this.stanzeAdiacenti.containsKey(direzione) || this.stanzeAdiacenti.size()<NUMERO_MASSIMO_DIREZIONI) {
			this.stanzeAdiacenti.put(direzione, stanza);
		}
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		//		Stanza stanza = null;
		//		for(int i=0; i<this.numeroStanzeAdiacenti; i++)
		//			if (this.direzioni[i].equals(direzione))
		//				stanza = this.stanzeAdiacenti[i];
		//		return stanza;
		Stanza stanzaAdiacente = this.stanzeAdiacenti.get(direzione);
		return stanzaAdiacente;
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public List<Attrezzo> getAttrezzi() {
		List<Attrezzo> attrezzi = new ArrayList<>(this.attrezzi.values());
		return attrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		//		if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
		//			this.attrezzi[numeroAttrezzi] = attrezzo;
		//			this.numeroAttrezzi++;
		//			return true;
		//		}
		//		else {
		//			return false;
		//		}
		if(attrezzo!=null) {
			this.attrezzi.put(attrezzo.getNome(), attrezzo);
			return true;
		}
		return false;
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for (String direzione : this.getDirezioni())
			if (direzione!=null)
				risultato.append(" " + direzione);
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.attrezzi.values()) {
			if( attrezzo!=null)
				risultato.append(attrezzo.toString()+" ");
		}
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		//		boolean trovato;
		//		trovato = false;
		//		for (Attrezzo attrezzo : this.attrezzi) {
		//			if(attrezzo!=null)
		//				if (attrezzo.getNome().equals(nomeAttrezzo))
		//					trovato = true;
		//		}
		//		 
		//		return trovato;
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		//		Attrezzo attrezzoCercato;
		//		attrezzoCercato = null;
		//		for (Attrezzo attrezzo : this.attrezzi) {
		//			if(attrezzo!=null)
		//				if (attrezzo.getNome().equals(nomeAttrezzo))
		//					attrezzoCercato = attrezzo;
		//		}
		//		return attrezzoCercato;	
		//		for(Attrezzo attrezzoCercato : this.attrezzi) {
		//			if(attrezzoCercato.getNome().equals(nomeAttrezzo))
		//				return attrezzoCercato;
		//		}
		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		//		if(this.numeroAttrezzi == 0)
		//			return false;
		//		for(int i = 0; i < this.numeroAttrezzi; i++) {
		//			if(attrezzo.equals(this.attrezzi[i])) {
		//				this.attrezzi[i] = this.attrezzi[this.numeroAttrezzi];
		//				this.numeroAttrezzi--;
		//				return true;
		//			}			
		//		}
		//		return false;
		return this.attrezzi.remove(attrezzo.getNome(),attrezzo);
	}


	public Set<String> getDirezioni() {
		//		String[] direzioni = new String[this.numeroStanzeAdiacenti];
		//		for(int i=0; i<this.numeroStanzeAdiacenti; i++)
		//			direzioni[i] = this.direzioni[i];
		//		return direzioni;
		Set<String> direzioni = this.stanzeAdiacenti.keySet();
		return direzioni;
	}

}