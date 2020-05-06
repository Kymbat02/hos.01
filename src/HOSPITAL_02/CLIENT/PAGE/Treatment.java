package HOSPITAL_02.CLIENT.PAGE;

import HOSPITAL_02.CLIENT.APP.Field;
import HOSPITAL_02.CLIENT.APP.Panel;
import HOSPITAL_02.CLIENT.APP.Label;
import HOSPITAL_02.CLIENT.APP.Button;
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

public class Treatment extends Panel {
    public MainFrame parent;
    private Label idLabel, doctorNameLabel, priceLabel,treatmentLabel,countLabel;
    private Button buyButton, backButton, refreshButton;
    private Field idField, doctorNameField, priceField,countField;
    private String[] places={"choose", "ALLERGOLOGY", "NEUROLOGY", "ONCOLOGY","THERAPY","PSYCHOTHERAPY"};
    private Object[] columns={"ID", "DOCTORNAME", "TREATMENT",  "PRICE", "COUNT"};
    private JComboBox jCombot;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane pane;
    private ArrayList<Doctor> doctor;

    public Treatment(MainFrame parent) {
        this.parent = parent;
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
                doctorNameField.setText(model.getValueAt(i,1).toString());
                jCombot.setSelectedItem(model.getValueAt(i,2).toString());
                priceField.setText(model.getValueAt(i,3).toString());
                countField.setText("");

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
        pane.setBounds(0,0, 600, 400);
        add(pane);
        updateDoctor();
        idLabel=new HOSPITAL_02.CLIENT.APP.Label("ID");
        idLabel.setLocation(120, 420);
        add(idLabel);
        idField=new Field();
        idField.setLocation(250, 420);
        add(idField);

        doctorNameLabel=new Label("Doctor name:");
        doctorNameLabel.setLocation(120, 460);
        add(doctorNameLabel);
        doctorNameField=new Field();
        doctorNameField.setLocation(250, 460);
        add(doctorNameField);

        treatmentLabel=new Label("Treatment:");
        treatmentLabel.setLocation(120, 500);
        add(treatmentLabel);
        jCombot=new JComboBox(places);
        jCombot.setBounds(250, 500, 200, 30);
        jCombot.setBackground(Color.orange);
        jCombot.setForeground(Color.black);
        jCombot.setFont(new Font("Arial",1, 16));
        add(jCombot);

        priceLabel=new Label("Price:");
        priceLabel.setLocation(120, 550);
        add(priceLabel);
        priceField=new Field();
        priceField.setLocation(250, 550);
        add(priceField);

        countLabel=new Label("Count:");
        countLabel.setLocation(120, 600);
        add(countLabel);
        countField=new Field();
        countField.setLocation(250, 600);
        add(countField);


        buyButton=new Button("RECORD");
        buyButton.setLocation(550, 450);
        add(buyButton);
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long id=Long.valueOf(idField.getText());
                String nameOfDoctor=doctorNameField.getText();
                String treatment1=jCombot.getSelectedItem().toString();
                int price=Integer.parseInt(priceField.getText());
                int count=Integer.parseInt(countField.getText());
                if(doctor.isEmpty() || treatment1.isEmpty()){
                    JOptionPane.showMessageDialog(parent, "Please, fill all fields!!!");
                }
                else{
                    HOSPITAL_02.DATA.Treatment treatment=new HOSPITAL_02.DATA.Treatment(null,ClientApp.currentUser.getId(),nameOfDoctor,treatment1,price,count);
                    parent.clientSocket.clientsRecord(treatment);
                    Doctor doctor=new Doctor(id, null, null,0,0);
                    parent.clientSocket.editDoctor(doctor);
                    doctorNameField.setText("");
                    jCombot.setSelectedIndex(0);
                    priceField.setText("");
                    countField.setText("");
                    JOptionPane.showMessageDialog(parent, "Treatment was recorded  successfully!!!");
                    clearDoctor();
                    updateDoctor();
                }
            }
        });

        backButton=new Button("BACK");
        backButton.setLocation(550, 500);
        add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.treatment.setVisible(false);
                parent.userPage.setVisible(true);
            }
        });
        refreshButton=new Button("REFRESH");
        refreshButton.setLocation(550,550);
        add(refreshButton);
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearDoctor();
                updateDoctor();
            }
        });

    }
    public void updateDoctor(){
        doctor=parent.clientSocket.getAllInformation();
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
    private void clearDoctor(){
        DefaultTableModel dm = (DefaultTableModel)table.getModel();
        while(dm.getRowCount() > 0)
        {
            dm.removeRow(0);
        }
    }
}
