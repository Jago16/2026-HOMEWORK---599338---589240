package it.uniroma3.diadia.ambienti;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;


public class CaricatoreLabirinto extends LabirintoBuilder{

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String ESTREMI_MARKER = "Estremi:"; 

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";


	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	private static final String STANZE_BLOCCATE_MARKER = "StanzeBloccate:";

	private static final String STANZE_BUIE_MARKER = "StanzeBuie:";

	private static final String STANZE_MAGICHE_MARKER = "StanzeMagiche:";

	private static final String CANE_MARKER = "Cane:";

	private static final String MAGO_MARKER = "Mago:";

	private static final String STREGA_MARKER = "Strega:";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;



	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public CaricatoreLabirinto(Reader reader) { //Per i test
		this.reader = new LineNumberReader(reader);
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiECreaStanzeBloccate();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeMagiche();
			this.leggiEImpostaUscite();
			this.leggiEInserisciCane();
			this.leggiEInserisciMago();
			this.leggiEInserisciStrega();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException{
		try {
			String riga = this.reader.readLine();
			String primaParola = this.leggiPrimaParola(riga);
			while(riga!=null && !primaParola.equals(STANZE_BUIE_MARKER)) {
				if(!primaParola.isEmpty()) {
					try(Scanner scanner = new Scanner(riga)){
						String nomeStanza = scanner.next();
						check(scanner.hasNext(), msgTerminazionePrecoce("Attrezzo sbloccante"));
						String nomeAttrezzo = scanner.next();
						check(scanner.hasNext(), msgTerminazionePrecoce("Direzione bloccata"));
						String direzioneBloccata = scanner.next();
						this.addStanzaBloccata(nomeStanza, direzioneBloccata, nomeAttrezzo);
					}
				}
				riga = this.reader.readLine();
				primaParola = this.leggiPrimaParola(riga);
			}
			check(riga != null, "Terminazione precoce, non è stato trovato il marcatore: " + STANZE_BUIE_MARKER);
		}catch(IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException{
		try {
			String riga = this.reader.readLine();
			String primaParola = this.leggiPrimaParola(riga);
			while(riga!=null && !primaParola.equals(STANZE_MAGICHE_MARKER)) {
				if(!primaParola.isEmpty()) {
					try(Scanner scanner = new Scanner(riga)){
						String nomeStanza = scanner.next();
						check(scanner.hasNext(), msgTerminazionePrecoce("Attrezzo luminoso"));
						String nomeAttrezzo = scanner.next();
						this.addStanzaBuia(nomeStanza, nomeAttrezzo);
					}
				}
				riga = this.reader.readLine();
				primaParola = this.leggiPrimaParola(riga);
			}
			check(riga != null, "Terminazione precoce, non è stato trovato il marcatore: " + STANZE_MAGICHE_MARKER);
		}catch(IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException{
		try {
			String riga = this.reader.readLine();
			String primaParola = this.leggiPrimaParola(riga);
			while(riga!=null && !primaParola.equals(USCITE_MARKER)) {
				if(!primaParola.isEmpty()) {
					try(Scanner scanner = new Scanner(riga)){
						String nomeStanza = scanner.next();
						check(scanner.hasNext(), msgTerminazionePrecoce("Soglia magica"));
						String sogliaMagicaStringa = scanner.next();
						int sogliaMagica;
						try {
							sogliaMagica = Integer.parseInt(sogliaMagicaStringa);
							this.addStanzaMagica(nomeStanza, sogliaMagica);
						}catch (NumberFormatException e) {
							check(false, "Soglia magica non valida");
						}
					}
				}
				riga = this.reader.readLine();
				primaParola = this.leggiPrimaParola(riga);
			}
			check(riga != null, "Terminazione precoce, non è stato trovato il marcatore: " + USCITE_MARKER);
		}catch(IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}


	private void leggiEInserisciCane() throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			String primaParola = this.leggiPrimaParola(riga);
			while(riga!=null && !primaParola.equals(MAGO_MARKER)) {
				if(!primaParola.isEmpty()) {
					try(Scanner scanner = new Scanner(riga)){
						String nomeCane = scanner.next();
						check(scanner.hasNext(), msgTerminazionePrecoce("Nome attrezzo del cane"));
						String nomeAttrezzo = scanner.next();
						check(scanner.hasNext(), msgTerminazionePrecoce("Peso attrezo del cane"));
						String pesoAttrezzo = scanner.next();
						check(scanner.hasNext(), msgTerminazionePrecoce("Nome stanza del cane"));
						String nomeStanza = scanner.next();
						this.inserisciCane(nomeCane, nomeAttrezzo, pesoAttrezzo, nomeStanza);
					}
				}
				riga = this.reader.readLine();
				primaParola = this.leggiPrimaParola(riga);
			}
			check(riga!=null, "Terminazione precoce, non è stato trovato il marcatore: " + MAGO_MARKER );
		}catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}

	}

	private void leggiEInserisciMago() throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			String primaParola = this.leggiPrimaParola(riga);
			while(riga!=null && !primaParola.equals(STREGA_MARKER)) {
				if(!primaParola.isEmpty()) {
					try(Scanner scanner = new Scanner(riga)){
						String nomeMago = scanner.next();
						check(scanner.hasNext(), msgTerminazionePrecoce("Nome attrezzo del mago"));
						String nomeAttrezzo = scanner.next();
						check(scanner.hasNext(), msgTerminazionePrecoce("Peso attrezo del mago"));
						String pesoAttrezzo = scanner.next();
						check(scanner.hasNext(), msgTerminazionePrecoce("Nome stanza del mago"));
						String nomeStanza = scanner.next();
						this.inserisciMago(nomeMago, nomeAttrezzo, pesoAttrezzo, nomeStanza);
					}
				}
				riga = this.reader.readLine();
				primaParola = this.leggiPrimaParola(riga);
			}
			check(riga!=null, "Terminazione precoce, non è stato trovato il marcatore: " + STREGA_MARKER );
		}catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}

	}

	private void leggiEInserisciStrega() throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			while(riga!=null && !leggiPrimaParola(riga).isEmpty()) {
				try(Scanner scanner = new Scanner(riga)){
					String nomeStrega = scanner.next();
					check(scanner.hasNext(), msgTerminazionePrecoce("Nome stanza della strega"));
					String nomeStanza = scanner.next();
					this.inserisciStrega(nomeStrega, nomeStanza);
				}
				riga = this.reader.readLine();
			}
		}catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}

	}

	private void leggiMarcatore(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga != null, "Era atteso un marcatore: " + marker);
			try(Scanner scanner = new Scanner(riga)){
				check(scanner.hasNext(), "Era atteso il marcatore: " + marker);
				String marcatorePresente = scanner.next();
				check(marker.equals(marcatorePresente), "Era atteso il marcatore: " + marker + 
						"ma è stato invece trovato: " + marcatorePresente);
			}
		}catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
		//		try {
		//			String riga = this.reader.readLine();
		//			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
		//			return riga.substring(marker.length());
		//		} catch (IOException e) {
		//			throw new FormatoFileNonValidoException(e.getMessage());
		//		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		this.leggiMarcatore(STANZE_MARKER);
		try {
			String riga = this.reader.readLine();
			String primaParola = this.leggiPrimaParola(riga);
			while(riga!=null && !primaParola.equals(ESTREMI_MARKER)) {
				if(!primaParola.isEmpty())
					this.addStanza(primaParola);
				riga = this.reader.readLine();
				primaParola = this.leggiPrimaParola(riga);
			}
			check(riga != null, "Terminazione precoce, non è stato trovato il marcatore: " + ESTREMI_MARKER);
		}catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
		//		String nomiStanze = this.leggiMarcatore(STANZE_MARKER);
		//		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
		//			this.addStanza(nomeStanza);
		//		}
	}

	private String leggiPrimaParola(String riga) {
		if(riga==null)
			return "";
		try(Scanner scanner = new Scanner(riga)){
			if(scanner.hasNext())
				return scanner.next();
		}
		return "";
	}

	//	private List<String> separaStringheAlleVirgole(String string) {
	//		List<String> result = new LinkedList<>();
	//		Scanner scanner = new Scanner(string);
	//		scanner.useDelimiter(",");
	//		try (Scanner scannerDiParole = scanner) {
	//			result.add(scannerDiParole.next());
	//		}
	//		return result;
	//	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		//this.leggiMarcatore(ESTREMI_MARKER); non va inserito in quanto reader sta gia su Estremi per via del ciclo in leggiECreaStanze
		try {
			String riga = this.reader.readLine();
			String stanzaIniziale = this.leggiPrimaParola(riga);
			check(stanzaIniziale!=null, "Non è presente una stanza iniziale.");
			this.addStanzaIniziale(stanzaIniziale);
			riga = this.reader.readLine();
			String stanzaVincente = this.leggiPrimaParola(riga);
			check(stanzaVincente!=null, "Non è presente una stanza vincente");
			this.addStanzaVincente(stanzaVincente);
		} catch(IOException e){
			throw new FormatoFileNonValidoException(e.getMessage());
		}
		//		String nomeStanzaIniziale = null;
		//		nomeStanzaIniziale = this.leggiMarcatore(STANZA_INIZIALE_MARKER);
		//		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		//		String nomeStanzaVincente = this.leggiMarcatore(STANZA_VINCENTE_MARKER);
		//		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		//		this.addStanzaIniziale(nomeStanzaIniziale);
		//		this.addStanzaVincente(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		this.leggiMarcatore(ATTREZZI_MARKER);
		try {
			String riga = this.reader.readLine();
			String primaParola = this.leggiPrimaParola(riga);

			while(riga != null && !primaParola.equals(STANZE_BLOCCATE_MARKER)) {
				if(!primaParola.isEmpty()) {
					try(Scanner scanner = new Scanner(riga)){
						String nomeAttrezzo = scanner.next();
						check(scanner.hasNext(), msgTerminazionePrecoce("Peso attrezzo"));
						String pesoAttrezzo = scanner.next();
						check(scanner.hasNext(), msgTerminazionePrecoce("Nome stanza dove collocare l'attrezzo"));
						String nomeStanza = scanner.next();
						posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
					}
				}
				riga = this.reader.readLine();
				primaParola = this.leggiPrimaParola(riga);
			}
			check(riga != null, "Terminazione precoce, non è stato trovato il marcatore: " + STANZE_BLOCCATE_MARKER);
		}catch(IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
		//		String specificheAttrezzi = this.leggiMarcatore(ATTREZZI_MARKER);
		//
		//		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
		//			String nomeAttrezzo = null;
		//			String pesoAttrezzo = null;
		//			String nomeStanza = null; 
		//			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
		//				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
		//				nomeAttrezzo = scannerLinea.next();
		//				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
		//				pesoAttrezzo = scannerLinea.next();
		//				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
		//				nomeStanza = scannerLinea.next();
		//			}				
		//			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		//		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.addAttrezzoAStanza(nomeAttrezzo, peso, nomeStanza);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}

	private void inserisciCane(String nomeCane, String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) 
			throws FormatoFileNonValidoException{
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			check(isStanzaValida(nomeStanza), "Cane "+ nomeCane+" non collocabile: stanza " +nomeStanza+" inesistente");
			Attrezzo attrezzoCane = new Attrezzo(nomeAttrezzo, peso);
			Cane caneCorrente = new Cane(nomeCane, attrezzoCane);
			this.getListaStanze().get(nomeStanza).setPersonaggio(caneCorrente);
		}catch(NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}

	private void inserisciMago(String nomeMago, String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) 
			throws FormatoFileNonValidoException{
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			check(isStanzaValida(nomeStanza), "Mago "+ nomeMago+" non collocabile: stanza " +nomeStanza+" inesistente");
			Attrezzo attrezzoMago = new Attrezzo(nomeAttrezzo, peso);
			Mago magoCorrente = new Mago(nomeMago, attrezzoMago);
			this.getListaStanze().get(nomeStanza).setPersonaggio(magoCorrente);
		}catch(NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}

	private void inserisciStrega(String nomeStrega, String nomeStanza) throws FormatoFileNonValidoException{
		check(isStanzaValida(nomeStanza), "Strega "+ nomeStrega+" non collocabile: stanza " +nomeStanza+" inesistente");
		Strega stregaCorrente = new Strega(nomeStrega);
		this.getListaStanze().get(nomeStanza).setPersonaggio(stregaCorrente);
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.getListaStanze().containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			String primaParola = this.leggiPrimaParola(riga);
			while(riga != null && !primaParola.equals(CANE_MARKER)) {
				try(Scanner scanner = new Scanner(riga)){
					String stanzaPartenza = scanner.next();
					check(scanner.hasNext(), msgTerminazionePrecoce("Stanza di partenza"));
					String direzione = scanner.next();
					check(scanner.hasNext(), msgTerminazionePrecoce("direzione della stanza adiacente"));
					String stanzaArrivo = scanner.next();
					impostaUscita(stanzaPartenza, direzione, stanzaArrivo);
				}
				riga = this.reader.readLine();
				primaParola = this.leggiPrimaParola(riga);	
			}
			check(riga!=null, "Terminazione precoce, non è stato trovato il marcatore: " + CANE_MARKER);
		}catch(IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
		//		String specificheUscite = this.leggiMarcatore(USCITE_MARKER);
		//		try (Scanner scannerDiLinea = new Scanner(specificheUscite)) {			
		//
		//			while (scannerDiLinea.hasNext()) {
		//				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
		//				String stanzaPartenza = scannerDiLinea.next();
		//				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
		//				String dir = scannerDiLinea.next();
		//				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
		//				String stanzaDestinazione = scannerDiLinea.next();
		//
		//				impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
		//			}
		//		} 
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		this.addAdiacenza(stanzaDa, nomeA, dir);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	@Override
	public Stanza getStanzaIniziale() {
		return super.getStanzaIniziale();
	}

	@Override
	public Stanza getStanzaVincente() {
		return super.getStanzaVincente();
	}
}
