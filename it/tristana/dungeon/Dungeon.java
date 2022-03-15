package it.tristana.dungeon;

import java.awt.Dimension;
import java.util.Random;

import it.tristana.dungeon.generator.Room;
import it.tristana.dungeon.generator.RoomBuilder;
import it.tristana.dungeon.tile.SpawnTile;
import it.tristana.dungeon.tile.Tile;
import it.tristana.dungeon.tile.TreasureTile;
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
		Node<Structure> root = tree.getRoot();
		if (tree.split(root, random, 3, 3)) {
			System.out.println("OK");
			Structure left = root.getLeftLeaf().getValue();
			Structure right = root.getRightLeaf().getValue();
			System.out.println(String.format("%d %d %d %d", left.getMinX(), left.getMinY(), left.getMaxX(), left.getMaxY()));
			System.out.println(String.format("%d %d %d %d", right.getMinX(), right.getMinY(), right.getMaxX(), right.getMaxY()));
			for (int i = left.getMinX(); i < left.getMaxX(); i ++) {
				for (int ii = left.getMinY(); ii < left.getMaxY(); ii ++) {
					map[i][ii] = new SpawnTile();
				}
			}
			for (int i = right.getMinX(); i < right.getMaxX(); i ++) {
				for (int ii = right.getMinY(); ii < right.getMaxY(); ii ++) {
					map[i][ii] = new TreasureTile();
				}
			}
		} else {
			System.out.println("NO");
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
