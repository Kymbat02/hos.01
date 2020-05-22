package HOSPITAL_02.CLIENT.PAGE;


import HOSPITAL_02.DATA.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ClientApp  {
    public static User currentUser;
    public static void main(String args[]){
        MainFrame clientFrame=new MainFrame();
        clientFrame.setVisible(true);

    }

}

