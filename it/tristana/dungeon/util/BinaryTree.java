package it.tristana.dungeon.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinaryTree {
	
	private Node<Structure> root;
	
	public BinaryTree(int width, int height) {
		this.root = new Node<>(new Structure(new Point(0, 0), new Point(width, height)));
	}
	
	public void splitAll(Random random, int minWidth, int minHeight) {
		List<Node<Structure>> nodes = new ArrayList<>();
		nodes.add(root);
		do {
			List<Node<Structure>> nextGen = new ArrayList<>();
			nodes.forEach(node -> {
				if (!split(node, random, minWidth, minHeight)) {
					return;
				}
				addIfNotNull(nextGen, node.getLeftLeaf());
				addIfNotNull(nextGen, node.getRightLeaf());
			});
			nodes = nextGen;
		} while (nodes.size() > 0);
	}
	
	public Node<Structure> getRoot() {
		return root;
	}
	
	public static boolean split(Node<Structure> parent, Random random, int minWidth, int minHeight) {
		Structure structure = parent.getValue();
		int minX = structure.getMinX();
		int minY = structure.getMinY();
		int maxX = structure.getMaxX();
		int maxY = structure.getMaxY();
		Point leftMin = new Point(minX, minY);
		Point leftMax;
		Point rightMin;
		Point rightMax = new Point(maxX, maxY);
		if (random.nextBoolean()) {
			int maxLength = getMax(maxY, minY, minHeight);
			if (maxLength <= 0) {
				return false;
			}
			int length = getLength(random, maxLength, minHeight);
			leftMax = new Point(maxX, minY + length);
			rightMin = new Point(minX, minY + length);
		} else {
			int maxLength = getMax(maxX, minX, minWidth);
			if (maxLength <= 0) {
				return false;
			}
			int length = getLength(random, maxLength, minWidth);
			leftMax = new Point(minX + length, maxY);
			rightMin = new Point(minX + length, minY);
		}
		parent.setLeftLeaf(new Node<>(new Structure(leftMin, leftMax)));
		parent.setRightLeaf(new Node<>(new Structure(rightMin, rightMax)));
		return true;
	}
	
	private static Pair<Point> getNodes(Random random, int minX, int maxX, int minY, int maxY, int minLength, boolean isHorizontal) {
		int max, min;
		if (isHorizontal) {
			max = maxY;
			min = minY;
		} else {
			max = maxX;
			min = minX;
		}
		int maxLength = getMax(max, min, minLength);
		if (maxLength <= 0) {
			return null;
		}
		int length = getLength(random, max, minLength);
		int leftX, leftY, rightX, rightY;
		if (isHorizontal) {
			leftX = maxX;
			leftY = minY + length;
			rightX = minX;
			rightY = minY + length;
		} else {
			leftX = minX + length;
			leftY = maxY;
			rightX = minX + length;
			rightY = minY;
		}
		return new Pair<>(new Point(leftX, leftY), new Point(rightX, rightY));
	}
	
	private static int getMax(int max, int min, int minLength) {
		return max - min - minLength * 2 - 1;
	}
	
	private static int getLength(Random random, int max, int minLength) {
		return random.nextInt(max) + minLength;
	}
	
	private static <T> void addIfNotNull(List<T> list, T object) {
		if (object != null) {
			list.add(object);
		}
	}
}
