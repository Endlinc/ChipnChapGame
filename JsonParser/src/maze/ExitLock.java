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
     }

	@Override
	public String toString() {
		return " L ";
	}
}
