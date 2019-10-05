import Application.Game;
import Application.GraphicalUserInterface;
import Persistence.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Character> c = new ArrayList<Character>();
        c.add('a');
        c.add('b');
        new GraphicalUserInterface().save();

//        String path = "ChapSave.JSON";
//        new JsonLoader().importJson(path);
    }
}
