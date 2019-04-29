/**
 * 
 */
package com.ideafactory.heroes.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;

/**
 * @author Aélion
 *
 */
@Entity
public class Hero {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String name;
	private int lifePoints;
	private int strength;
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getLifePoints() {
		return this.lifePoints;
	}
	
	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}
	
	public int getStrength() {
		return this.strength;
	}
	
	public void setStrength(int strength) {
		this.strength = strength;
	}
}
