import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.util.Timer;
import java.util.TimerTask;

public class GameFrame extends JFrame implements KeyListener, ActionListener {

    //Static so that GamePanel can access this JLabel
    static JLabel score;

    JButton newGame;
    GamePanel panel;

    public GameFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(520,600);
        this.setResizable(false);
        this.setTitle("2048");
        this.setLayout(new BorderLayout());

        score= new JLabel();
        score.setVisible(true);
        score.setOpaque(true);
        score.setText("Score: 0");
        score.setHorizontalAlignment(0);
        score.setBackground(new Color(187, 173, 160));
        score.setForeground(new Color(238, 228, 218));
        score.setFont(new Font("Monospaced", Font.BOLD, 35));
        score.setBorder(new LineBorder(new Color(238, 228, 218), 5));

        newGame= new JButton();
        newGame.setVisible(true);
        newGame.setText("New Game");
        newGame.setForeground(new Color(238, 228, 218));
        newGame.setBackground(new Color(143, 122, 102));
        newGame.setFont(new Font("Monospaced", Font.BOLD, 30));
        newGame.addActionListener(this);
        newGame.setFocusable(false);
        newGame.setBorder(new LineBorder(new Color(238, 228, 218), 5));

        panel= new GamePanel();

        this.add(panel, BorderLayout.CENTER);
        this.add(score, BorderLayout.NORTH);
        this.add(newGame, BorderLayout.SOUTH);

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
                }, 150);
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==newGame)
            panel.reset();
    }
}