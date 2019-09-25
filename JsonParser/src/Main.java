import Persistence.Board;
import Persistence.Game;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Main {
    static Gson gson = new Gson();
    public static void main(String[] args) {
        ArrayList<Character> c = new ArrayList<Character>();
        c.add('a');
        c.add('b');
        Game game = new Game(c, new Board());

        System.out.println(gson.toJson(game));
    }
}
