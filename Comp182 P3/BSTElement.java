//Comp 182
//Spring 2019
//Allen Zohrabians
//Project 3
//BSTElement.java
//This class contains the integer key values for our element and implements Comparable amongst other values in the binary search tree.

public class BSTElement implements Comparable<BSTElement> {
	private int value;
	
	public BSTElement(int newValue) {
		value = newValue;
	}
	
	public int getValue() {
		return value;
	}
	
	public int compareTo(BSTElement otherElement) {
		if (getValue() > otherElement.getValue()) {
			return 1;
		} else if (getValue() < otherElement.getValue()) {
			return -1;
		} else {
			return 0;
		}
	}
}
