package org.java.app.db.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//NOME
	@Column(length = 128, nullable = false)
	@NotBlank(message = "Il nome non può essere vuoto")
	@Length(
		min = 3, 
		max = 255, 
		message = "Il nome deve essere composte da 3~128 caratteri"
	)
	private String nome;
	
	
	//DESCRIZIONE
	@Column(length = 255, nullable = false)
	@Length(
		min = 3, 
		max = 255, 
		message = "La descrizione deve essere composta da 3~255 caratteri"
	)
	private String descrizione;
	
	
	//FOTO
	@Column(unique = true)
	@Nullable
	
//	FIXME: se inserisco una sringa vuota mi calcola comunque come nome unico
	private String foto;
	
	
	//PREZZO
	@NotNull(message = "Inserire il prezzo")
	@DecimalMin(value = "0.00", inclusive = false, message="Il prezzo non può essere inferiore o uguale a 0")
	private float prezzo;
	
	
	//RELAZIONE TABELLA SPECIAL
	@OneToMany(mappedBy = "pizza")
	private List<Special> specials;
	
	//RELAZIONE TABELLA INGREDIENTI
	@ManyToMany
	private List<Ingredient> ingredients;
	
	//COSTRUTTORE
	public Pizza() { }
	public Pizza(String nome, String descrizione, String foto, float prezzo, Ingredient...ingredients) {

		setId(id);
		setNome(nome);
		setDescrizione(descrizione);
		setFoto(foto);
		setPrezzo(prezzo);
		setIngredients(Arrays.asList(ingredients));
	}

	//ID
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	//NOME
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	//DESCRIZIONE
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	//FOTO(URL)
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}

	//PREZZO
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public String getFormattedPrice() {
		return String.format("%.2f", prezzo);
	}

	//SPECIAL
	public List<Special> getSpecials() {
		return specials;
	}
	public void setSpecials(List<Special> specials) {
		this.specials = specials;
	}
	
	//INGREDIENTI
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public void deleteIngredient(Ingredient ingredient) {
		
		getIngredients().remove(ingredient);
	}
	
	
	@Override
	public String toString() {
		return "[" + getId() + "] Pizza: \n"
				+ "nome: " + getNome() + "\n"
				+ "descrizione: " + getDescrizione() + "\n"
				+ "nome foto: " + getFoto() + "\n"
				+ "prezzo: " + getPrezzo();
		
	}

}
