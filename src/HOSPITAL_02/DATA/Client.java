package HOSPITAL_02.DATA;

public class Client extends User {
    private int iin;
    private int card;
    private String usluga;
    private int price;

    public Client() {
    }

    public Client(Long id, String login, String password, String fullname, int role, int iin, int card, String usluga, int price) {
        super(id, login, password, fullname, role);
        this.iin = iin;
        this.card = card;
        this.usluga = usluga;
        this.price = price;
    }

    public int getIin() {
        return iin;
    }

    public void setIin(int iin) {
        this.iin = iin;
    }

    public int getCard() {
        return card;
    }

    public void setCard(int card) {
        this.card = card;
    }

    public String getUsluga() {
        return usluga;
    }

    public void setUsluga(String usluga) {
        this.usluga = usluga;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
