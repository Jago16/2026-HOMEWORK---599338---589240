import it.uniroma3.diadia.Attrezzo;
import it.uniroma3.diadia.Stanza;

public class StanzaTest2 {

	public static void main(String[] args) {
		Stanza bar=new Stanza("bar");
		Stanza mensa=new Stanza("mensa");
		Attrezzo tazzina=new Attrezzo("tazzina", 1);
		Attrezzo piatto=new Attrezzo("piatto", 2);
		bar.impostaStanzaAdiacente("nord", mensa);
		mensa.impostaStanzaAdiacente("sud", bar);
		bar.addAttrezzo(tazzina);
		mensa.addAttrezzo(piatto);
		System.out.println(bar.getStanzaAdiacente("nord").getAttrezzo("piatto"));
		System.out.println(mensa.getStanzaAdiacente("sud").getAttrezzo("tazzina"));
	}

}
