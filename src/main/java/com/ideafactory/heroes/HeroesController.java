/**
 * 
 */
package com.ideafactory.heroes;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public List<Hero> getAll() {
		return (List<Hero>) this.heroes;
	}

	
	@PostMapping("/add")
	public Hero addHero(@RequestBody Hero hero) {
		hero.setId(this.heroes.size() > 0 ? this.heroes.size() + 1 : 1);
		this.heroes.add(hero);
		
		return hero;
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
	public ResponseEntity<?> deleteHero(@PathVariable int id) {
		Hero hero = this.findById(id);
		if (hero != null) {
			this.heroes.remove(hero);
			return new ResponseEntity<>(hero, HttpStatus.OK);

		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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
