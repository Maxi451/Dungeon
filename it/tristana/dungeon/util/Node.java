package it.tristana.dungeon.util;

public class Node<T> {

	private Node<T> leftLeaf;
	private Node<T> rightLeaf;

	private T value;

	public Node(T value) {
		this.value = value;
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
}
