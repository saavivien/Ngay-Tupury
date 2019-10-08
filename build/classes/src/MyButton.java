package src;

import java.awt.Cursor;
import javax.swing.Icon;
import javax.swing.JButton;

public class MyButton extends JButton {

    public MyButton(Icon icon) {
        super(icon);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
    }

}
