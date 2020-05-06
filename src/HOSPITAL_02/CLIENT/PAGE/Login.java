package HOSPITAL_02.CLIENT.PAGE;

import HOSPITAL_02.CLIENT.APP.*;
import HOSPITAL_02.CLIENT.PAGE.MainFrame;
import HOSPITAL_02.DATA.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends Panel {
    private MainFrame parent;
    private Label logLabel, passLabel;
    private Field logField;
    private Password passField;
    private Button addButton, backButton;
    private User currentUser;

    public Login(MainFrame parent){
        this.parent=parent;
        logLabel=new Label("LOGIN");
        logLabel.setLocation(200, 200);
        add(logLabel);
        logField=new Field();
        logField.setLocation(350, 200);
        add(logField);

        passLabel=new Label("Password");
        passLabel.setLocation(200,250);
        add(passLabel);
        passField= new Password();
        passField.setLocation(350,250);
        add(passField);

        addButton=new Button("SIGN UP");
        addButton.setLocation(150, 300);
        add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login=logField.getText();
                String password=passField.getText();
                User user=parent.clientSocket.toLogin(new User(null, login, password, null, 0));
                if(user!=null) {
                    ClientApp.currentUser = user;
                    if (user.getRole() == 1) {
                        parent.logPage.setVisible(false);
                        parent.adminPage.updateData();
                        parent.adminPage.setVisible(true);
                    } else if (user.getRole() == 2) {
                        parent.logPage.setVisible(false);
                        parent.userPage.updateData();
                        parent.userPage.setVisible(true);
                    }
                }

                else {
                    logField.setText("");
                    passField.setText("");
                    JOptionPane.showMessageDialog(parent, "Your login or password incorrect");
                }
            }
        });

        backButton=new Button("BACK");
        backButton.setLocation(400, 300);
        add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.logPage.setVisible(false);
                parent.menuPage.setVisible(true);
            }
        });
    }
}
