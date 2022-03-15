package it.tristana.dungeon.util;

import java.util.Random;

public class BinaryTree {
	
	private Node<Structure> root;
	
	public BinaryTree(int width, int height) {
		this.root = new Node<>(new Structure(new Point(0, 0), new Point(width, height)));
	}
	
	public boolean split(Node<Structure> parent, Random random, int minWidth, int minHeight) {
		boolean isHorizontal = random.nextBoolean();
		System.out.println(isHorizontal);
		Structure structure = parent.getValue();
		int minX = structure.getMinX();
		int minY = structure.getMinY();
		int maxX = structure.getMaxX();
		int maxY = structure.getMaxY();
		Point minLeft = new Point(minX, minY);
		Point maxLeft;
		Point minRight;
		Point maxRight = new Point(maxX, maxY);
		if (isHorizontal) {
			if (maxY - minY - minHeight <= 0) {
				return false;
			}
			int height = random.nextInt(maxY - minY - minHeight) + minHeight;
			maxLeft = new Point(height, maxX);
			minRight = new Point(minX, height + 1);
		} else {
			if (maxX - minX - minWidth <= 0) {
				return false;
			}
			int width = random.nextInt(maxX - minX - minWidth) + minWidth;
			maxLeft = new Point(maxY, width);
			minRight = new Point(width + 1, minY);
		}
		parent.setLeftLeaf(new Node<>(new Structure(minLeft, maxLeft)));
		parent.setRightLeaf(new Node<>(new Structure(minRight, maxRight)));
		return true;
	}
	
	public Node<Structure> getRoot() {
		return root;
	}
}
