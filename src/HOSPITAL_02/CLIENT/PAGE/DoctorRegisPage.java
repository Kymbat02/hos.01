
package HOSPITAL_02.CLIENT.PAGE;



import HOSPITAL_02.CLIENT.APP.Button;
import HOSPITAL_02.CLIENT.APP.Panel;
import HOSPITAL_02.CLIENT.APP.Label;
import HOSPITAL_02.CLIENT.APP.Field;
import HOSPITAL_02.DATA.Doctor;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class DoctorRegisPage extends Panel {
    private MainFrame clientFrame;
    private Label idLabel,  fiotLabel, placeOfWorkLabel,priceLabel,countLabel;
    private Button addButton, editButton, deleteButton, backButton, refreshButton;
    private Field idField,fioField,priceField,oplataField,zakluchenieField,countField;
    private String[] places={"choose", "ALLERGOLOGY", "NEUROLOGY", "ONCOLOGY","THERAPY","PSYCHOTHERAPY"};
    private Object[] columns={"ID", "Full name", "Place of Work", "Price","Count"};
    private JComboBox jComboPlaces;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane pane;
    private ArrayList<Doctor> doctor;

    public DoctorRegisPage(MainFrame clientFrame ) {
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
                jComboPlaces.setSelectedItem(model.getValueAt(i,2).toString());
                priceField.setText(model.getValueAt(i,3).toString());
                countField.setText(model.getValueAt(i,4).toString());


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

        placeOfWorkLabel=new Label("Working place: ");
        placeOfWorkLabel.setLocation(20, 660);
        add(placeOfWorkLabel);
        jComboPlaces=new JComboBox(places);
        jComboPlaces.setBounds(150, 660, 200, 30);
        jComboPlaces.setBackground(Color.orange);
        jComboPlaces.setForeground(Color.black);
        jComboPlaces.setFont(new Font("Arial",1, 16));
        jComboPlaces.setBorder(new EtchedBorder(Color.black,Color.black));
        add(jComboPlaces);

        priceLabel=new Label("Price:");
        priceLabel.setLocation(20, 500);
        add(priceLabel);
        priceField=new Field();
        priceField.setLocation(150, 500);
        add(priceField);

        countLabel=new Label("Count:");
        countLabel.setLocation(20, 540);
        add(countLabel);
        countField=new Field();
        countField.setLocation(150, 540);
        add(countField);



        addButton= new Button("ADD");
        addButton.setLocation(500, 450);
        add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fullname=fioField.getText();
                String placeOfWork=jComboPlaces.getSelectedItem().toString();
                int price=Integer.parseInt(priceField.getText());
                int count=Integer.parseInt(countField.getText());


                if(fullname.isEmpty() || placeOfWork.isEmpty() ){
                    JOptionPane.showMessageDialog(clientFrame, "Please, fill all fields!!!");
                }
                else{
                    Doctor doctor=new Doctor(null, fullname,placeOfWork,price,count);
                    clientFrame.clientSocket.addDoctor(doctor);
                    fioField.setText("");
                    jComboPlaces.setSelectedIndex(0);
                    priceField.setText("");
                    countField.setText("");
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
                String placeOfWork=jComboPlaces.getSelectedItem().toString();
                int price=Integer.parseInt(priceField.getText());
                int count=Integer.parseInt(countField.getText());
                Doctor doctor=new Doctor(id,fullname,placeOfWork,price,count);
                clientFrame.clientSocket.editDoctor(doctor);
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
                Doctor doctor=new Doctor(id, null, null, 0,0);
                clientFrame.clientSocket.deleteDoctor(doctor);
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
                clientFrame.doctorRegisPage.setVisible(false);
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
        doctor=clientFrame.clientSocket.getAllInformation();
        Object[] row=new Object[5];
        for(Doctor d:doctor){
            row[0]=d.getId();
            row[1]=d.getFullname();
            row[2]=d.getPlaceOfWork();
            row[3]=d.getPrice();
            row[4]=d.getCount();
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


