package org.java.app;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.java.app.auth.pojo.Role;
import org.java.app.auth.pojo.User;
import org.java.app.auth.service.RoleService;
import org.java.app.auth.service.UserService;
import org.java.app.db.pojo.Ingredient;
import org.java.app.db.pojo.Pizza;
import org.java.app.db.pojo.Special;
import org.java.app.db.serv.IngredientService;
import org.java.app.db.serv.PizzaService;
import org.java.app.db.serv.SpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private SpecialService specialService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}
	
	Random random = new Random();
	
	@Override
	public void run(String... args) throws Exception {
		
		Ingredient pomodoro = new Ingredient("pomdoro");
		Ingredient mozzarella = new Ingredient("mozzarella");
		Ingredient basilico = new Ingredient("basilico");
		
		ingredientService.save(pomodoro);
		ingredientService.save(mozzarella);
		ingredientService.save(basilico);
		
		List<Pizza> pizze = Arrays.asList(
			    new Pizza("marinara", "Pizza classica con pomodoro e origano", "https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/97C2C718-5015-437F-8355-316EE000F57C/Derivates/556409BB-02E0-4BA3-BC16-F409F609FCED.jpg", 4.5f, pomodoro),
			    
			    new Pizza("margherita", "Deliziosa pizza con pomodoro, mozzarella e basilico", "https://upload.wikimedia.org/wikipedia/commons/c/c8/Pizza_Margherita_stu_spivack.jpg", 6.5f, pomodoro, mozzarella, basilico),
			    
			    new Pizza("wurstel", "Un tocco di wurstel su questa pizza classica", "https://www.dolcidee.it/media/uploads/recipe/5a5bb13339fd4_tmp26914248_2055067254519979_806649253_n.jpg", 5.5f, pomodoro, mozzarella),
			    
			    new Pizza("diavola", "Per gli amanti del piccante", "https://i1.wp.com/www.piccolericette.net/piccolericette/wp-content/uploads/2017/06/3236_Pizza.jpg?resize=895%2C616&ssl=1", 8.5f),
			    
			    new Pizza("prosciutto", "Una pizza semplice e gustosa", "https://www.petitchef.it/imgupl/recipe/pizza-al-prosciutto-crudo--450186p695926.jpg", 6f),
			    
			    new Pizza("napoletana", "Pizza tradizionale", "https://media-assets.lacucinaitaliana.it/photos/61fd2987802734d1e3672423/16:9/w_1280,c_limit/Pizza-alle-alici2.jpg", 5.5f),
			    
			    new Pizza("pugliese", "Una pizza con un tocco di cipolla", "https://www.lospicchiodaglio.it/img/ricette/pizzabiancacipolle.jpg", 5.5f),
			    
			    new Pizza("romana", "Il sapore unico", "https://www.petitchef.it/imgupl/recipe/pizza-napoletana-acciughe-e-capperi--457933p714661.jpg", 6.5f),
			    
			    new Pizza("prosciutto e funghi", "Una combinazione classica", "https://www.pizzeriasenzatempo.it/wp-content/uploads/2020/04/PROSCIUTTO-E-FUNGHI.jpg", 6.5f),
			    
			    new Pizza("quattro stagioni", "Una pizza per tutti i gusti", "https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/A7B9D1B2-20CE-4D60-B18D-77D8E09F847A/Derivates/349C6F0F-E5E5-4327-9892-930DE2D8E89F.jpg", 8.5f),
			    
			    new Pizza("parmigiana", "Un mix irresistibile", "https://www.amando.it/imagesdyn/articoli/120/72/560/117684.jpg", 6f),
			    new Pizza("capricciosa", "Una combinazione di ingredienti per i veri amanti delle pizze", "https://wips.plug.it/cips/buonissimo.org/cms/2019/04/pizza-capricciosa.jpg", 8f),
			    
			    new Pizza("quattro formaggi", "Per gli amanti dei formaggi", "https://wips.plug.it/cips/buonissimo.org/cms/2012/12/10798952_l.jpg", 8f),
			    
			    new Pizza("speck e zola", "La combinazione", "https://www.giallozafferano.it/images/9-943/Pizza-mascarpone-speck-e-noci_780x520_wm.jpg", 7f),
			    
			    new Pizza("estate", "Il sapore fresco di pomodoro fresco e basilico", "https://blog.giallozafferano.it/lebistro/wp-content/uploads/2015/06/IMG_0139.jpg", 6f),
			    
			    new Pizza("zucchine", "Una gustosa pizza", "https://s3.eu-central-1.amazonaws.com/quomi/media/174770/conversions/Pizza-bianca-zucchine-e-scamorza-affumicata-thumb-big.jpg", 6f),
			    
			    new Pizza("bufalina", "Il tocco unico", "https://www.delizieeconfidenze.com/wp-content/uploads/2019/02/IMG_8640.jpg", 7f),
			    
			    new Pizza("prosciutto e carciofi", "Un'ottima scelta", "https://www.ristorantelespecialita.it/wp-content/uploads/2016/04/pizza-con-carciofi-ricetta-lespecialita.jpg", 6.5f),
			    
			    new Pizza("tonno e cipolla", "Una combinazione di sapori", "https://www.bofrost.it/on/demandware.static/-/Sites-IT-master-catalog/default/dw012891e0/images/15196-Pizza-Tonno-e-Cipolla-R.jpg", 6.5f),
			    
			    new Pizza("ortolana", "Un'esplosione di verdure", "https://www.pizza.it/wp-content/uploads/2021/06/PIZZA-ORTOLANA-1024x683.jpg", 7.5f)
			);
	    
	    Pizza pizzaSpecial =  new Pizza("special-1", "La pizza speciale", "", 9.5f);
	    pizzaService.save(pizzaSpecial);
	    
	    pizze.forEach(pizzaService::save);
	    //alternativa
//	    pizze.forEach(p -> pizzaService.save(p));
	    
	    
	    
	    int indiceCasuale = random.nextInt(pizze.size() - 1);
//	    								String titolo, Pizza pizza, LocalDate specialDate, LocalDate returnDate
	    Special special1 = new Special("special 1", pizzaSpecial, LocalDate.now(), LocalDate.parse("2023-11-12"));
	    specialService.save(special1);
	    
	    Special special2 = new Special("special 2", pizze.get(indiceCasuale), LocalDate.now(), LocalDate.parse("2023-11-12"));
	    specialService.save(special2);
	    
	    System.out.println("Insert OK!");
	    
	    //CREO I RUOLI
		Role user = new Role("USER");
		Role admin = new Role("ADMIN");
		
		//SALVO I RUOLI
		roleService.save(user);
		roleService.save(admin);
		
		//stringa che converte la password in criptata
		final String pwsUser = new BCryptPasswordEncoder().encode("password");
		final String pwsAdmin = new BCryptPasswordEncoder().encode("password");
		
		//CREO USER E ADMIN
		User User = new User("User", pwsUser, user);
		User Admin = new User("Admin", pwsAdmin, admin, user);
		
		//SALVO USER E ADMIN
		userService.save(User);
		userService.save(Admin);
	    
	}
	

}
