package maze;

/**
 * @author kongzhan1
 *
 */
public class Exit extends Tile {
	boolean locked=true;
	public Exit(int x,int y) {
		this.x=x;
		this.y=y;
        accessible=true;
        fileName = "Exit.png";
	}

	@Override
	public String toString() {
		return " E ";
	}

	@Override
	public Object getObject() {
		return null;
	}

	@Override
	public String getType() {
		return "Exit";
	}

	@Override
	public String getFileName() {
		return fileName;
	}
}

