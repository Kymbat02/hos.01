package HOSPITAL_02.CLIENT.PAGE;


import HOSPITAL_02.CLIENT.APP.Button;
import HOSPITAL_02.CLIENT.APP.Label;
import HOSPITAL_02.CLIENT.APP.Panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin extends Panel {
    private MainFrame parent;
    private Label label;
    private Button doctorButton, clientButton, incomeButton, logoutButton;

    public Admin(MainFrame parent) {
        this.parent = parent;

        label = new Label("");
        label.setBounds(220, 120, 200, 50);
        add(label);

        doctorButton = new Button("DOCTORS: ");
        doctorButton.setLocation(280, 200);
        add(doctorButton);
        doctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.adminPage.setVisible(false);
                    parent.doctorRegisPage.setVisible(true);
            }
        });

        clientButton = new Button("CLIENTS");
        clientButton.setLocation(280, 260);
        add(clientButton);
        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.adminPage.setVisible(false);
                parent.clientsPage.setVisible(true);
            }
        });
        incomeButton = new Button("MONEY");
        incomeButton.setLocation(280, 320);
        add(incomeButton);

        logoutButton = new Button("LOGOUT");
        logoutButton.setLocation(280, 380);
        add(logoutButton);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientApp.currentUser = null;
                parent.adminPage.setVisible(false);
                parent.logPage.setVisible(true);
            }
        });
    }
        public void updateData(){
            label.setText("Welcome " +ClientApp.currentUser.getFullname());
        }}

