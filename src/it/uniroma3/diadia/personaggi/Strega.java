package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{
	
	private static final String MESSAGGIO_SALUTO = "Grazie per avermi salutato, "+
			"questo posto ti piacerà!!";
	private static final String MESSAGGIO_NESSUSALUTO = "SEI UN MALEDUCATO! VIA DA QUI!!";
	private static final String MESSAGGIO_PRESENTAZIONE = "Sono molto permalosa";

	public Strega(String nome) {
		super(nome, MESSAGGIO_PRESENTAZIONE);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		if(this.haSalutato()) 
			msg = MESSAGGIO_SALUTO;		
		else
			msg = MESSAGGIO_NESSUSALUTO;
		partita.setStanzaCorrente(partita.getStanzaCorrente().stanzaAdiacenteConMinMaxNumeroDiOggetti
				(partita.getStanzaCorrente().getMapStanzeAdiacenti(), this.haSalutato()));
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo != null) {
			partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
			return "AHAHAHAAHAHAHAH" + "-Bravo genio, ti ha rubato l'attrezzo-";
		}
		return null;
	}

}
