package org.java.app.mvc.controller;

import java.util.List;

import org.java.app.db.pojo.Ingredient;
import org.java.app.db.pojo.Pizza;
import org.java.app.db.serv.IngredientService;
import org.java.app.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping()
public class IngredientController {
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping("/ingredients")
	public String getIndex(Model model) {
		
		List<Ingredient> ingredients = ingredientService.findAll();
		
		model.addAttribute("ingredients", ingredients);
		
		return "ingredient-index";
	}
	
	//CREATE NEW INGREDIENTE
	@GetMapping("/ingredients/create")
	public String getCreateForm(Model model) {
		
		model.addAttribute("ingredient", new Ingredient());
		return "ingredient-create-edit";
	}
	@PostMapping("/ingredients/create")
	public String storeIngredient(
			@Valid @ModelAttribute Ingredient ingredient,
			BindingResult bindingResult,
			Model model) {
		
		return saveIngrediente(ingredient, bindingResult, model);
	}
	
	//EDIT INGREDIENTE
	@GetMapping("/ingredients/update/{id}")
	public String getEditForm(
			@PathVariable int id,
			Model model
		) {

		Ingredient ingredient = ingredientService.findById(id);
		model.addAttribute("ingredient", ingredient);

		return "ingredient-create-edit";
	}
	@PostMapping("ingredients/update/{id}")
	public String updateIngredient(
			@Valid @ModelAttribute Ingredient ingredient,
			BindingResult bindingResult,
			Model model) {
		
		return saveIngrediente(ingredient, bindingResult, model);
	}
	
	//METODO CHE ACCOMUNA IL CREATE E L'EDIT
	private String saveIngrediente(
			Ingredient ingredient,
			BindingResult bindingResult,
			Model model) {
		
		if (bindingResult.hasErrors()) {
			System.out.println("Error:");
			bindingResult.getAllErrors().forEach(System.out::println);
			
			return "ingredient-create-edit";
		}
		
		try {
			ingredientService.save(ingredient);
		} catch (Exception e) {
			
			// CONSTRAIN VALIDATION (unique)
			System.out.println("Errore constrain: " + e.getClass().getSimpleName());
			
			return "ingredient-create-edit";
		}
		
		return "redirect:/ingredients";
	}
	
	//DELETE INGREDIENTE
		@PostMapping("/ingredients/delete/{id}")
		public String deleteIngredient(@PathVariable int id) {
			
			Ingredient ingredient = ingredientService.findById(id);
			
			for(Pizza p : ingredient.getPizze()) {
				
				 p.deleteIngredient(ingredient);
				 pizzaService.save(p);
			}
			
			ingredientService.deleteIngredient(ingredient);
			
			return "redirect:/ingredients";
		}

}
