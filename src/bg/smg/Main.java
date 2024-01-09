package bg.smg;

import bg.smg.util.DBManager;
import bg.smg.view.LoginScreen;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        connectionWithDataBase();
        LoginScreen loginScreen = new LoginScreen();
        //String encode = Base64.getEncoder().encodeToString("password1234".getBytes());
        //System.out.println(encode);
    }

    public static void connectionWithDataBase(){
        DBManager manager;
        try {
            manager = DBManager.getInstance();
            DataSource dataSource = manager.getDataSource();
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM rooms");
            //индексите на колоните се броят от 1 :)
            while (resultSet.next()){
                System.out.print(resultSet.getInt(1) + " ");
                System.out.print(resultSet.getInt(2) + " ");
                System.out.print(resultSet.getInt(3) + " ");
                System.out.print(resultSet.getString(4) + " ");
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}