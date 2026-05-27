package it.uniroma3.diadia.comandi;

public enum ElencoComandi {
	VAI("vai"),
	AIUTO("aiuto"),
	PRENDI("prendi"),
	POSA("posa"),
	FINE("fine"),
	GUARDA("guarda"),
	SALUTA("saluta"),
	INTERAGISCI("interagisci"),
	REGALA("regala");
	
	
	private final String nome;
	
	private ElencoComandi(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
}
