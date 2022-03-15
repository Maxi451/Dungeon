package it.tristana.dungeon.util;

public class Structure {

	private Point min;
	private Point max;
	
	public Structure(Point p1, Point p2) {
		this.min = new Point(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y));
		this.max = new Point(Math.max(p1.x, p2.x), Math.max(p1.y, p2.y));
	}
	
	public int getMinX() {
		return min.x;
	}
	
	public int getMinY() {
		return min.y;
	}
	
	public int getMaxX() {
		return max.x;
	}
	
	public int getMaxY() {
		return max.y;
	}
}
