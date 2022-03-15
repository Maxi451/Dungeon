package it.tristana.dungeon;

import java.util.Random;

public class Main {

	private static final int WIDTH = 30;
	private static final int HEIGHT = 30;
	
	public static void main(String[] args) {
		Dungeon dungeon = new Dungeon(WIDTH, HEIGHT, new Random());
		dungeon.print();
	}
}
