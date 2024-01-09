package bg.smg.model;

enum TypeOfRoom{
    ICU, MEDICAL_SURGICAL, MATERNITY_CARE, MENTAL_HEALTH, CONSULTATION
}
public class Room{
    TypeOfRoom type;
    int roomNumber;
    int numberOfBeds;

    public TypeOfRoom getType() {
        return type;
    }

    public void setType(TypeOfRoom type) {
        this.type = type;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }
}
