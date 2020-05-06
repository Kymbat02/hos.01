package HOSPITAL_02.CLIENT.APP;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {
    public Label(String text){
        setText(text);
        setSize(200, 30);
        setForeground(Color.black);
        setFont(new Font("Arial",1, 16));
    }
}

