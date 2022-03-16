package it.tristana.dungeon;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.tristana.dungeon.generator.Room;
import it.tristana.dungeon.generator.RoomBuilder;
import it.tristana.dungeon.tile.NamedTile;
import it.tristana.dungeon.tile.Tile;
import it.tristana.dungeon.tile.WallTile;
import it.tristana.dungeon.util.BinaryTree;
import it.tristana.dungeon.util.Node;
import it.tristana.dungeon.util.Structure;

public class Dungeon {

	private final Tile[][] map;
	private final Random random;

	private int weight;

	public Dungeon(int width, int height, Random random) {
		this.map = new Tile[width][height];
		this.random = random;
		//placeExternalWalls();
		generate();
	}

	public void print() {
		row();
		for (int i = 0; i < map.length; i ++) {
			for (int ii = 0; ii < map[0].length; ii ++) {
				System.out.print("| " + (map[i][ii] == null ? ' ' : map[i][ii].getDisplayChar()) + " ");
			}
			System.out.println("|");
			row();
		}
	}

	private void generate() {
		//placeStartingRoom();
		BinaryTree tree = new BinaryTree(map.length, map[0].length);
		tree.splitAll(random, 3, 3);
		List<Node<Structure>> nodes = new ArrayList<>();
		nodes.add(tree.getRoot());
		boolean nodeFound;
		do {
			nodes.forEach(node -> {
				if (!node.isLeaf()) {
					return;
				}
				drawNode(node);
			});
			nodeFound = false;
			List<Node<Structure>> nextGenNodes = new ArrayList<>();
			for (Node<Structure> node : nodes) {
				boolean isLeaf = node.isLeaf();
				nodeFound = nodeFound || !isLeaf;
				if (!isLeaf) {
					nextGenNodes.add(node.getLeftLeaf());
					nextGenNodes.add(node.getRightLeaf());
				}
			}
			nodes = nextGenNodes;
		} while (nodeFound);
	}

	private void drawNode(Node<Structure> node) {
		Structure structure = node.getValue();
		int minX = structure.getMinX();
		int maxX = structure.getMaxX();
		int minY = structure.getMinY();
		int maxY = structure.getMaxY();
		char name = node.getChar();
		for (int i = minX; i < maxX; i ++) {
			map[i][minY] = new NamedTile(name);
			map[i][maxY - 1] = new NamedTile(name);
		}
		for (int ii = minY; ii < maxY; ii ++) {
			map[minX][ii] = new NamedTile(name);
			map[maxX - 1][ii] = new NamedTile(name);
		}
	}

	private void placeStartingRoom() {
		build(Room.SPAWN, map.length / 2, map[0].length / 2);
	}

	private void build(Room room, int x, int y) {
		RoomBuilder builder = room.getBuilder();
		builder.build(map, random, x, y);
		Dimension dim = builder.getDimension();
		for (int i = x - 1; i < x + dim.width + 1; i ++) {
			addWall(i, y - 1);
			addWall(i, y + dim.height);
		}
		for (int ii = y; ii < y + dim.height; ii ++) {
			addWall(x - 1, ii);
			addWall(x + dim.width, ii);
		}
	}

	private void addWall(int x, int y) {
		placeIfAllowed(new WallTile(), x, y);
	}

	private void placeExternalWalls() {
		for (int i = 0; i < map.length; i ++) {
			map[i][0] = new WallTile();
			map[i][map[0].length - 1] = new WallTile();
		}
		for (int ii = 1; ii < map[0].length - 1; ii ++) {
			map[0][ii] = new WallTile();
			map[map.length - 1][ii] = new WallTile();
		}
	}

	private boolean placeIfAllowed(Tile tile, int x, int y) {
		boolean flag = x >= 0
				&& x < map.length - 1
				&& y >= 0
				&& y < map[0].length - 1
				&& map[x][y] == null;
				if (flag) {
					map[x][y] = tile;
				}
				return flag;
	}

	private void row() {
		for (int i = 0; i < map.length; i ++) {
			System.out.print("+---");
		}
		System.out.println("+");
	}
}
