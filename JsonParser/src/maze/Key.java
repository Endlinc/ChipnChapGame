package maze;

import java.awt.*;

/**
 * @author kongzhan1
 *
 */
public class Key implements Object {
	private int x, y;
	private Color color;
	private boolean pickedUp = false;
	private String type = "key";
	private LockedDoor door;

	public Key(Color color) {
		this.color = color;
	}

	public LockedDoor getDoor() {
		return door;
	}

	public void setDoor(LockedDoor door){
		this.door = door;
	}

	@Override
	public String getType() {
		return "key";
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Color getColor() {
		return color;
	}

	public boolean isPickedUp() {
		return pickedUp;
	}
}
