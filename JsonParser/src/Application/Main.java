package Application;

import Persistence.JsonSaver;

public class Main {
    public static void main(String[] args){
        Game game = new Game();
        JsonSaver.makeJson(game);
//        GraphicalUserInterface g = new GraphicalUserInterface();
    }
}
