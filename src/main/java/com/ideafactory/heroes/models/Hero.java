/**
 * 
 */
package com.ideafactory.heroes.models;

/**
 * @author Aélion
 *
 */
public class Hero {
	private long id;
	private String name;
	private int lifePoints;
	private int strength;
	
	public int getId() {
		return (int) this.id;
	}
	
	public void setId(long id) {
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
