import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {

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

        for(int i=0; i<4; i++)
            for(int j=0; j<4; j++) {
                tiles[i][j]=new Tile();
                this.add(tiles[i][j]);
            }

        startgame();
    }

    public void startgame() {
        for(int i=0; i<2; i++)
            spawnNumber();
    }

    public int getRandom(int range) {
        return random.nextInt(range);
    }

    public void moveUp(int column) {
        for(int i=0; i<3; i++) {
            takeAction(tiles[i][column], tiles[i+1][column]);

            //if(tiles[i+1][column].empty)

        }


        if(column<3)
            moveUp(++column);

    }

    public void moveDown(int column) {

    }

    public void moveLeft(int row) {
        System.out.println("Left");
    }

    public void moveRight(int row) {

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
    }

    public void takeAction(Tile tile1, Tile tile2) {

        if(tile1.getValue()==tile2.getValue()) {
            tile1.setValue(tile1.getValue()*2);
            tile2.setValue(0);
        }

        if(tile1.isEmpty()) {
            tile1.setValue(tile2.getValue());
            tile2.setValue(0);
        }

    }
}