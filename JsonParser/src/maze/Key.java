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
	private String fileName;

	public Color getColor() {
		return color;
	}

	public Key(Color color) {
		this.color = color;
		if(color.equals(Color.red)){
			fileName = "RedKey.png";
		}
		else if(color.equals(Color.blue)){
			fileName = "BlueKey.png";
		}
		else if(color.equals(Color.green)){
			fileName = "GreenKey.png";
		}
	}

	public LockedDoor getDoor() {
		return door;
	}

	public void setDoor(LockedDoor door){
		this.door = door;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String getFileName(){
		return fileName;
	}

	@Override
	public String getType() {
		return "Key";
	}
}
