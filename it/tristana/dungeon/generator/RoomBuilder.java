package it.tristana.dungeon.generator;

import java.awt.Dimension;
import java.util.Random;

import it.tristana.dungeon.tile.Tile;

public abstract class RoomBuilder {
	
	protected int width;
	protected int height;
	
	public RoomBuilder(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public Dimension getDimension() {
		return new Dimension(width, height);
	}
	
	public abstract void build(Tile[][] map, Random random, int x, int y);
}
