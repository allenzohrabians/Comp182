//Comp 182
//Spring 2019
//Allen Zohrabians
//Project 2
//Energy.java
//This class is for energy locations as well as their storage.

import java.awt.Point;

public class Energy extends Point {
	private double amount;
	
	public Energy(Point position, double capacity) {
		super(position);
		amount = capacity;
	}
	
	public double supply(double requested) {
		double supplyAmount;
		if (amount >= requested) {
			supplyAmount = requested;
			amount -= requested;
		} else {
			supplyAmount = amount;
			amount = 0;
		}
		return supplyAmount;
	}
	
	public boolean isActive() {
		return amount > 0;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
