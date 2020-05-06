package HOSPITAL_02.CLIENT.PAGE;

import HOSPITAL_02.CLIENT.APP.Button;
import HOSPITAL_02.CLIENT.APP.Panel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends Panel {
    private MainFrame parent;
    private Button logBut, regBut, exitBut;
    public Menu(MainFrame parent){
        this.parent=parent;
        logBut=new Button("SIGN UP");
        logBut.setLocation(280,150);
        add(logBut);
        logBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.menuPage.setVisible(false);
                parent.logPage.setVisible(true);
            }
        });

       regBut=new Button("REGISTERATION");
        regBut.setLocation(280,220);
       add(regBut);
        regBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.menuPage.setVisible(false);
                parent.regPage.setVisible(true);
            }
        });

        exitBut=new Button("EXIT");
        exitBut.setLocation(280,290);
        add(exitBut);
        exitBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }
}


