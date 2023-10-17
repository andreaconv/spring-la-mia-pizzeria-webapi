package org.java.app.api.controller;

import java.util.List;

import org.java.app.db.pojo.Pizza;
import org.java.app.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

}