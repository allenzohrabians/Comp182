//Comp 182
//Spring 2019
//Allen Zohrabians
//Project 3
//BSTNode.java
//This class implements Comparable nodes for our binary search tree. It also holds the get and set methods for the left child and the right child

public class BSTNode<E extends Comparable<E>> {
	private E element;
	private BSTNode<E> leftChild;
	private BSTNode<E> rightChild;
	
	public BSTNode(E newElement) {
		element = newElement;
		leftChild = null;
		rightChild = null;
	}
	
	public E getElement() {
		return element;
	}
	
	public BSTNode<E> getLeftChild() {
		return leftChild;
	}
	
	public BSTNode<E> getRightChild() {
		return rightChild;
	}
	
	public void setLeftChild(BSTNode<E> newChild) {
		leftChild = newChild;
	}
	
	public void setRightChild(BSTNode<E> newChild) {
		rightChild = newChild;
	}
}


