package HOSPITAL_02.CLIENT.APP;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class Button extends JButton {
    public Button(String text){
        setText(text);
        setSize(200,40);
        setBackground(Color.orange);
        setForeground(Color.black);
        setFont(new Font("Arial",1, 16));
        setBorder(new EtchedBorder(Color.black,Color.black));
    }
}
