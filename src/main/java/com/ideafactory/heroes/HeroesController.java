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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ideafactory.heroes.models.Hero;

import java.util.List;

/**
 * @author Aélion
 *
 */
@RequestMapping(path="/api/v1")
@RestController
public class HeroesController {
	private ArrayList<Hero> heroes = new ArrayList<Hero>();
	
	public HeroesController() {
	}
	
	@GetMapping("/all")
	public @ResponseBody List<Hero> getAll() {
		return (List<Hero>) this.heroes;
	}

	
	@PostMapping("/add")
	public String addHero(@RequestBody Hero hero) {
		hero.setId(this.heroes.size() > 0 ? this.heroes.size() + 1 : 1);
		this.heroes.add(hero);
		
		return "Ajout terminé (requestBody) : " + this.heroes.size();
	}
	
	@PutMapping("/update")
	public ArrayList<Hero> updateHero(@RequestBody Hero hero) {
		int index = 0;
		boolean found = false;
		for (Hero storedHero : this.heroes) {
			if (storedHero.getId() == hero.getId()) {
				found = !found;
				break;
			}
			index++;
		}
		if (found) {
			this.heroes.set(index, hero);
		}
		
		return this.heroes;
	}
	@DeleteMapping("/delete/{id}")
	public String deleteHero(@PathVariable int id) {
		Hero hero = this.findById(id);
		if (hero != null) {
			this.heroes.remove(hero);
			return "Suppression effectuée : " + this.heroes.size();
		}
		
		return "Le héro " + id + " n'a pas été trouvé";
	}
	
	private Hero findById(int id) {
		int index = 0;
		boolean found = false;
		for (Hero storedHero : this.heroes) {
			if (storedHero.getId() == id) {
				found = !found;
				break;
			}
			index++;
		}
		if (found) {
			return this.heroes.get(index);
		}
		
		return null;
	}
}
