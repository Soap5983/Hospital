package src.model;

public class Nurse extends Client{

    private static int id = 0;
    public Nurse() {
        super();
        id++;
    }

    public Nurse(String username, String password) {
        super(username, password);
        id++;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Nurse.id = id;
    }

    public String toString() {
        return "Nurse{" +
                "id: " + id + '/' +
                "username: " + getUsername() + '/' +
                "password: " + getPassword() + '/' +
                "}";
    }
}
