package bg.smg.model;

public class Diagnosis {
    String name;
    String prescription;

    public Diagnosis(String name, String prescription) {
        this.name = name;
        this.prescription = prescription;
    }
    public Diagnosis(){
        name = "";
        prescription = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }
}
