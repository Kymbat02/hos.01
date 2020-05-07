package HOSPITAL_02.DATA;

import java.io.Serializable;

public class Treatment implements Serializable {
    private Long id;
    private Long client_id;
    private String doctorName;
    private String treatment;
    private int count;
    private int sum;

    public Treatment() {
    }

    public Treatment(Long id, Long client_id, String doctorName,String treatment, int count, int sum) {
        this.id = id;
        this.client_id = client_id;
        this.doctorName = doctorName;
        this.treatment=treatment;
        this.count = count;
        this.sum=sum;
    }


    public int getSum() {
        return sum;
    }

    public void setSum(int price) {
        this.sum = sum;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

}
