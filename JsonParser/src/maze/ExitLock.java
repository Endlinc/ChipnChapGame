package maze;

/**
 * @author kongzhan1
 *
 */
public class ExitLock extends Tile{

    public ExitLock(int x,int y) {
    	this.x=x;
    	this.y=y;
		accessible=false;
		fileName = "ExitDoor.png";
     }

	@Override
	public String toString() {
		return " L ";
	}

	@Override
	public Object getObject() {
		return null;
	}

	@Override
	public String getType() {
		return "ExitLock";
	}

	@Override
	public String getFileName() {
		return fileName;
	}
}
