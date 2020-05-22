package HOSPITAL_02.CLIENT.PAGE;

import HOSPITAL_02.CLIENT.APP.Button;
import HOSPITAL_02.CLIENT.APP.Label;
import HOSPITAL_02.CLIENT.APP.Panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    public class UserPage extends Panel {
        private MainFrame parent;
        private Label userLabel;
        private Button treatment,recordButton,logoutButton;
        public UserPage(MainFrame parent) {
            this.parent = parent;
            userLabel=new Label("");
            userLabel.setBounds(220, 120, 200, 50 );
            add(userLabel);

            treatment=new Button("Sign to treatment");
            treatment.setLocation(280, 220);
            add(treatment);
            treatment.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    parent.userPage.setVisible(false);
                    parent.treatment.setVisible(true);
                }
            });

            recordButton=new Button("Records");
           recordButton.setLocation(280, 300);
            add(recordButton);
            //recordButton.addActionListener(new ActionListener() {
                //@Override
             //   public void actionPerformed(ActionEvent e) {

             //       parent.recordPage.setVisible(true);
           //     }
//            });


            logoutButton=new Button("LOGOUT");
            logoutButton.setLocation(280, 380);
            add(logoutButton);
            logoutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ClientApp.currentUser=null;
                    parent.userPage.setVisible(false);
                    parent.logPage.setVisible(true);
                }
            });
        }
        public void updateData(){
            userLabel.setText("Welcome "+ClientApp.currentUser.getFullname());
        }

    }

