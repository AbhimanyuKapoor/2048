import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;

public class Tile extends JLabel {

    boolean empty;

    public Tile() {

        this.setVisible(true);
        this.setOpaque(true);
        this.setHorizontalAlignment(CENTER);
        this.setFont(new Font("Monospaced", Font.BOLD, 55));
        this.setBorder(new LineBorder(new Color(187,173,160), 7));
        this.setForeground(new Color(249,246,242));

        this.setValue(0);
        empty=true;
    }

    public int getValue() {
        if(this.getText().isEmpty())
            return 0;
        else
            return Integer.parseInt(this.getText());
    }

    //If value is being set to 0: Make it empty
    public void setValue(int a) {
        if(a==0) {
            this.setText("");
            empty=true;
        }
        else {
            this.setText(String.valueOf(a));
            empty=false;
        }

        if(a==2 || a==4)
            this.setForeground(new Color(119, 110, 101));
        else
            this.setForeground(new Color(255, 255, 255));

        setTileColor(a);
    }

    public void setColor(int r, int g, int b) {
        this.setBackground(new Color(r,g,b));
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setTileColor(int num) {

        switch (num) {
            case 0:
                setColor(205,193,180);
                break;
            case 2:
                setColor(238, 228, 218);
                break;
            case 4:
                setColor(238, 223, 200);
                break;
            case 8:
                setColor(243, 177, 120);
                break;
            case 16:
                setColor(245, 149, 99);
                break;
            case 32:
                setColor(245, 124, 95);
                break;
            case 64:
                setColor(247, 94, 62);
                break;
            case 128:
                setColor(236, 205, 114);
                break;
            case 256:
                setColor(236, 203, 96);
                break;
            case 512:
                setColor(236, 199, 81);
                break;
            case 1024:
                setColor(236, 196, 65);
                break;
            default:
                setColor(235, 193, 45);
                break;
        }
    }
}