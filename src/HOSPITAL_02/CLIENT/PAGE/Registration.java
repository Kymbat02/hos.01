
package HOSPITAL_02.CLIENT.PAGE;

import HOSPITAL_02.CLIENT.APP.*;
import HOSPITAL_02.DATA.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registration extends Panel {
    private MainFrame parent;
    private Label logLabel, passLabel,fioLabel,roleLabel;
    private Field logField;
    private Password passField;
    private Field fioField;
    private Field roleField;
    private Button addButton, backButton;

    public Registration(MainFrame parent) {
        this.parent = parent;
        logLabel=new Label("Login:");
        logLabel.setLocation(200, 150);
        add(logLabel);
        logField=new Field();
        logField.setLocation(350,150);
        add(logField);

        passLabel=new Label("Password:");
        passLabel.setLocation(200, 210);
        add(passLabel);
        passField=new Password();
        passField.setLocation(350,210);
        add(passField);

        fioLabel=new Label("Full name:");
        fioLabel.setLocation(200, 270);
        add(fioLabel);
        fioField=new Field();
        fioField.setLocation(350,270);
        add(fioField);

        roleLabel=new Label("Role:");
        roleLabel.setLocation(200, 330);
        add(roleLabel);
        roleField=new Field();
        roleField.setLocation(350,330);
        add(roleField);

        addButton=new Button("ADD");
        addButton.setLocation(200, 390);
        add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login=logField.getText();
                String password=passField.getText();
                String fullname=fioField.getText();
                int role= Integer.parseInt(roleField.getText());
                if(login.isEmpty() || password.isEmpty() || fullname.isEmpty()){
                    JOptionPane.showMessageDialog(parent,"Please, fill all fields!!!");

                }
                else{
                    User users=new User(null, login, password,fullname,role);
                    parent.clientSocket.addUser(users);
                    logField.setText("");
                    passField.setText("");
                    fioField.setText("");
                    roleField.setText("");
                    JOptionPane.showMessageDialog(parent,"You are registered successfully!!!");
                }
            }
        });

        backButton=new Button("BACK");
        backButton.setLocation(420, 390);
        add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.regPage.setVisible(false);
                parent.menuPage.setVisible(true);
            }
        });


    }
}

