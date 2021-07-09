//Comp 182
//Spring 2019
//Allen Zohrabians
//Project 2
//Robot.java
//This is an abstract class which extends Robot182.java and is where the robot functions take place.

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Random;

public abstract class Robot extends Robot182 {
	protected double energyAmount;
	protected boolean isExploring;
	protected Random random;
	protected Energy goal;
	protected Deque<Energy> memories;
	protected ArrayList<Energy> locations;
	protected double traveled;
	
	public Robot(double startAmount, ArrayList<Energy> locations, String name) {
		super(name);
		energyAmount = startAmount;
		isExploring = true;
		random = new Random();
		goal = locations.get(random.nextInt(locations.size()));
		memories = new ArrayDeque<Energy>();
		this.locations = locations;
		traveled = 0;
	}
	
	public void move() {
		turnToFace(goal);
		forward(RobotMemory.STEP);
		energyAmount -= RobotMemory.STEP;
		traveled += RobotMemory.STEP;
		if (distance(goal) <= RobotMemory.SNAP_DISTANCE) {
			energyAmount -= distance(goal);
			traveled += distance(goal);
			setLocation(goal);
			if (!isExploring) {
				double requested = RobotMemory.ROBOT_ENERGY_CAPACITY - energyAmount;
				double granted = goal.supply(requested);
				energyAmount += granted;
				if (!goal.isActive()) {
					locations.remove(goal);
					forget(goal);
				}
			}
			if (energyAmount <= RobotMemory.ROBOT_ENERGY_CAPACITY / 2) {
				becomeHungry();
			} else {
				startExploring();
			}
		} else if (isExploring && energyAmount <= RobotMemory.ROBOT_ENERGY_CAPACITY / 2) {
			becomeHungry();
		}
		for (int i = 0; i < locations.size(); i++) {
			if (distance(locations.get(i)) <= RobotMemory.ROBOT_DETECTION_DISTANCE) {
				learn(locations.get(i));
				break;
			}
		}
	}
	
	protected void becomeHungry() {
		isExploring = false;
		goal = remember();
	}
	
	protected void startExploring() {
		isExploring = true;
		if (locations.size() > 0) {
			goal = locations.get(random.nextInt(locations.size()));
		} else {
			goal = new Energy(new Point(x + (int)RobotMemory.ROBOT_ENERGY_CAPACITY, y + (int)RobotMemory.ROBOT_ENERGY_CAPACITY), RobotMemory.ENERGY_INTERVAL_CAPACITY);
		}
	}
	
	public boolean isActive() {
		return energyAmount > 0;
	}
	
	public double getTraveled() {
		return traveled;
	}
	
	public int getSizeOfMemories() {
		return memories.size();
	}
	
	protected abstract void learn(Energy location);
	
	protected abstract Energy remember();
	
	protected void forget(Energy location) {
		memories.remove(location);
	}
}
