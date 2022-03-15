package it.tristana.dungeon.generator;

import java.util.Random;

import it.tristana.dungeon.tile.SpawnTile;
import it.tristana.dungeon.tile.Tile;

public class StartingRoomBuilder extends RoomBuilder {
	
	public StartingRoomBuilder() {
		super(5, 5);
	}
	
	@Override
	public void build(Tile[][] map, Random random, int x, int y) {
		for (int i = x; i < x + width; i ++) {
			for (int ii = y; ii < y + height; ii ++) {
				map[i][ii] = new SpawnTile();
			}
		}
	}
}
