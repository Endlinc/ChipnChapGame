package maze;

/**
 * @author kongzhan1
 *
 */
public class FreeTile extends Tile{

	public FreeTile(int x,int y,Object o) {
		this.x=x;
		this.y=y;
		this.object = o;
		accessible=true;
	}
	public FreeTile(int x,int y) {
		this.x=x;
		this.y=y;
		this.object = null;
		accessible=true;
	}

	public void setObject(Object o){
		object = o;
	}

	public Object getObject(){
		return object;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}

	@Override
	public String toString() {
		if(object==null) return " _ ";
		if(object instanceof Key){
			return " K ";
		}
		else if(object instanceof Treasure){
			return " T ";
		}
		else if(object instanceof Chap){
			return " C ";
		}
		else{
			return " _ ";
		}

	}
}
