package org.java.app.db.pojo;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//NOME
		@Column(length = 255, nullable = false)
		@NotBlank(message = "Il nome non può essere vuoto")
		@Length(
			min = 2, 
			max = 255, 
			message = "Il nome deve essere composto da 2~255 caratteri"
		)
	private String nome;
	
	//RELAZIONE TABELLA INGREDIENTI
	@ManyToMany(mappedBy = "ingredients")
	private List<Pizza> pizze;

	//COSTRUTTORE
	public Ingredient() {}
	public Ingredient(String nome) {
		
		setNome(nome);
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
	
	//PIZZA
	public List<Pizza> getPizze() {
		return pizze;
	}
	public void setPizze(List<Pizza> pizze) {
		this.pizze = pizze;
	}
	
	
	@Override
	public String toString() {
		
		return "[" + getId() + "]" + getNome();
	}
	
	// implementazione di metodi ereditati da OBJECT 
	@Override
	public int hashCode() {
		
		return getId();
	}
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Ingredient)) return false;
		
		Ingredient objIng = (Ingredient) obj;
		
		return getId() == objIng.getId();
	}

}
