package it.tristana.dungeon.generator;

import java.util.Random;

import it.tristana.dungeon.tile.FloorTile;
import it.tristana.dungeon.tile.Tile;

public class EmptyRoomBuilder extends RoomBuilder {
	
	public EmptyRoomBuilder() {
		super(5, 4);
	}
	
	@Override
	public void build(Tile[][] map, Random random, int x, int y) {
		for (int i = x; i < x + width; i ++) {
			for (int ii = y; ii < y + height; ii ++) {
				map[i][ii] = new FloorTile();
			}
		}
	}
}
