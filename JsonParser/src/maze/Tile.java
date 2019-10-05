package maze;

/**
 * @author kongzhan1
 *
 */
public abstract class Tile {
    public Object object;
    public boolean accessible;
    public int x,y;
    public abstract String toString();

    public boolean isAccessible() {
        return accessible;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
