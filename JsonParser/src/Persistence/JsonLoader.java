package Persistence;

import Application.Board;
import Application.Game;
import maze.*;
import maze.Object;

import java.awt.*;
import java.io.*;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Pattern;

public class JsonLoader {

    public static void importJson(String path) {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(new File(path)));
            String content = "";
            String holder = null;
            while ((holder = bf.readLine()) != null) {
                content = content + "\n" + holder;
            }

            reloadGame(content);
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
        Scanner sc = new Scanner(content)
//                .useDelimiter(",|\"|:|\\s+|\t|\n");
                .useDelimiter("\\s+|(?=[{}(),;])|(?<=[{}();])");
        if (!sc.next().equals("{")/* && !sc.next().equals("\"")*/) {
            throw new WrongFileException("Incorrect file format.");
        }
        while (sc.hasNext()) {
            String clip = sc.next();
            if (clip.contains("Chap")) {
                chap = reloadChap(sc);
            }
            else if (clip.contains("Board")) {
                board = reloadBoard(sc);
            }
            else if (clip.contains("GameWon")) {
                gameWon = Boolean.parseBoolean(sc.next());
            }
        }
        return new Game(chap, board, gameWon);
    }

    private static Chap reloadChap(Scanner sc) throws WrongFileException {
        if (!sc.next().equals("{")) {
            throw new WrongFileException("Chap wrong start saving");
        }
        int x = 0, y = 0;
        if (sc.next().contains("ChapLoc")) {
            String s1 = sc.next();
            String s2 = sc.next();
            x = Integer.parseInt(String.valueOf(s1.charAt(1)));
            y = Integer.parseInt(String.valueOf(s2.charAt(0)));
            if (!sc.next().equals(",")) {
                throw new WrongFileException("Without separator in between");
            }
        }
        Chap chap = new Chap(x, y);
        if (sc.next().contains("Inventory") && sc.next().equals("[")) {
            while(!sc.hasNext("]")) {
                // add object to chap's inventory ( implement later with species)
                chap.addInventory(new Key(Color.red));
            }
            sc.next();
        }
        String hold = sc.next();
        if (!hold.equals("}") && !sc.next().equals(",")) {
            throw new WrongFileException("Chap wrong end saving");
        }

        return chap;
    }

    private static Board reloadBoard(Scanner sc) throws WrongFileException {
        if (!sc.next().equals("{")) {
            throw new WrongFileException("Board wrong start saving");
        }
        Tile[][] layout = new Tile[9][9];
        if (sc.next().contains("BoardLayout")) {
            if (!sc.next().equals("[")) {
                throw new WrongFileException("BoardLayout wrong start saving");
            }
            int x = 0, y = 0;
            while (!sc.hasNext("]")) {
                try {
                    if (y < 9) {
                        layout[x][y] = reloadTile(sc);
                        y++;
                    }
                    else {
                        x++;
                        y = 0;
                        layout[x][y] = reloadTile(sc);
                        y++;
                    }
                } catch (Exception e) {
                    break;
                }
            }
            sc.next();
        }
        if (!sc.next().equals(",")) {
            throw new WrongFileException("Board wrong end saving");
        }
        return new Board(layout);
    }

    private static Tile reloadTile(Scanner sc) throws Exception {
        sc.next();
        Object o = null;
        String hold = sc.next();
        if (hold.contains("Object")) {
            sc.next();
            if (sc.next().contains("Type")) {
                String oType = sc.next();
                if (oType.contains("treasure")) {
                    o = new Treasure();
                    sc.next();
                }
                else if (oType.contains("Chap")) {
                    o = new Chap(4, 4);
                    sc.next();
                }
                else if (oType.contains("key")) {
                    sc.next();
                    sc.next();
                    String s1 = sc.next();
                    String s2 = sc.next();
                    String s3 = sc.next();
                    s1 = s1.substring(1);
                    s3 = s3.substring(0, s3.length()-1);
                    o = new Key(new Color(Integer.parseInt(s1), Integer.parseInt(s2), Integer.parseInt(s3)));
                    sc.next();
                    sc.next();
                    sc.next();
                    sc.next();
                    sc.next();
                }
            }
            sc.next();
        }
        Tile tile = null;
        sc.next();
        String type = sc.next();
        if (type.contains("FreeTile")) {
            sc.next();
            sc.next();
            String l1 = sc.next();
            String l2 = sc.next();
            l1 = l1.substring(1);
            l2 = l2.substring(0,1);
            tile = new FreeTile(Integer.parseInt(l1), Integer.parseInt(l2), o);
        }
        else if (type.contains("WallTile")) {
            sc.next();
            sc.next();
            String l1 = sc.next();
            String l2 = sc.next();
            l1 = l1.substring(1);
            l2 = l2.substring(0,1);
            tile = new WallTile(Integer.parseInt(l1), Integer.parseInt(l2));
        }
        else if (type.contains("LockedDoor")) {
            sc.next();
            sc.next();
            String l1 = sc.next();
            String l2 = sc.next();
            sc.next();
            sc.next();
            String l3 = sc.next();
            String l4 = sc.next();
            String l5 = sc.next();
            l1 = l1.substring(1);
            l2 = l2.substring(0,1);
            l3 = l3.substring(1);
            l5 = l5.substring(0, l5.length()-1);
            tile = new LockedDoor(Integer.parseInt(l1), Integer.parseInt(l2), new Color(Integer.parseInt(l3), Integer.parseInt(l4), Integer.parseInt(l5)));
        }
        else if (type.contains("infoField")) {
            sc.next();
            sc.next();
            String l1 = sc.next();
            String l2 = sc.next();
            sc.next();
            sc.next();
            sc.next();
            String l3 = sc.next();
            l3 = l3 + sc.next();
            l1 = l1.substring(1);
            l2 = l2.substring(0,1);
            l3 = l3.substring(0, l3.length()-1);
            tile = new infoField(Integer.parseInt(l1), Integer.parseInt(l2), l3);
        }
        else if (type.contains("ExitLock")) {
            sc.next();
            sc.next();
            String l1 = sc.next();
            String l2 = sc.next();
            l1 = l1.substring(1);
            l2 = l2.substring(0,1);
            tile = new ExitLock(Integer.parseInt(l1), Integer.parseInt(l2));
        }
        else if (type.contains("Exit")) {
            sc.next();
            sc.next();
            String l1 = sc.next();
            String l2 = sc.next();
            l1 = l1.substring(1);
            l2 = l2.substring(0,1);
            tile = new Exit(Integer.parseInt(l1), Integer.parseInt(l2));
        }
        sc.next();
        if (sc.next().equals("]")) throw new Exception();
        return tile;
    }
}
