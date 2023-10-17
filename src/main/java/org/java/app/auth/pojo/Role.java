package org.java.app.auth.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true)
	@NotNull
	private String name;
	
	
	//COSTRUTTORE
	public Role() { }
	public Role(String name) {
		
		setName(name);
	}
	
	//ID
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	//NAME
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		
		return "[" + getId() + "] " + getName();
	}
	
	// implementazione di metodi ereditati da OBJECT se si dovesse usare List<Role> nella ManyToMany 
//	@Override
//	public int hashCode() {
//
//		return getId();
//	}
//	@Override
//	public boolean equals(Object obj) {
//
//		if (!(obj instanceof Role)) return false;
//
//		Role objIng = (Role) obj;
//
//		return getId() == objIng.getId();
//	}

}
