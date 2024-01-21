package src.model;


public class Patient extends Client{
    private static int id = 0;
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
    public Patient(String username,String diagnosis, int payment, int roomNumber){
        super(username);
        this.diagnosis = diagnosis;
        this.payment = payment;
        this.roomNumber = roomNumber;

    }


    public String toString() {
        return getUsername() + ',' + diagnosis + ',' + payment + "," + roomNumber;
    }
}
