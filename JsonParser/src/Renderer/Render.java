package Renderer;

import Application.Game;
import maze.FreeTile;
import maze.Key;
import maze.LockedDoor;
import maze.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;


public class Render {
    public void renderGame(Graphics2D g, int width, int height, Game game){
        System.out.println("Render");
        System.out.println(game.getBoard().toString());
        g.setColor(Color.BLACK);
        Tile[][] tiles= game.getBoard().getBoard();
        for(int i=0;i<tiles.length;i++){
            for(int j=0;j<tiles[0].length;j++){

                g.drawImage(getImage(tiles[i][j]),128*j,128*i,null);
                //g.setColor(Color.BLACK);
            }

        }
    }

    public void renderGame1(Graphics2D g, int width, int height, Game game){
        System.out.println(game.getBoard().toString());
        g.setColor(Color.BLACK);
        //g.fillRect(100,100,100,100);
        //g.drawString(game.getBoard().toString(),100,100);
        Tile[][] tiles= game.getBoard().getBoard();
        for(int i=0;i<tiles.length;i++){
            for(int j=0;j<tiles[0].length;j++){
                if(tiles[i][j] instanceof LockedDoor){
                    g.setColor(((LockedDoor)tiles[i][j]).getColor());
                }
                if(tiles[i][j] instanceof FreeTile){
                    if(tiles[i][j].object instanceof Key){
                        g.setColor(((Key)(tiles[i][j].object)).getColor());
                    }
                }
                g.drawString(tiles[i][j].toString(),100+20*j,100+20*i);
                g.setColor(Color.BLACK);
            }

        }
    }


    public Image getImage(Tile t){
        //System.out.println(t+" "+t.getFileName());
        java.net.URL imageUrl = Render.class.getResource("DrawingImage/" + t.getFileName());

        Image img;
        try {
            img = ImageIO.read(imageUrl);
            return img;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to load image" + t.getFileName());
        }
    }


    public Image getImage1(Tile t){
        String output = t.toString();
        String fileName = "";
        switch (output){
            case " _ ":
                fileName = "FreeTile.jpg";
                break;
            case " C ":
                fileName = "ChapSouth.jpg";
                break;
            case " E ":
                fileName = "Exit.jpg";
                break;
            case " L ":
                fileName = "exitLock.jpg";
                break;
            case " K ":
                fileName = "redKey.jpg";
                break;
            case " D ":
                fileName = "redDoor.jpg";
                break;
            case " T ":
                fileName = "treasure.jpg";
                break;
            case " W ":
                fileName = "walltile.jpg";
                break;
            case " I ":
                fileName = "QuestionMark.jpg";
                break;
        }
        java.net.URL imageUrl = Render.class.getResource("DrawingImage/" + fileName);

        Image img;
        try {
            img = ImageIO.read(imageUrl);
            return img;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to load image" + fileName);
        }
    }
}
