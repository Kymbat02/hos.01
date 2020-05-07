package HOSPITAL_02.CLIENT.PAGE;

import HOSPITAL_02.DATA.Doctor;
import HOSPITAL_02.DATA.Packet;
import HOSPITAL_02.DATA.Treatment;
import HOSPITAL_02.DATA.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientSocket {
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public ClientSocket() {
        try {
            socket=new Socket("127.0.0.1",2012);
            oos=new ObjectOutputStream(socket.getOutputStream());
            ois=new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void disconnect(){
        try {
            oos.close();
            ois.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addUser(User user){
        Packet packet=new Packet("ADD_USER",user);
        try {
            oos.writeObject(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public User toLogin(User user){
        User res=null;
        try {
            Packet packet=new Packet("AUTH", user);
            oos.writeObject(packet);

            Packet packet1=(Packet)ois.readObject();
            res=(User) packet1.getData();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    public void addDoctor(Doctor doctor){
        Packet packet=new Packet("ADD_DOCTOR", doctor);
        try {
            oos.writeObject(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Doctor> getAllInformation(){
        ArrayList<Doctor> doctor=new ArrayList<>();
        Packet packet=new Packet("LIST_DOCTOR", null);
        try {
            oos.writeObject(packet);
            Packet response=(Packet)ois.readObject();
            doctor=(ArrayList<Doctor>)response.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctor;
    }
    public void editDoctor(Doctor doctor){
        Packet packet=new Packet("EDIT_DOCTOR", doctor);
        try {
            oos.writeObject(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deleteDoctor(Doctor doctor){
        Packet packet=new Packet("DELETE_DOCTOR", doctor);
        try {
            oos.writeObject(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clientsRecord(Treatment treatment){
        Packet packet=new Packet("RECORD_TREATMENT", treatment);
        try {
            oos.writeObject(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editTreatment(Doctor doctor){
        Packet packet=new Packet("EDIT_TREATMENT", doctor);
        try {
            oos.writeObject(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Treatment> getAllTreatment(Treatment treatment){
        ArrayList<Treatment> treatments=new ArrayList<>();
        Packet packet=new Packet("LIST_CLIENT_TREAT", treatments);
        try {
            oos.writeObject(packet);
            Packet response=(Packet)ois.readObject();
            treatments=(ArrayList<Treatment>)response.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return treatments;
    }

}
