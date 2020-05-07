package HOSPITAL_02.DATA;

public class Doctor extends User {

    private String placeOfWork;
    private int price;
    private int oplata;
    private String zakluchenie;
    private int count;
    private int occupied;

    public Doctor(Object o, String fullname, int price, String placeOfWork, int i, String oplata, String zakluchenie,int count) {
    }

    public Doctor(Long id, String login, String password, String fullname, int role, String placeOfWork, int price, int oplata, String zakluchenie,int count,int occupied) {
        super(id, login, password, fullname, role);
        this.placeOfWork = placeOfWork;
        this.price = price;
        this.oplata = oplata;
        this.zakluchenie = zakluchenie;
        this.count=count;
        this.occupied=occupied;
    }

    public Doctor() {
    }

    public Doctor(Long id, String fullname, String placeOfWork, int price, int oplata, String zakluchenie) {
        super(id, fullname);
        this.placeOfWork = placeOfWork;
        this.price = price;
        this.oplata = oplata;
        this.zakluchenie = zakluchenie;
    }

    public Doctor(Long id,  String fullname,  String placeOfWork, int price,int count) {
        super(id,  fullname);
        this.placeOfWork = placeOfWork;
        this.price = price;
        this.count=count;
    }

    public Doctor(Long id,  String fullname, String placeOfWork, int price,int count,int occupied) {
        super(id,  fullname);
        this.placeOfWork = placeOfWork;
        this.price = price;
        this.count=count;
        this.occupied=occupied;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOplata() {
        return oplata;
    }

    public void setOplata(int oplata) {
        this.oplata = oplata;
    }

    public String getZakluchenie() {
        return zakluchenie;
    }

    public void setZakluchenie(String zakluchenie) {
        this.zakluchenie = zakluchenie;
    }

    public int getOccupied() {
        return occupied;
    }

    public void setOccupied(int occupied) {
        this.occupied = occupied;
    }
}
