/**
 * 
 */
package com.ideafactory.heroes;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jean-luc
 *
 */
@RequestMapping(path="/api/v1")
@RestController
public class HeroesController {
	private ArrayList<String> heroes = new ArrayList<String>();
	
	public HeroesController() {
		this.heroes.add("Superman");
		this.heroes.add("Batman");
		this.heroes.add("Spiderman");
	}
	
	@GetMapping("/all")
	public ArrayList<String> getAll() {
		return this.heroes;
	}
	
	@GetMapping("/add/{name}")
	public String add(@PathVariable String name) {
		this.heroes.add(name);
		
		return "Ajout terminé : " + this.heroes.size();
	}
	
	@PostMapping("/add")
	public String addHero(@RequestBody String hero) {
		this.heroes.add(hero);
		
		return "Ajout terminé (requestBody) : " + this.heroes.size();
	}
	
	@PutMapping("/update/{oldHero}/{hero}")
	public ArrayList<String> updateHero(@PathVariable String oldHero, @PathVariable String hero) {
		int index = this.heroes.indexOf(oldHero);
		this.heroes.set(index, hero);

		
		return this.heroes;
	}
	@DeleteMapping("/delete/{name}")
	public String deleteHero(@PathVariable String name) {
		// Trouver le ou les héros dans la liste, et supprimer
		if (this.heroes.contains(name)) {
			this.heroes.remove(name);
			return "Suppression effectuée : " + this.heroes.size();
		}
		return "Le héro " + name + " n'a pas été trouvé";
	}
}
