package maze;

/**
 * @author kongzhan1
 *
 */
public class infoField extends Tile {
	private String info;

	public infoField(int x, int y,String info) {
		this.x=x;
		this.y=y;
		this.info = info;
		accessible=true;
	}

	public infoField() {
		accessible=true;
	}

	@Override
	public String toString() {
		return " I ";
	}
}
