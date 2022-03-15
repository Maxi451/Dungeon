package it.tristana.dungeon.generator;

public enum Room {

	SPAWN(new StartingRoomBuilder(), 0, 0),
	EMPTY(new EmptyRoomBuilder(), 1, 1),
	TREASURE(new TreasureRoomBuilder(), 1, 1);
	
	public static final Room[] rooms = values();
	
	private RoomBuilder builder;
	private int chance;
	private int weight;
	
	Room(RoomBuilder builder, int chance, int weight) {
		this.builder = builder;
		this.chance = chance;
		this.weight = weight;
	}

	public RoomBuilder getBuilder() {
		return builder;
	}

	public int getChance() {
		return chance;
	}

	public int getWeight() {
		return weight;
	}
}
