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
import org.springframework.web.bind.annotation.RestController;

import com.ideafactory.heroes.models.Hero;

import java.util.List;
import java.util.stream.*;

/**
 * @author Aélion
 *
 */
@RequestMapping(path="/api/v1")
@RestController
public class HeroesController {
	private ArrayList<Hero> heroes = new ArrayList<Hero>();
	
	public HeroesController() {}
	
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
	
	@PostMapping("/add/heroes")
	public ArrayList<Hero> addHeroes(@RequestBody Hero[] heroes) {
		for(int i=0; i < heroes.length; i++) {
			Hero hero = heroes[i];
			hero.setId(this.heroes.size() > 0 ? this.heroes.size() + 1 : 1);
			this.heroes.add(hero);
		}
		return this.heroes;
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateHero(@RequestBody Hero hero) {
		Hero oldHero = this.findById(hero.getId());
		int index = this.heroes.indexOf(oldHero);

		if (index != -1) {
			this.heroes.set(index, hero);
			return new ResponseEntity<>(hero, HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
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
		return (Hero) this.heroes.stream()
				.filter((obj) -> obj.getId() == id)
				.findFirst()
				.orElse(null);
	}
}
