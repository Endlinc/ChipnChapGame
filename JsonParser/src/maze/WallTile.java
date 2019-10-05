package maze;

/**
 * @author kongzhan1
 *
 */
public class WallTile extends Tile {

    public WallTile(int x,int y) {
        this.x=x;
        this.y=y;
        accessible=false;
    }

    @Override
    public String toString() {
        return " W ";
    }
}
