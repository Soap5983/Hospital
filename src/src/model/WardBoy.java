package src.model;

public class WardBoy extends Client{
    private static int id = 0;

    public WardBoy() {
        super();
        id++;
    }

    public WardBoy(String username, String password) {
        super(username, password);
        id++;
    }

    @Override
    public String toString() {
        return "WardBoy{" +
                "id: " + id + '/' +
                "username: " + getUsername() + '/' +
                "password: " + getPassword() + '/' +
        "}";
    }
}
