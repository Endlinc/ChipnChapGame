package Persistence;

import java.util.ArrayList;
import java.util.List;

public class Game {
    List<Character> characters = new ArrayList<>();
    Board board = new Board();

    public Game(List<Character> characters, Board board) {
        this.characters.addAll(characters);
        this.board = board;
    }
}
