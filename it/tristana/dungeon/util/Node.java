package it.tristana.dungeon.util;

public class Node<T> {

	private static char current = 'A';
	
	private Node<T> leftLeaf;
	private Node<T> rightLeaf;

	private T value;
	private char val;

	public Node(T value) {
		this.value = value;
		this.val = current ++;
	}

	public Node<T> getLeftLeaf() {
		return leftLeaf;
	}

	public void setLeftLeaf(Node<T> leftLeaf) {
		this.leftLeaf = leftLeaf;
	}

	public Node<T> getRightLeaf() {
		return rightLeaf;
	}

	public void setRightLeaf(Node<T> rightLeaf) {
		this.rightLeaf = rightLeaf;
	}

	public T getValue() {
		return value;
	}
	
	public char getChar() {
		return val;
	}
	
	public boolean isLeaf() {
		return leftLeaf == null && rightLeaf == null;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
}
