//Comp 182
//Spring 2019
//Allen Zohrabians
//Project 3
//BSTree.java
//This class contains our methods for insertion, deletion, and reinsertion of the nodes within the binary search tree.

public class BSTree<E extends Comparable<E>> {
	private BSTNode<E> root;
	
	public BSTree() {
		root = null;
	}
	
	public void insert(E element) {
		if (root != null) {
			insert(root, element);
		} else {
			root = new BSTNode<E>(element);
		}
	}
	
	private void insert(BSTNode<E> currentNode, E element) {
		if (currentNode.getElement().compareTo(element) > 0) {
			if (currentNode.getLeftChild() != null) {
				insert(currentNode.getLeftChild(), element);
			} else {
				currentNode.setLeftChild(new BSTNode<E>(element));
			}
		} else if (currentNode.getElement().compareTo(element) < 0) {
			if (currentNode.getRightChild() != null) {
				insert(currentNode.getRightChild(), element);
			} else {
				currentNode.setRightChild(new BSTNode<E>(element));
			}
		}
	}
	
	public void delete(E element) {
		if (root != null) {
			root = delete(root, element);
		}
	}
	
	private BSTNode<E> delete(BSTNode<E> currentNode, E element) {
		if (currentNode.getElement().compareTo(element) > 0) {
			if (currentNode.getLeftChild() != null) {
				currentNode.setLeftChild(delete(currentNode.getLeftChild(), element));
			}
			return currentNode;
		} else if (currentNode.getElement().compareTo(element) < 0) {
			if (currentNode.getRightChild() != null) {
				currentNode.setRightChild(delete(currentNode.getRightChild(), element));
			}
			return currentNode;
		} else {
			if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null) {
				return null;
			} else {
				BSTNode<E> replacement;
				BSTNode<E> otherNode;
				if (currentNode.getRightChild() == null) {
					replacement = currentNode.getLeftChild();
					currentNode.setLeftChild(null);
					return replacement;
				} else if (currentNode.getLeftChild() == null) {
					replacement = currentNode.getRightChild();
					currentNode.setRightChild(null);
					return replacement;
				} else {
					replacement = currentNode.getRightChild();
					otherNode = currentNode.getLeftChild();
					currentNode.setRightChild(null);
					currentNode.setLeftChild(null);
					insertOtherNode(replacement, otherNode);
					return replacement;
				}
			}
		}
	}
	
	private void insertOtherNode(BSTNode<E> currentNode, BSTNode<E> insertNode) {
		if (currentNode.getElement().compareTo(insertNode.getElement()) > 0) {
			if (currentNode.getLeftChild() != null) {
				insertOtherNode(currentNode.getLeftChild(), insertNode);
			} else {
				currentNode.setLeftChild(insertNode);
			}
		} else if (currentNode.getElement().compareTo(insertNode.getElement()) < 0) {
			if (currentNode.getRightChild() != null) {
				insertOtherNode(currentNode.getRightChild(), insertNode);
			} else {
				currentNode.setRightChild(insertNode);
			}
		}
	}
	
	public void nodeLevel(Sample sampleObject) {
		if (root != null) {
			nodeLevel(root, 1, sampleObject);
		}
	}
	
	private void nodeLevel(BSTNode<E> currentNode, int height, Sample sampleObject) {
		if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null) {
			sampleObject.add(height);
		} else {
			if (currentNode.getLeftChild() != null) {
				nodeLevel(currentNode.getLeftChild(), height + 1, sampleObject);
			}
			if (currentNode.getRightChild() != null) {
				nodeLevel(currentNode.getRightChild(), height + 1, sampleObject);
			}
		}
	}
}

	
