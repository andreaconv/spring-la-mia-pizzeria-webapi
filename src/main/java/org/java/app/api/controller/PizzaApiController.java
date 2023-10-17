package org.java.app.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1.0")
public class PizzaApiController {
	
	@GetMapping
	public ResponseEntity<String> test(){
		
		return new ResponseEntity<>("Hello, World", HttpStatus.OK);
	}

}