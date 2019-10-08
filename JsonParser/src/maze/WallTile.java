package maze;

/**
 * @author kongzhan1
 *
 */
public class WallTile extends Tile {

    public WallTile(int x,int y) {
        this.x=x;
        this.y=y;
        fileName = "WallTile.png";
        accessible=false;
    }

    @Override
    public String toString() {
        return " W ";
    }

    @Override
    public Object getObject() {
        return null;
    }

    @Override
    public String getType() {
        return "WallTile";
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
