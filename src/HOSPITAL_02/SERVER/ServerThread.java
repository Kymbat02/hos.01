package HOSPITAL_02.SERVER;

import HOSPITAL_02.DATA.Doctor;
import HOSPITAL_02.DATA.Packet;
import HOSPITAL_02.DATA.Treatment;
import HOSPITAL_02.DATA.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run(){
        try {
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
            while(true){
                Packet packet=(Packet)ois.readObject();
                if(packet.getCode().equals("ADD_USER")){
                    User user=(User)packet.getData();
                    ServerApp.addUser(user);
                }
                else if(packet.getCode().equals("AUTH")){
                    User user=(User)packet.getData();
                    User response=ServerApp.getUser(user.getLogin(), user.getPassword());
                    Packet packet1=new Packet("AUTH", response);
                    oos.writeObject(packet1);
                }

                else if(packet.getCode().equals("Record_DOCTOR")){
                    Treatment treatment=(Treatment) packet.getData();
                    ServerApp.ClientsRecord(treatment);
                }
                else if(packet.getCode().equals("ADD_DOCTOR")){
                    Doctor doctor =(Doctor)packet.getData();
                    ServerApp.addDoctor(doctor);
                }
                else if(packet.getCode().equals("LIST_DOCTOR")){
                    ArrayList<Doctor> doctor=ServerApp.getInformation();
                    Packet packet1=new Packet("LIST_DOCTOR", doctor);
                    oos.writeObject(packet1);
                }
                else if(packet.getCode().equals("EDIT_DOCTOR")){
                    Doctor doctor=(Doctor)packet.getData();
                    ServerApp.editDoctor(doctor);
                }
                else if(packet.getCode().equals("DELETE_DOCTOR")){
                    Doctor doctor=(Doctor)packet.getData();
                    ServerApp.deleteDoctor(doctor.getId());
                }
                else if(packet.getCode().equals("RECORD_TREATMENT")){
                    Treatment treatment=(Treatment)packet.getData();
                    ServerApp.ClientsRecord(treatment);
                }
                else if(packet.getCode().equals("EDIT_TREATMENT")){
                    Doctor doctor=(Doctor) packet.getData();
                    Doctor dr=ServerApp.getDoctor(doctor.getId());
                    int newcount=dr.getCount()-doctor.getCount();
                    ServerApp.updateRecord(doctor.getId(), newcount, doctor.getCount());
                }
                else if(packet.getCode().equals("LIST_CLIENT_TREAT")){
                    Treatment treatment=(Treatment) packet.getData();
                    ArrayList<Treatment> treatments=ServerApp.getAllTreatment(treatment.getClient_id());
                    Packet packet1=new Packet("LIST_CLIENT_TREAT", treatments);
                    oos.writeObject(packet1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
