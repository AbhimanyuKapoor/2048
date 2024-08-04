import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {

    int score=0;
    boolean modified;
    boolean gameOver;
    Tile[][] tiles;
    Random random;

    public GamePanel() {
        this.setVisible(true);
        this.setFocusable(true);

        this.setLayout(new GridLayout(4, 4));
        this.setBackground(new Color(187,173,160));
        this.setBorder(new LineBorder(new Color(187,173,160), 7));

        tiles=new Tile[4][4];
        random=new Random();
        modified=false;
        gameOver=false;

        for(int i=0; i<4; i++)
            for(int j=0; j<4; j++) {
                tiles[i][j]=new Tile();
                this.add(tiles[i][j]);
            }
        start();
    }

    public void start() {
        for(int i=0; i<2; i++)
            spawnNumber();
    }

    public int getRandom(int range) {
        return random.nextInt(range);
    }

    public void moveUp(int column) {

        for(int i=0; i<4; i++)
            for(int j=i+1; j<4; j++)
                if (!tiles[j][column].isEmpty()) {

                    if(tiles[i][column].isEmpty()) {
                        takeAction(tiles[i][column], tiles[j][column]);
                        i--;
                    }
                    else
                        takeAction(tiles[i][column], tiles[j][column]);
                    break;
                }

        if(column<3)
            moveUp(++column);
    }

    public void moveDown(int column) {

        for(int i=3; i>=0; i--)
            for(int j=i-1; j>=0; j--)
                if (!tiles[j][column].isEmpty()) {

                    if(tiles[i][column].isEmpty()){
                        takeAction(tiles[i][column], tiles[j][column]);
                        i++;
                    }
                    else
                        takeAction(tiles[i][column], tiles[j][column]);
                    break;
                }

        if(column<3)
            moveDown(++column);
    }

    public void moveLeft(int row) {

        for(int i=0; i<4; i++)
            for(int j=i+1; j<4; j++)
                if (!tiles[row][j].isEmpty()) {

                    if(tiles[row][i].isEmpty()){
                        takeAction(tiles[row][i], tiles[row][j]);
                        i--;
                    }
                    else
                        takeAction(tiles[row][i], tiles[row][j]);
                    break;
                }

        if(row<3)
            moveLeft(++row);
    }

    public void moveRight(int row) {

        for(int i=3; i>=0; i--)
            for(int j=i-1; j>=0; j--)
                if (!tiles[row][j].isEmpty()) {

                    if(tiles[row][i].isEmpty()){
                        takeAction(tiles[row][i], tiles[row][j]);
                        i++;
                    }
                    else
                        takeAction(tiles[row][i], tiles[row][j]);
                    break;
                }

        if(row<3)
            moveRight(++row);
    }

    //10% Chance of 4 getting spawned
    public void spawnNumber() {
        int row, column;

        for(;true;) {
            row= getRandom(4);
            column= getRandom(4);

            if(tiles[row][column].isEmpty())
                break;
        }

        if(getRandom(10)==0)
            tiles[row][column].setValue(4);
        else
            tiles[row][column].setValue(2);

        checkOver();
    }

    public void takeAction(Tile tile1, Tile tile2) {

        if(tile1.getValue()==tile2.getValue()) {
            tile1.setValue(tile1.getValue()*2);
            tile2.setValue(0);

            modified=true;

            score+=tile1.getValue();
            GameFrame.score.setText("Score: "+score);
        }

        if(tile1.isEmpty()) {
            tile1.setValue(tile2.getValue());
            tile2.setValue(0);

            modified=true;
        }
    }

    public void reset() {

        Component[] componentList = this.getComponents();
        for(Component c: componentList)
            this.remove(c);

        this.repaint();
        this.revalidate();

        for(int i=0; i<4; i++)
            for(int j=0; j<4; j++) {
                tiles[i][j]=new Tile();
                this.add(tiles[i][j]);
            }
        score=0;
        gameOver=false;
        GameFrame.score.setText("Score: "+score);

        start();
    }

    public void checkOver() {

        boolean filled = true;

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (tiles[i][j].isEmpty())
                    filled = false;

        if (filled) {

            boolean over=true;

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {

                    if(i-1>=0)
                        if(tiles[i][j].getValue()==tiles[i-1][j].getValue())
                            over=false;
                    if(j-1>=0)
                        if(tiles[i][j].getValue()==tiles[i][j-1].getValue())
                            over=false;
                    if(i+1<=3)
                        if(tiles[i][j].getValue()==tiles[i+1][j].getValue())
                            over=false;
                    if(j+1<=3)
                        if(tiles[i][j].getValue()==tiles[i][j+1].getValue())
                            over=false;
                }
            }

            if(over) {
                gameOver=true;
                GameFrame.score.setText("Game Over!");
            }

        }

    }
}