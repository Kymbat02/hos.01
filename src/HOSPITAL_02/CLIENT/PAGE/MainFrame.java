package HOSPITAL_02.CLIENT.PAGE;


import com.sun.glass.ui.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class MainFrame extends JFrame  {
    public ClientSocket clientSocket;
    public Menu menuPage;
    public Registration regPage;
    public Login logPage;
    public Admin adminPage;
    public DoctorRegisPage doctorRegisPage;
    public UserPage userPage;
    public Treatment treatment;
    public ClientsPage clientsPage;
    public MainFrame() {

    setSize(800, 800);
        setTitle("Hospital");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JPanel pan = new JPanel();
//        JLabel jlb = new JLabel();
//        jlb.setIcon(new ImageIcon(getClass().getResource("HOSPITAL_02//1mozgovye-disfunkcii.jpg")));
//
//        pan.add(jlb);
//
//        this.setContentPane(jlb);
//        this.setResizable(false);
//        this.pack();

        clientSocket = new ClientSocket();

        menuPage = new Menu(this);
        menuPage.setVisible(true);
        add(menuPage);

        regPage = new Registration(this);
        regPage.setVisible(false);
        add(regPage);

        logPage = new Login(this);
        logPage.setVisible(false);
        add(logPage);

        adminPage = new Admin(this);
        adminPage.setVisible(false);
        add(adminPage);

       doctorRegisPage=new DoctorRegisPage(this);
       doctorRegisPage.setVisible(false);
       add(doctorRegisPage);

        userPage=new UserPage(this);
       userPage.setVisible(false);
       add(userPage);

        treatment=new Treatment(this);
        treatment.setVisible(false);
        add(treatment);


        clientsPage=new ClientsPage(this);
        clientsPage.setVisible(false);
        add(clientsPage);
    }
}
