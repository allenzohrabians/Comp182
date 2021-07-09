//Comp 182
//Spring 2019
//Allen Zohrabians
//Project 2
//RobotMemory.java
//This class contains our main method, the symbolic constants, and is where the simulations run. 

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class RobotMemory {
	public static final int SIMULATION_TRIALS = 1000;
	public static final int PLANE_EXTENT = 200;
	public static final int STEP = 13;
	public static final double SNAP_DISTANCE = 9.0;
	public static final double ROBOT_ENERGY_CAPACITY = 800.0;
	public static final double ROBOT_DETECTION_DISTANCE = 7.0;
	public static final int ENERGY_LOCATIONS = 200;
	public static final double ENERGY_INTERVAL_DISTANCE = 20.0;
	public static final double ENERGY_INTERVAL_CAPACITY = 600.0;
	
	private ArrayList<Energy> locations;
	private StackRobot stackRobot;
	private QueueRobot queueRobot;
	private Sample stackRobotTraveled;
	private Sample queueRobotTraveled;
	private Random random;
	
	public static void main(String[] args) {
		new RobotMemory();
	}
	
	public RobotMemory() {
		stackRobotTraveled = new Sample("Stack Robot Traveled");
		queueRobotTraveled = new Sample("Queue Robot Traveled");
		random = new Random();
		for (int i = 0; i < SIMULATION_TRIALS; i++) {
			simulation();
		}
		stackRobotTraveled.computeStats();
		System.out.println(stackRobotTraveled);
		queueRobotTraveled.computeStats();
		System.out.println(queueRobotTraveled);
	}
	
	public void simulation() {
		locations = new ArrayList<Energy>();
		while (locations.size() < ENERGY_LOCATIONS) {
			int randomX = random.nextInt((PLANE_EXTENT * 2) + 1);
			int randomY = random.nextInt((PLANE_EXTENT * 2) + 1);
			Point randomPoint = new Point(randomX - PLANE_EXTENT, randomY - PLANE_EXTENT);
			if (isFarEnough(randomPoint)) {
				locations.add(new Energy(randomPoint, ENERGY_INTERVAL_CAPACITY));
			}
		}
		stackRobot = new StackRobot(ROBOT_ENERGY_CAPACITY, locations, "Stack Robot");
		queueRobot = new QueueRobot(ROBOT_ENERGY_CAPACITY, locations, "Queue Robot");
		while (stackRobot.isActive() || queueRobot.isActive()) {
			if (stackRobot.isActive() && !queueRobot.isActive()) {
				stackRobot.move();
			} else if (!stackRobot.isActive() && queueRobot.isActive()) {
				queueRobot.move();
			} else {
				boolean randomBool = random.nextBoolean();
				if (randomBool) {
					stackRobot.move();
				} else {
					queueRobot.move();
				}
			}
		}
		stackRobotTraveled.add(stackRobot.getTraveled());
		queueRobotTraveled.add(queueRobot.getTraveled());
	}
	
	public boolean isFarEnough(Point position) {
		boolean result = true;
		for (int i = 0; i < locations.size(); i++) {
			if (position.distance(locations.get(i)) <= ENERGY_INTERVAL_DISTANCE) {
				result = false;
				break;
			}
		}
		return result;
	}
}
