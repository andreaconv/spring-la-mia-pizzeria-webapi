package org.java.app.api.controller;

import java.util.List;
import java.util.Optional;

import org.java.app.api.dto.PizzaDTO;
import org.java.app.db.pojo.Pizza;
import org.java.app.db.pojo.Special;
import org.java.app.db.serv.PizzaService;
import org.java.app.db.serv.SpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1.0/pizze")
public class PizzaApiController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private SpecialService specialService;
	
	//LISTA DI TUTTE LE PIZZE
	@GetMapping
	public ResponseEntity<List<Pizza>> getAllPizze(
			@RequestParam(required = false, name = "q") String query
			){
		
		List<Pizza> pizze = pizzaService.findAll();
		
		if (query == null) 
			pizze = pizzaService.findAll();
		else 
			pizze = pizzaService.findByNome(query);
		
		return new ResponseEntity<>(pizze, HttpStatus.OK);
	}
	
	//OTTENDO SINGOLA PIZZA TRAMITE L'ID
	@GetMapping("{id}")
	public ResponseEntity<Pizza> getPizzaById(
			@PathVariable int id
		) {
		
		Optional<Pizza> optPizza = Optional.ofNullable(pizzaService.findById(id));
		
		if (optPizza.isEmpty()) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(optPizza.get(), HttpStatus.OK);
	}
	
	//AGGIUNGO E SALVO UNA NUOVA PIZZA
	@PostMapping
	public ResponseEntity<Pizza> createPizza(
			@RequestBody PizzaDTO pizzaDto
		) {
		
		Pizza pizza = new Pizza(pizzaDto);
		pizza = pizzaService.save(pizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	//MODIFICO E SALVO UNA PIZZA ESISTENTE
	@PostMapping("{id}")
	public ResponseEntity<Pizza> updatePizza(
			@PathVariable int id,
			@RequestBody PizzaDTO pizzaDto
		) {
		
		Optional<Pizza> optPizza = Optional.ofNullable(pizzaService.findById(id));
		
		if (optPizza.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		Pizza pizza = optPizza.get();
		pizza.fillFromDto(pizzaDto);
		
		pizza = pizzaService.save(pizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	//CANCELLO UNA PIZZA ESISTENTE
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deletePizza(
			@PathVariable int id
		) {
		
		Optional<Pizza> optPizza = Optional.ofNullable(pizzaService.findById(id));
		
		if (optPizza.isEmpty())
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		
		Pizza pizza = optPizza.get();
		
		//eliminare il collegamento con special prima di eliminare la pizza
		List<Special> specials = specialService.findByPizza(pizza);
		for (Special special : specials) {
			
			specialService.deleteSpecial(special);
		}
		
		pizzaService.deletePizza(pizza);
		
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	
	
	

}