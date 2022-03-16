package it.tristana.dungeon.tile;

public class NamedTile implements Tile {

	private char name;
	
	public NamedTile(char name) {
		this.name = name;
	}
	
	@Override
	public char getDisplayChar() {
		return name;
	}
}
