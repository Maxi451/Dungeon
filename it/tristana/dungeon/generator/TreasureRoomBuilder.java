package it.tristana.dungeon.generator;

import java.util.Random;

import it.tristana.dungeon.tile.FloorTile;
import it.tristana.dungeon.tile.Tile;
import it.tristana.dungeon.tile.TreasureTile;

public class TreasureRoomBuilder extends RoomBuilder {
	
	public TreasureRoomBuilder() {
		super(3, 3);
	}
	
	@Override
	public void build(Tile[][] map, Random random, int x, int y) {
		for (int i = x; i < x + width; i ++) {
			for (int ii = y; ii < y + height; ii ++) {
				map[i][ii] = new FloorTile();
			}
		}
		map[x + width / 2][y + height / 2] = new TreasureTile();
	}
}
