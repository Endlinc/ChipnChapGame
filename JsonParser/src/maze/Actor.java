package maze;

import java.util.ArrayList;
import java.util.List;

public class Actor implements Object{
    public List<Integer> path = new ArrayList<Integer>();

    @Override
    public String getType() {
        return "Actor";
    }

    @Override
    public String getFileName() {
        return null;
    }
}
