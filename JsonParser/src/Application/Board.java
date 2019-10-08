package Application;

import maze.*;
import maze.Object;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    Tile[][] board;
    private List<String> convertString = new ArrayList<>();
    public Board(){
        board = new Tile[9][9];
        setBoard();
    }

    public Board(Tile[][] layout) {
        board = layout;
    }

    @Override
    public String toString() {
        String output = "";
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                output = output+ board[i][j].toString();
            }
            output = output+"\n";
        }
        return output;
    }
    public Tile getLocation(int x, int y){
        return board[x][y];
    }
    public Tile[][] getBoard() {
        return board;
    }

    public void setBoard(){
        buildDoor();
        buildWall();
        buildInfoField();
        buildKey();
        buildTreasure();
        buildFreeTile();
        buildExitandExitLock();
    }
    public void buildWall(){
        board[3][0] = new WallTile(3,0);
        board[5][0] = new WallTile(5,0);

        board[0][1] = new WallTile(0,1);
        board[1][1] = new WallTile(1,1);
        board[3][1] = new WallTile(3,1);
        board[5][1] = new WallTile(5,1);
        board[7][1] = new WallTile(7,1);
        board[8][1] = new WallTile(8,1);

        board[1][3] = new WallTile(1,3);
        board[7][3] = new WallTile(7,3);

        //board[0][4] = new WallTile(0,4);
        //board[1][4] = new WallTile(1,4);
        board[7][4] = new WallTile(7,4);
        board[8][4] = new WallTile(8,4);

        board[1][5] = new WallTile(1,5);
        board[7][5] = new WallTile(7,5);

        board[1][6] = new WallTile(1,6);

        board[3][7] = new WallTile(3,7);
        board[5][7] = new WallTile(5,7);
        board[7][7] = new WallTile(7,7);

        board[1][8] = new WallTile(1,8);
        board[2][8] = new WallTile(2,8);
        board[5][8] = new WallTile(5,8);
        board[7][8] = new WallTile(7,8);
        //////////////////////////////////////////////

        board[0][3] = new WallTile(0,3);
        board[0][5] = new WallTile(0,5);
    }
    public void buildTreasure (){
        board[1][0] = new FreeTile(1,0,new Treasure());
        //board[0][3] = new FreeTile(0,3,new Treasure());
        //board[0][5] = new FreeTile(0,5,new Treasure());
        board[7][0] = new FreeTile(7,0,new Treasure());
        board[8][3] = new FreeTile(8,3,new Treasure());
        board[8][7] = new FreeTile(8,7,new Treasure());
        board[3][8] = new FreeTile(3,8,new Treasure());
        board[6][8] = new FreeTile(6,8,new Treasure());
        /////////////////////////////////////////////////////////
        board[0][2] = new FreeTile(0,2,new Treasure());
        board[0][6] = new FreeTile(0,6,new Treasure());
    }

    public void buildKey(){
        board[2][3] = new FreeTile(2,3,new Key(Color.blue));
        board[6][3] = new FreeTile(6,3,new Key(Color.red));
        board[2][5] = new FreeTile(2,5,new Key(Color.blue));
        board[6][5] = new FreeTile(6,5,new Key(Color.red));
        //board[0][6] = new FreeTile(0,6,new Key(Color.green));
        board[8][5] = new FreeTile(8,5,new Key(Color.green));
        //////////////////////////////////////////////////////////////
        board[0][7] = new FreeTile(0,7,new Key(Color.green));
    }
    public void buildDoor(){
        board[2][1] = new LockedDoor(2,1,Color.green);
        board[1][2] =  new LockedDoor(1,2,Color.blue);
        board[1][7] =  new LockedDoor(1,7,Color.red);
        board[7][2] = new LockedDoor(7,2,Color.red);
        board[7][6] = new LockedDoor(7,6,Color.blue);
        board[6][1] = new LockedDoor(6,1,Color.green);
    }

    public void buildExitandExitLock(){
        board[0][4] = new Exit(0,4);
        board[1][4] = new ExitLock(1,4);
    }

    public void buildInfoField(){
        board[4][3] = new infoField();
    }


    public void buildFreeTile() {
        for (int i = 0; i < 9 ; i++) {
            for (int j = 0; j < 9; j++) {
                if(board[i][j] == null){
                    board[i][j] = new FreeTile(i,j);
                }
            }
        }
    }

    public Tile getTileByDirection(Tile t, Game.Direction dir){
        int x = t.getX();
        int y = t.getY();
        switch (dir) {
            case NORTH:
                if(x>0){
                    return getLocation(x-1,y);
                }
                break;
            case SOUTH:
                if(x<board.length){
                    return getLocation(x+1,y);
                }
                break;
            case EAST:
                if(y<board[0].length){
                    return getLocation(x,y+1);
                }
                break;
            case WEST:
                if(y>0){
                    return getLocation(x,y-1);
                }
                break;
        }
        return null;
    }

//    public Tile[][] convert(List <String> l){
//        int j;
//        for(int i = 0; i < convertString.size(); i++){
//            if( i<=8 ){
//                j=0;
//                if(convertString.get(i) == "_"){
//                    board[i][j] = new FreeTile(i,j,null);
//                }
//                if(convertString.get(i) == "W"){
//                    board[i][j] = new WallTile(i,j);
//                }
//                if(convertString.get(i) == "T"){
//                    board[i][j] = new FreeTile(i,j,new Treasure());
//                }
//                if(convertString.get(i) == "K"){
//                    board[i][j] = new FreeTile(i,j,new Key(Color.blue));//now give all key blue color!!!!!!!!!!!!!!
//                }
//                if(convertString.get(i) == "D"){
//                    board[i][j] = new LockedDoor(i,j,Color.blue);//now give all key blue color!!!!!!!!!!!!!!
//                }
//            }
//
//            if( i>8 && i<=17 ){
//                j=1 ;
//            }
//        }
//        return board;
//    }

}



