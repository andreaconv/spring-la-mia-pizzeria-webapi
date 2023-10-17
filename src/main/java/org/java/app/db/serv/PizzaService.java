package org.java.app.db.serv;

import java.util.List;

import org.java.app.db.pojo.Pizza;
import org.java.app.db.repo.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

	@Autowired
	private PizzaRepo pizzaRepo;
	
	//INSERT
	public void save(Pizza pizza) {
		
		pizzaRepo.save(pizza);
	}
	
	//FIND ALL
	public List<Pizza> findAll(){
		
		return pizzaRepo.findAll();
	}
	
	//FIND BY ID
	public Pizza findById(int id) {
		
		return pizzaRepo.findById(id).get();
	}
	
	public List<Pizza> findByNome(String nomePizza){
		
		return pizzaRepo.findByNomeContaining(nomePizza);
	}
	
	public void deletePizza(Pizza pizza) {
			
			pizzaRepo.delete(pizza);
		}
}
