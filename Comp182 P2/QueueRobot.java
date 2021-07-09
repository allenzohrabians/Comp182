//Comp 182
//Spring 2019
//Allen Zohrabians
//Project 2
//QueueRobot.java
//This is a child class of Robot.java with detected energy locations being processed by a "First In First Out" approach.

import java.awt.Point;
import java.util.ArrayList;

public class QueueRobot extends Robot {
	public QueueRobot(double startAmount, ArrayList<Energy> locations, String name) {
		super(startAmount, locations, name);
	}
	
	protected void learn(Energy location) {
		forget(location);
		memories.addLast(location); //alternate way: addFirst(location);
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