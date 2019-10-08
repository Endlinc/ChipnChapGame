package Application;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RenderPanel extends JPanel implements KeyListener {
    private Game game;
    RenderPanel(Game game){
        this.game = game;
    }
    public void newGame(Game game){
        this.game=game;
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT||e.getKeyCode() == KeyEvent.VK_A) {
            game.movePlayer(game.getChap(), Game.Direction.WEST);
            this.repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT||e.getKeyCode() == KeyEvent.VK_D) {
            game.movePlayer(game.getChap(), Game.Direction.EAST);
            this.repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_UP||e.getKeyCode() == KeyEvent.VK_W) {
            game.movePlayer(game.getChap(), Game.Direction.NORTH);
            this.repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN||e.getKeyCode() == KeyEvent.VK_S) {
            game.movePlayer(game.getChap(), Game.Direction.SOUTH);
            this.repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            return;
        } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
            return;
        } else if ((e.getKeyCode() == KeyEvent.VK_X) && (e.isControlDown())) {
            return;
        } else if ((e.getKeyCode() == KeyEvent.VK_S) && (e.isControlDown())) {
            return;
        } else if ((e.getKeyCode() == KeyEvent.VK_R) && (e.isControlDown())) {
            return;
        } else if ((e.getKeyCode() == KeyEvent.VK_P) && (e.isControlDown())) {
            return;
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            return;
        } else {
            System.out.println("Wrong Keyword input");
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
