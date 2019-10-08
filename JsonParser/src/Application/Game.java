package Application;

import maze.*;
import maze.Object;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    public Game(){
        board = new Board();
        gameWon = false;

        startGame();

    }

    public Game(JPanel render){
        this.render=render;
        board = new Board();
        gameWon = false;

        startGame();
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
    private JPanel render;
    private int numberOfTreasure=8;

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

    public void setTreasure(){
        numberOfTreasure = 8;
    }

    /**
     * player move by direction way.
     * @param chap - who will move
     * @param dir - the direction of move
     * @return boolean - whether successfully move or not
     */
    public boolean movePlayer(Chap chap, Direction dir) {
        FreeTile ChapLoc =(FreeTile) chap.getLocation();
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
                //ChapLoc.object=null;
                ChapLoc.setObject(null);
                ((FreeTile) next).setObject(chap);
                chap.setLocation(next);
            }
            return true;
        }
        return false;
    }


    public boolean isValidMove(FreeTile chapLoc,Direction dir){
        switch (dir) {
            case NORTH:
                if(chapLoc.getX()>0){
                    Tile next = board.getTileByDirection(chapLoc,dir);
                    if(next.isAccessible()){
                        return true;
                    }
                    else{
                        if(next instanceof LockedDoor){
                            Color r = ((LockedDoor) next).getColor();
                            for(Object o : chap.inventory){
                                if(o instanceof Key){
                                    if(((Key) o).getColor().equals(r)){
                                        next.accessible=true;
                                        board.board[next.x][next.y] = new FreeTile(next.x,next.y);

                                        chap.inventory.remove(o);
                                        return true;
                                    }
                                }
                            }
                        }
                        if(next instanceof ExitLock&&getAllTreasure()){
                            next.accessible=true;
                            board.board[next.x][next.y] = new FreeTile(next.x,next.y);
                            return true;
                        }
                    }
                }
                break;
            case SOUTH:
                if(chapLoc.getX()<board.getBoard().length-1){
                    Tile next = board.getTileByDirection(chapLoc,dir);
                    if(next.isAccessible()){
                        return true;
                    }
                    else{
                        if(next instanceof LockedDoor){
                            Color r = ((LockedDoor) next).getColor();
                            for(Object o : chap.inventory){
                                if(o instanceof Key){
                                    if(((Key) o).getColor().equals(r)){
                                        next.accessible=true;
                                        board.board[next.x][next.y] = new FreeTile(next.x,next.y);
                                        chap.inventory.remove(o);
                                        return true;
                                    }
                                }
                            }
                        }
                        if(next instanceof ExitLock&&getAllTreasure()){
                            next.accessible=true;
                            board.board[next.x][next.y] = new FreeTile(next.x,next.y);
                            return true;
                        }
                    }
                }
                break;
            case EAST:
                if(chapLoc.getY()<board.getBoard()[0].length-1){
                    Tile next = board.getTileByDirection(chapLoc,dir);
                    if(next.isAccessible()){
                        return true;
                    }
                    else{
                        if(next instanceof LockedDoor){
                            Color r = ((LockedDoor) next).getColor();
                            for(Object o : chap.inventory){
                                if(o instanceof Key){
                                    if(((Key) o).getColor().equals(r)){
                                        next.accessible=true;
                                        board.board[next.x][next.y] = new FreeTile(next.x,next.y);
                                        chap.inventory.remove(o);
                                        return true;
                                    }
                                }
                            }
                        }
                        if(next instanceof ExitLock&&getAllTreasure()){
                            next.accessible=true;
                            board.board[next.x][next.y] = new FreeTile(next.x,next.y);
                            return true;
                        }
                    }
                }
                break;
            case WEST:
                if(chapLoc.getY()>0){
                    Tile next = board.getTileByDirection(chapLoc,dir);
                    if(next.isAccessible()){
                        return true;
                    }
                    else{
                        if(next instanceof LockedDoor){
                            Color r = ((LockedDoor) next).getColor();
                            for(Object o : chap.inventory){
                                if(o instanceof Key){
                                    if(((Key) o).getColor().equals(r)){
                                        next.accessible=true;
                                        board.board[next.x][next.y] = new FreeTile(next.x,next.y);
                                        chap.inventory.remove(o);
                                        return true;
                                    }
                                }
                            }
                        }
                        if(next instanceof ExitLock&&getAllTreasure()){
                            next.accessible=true;
                            board.board[next.x][next.y] = new FreeTile(next.x,next.y);
                            return true;
                        }
                    }

                }
                break;
        }
        return false;
    }

    public boolean getAllTreasure(){
        int count = 0;
        for(Object o :chap.getInventory()){
            if(o instanceof Treasure){
                count++;
            }
        }
        return count==numberOfTreasure;
    }

}


