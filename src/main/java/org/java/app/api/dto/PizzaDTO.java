package org.java.app.api.dto;

public class PizzaDTO {
	
	private String nome;
	private String descrizione;
	private int prezzo;

	//COSTRUTTORE
	public PizzaDTO() {}
	public PizzaDTO(String nome, String descrizione, int prezzo) {

		setNome(nome);
		setDescrizione(descrizione);
		setPrezzo(prezzo);
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public int getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}
	
	@Override
	public String toString() {
		
		return "nome: " + getNome()
			+ "\ndescrizione: " + getDescrizione()
			+ "\nprezzo: " + getPrezzo();
	}

}
