//Comp 182
//Spring 2019
//Allen Zohrabians
//Project 2
//StackRobot.java
//This is a child class of the Robot.java with detected energy locations being processed by a "Last In First Out" approach.

import java.awt.Point;
import java.util.ArrayList;

public class StackRobot extends Robot {
	public StackRobot(double startAmount, ArrayList<Energy> locations, String name) {
		super(startAmount, locations, name);
	}
	
	protected void learn(Energy location) {
		forget(location);
		memories.addFirst(location); //alternate way: addLast(location);
	}
	
	protected Energy remember() {
		if (memories.size() > 0) {
		return memories.removeFirst(); //alternate way: removeLast();
	} else {
		if (locations.size() > 0) {
				return locations.get(random.nextInt(locations.size()));
			} else {
				return new Energy(new Point(x + (int)RobotMemory.ROBOT_ENERGY_CAPACITY, y + (int)RobotMemory.ROBOT_ENERGY_CAPACITY), RobotMemory.ENERGY_INTERVAL_CAPACITY);
			}
	}
	}
}