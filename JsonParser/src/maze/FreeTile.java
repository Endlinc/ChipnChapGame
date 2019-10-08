package maze;

/**
 * @author kongzhan1
 *
 */
public class FreeTile extends Tile{

	public FreeTile(int x,int y,Object o) {
		this.x=x;
		this.y=y;
		setObject(o);
		accessible=true;
	}
	public FreeTile(int x,int y) {
		this.x=x;
		this.y=y;
		setObject(null);
		accessible=true;
	}

	public void setObject(Object o){
		object = o;
		if(o!=null){
			System.out.println("Object:"+o);
			fileName = o.getFileName();
		}
		else{
			fileName = "FreeTile.png";
		}

	}

	public Object getObject(){
		return object;
	}

	@Override
	public String getType() {
		return "FreeTile";
	}

	@Override
	public String getFileName() {
		return fileName;
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
