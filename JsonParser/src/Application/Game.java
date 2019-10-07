package Application;

import maze.*;
import maze.Object;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    public Game(){
        board = new Board();
        gameWon = false;

        boolean set = setChap(board,4,4);
//        startGame();

    }

    public Game(Chap chap, Board board, boolean gameWon) {
        this.chap = chap;
        this.board = board;
        this.gameWon = gameWon;
    }

    public Board getBoard() {
        return board;
    }

    public Chap getChap() {
        return chap;
    }

    private Board board;
    private int ChapX;
    private int ChapY;
    private Chap chap;

    public boolean isGameWon() {
        return gameWon;
    }

    private boolean gameWon;


    public enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST;
    }

    public void startGame(){
        boolean set = setChap(board,4,4);
        System.out.println(set);
        System.out.println(board);
        while(!gameWon){
            // ask input
            System.out.println("Move the Chap: \n");
            String input = keyboardInput();
            Direction dir=null;
            switch (input) {
                case "a":
                    dir = Direction.WEST;
                    break;
                case "d":
                    dir = Direction.EAST;
                    break;
                case "w":
                    dir = Direction.NORTH;
                    break;
                case "s":
                    dir = Direction.SOUTH;
            }
            if(dir!=null){
                System.out.println(dir);
                movePlayer(chap,dir);
                System.out.println(board);
            }

        }

    }

    public String keyboardInput(){
        InputStreamReader in  = new InputStreamReader(System.in);
        BufferedReader keyboard = new BufferedReader(in);
        try{
            return keyboard.readLine();
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }catch (NumberFormatException e){
            e.printStackTrace();
            return "";
        }

    }

    public boolean setChap(Board b,int x,int y) {
        board = b;
        Tile t = b.getLocation(x,y);
        ChapX = x;
        ChapY = y;
        chap= new Chap(x, y);
        if(t instanceof FreeTile){
            ((FreeTile)t).setObject((Object) chap);
            chap.setLocation(t);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * player move by direction way.
     * @param chap - who will move
     * @param dir - the direction of move
     * @return boolean - whether successfully move or not
     */
    public boolean movePlayer(Chap chap, Direction dir) {
        FreeTile ChapLoc =(FreeTile) chap.getLocation();
        //System.out.println(ChapLoc);
        if(!isValidMove(ChapLoc,dir)){
            return false;
        }
        if(isValidMove(ChapLoc,dir)){
            Tile next = board.getTileByDirection(ChapLoc,dir);
            if(next instanceof FreeTile){
                if(((FreeTile) next).getObject()!=null){
                    Object inventory = ((FreeTile) next).getObject();
                    chap.addInventory(inventory);
                }
                ChapLoc.object=null;
                ((FreeTile) next).setObject(chap);
                chap.setLocation(next);
            }
            return true;
        }
        return false;
    }


    public boolean isValidMove(FreeTile chapLoc,Direction dir){
        //System.out.println(chapLoc+" "+dir);
        switch (dir) {
            case NORTH:
                if(chapLoc.getX()>0){
                    Tile next = board.getTileByDirection(chapLoc,dir);
                    if(next.isAccessible()){
                        return true;
                    }
                }
                break;
            case SOUTH:
                if(chapLoc.getX()<board.getBoard().length){
                    Tile next = board.getTileByDirection(chapLoc,dir);
                    if(next.isAccessible()){
                        return true;
                    }
                }
                break;
            case EAST:
                if(chapLoc.getY()<board.getBoard()[0].length){
                    Tile next = board.getTileByDirection(chapLoc,dir);
                    if(next.isAccessible()){
                        return true;
                    }
                }
                break;
            case WEST:
                if(chapLoc.getY()>0){
                    Tile next = board.getTileByDirection(chapLoc,dir);
                    if(next.isAccessible()){
                        return true;
                    }
                }
                break;
        }
        return false;
    }

}


