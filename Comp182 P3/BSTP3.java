//Comp 182
//Spring 2019
//Allen Zohrabians
//Project 3
//BSTP3.java
//This is the application class which contains our main method, Sample instance variables, and the array list as well as the constructor used to determine the number of integers put into it.

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BSTP3 {
	public static final int SIMULATION_TRIALS = 1000;
	
	private ArrayList<Integer> numbers;
	private BSTree<BSTElement> tree;
	private Sample createMax;
	private Sample createAverage;
	private Sample deleteMax;
	private Sample deleteAverage;
	private Sample reinsertMax;
	private Sample reinsertAverage;
	private Random random;
	
	public static void main(String[] args) {
		new BSTP3(10);
	}
	
	public BSTP3(int power) {
		createMax = new Sample("Create Max");
		createAverage = new Sample("Create Average");
		deleteMax = new Sample("Delete Max");
		deleteAverage = new Sample("Delete Average");
		reinsertMax = new Sample("Reinsert Max");
		reinsertAverage = new Sample("Reinsert Average");
		random = new Random();
		for (int i = 0; i < SIMULATION_TRIALS; i++) {
		   simulation(power);
		}
		createMax.computeStats();
		System.out.println(createMax);
		createAverage.computeStats();
		System.out.println(createAverage);
		deleteMax.computeStats();
		System.out.println(deleteMax);
		deleteAverage.computeStats();
		System.out.println(deleteAverage);
		reinsertMax.computeStats();
		System.out.println(reinsertMax);
		reinsertAverage.computeStats();
		System.out.println(reinsertAverage);
	}
	
	public void simulation(int power) {
		//initializing part
		int createSize = (int)Math.pow(2, power) - 1;
		numbers = new ArrayList<Integer>();
		for (int i = 0; i < createSize; i++) {
			numbers.add(i);
		}
		//shuffling part
		Collections.shuffle(numbers);
		//part 1 - creation
		tree = new BSTree<BSTElement>();
		for (int i = 0; i < numbers.size(); i++) {
			tree.insert(new BSTElement(numbers.get(i)));
		}
		Sample createSample = new Sample("Create");
		tree.nodeLevel(createSample);
		createSample.computeStats();
		createMax.add(createSample.getMax());
		createAverage.add(createSample.getAvg());
		//part 2 - deletion - phase 2
		int deleteSize = (int)Math.pow(2, power - 1) - 1;
		for (int i = 0; i < deleteSize; i++) {
			tree.delete(new BSTElement(numbers.get(i)));
		}
		Sample deleteSample = new Sample("Delete");
		tree.nodeLevel(deleteSample);
		deleteSample.computeStats();
		deleteMax.add(deleteSample.getMax());
		deleteAverage.add(deleteSample.getAvg());
		//part 3 - reinsertion - phase 2
		for (int i = 0; i < deleteSize; i++) {
			tree.insert(new BSTElement(numbers.get(i)));
		}
		Sample reinsertSample = new Sample("Reinsert");
		tree.nodeLevel(reinsertSample);
		reinsertSample.computeStats();
		reinsertMax.add(reinsertSample.getMax());
		reinsertAverage.add(reinsertSample.getAvg());
	}
}
