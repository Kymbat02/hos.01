package HOSPITAL_02.CLIENT.APP;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class Field extends JTextField {
    public Field() {
        setSize(300, 30);
        setBackground(Color.white);
        setForeground(Color.black);
        setFont(new Font("Arial", 1, 16));
        setBorder(new EtchedBorder(Color.black, Color.black));
    }
}