package bg.smg.model;


public class Patient extends Client{
    private static int id = 0;
    Room room;
    Diagnosis diagnosis;
    //Doctor[] doctors;
    //Nurse[] nurses;
    //WardBoy[] wardBoys;
    Payment payment;

    public Patient() {
        super();
        id++;
    }

    public Patient(String username, String password) {
        super(username, password);
        id++;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String toString() {
        return "Patient{" +
                "id: " + id + '/' +
                "username: " + getUsername() + '/' +
                "password: " + getPassword() + '/' +
                "}";
    }
}
