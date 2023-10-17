package org.java.app.api.controller;

import java.util.List;
import java.util.Optional;

import org.java.app.db.pojo.Pizza;
import org.java.app.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1.0/pizze")
public class PizzaApiController {
	
	@Autowired
	private PizzaService pizzaService;
	
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

}