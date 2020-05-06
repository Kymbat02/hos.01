package HOSPITAL_02.CLIENT.PAGE;


import HOSPITAL_02.DATA.User;

public class ClientApp {
    public static User currentUser;
    public static void main(String args[]){
        MainFrame clientFrame=new MainFrame();
        clientFrame.setVisible(true);
    }
}
