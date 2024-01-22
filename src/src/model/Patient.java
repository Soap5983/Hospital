package src.model;


public class Patient extends Client{
    int id = 0;
    int roomNumber;
    String diagnosis;
    //Doctor[] doctors;
    //Nurse[] nurses;
    //WardBoy[] wardBoys;
    int payment;

    public Patient() {
        super();
        id++;
    }

    public Patient(String username, String password) {
        super(username, password);
        id++;
    }
    public Patient(int id, String username,String diagnosis, int payment, int roomNumber){
        super(username);
        this.id = id;
        this.diagnosis = diagnosis;
        this.payment = payment;
        this.roomNumber = roomNumber;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public String toString() {
        return getUsername() + ',' + diagnosis + ',' + payment + "," + roomNumber;
    }
}
