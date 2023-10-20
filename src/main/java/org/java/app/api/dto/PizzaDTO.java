package org.java.app.api.dto;

public class PizzaDTO {
	
	private String nome;
	private String descrizione;
	private String foto;
	private int prezzo;

	//COSTRUTTORE
	public PizzaDTO() {}
	public PizzaDTO(String nome, String descrizione, String foto, int prezzo) {

		setNome(nome);
		setDescrizione(descrizione);
		setFoto(foto);
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
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
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
			+ "\nfoto: " + getFoto()
			+ "\nprezzo: " + getPrezzo();
	}

}
