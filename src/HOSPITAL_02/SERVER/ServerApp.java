package HOSPITAL_02.SERVER;

import HOSPITAL_02.DATA.Doctor;
import HOSPITAL_02.DATA.Treatment;
import HOSPITAL_02.DATA.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

public class ServerApp {
    public static Connection connection;
    public static void main(String args[]){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/user?useUnicode=true&serverTimezone=UTC","root", "");
            ServerSocket serverSocket=new ServerSocket(2012);
            System.out.println("Waiting for a client...");
            while(true){
                Socket socket=serverSocket.accept();
                System.out.println("Client connected");
                ServerThread serverThread=new ServerThread(socket);
                serverThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void addUser(User user){
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "INSERT INTO user(id, login, password, fuulname, role)"+
                    " VALUES(null, ?, ?, ?, ?) ");
            statement.setString(1,user.getLogin());
            statement.setString(2,user.getPassword());
            statement.setString(3,user.getFullname());
            statement.setInt(4,user.getRole());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void ClientsRecord(Treatment treatment){
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "INSERT INTO records(id, client_id, nameOfDoctor,treatment1, price, count)"+
                    " VALUES(null, ?, ?, ?, ?,?) ");
            statement.setLong(1,treatment.getClient_id());
            statement.setString(2,treatment.getDoctorName());
            statement.setString(3,treatment.getTreatment());
            statement.setInt(4,treatment.getPrice());
            statement.setInt(5,treatment.getCount());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static User getUser(String login, String password){
        User user=null;
        try {
            PreparedStatement statement=connection.prepareStatement("SELECT * FROM user where login=? and password=? ");
            statement.setString(1,login);
            statement.setString(2,password);
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                Long id=resultSet.getLong("id");
                String login1=resultSet.getString("login");
                String password1=resultSet.getString("password");
                String fullname=resultSet.getString("fuulname");
                int role=resultSet.getInt("role");
                user=new User(id, login1, password1, fullname, role);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public static void addDoctor(Doctor doc){
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "INSERT INTO doctors(id_doctor, fullname,placeOfWork, price,count )"+
                    " VALUES(null, ?,?,?,?)");
            statement.setString(1,doc.getFullname());
            statement.setString(2,doc.getPlaceOfWork());
            statement.setInt(3,doc.getPrice());
            statement.setInt(4,doc.getCount());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Doctor> getInformation(){
        ArrayList<Doctor> doctor=new ArrayList<>();
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "SELECT * FROM doctors");
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                Long id_doctor=resultSet.getLong("id_doctor");
                String fullname=resultSet.getString("fullname");
                String placeOfWork=resultSet.getString("placeOfwork");
                int price=resultSet.getInt("price");
                int count=resultSet.getInt("count");

                doctor.add(new Doctor(id_doctor, fullname, placeOfWork, price,count));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }
    public static void editDoctor(Doctor doctor){
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    " UPDATE doctors set fullname=?,placeOfWork=?,price=?,count=?"+
                    " WHERE id_doctor=?");
            statement.setString(1,doctor.getFullname());
            statement.setString(2,doctor.getPlaceOfWork());
            statement.setInt(3,doctor.getPrice());
            statement.setInt(4,doctor.getCount());
            statement.setLong(5,doctor.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Doctor getDoctor(Long id) {
        Doctor doctor=new Doctor();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM doctors where id_doctor=?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id_doctor = resultSet.getLong("id_doctor");
                String fullname = resultSet.getString("fullname");
                String placeOfWork = resultSet.getString("placeOfwork");
                int price = resultSet.getInt("price");
                int count = resultSet.getInt("count");
                doctor = new Doctor(id_doctor, fullname, placeOfWork, price, count);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }
    public static void updateRecord(Long id,int newcount, int count){
        try {
            PreparedStatement statement=connection.prepareStatement("UPDATE records set count=? WHERE id=?");
            statement.setInt(1, count);
            statement.setInt(2, newcount);
            statement.setLong(3, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDoctor(Long id){
        try {
            PreparedStatement statement=connection.prepareStatement("DELETE FROM doctors WHERE id_doctor=? ");
            statement.setLong(1,id);
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Treatment> getAllTreatment(Long id){
        ArrayList<Treatment> treatments=new ArrayList<>();
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "SELECT * FROM records WHERE id=?");
            statement.setLong(1, id);
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                Long id1=resultSet.getLong("id");
                Long client_id=resultSet.getLong("id_client");
                String nameOfDoctor=resultSet.getString("nameOfDoctor");
                String treatment1=resultSet.getString("treatment1");
                int price=resultSet.getInt("price");
                int count=resultSet.getInt("count");
                Treatment treatment=new Treatment(id, client_id, nameOfDoctor,treatment1,price, count);
                treatments.add(treatment);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return treatments;
    }
}


