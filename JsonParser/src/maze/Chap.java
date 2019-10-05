package maze;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kongzhan1
 *
 */
public class Chap implements Object{

    public List<Object> inventory = new ArrayList<Object>();
    public Tile t;
	private int x,y;
	/**
	 * @param x
	 * @param y
	 */
	public Chap(int x, int y) {
		this.x=x;
		this.y=y;
	}

	public Chap(Tile t) {
		this.t=t;
	}

	public Tile getLocation(){
		return t;
	}
	public void setLocation(Tile t) {
		this.t = t;
		this.x = t.getX();
		this.y = t.getY();
	}

	public boolean addInventory(Object o){
		if(o!=null){
			inventory.add(o);
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * @return inventory
	 */
	public List<Object> getInventory() {
		return inventory;
	}
	/**
	 * @param inventory
	 */
	public void setInventory(ArrayList<Object> inventory) {
		this.inventory = inventory;
	}
	/**
	 * @return x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

}
