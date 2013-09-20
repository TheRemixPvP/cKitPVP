package me.theremixpvp.ckitpvp;

public class ShopKit {
	
	private String name;
	private double price;
	
	public ShopKit(String name, double price) {
		this.name = name;
		this.price = price;
	}
	
	public String name() {
		return this.name;
	}
	
	public double price() {
		return this.price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

}
