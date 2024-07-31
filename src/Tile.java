import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;

public class Tile extends JLabel {

    boolean empty;

    public Tile() {
        setColor(205,193,180);
        empty=true;

        this.setVisible(true);
        this.setOpaque(true);
        this.setHorizontalAlignment(CENTER);
        this.setFont(new Font("Monospaced", Font.BOLD, 55));
        this.setBorder(new LineBorder(new Color(187,173,160), 7));
        this.setForeground(new Color(249,246,242));
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
    }

    public Color getColor() {
        return this.getForeground();
    }

    public void setColor(int r, int g, int b) {
        this.setBackground(new Color(r,g,b));
    }

    public boolean isEmpty() {
        return empty;
    }
}