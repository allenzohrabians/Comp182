//Comp 182
//Spring 2019
//Allen Zohrabians
//Project 1
//Sample.java
//This class takes care of all the calculations involved with the simulation as well as establishing all the variables.

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Sample {
	private String name;
	private ArrayList<Double> distribution;
	private double min;
	private double average;
	private double median;
	private double sd;
	private double max;
	private Random aRandom;
	
	public Sample(String name, ArrayList<Double> distribution) {
		this.name = name;
		this.distribution = distribution;
		min = 0;
		average = 0;
		median = 0;
		sd = 0;
		max = 0;
		aRandom = new Random();
	}
	
	public Sample(String name) {
		this.name = name;
		distribution = new ArrayList<>();
		min = 0;
		average = 0;
		median = 0;
		sd = 0;
		max = 0;
		aRandom = new Random();
	}
	
	public double get(int i) {
		if (i >= 0 && i <= distribution.size() - 1) {
			return distribution.get(i);
		} else {
			System.out.println("Error: Invalid index: " + i + ", returning error value -999.");
			//System.exit(1);
			return -999;
		}
	}
	
	public void add(double value) {
		distribution.add(value);
	}
	
	public void computeStats() {
		if (distribution.size() > 0) {
			ArrayList<Double> sorted = new ArrayList<>(distribution);
			Collections.sort(sorted);
			min = sorted.get(0);
			max = sorted.get(sorted.size() - 1);
			median = sorted.get(sorted.size() / 2);
			double sum = sorted.get(0);
			for (int i = 1; i < sorted.size(); i++) {
				sum += sorted.get(i);
			}
			average = sum / sorted.size();
			double deviation = Math.pow(average - sorted.get(0), 2);
			for (int i = 1; i < sorted.size(); i++) {
				deviation += Math.pow(average - sorted.get(i), 2);
			}
			double variance = deviation / sorted.size();
			sd = Math.sqrt(variance);
		}
	}
	
	public double getRandom() {
		return distribution.get(aRandom.nextInt(distribution.size()));
	}
	
	public String toString() {
		return String.format("%s: size = %d, min = %.4f, average = %.4f, median = %.4f, sd = %.4f, max = %.4f", name, distribution.size(), min, average, median, sd, max);
	}
	
	public String getAvgMed() {
		return String.format("$ %.2f (average), and $ %.2f (median)", average, median);
	}
}
