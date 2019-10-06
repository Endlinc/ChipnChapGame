package Persistence;

import Application.Board;
import Application.Game;
import maze.Chap;

import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.Pattern;

public class JsonLoader {

    public static void importJson(String path) {
        try {
//            System.out.println(new File("./").getAbsolutePath());
            BufferedReader bf = new BufferedReader(new FileReader(new File(path)));
            String content = "";
            String holder = null;
            while ((holder = bf.readLine()) != null) {
                content = content + "\n" + holder;
            }

            System.out.println("===============");
//            System.out.println(content);
            reloadGame(content);
            System.out.println("===============");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WrongFileException e) {
            e.printStackTrace();
        }
    }

    private static Game reloadGame (String content) throws WrongFileException {
        // create game component
        Chap chap = null;
        Board board = null;
        boolean gameWon = false;
        // read specie content and reload
        Scanner sc = new Scanner(content).useDelimiter("\\s+|(?=[:{}(),;])|(?<=[{}(),;])|\"");
        if (!sc.next().equals("{")) {
            throw new WrongFileException("Incorrect file format.");
        }
        while (sc.hasNext()) {
            if (sc.next().equals("Chap")) {
                reloadChap(sc);
            }
            else if (sc.next().equals("Board")) {
                reloadBoard(sc);
            }
            else if (sc.next().equals("GameWon")) {
                gameWon = Boolean.parseBoolean(sc.next());
            }
        }
        return null;
    }

    private static Chap reloadChap(Scanner sc) {
        return null;
    }

    private static Board reloadBoard(Scanner sc) {
        return null;
    }
}
