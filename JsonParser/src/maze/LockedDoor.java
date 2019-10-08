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
		if(color.equals(Color.red)){
			fileName = "RedDoor.png";
		}
		else if(color.equals(Color.blue)){
			fileName = "BlueDoor.png";
		}
		else if(color.equals(Color.green)){
			fileName = "GreenDoor.png";
		}
	}
	public LockedDoor() {

	}
	public Color getColor(){
		return color;
	}
	@Override
	public String toString() {
		return " D ";
	}

	@Override
	public Object getObject() {
		return null;
	}

	@Override
	public String getType() {
		return "LockedDoor";
	}

	@Override
	public String getFileName() {
		return fileName;
	}
}
