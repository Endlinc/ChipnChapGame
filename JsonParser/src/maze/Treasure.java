package maze;

/**
 * @author kongzhan1
 *
 */
public class Treasure implements Object {
	private String fileName;
	public Treasure(){
		fileName = "Treasure.png";
	}

	@Override
	public String getType() {
		return "Treasure";
	}

	@Override
	public String getFileName() {
		return fileName;
	}
}
