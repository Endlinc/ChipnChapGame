import Persistence.*;

import javax.swing.*;
import javax.swing.plaf.FileChooserUI;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Character> c = new ArrayList<Character>();
        c.add('a');
        c.add('b');
        Game game = new Game(c, new Board());

        String s = new JsonParser().byJsonObject(game);
        String s1 = new JsonParser().byJsonArray(game);
        new JsonSaver().makeJson(game);
        String path = "ChapSave.JSON";
        new JsonLoader().importJson(path);

        System.out.println(s);
        System.out.println(s1);
    }
}
