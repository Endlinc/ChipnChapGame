package maze;

import java.awt.*;

/**
 * @author kongzhan1
 *
 */
public class LockedDoor extends Tile {
	private Color color;

	public LockedDoor(int x,int y,Color color) {
		this.x=x;
		this.y=y;
		this.color = color;
		accessible=false;
	}
	public LockedDoor() {

	}

	@Override
	public String toString() {
		return " D ";
	}
}