package bg.smg.model;

public class Doctor extends Client{
    private static int id;
    private Patient[] allPatients;
    //team of nurses and ward boys
    private Nurse[] allNurses;
    private WardBoy[] allWardBoys;

    public Doctor() {
        super();
        id++;
    }

    public Doctor(String username, String password) {
        super(username, password);
        id++;
    }

    public Patient[] getAllPatients() {
        return allPatients;
    }

    public void setAllPatients(Patient[] allPatients) {
        this.allPatients = allPatients;
    }

    public Nurse[] getAllNurses() {
        return allNurses;
    }

    public void setAllNurses(Nurse[] allNurses) {
        this.allNurses = allNurses;
    }

    public WardBoy[] getAllWardBoys() {
        return allWardBoys;
    }

    public void setAllWardBoys(WardBoy[] allWardBoys) {
        this.allWardBoys = allWardBoys;
    }

    public String toString() {
        return "Doctor{" +
                "id: " + id + '/' +
                "username: " + getUsername() + '/' +
                "password: " + getPassword() + '/' +
                "}";
    }
}
