package Application;

import Persistence.JsonLoader;
import Persistence.JsonSaver;

import javax.swing.plaf.FileChooserUI;
import java.io.File;

public class Main {
    public static void main(String[] args){
        Game game = new Game();
//        JsonSaver.makeJson(game);
        JsonLoader.importJson("ChapSave2019-10-07-17:34:02.JSON");
//        GraphicalUserInterface g = new GraphicalUserInterface();
    }
}
