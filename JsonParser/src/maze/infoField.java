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
		fileName = "QuestionMark.png";
	}

	public infoField() {
		accessible=true;
		fileName = "QuestionMark.png";
	}

	@Override
	public String toString() {
		return " I ";
	}

	@Override
	public Object getObject() {
		return null;
	}

	@Override
	public String getType() {
		return "infoField";
	}

	@Override
	public String getFileName() {
		return fileName;
	}
}
