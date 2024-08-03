import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

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

        if(!panel.modified) {
            if(e.getKeyCode()==37)
                panel.moveLeft(0);

            if(e.getKeyCode()==38)
                panel.moveUp(0);

            if(e.getKeyCode()==39)
                panel.moveRight(0);

            if(e.getKeyCode()==40)
                panel.moveDown(0);
        }

        if(e.getKeyCode()==37 || e.getKeyCode()==38 || e.getKeyCode()==39 || e.getKeyCode()==40) {

            /*Thread.sleep blocks the running of the current rule for a certain number of milliseconds
            and then continues executing the rest of the logic in the rule. A Timer spins up a separate thread
            which starts executing in the background after the Timer goes off. Which is why I preferred Timer here. */

            if (panel.modified) {

                Timer timer=new Timer();

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        panel.spawnNumber();
                        panel.modified=false;
                    }
                }, 300);

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}