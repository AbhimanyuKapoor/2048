import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GameFrame extends JFrame implements KeyListener{

    GamePanel panel;

    public GameFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(520,520);
        this.setTitle("2048");

        panel= new GamePanel();

        this.add(panel);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setLocationRelativeTo(null);

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==37)
            panel.moveLeft(0);

        if(e.getKeyCode()==38)
            panel.moveUp(0);

        if(e.getKeyCode()==39)
            panel.moveRight(0);

        if(e.getKeyCode()==40)
            panel.moveDown(0);

        if(e.getKeyCode()==37 || e.getKeyCode()==38 || e.getKeyCode()==39 || e.getKeyCode()==40) {
            panel.spawnNumber();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}