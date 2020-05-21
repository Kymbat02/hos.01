package HOSPITAL_02.CLIENT.PAGE;

import HOSPITAL_02.CLIENT.APP.Button;
import HOSPITAL_02.CLIENT.APP.Panel;
import HOSPITAL_02.CLIENT.APP.Label;
import HOSPITAL_02.CLIENT.APP.Field;
import HOSPITAL_02.DATA.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ClientsPage extends Panel {
    private MainFrame clientFrame;
    private Label idLabel,  fiotLabel,loginLabel;
    private Button addButton, editButton, deleteButton, backButton, refreshButton;
    private Field idField,fioField,loginField;
    private Object[] columns={"ID", "Full name", "Login"};
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane pane;
    private ArrayList<User> user;

    public ClientsPage(MainFrame clientFrame ) {
        this.clientFrame =clientFrame;
        table=new JTable();
        model=new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setBackground(Color.lightGray);
        table.setForeground(Color.black);
        table.setFont(new Font("Arial", 1, 16));
        table.setRowHeight(30);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i=table.getSelectedRow();
                idField.setText(model.getValueAt(i,0).toString());
                fioField.setText(model.getValueAt(i,1).toString());
                loginField.setText(model.getValueAt(i,2).toString());

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        pane=new JScrollPane(table);
        pane.setBounds(0,0, 800, 400);
        add(pane);
        updateMedCenter();

        idLabel=new Label("ID");
        idLabel.setLocation(20, 420);
        add(idLabel);
        idField=new Field();
        idField.setLocation(150, 420);
        add(idField);

        fiotLabel=new Label(" Full name :");
        fiotLabel.setLocation(20, 460);
        add(fiotLabel);
        fioField=new Field();
        fioField.setLocation(150, 460);
        add(fioField);

        loginLabel=new Label(" LOGIN :");
        loginLabel.setLocation(20, 500);
        add(loginLabel);
        loginField=new Field();
        loginField.setLocation(150, 500);
        add(loginField);

        addButton= new Button("ADD");
        addButton.setLocation(500, 450);
        add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fullname=fioField.getText();
                String login=loginField.getText();

                if(fullname.isEmpty() || login.isEmpty() ){
                    JOptionPane.showMessageDialog(clientFrame, "Please, fill all fields!!!");
                }
                else{
                    User user=new User(null, fullname,login);
                    clientFrame.clientSocket.addUser(user);
                    fioField.setText("");
                    loginField.setText("");

                    JOptionPane.showMessageDialog(clientFrame, " Your appointment is added successfully!!!");
                    clearMedCenter();
                    updateMedCenter();

                }
            }
        });

        editButton=new Button("EDIT");
        editButton.setLocation(500, 500);
        add(editButton);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long id=Long.valueOf(idField.getText());
                String fullname=fioField.getText();
                String login=loginField.getText();

                User user=new User(id,fullname,login);
                clientFrame.clientSocket.editUser(user);
                JOptionPane.showMessageDialog(clientFrame, " Your edit was added successfully!!!");
                clearMedCenter();
                updateMedCenter();
            }
        });

        deleteButton=new Button("DELETE");
        deleteButton.setLocation(500, 550);
        add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long id=Long.valueOf(idField.getText());
                User user=new User(id, null, null);
                clientFrame.clientSocket.deleteUser(user);
                JOptionPane.showMessageDialog(clientFrame, " You deleted doctor from list!!!");
                clearMedCenter();
                updateMedCenter();
            }
        });

        backButton=new Button("BACK");
        backButton.setLocation(500, 600);
        add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientFrame.clientsPage.setVisible(false);
                clientFrame.adminPage.setVisible(true);
            }
        });
        refreshButton=new Button("REFRESH");
        refreshButton.setLocation(500,650);
        add(refreshButton);
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearMedCenter();
                updateMedCenter();
            }
        });



    }
    public void updateMedCenter(){
        user=clientFrame.clientSocket.getInfoUs();
        Object[] row=new Object[3];
        for(User d:user){
            row[0]=d.getId();
            row[1]=d.getFullname();
            row[2]=d.getLogin();

            model.addRow(row);
        }
    }
    private void clearMedCenter(){
        DefaultTableModel dm = (DefaultTableModel)table.getModel();
        while(dm.getRowCount() > 0)
        {
            dm.removeRow(0);
        }
    }
}



