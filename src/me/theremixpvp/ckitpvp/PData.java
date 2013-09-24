package me.theremixpvp.ckitpvp;

import java.util.ArrayList;

import org.bukkit.inventory.Inventory;

public class PData {
	
	private String name;
	private double credits;
	private int lvl;
	private float xp;
	private String kit;
	private int kills;
	private int deaths;
	private ArrayList<String> unlockedkits = new ArrayList<String>();
	private Inventory bank;
	
	public PData(String name) {
		this.name = name;
	}
	
	public String name() {
		return this.name;
	}
	
	public double credits() {
		return this.credits;
	}
	
	public void setCredits(double credits) {
		this.credits = credits;
	}
	
	public int lvl() {
		return this.lvl;
	}
	
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	
	public float xp() {
		return this.xp;
	}
	
	public void setXP(float xp) {
		this.xp = xp;
	}
	
	public int kills() {
		return this.kills;
	}
	
	public void setKills(int kills) {
		this.kills = kills;
	}
	
	public int deaths() {
		return this.deaths;
	}
	
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	
	public ArrayList<String> unlockedkits() {
		return this.unlockedkits;
	}
	
	public void addkit(String kit) {
		this.unlockedkits.add(kit);
	}
	
	public String getKit() {
		return this.kit;
	}
	
	public void setKit(String kit) {
		this.kit = kit;
	}
	
	public Inventory getBank() {
		return this.bank;
	}
	
	public void setBank(Inventory bank) {
		this.bank = bank;
	}

}
