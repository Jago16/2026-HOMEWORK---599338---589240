package it.uniroma3.diadia;

public class IOSimulator implements IO{

	private String[] righeDaLeggere;
	private String[] messaggiDaMostrare;
	private int indiceMessaggioDaMostrare;
	private int indiceIstruzioneCorrente;
	
	public IOSimulator(String [] righeDaLeggere) {
		this.righeDaLeggere = righeDaLeggere;
		this.messaggiDaMostrare = new String[100];
		this.indiceMessaggioDaMostrare = 0;
		this.indiceIstruzioneCorrente = 0;
	}
	
	@Override
	public void mostraMessaggio(String messaggio) {
		if(this.indiceMessaggioDaMostrare < this.messaggiDaMostrare.length) {
			this.messaggiDaMostrare[this.indiceMessaggioDaMostrare] = messaggio;
			this.indiceMessaggioDaMostrare++;
		}
		
	}

	@Override
	public String leggiRiga() {
		if(this.indiceIstruzioneCorrente < this.righeDaLeggere.length) {
			String istruzioneCorrente = this.righeDaLeggere[this.indiceIstruzioneCorrente];
			this.indiceIstruzioneCorrente++;
			return istruzioneCorrente;
		} else	
			return null;
	}
	
	public String getMessaggio(int i) {
		return this.messaggiDaMostrare[i];
	}

}
