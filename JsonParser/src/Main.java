import Persistence.Board;
import Persistence.Game;
import Persistence.JsonParser;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Character> c = new ArrayList<Character>();
        c.add('a');
        c.add('b');
        Game game = new Game(c, new Board());

        String s = new JsonParser().byJsonObject(game);
        String s1 = new JsonParser().byJsonArray(game);

        System.out.println(s);
        System.out.println(s1);
    }
}
