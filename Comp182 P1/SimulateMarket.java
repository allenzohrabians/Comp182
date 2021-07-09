//Comp 182
//Spring 2019
//Allen Zohrabians
//Project 1
//SimulateMarket.java
//This class contains our main method and runs the program along with providing the output.

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SimulateMarket {
private final String FILE_NAME = "SP500-1950-2018-weekly.txt";
private final int WEEKS = 3601;
private final double INVEST = 100.0;
private final int TRIALS = 10000;
private final double AVERAGE = 0.0016;
private final double STDEV = 0.0206;
private Sample model;
private Random aRandom;
	
	public static void main(String[] args) {
		System.out.println("P1 Simulate Market By Allen Zohrabians\n");
      new SimulateMarket();
	}
	
	public SimulateMarket() {
		model = new Sample("SP500", makeDistribution(FILE_NAME));
		aRandom = new Random();
		historicalSP500();
		normalSP500();
		sampledSP500();
	}
	
	private ArrayList<Double> makeDistribution(String fileName) {
		ArrayList<Double> result = new ArrayList<>();
		try {
			Scanner input = new Scanner(new FileInputStream(fileName));
			while (input.hasNextLine()) {
	String[] strings = input.nextLine().split("\t");
	if (strings.length == 2) {
		Scanner data = new Scanner(strings[1]);
		if (data.hasNextDouble()) {
			result.add(data.nextDouble());
		}
}
}
		} catch (Exception e) {
			System.out.println("Error: File not found.");
		}
		return result;
	}
	
	private void historicalSP500() {
		double money = INVEST;
		for (int week = 0; week < WEEKS; week++) {
			money += money * model.get(week);
		}
		model.computeStats();
		System.out.println("Historical SP500 statistics:");
		System.out.println(model);
		System.out.println(String.format("Historical SP500 $ %.2f becomes $ %.2f", INVEST, money));
      System.out.println("\n");
	}
	
	private void normalSP500() {
		Sample equity = new Sample("Normal");
		for (int trial = 0; trial < TRIALS; trial++) {
			double money = INVEST;
			for (int week = 0; week < WEEKS; week++) {
				double weeklyReturn = AVERAGE + aRandom.nextGaussian() * STDEV;
				money += money * weeklyReturn;
			}
			equity.add(money);
		}
		equity.computeStats();
		System.out.println("Normal SP500 simulation:");
		System.out.println(equity);
		System.out.println(String.format("$ %.2f becomes %s", INVEST, equity.getAvgMed()));
      System.out.println("\n");
	}
	
	private void sampledSP500() {
		Sample equity = new Sample("Sampled");
		for (int trial = 0; trial < TRIALS; trial++) {
double money = INVEST;
			for (int week = 0; week < WEEKS; week++) {
				money += money * model.getRandom();
			}
			equity.add(money);
		}
equity.computeStats();
		System.out.println("Sampled SP500 simulation:");
		System.out.println(equity);
		System.out.println(String.format("$ %.2f becomes %s", INVEST, equity.getAvgMed()));
	}
}

