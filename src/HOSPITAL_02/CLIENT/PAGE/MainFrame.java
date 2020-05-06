package HOSPITAL_02.CLIENT.PAGE;


import javax.swing.*;

public class MainFrame extends JFrame {
    public ClientSocket clientSocket;
    public Menu menuPage;
    public Registration regPage;
    public Login logPage;
    public Admin adminPage;
    public DoctorRegisPage doctorRegisPage;
    public UserPage userPage;
    public Treatment treatment;
    public MainFrame() {
    setSize(800, 800);
        setTitle("Hospital");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new JLabel(new ImageIcon("img9.jpg")));

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

    }
}
