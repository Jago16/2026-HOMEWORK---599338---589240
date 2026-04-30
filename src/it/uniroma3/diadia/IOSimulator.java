package it.uniroma3.diadia;

public class IOSimulator implements IO{

	private String[] righeDaLeggere;
	private String[] messaggiDaMostrare;
	private int indiceMessaggioDaMostrare;
	
	public IOSimulator(String [] righeDaLeggere) {
		this.righeDaLeggere = righeDaLeggere;
		this.messaggiDaMostrare = new String[100];
		this.indiceMessaggioDaMostrare = 0;
	}
	
	@Override
	public void mostraMessaggio(String messaggio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String leggiRiga() {
		// TODO Auto-generated method stub
		return null;
	}

}
