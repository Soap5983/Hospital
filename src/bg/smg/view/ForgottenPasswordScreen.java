package bg.smg.view;

import bg.smg.util.DBManager;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;
import java.util.Base64;

public class ForgottenPasswordScreen extends JFrame {
    private JFrame frame;
    private JTextField textField;
    private JPasswordField passwordField;

    public ForgottenPasswordScreen() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 561, 344);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Смяна на парола");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel.setBounds(150, 35, 258, 54);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Потребител:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(55, 138, 142, 25);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Нова парола:");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_1.setBounds(55, 200, 142, 25);
        frame.getContentPane().add(lblNewLabel_1_1);

        textField = new JTextField();
        textField.setBounds(342, 138, 142, 32);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(342, 191, 142, 35);
        frame.getContentPane().add(passwordField);

        JButton btnNewButton = new JButton("Запази промените");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnNewButton.setBounds(179, 257, 220, 40);
        frame.getContentPane().add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordField.getPassword());
                String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
                changePassword(textField.getText(), encodedPassword);
                frame.dispose();
                LoginScreen loginScreen = new LoginScreen();
            }
        });


        frame.setVisible(true);
    }

    public int changePassword(String username, String newPassword){
        DBManager manager;
        try {
            manager = DBManager.getInstance();
            DataSource dataSource = manager.getDataSource();
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            //connection.prepareStatement("INSERT INTO `doctors` " + "VALUES (" + "`5`" + ","  + "`Soapa`" +  "," +  "`4`" + ",`" +  newPassword + "`,`" + "bla" + "`);");
            statement.executeQuery("UPDATE doctors SET doctors.password = '" + newPassword + "' WHERE doctors.nameOfDoctor = '" + username+"'");
            //ResultSet resultSet = statement.executeQuery("SELECT * FROM doctors;");
            //statement.executeUpdate("INSERT INTO doctors " + "VALUES (" + 5 + ","  + "Soapa" +  "," +  4 + "," +  newPassword + "," + "bla" + ");");
            /*while(resultSet.next()){
                if(resultSet.getString(2).equals(username)){
                    int id = resultSet.getInt(1);
                    long UIN = resultSet.getLong(3);
                    String specialty = resultSet.getString(5);
                    //statement.executeQuery("DELETE FROM doctors WHERE id = " + id +";");
                    //statement.executeQuery("INSERT INTO doctors (id, nameOfDoctor, UIN, password, specialty) VALUES (" + id + ","  + username +  "," +  UIN + "," +  newPassword + "," + specialty + ");");
                    return 0;
                }
            }
            resultSet = statement.executeQuery("SELECT nameOfNurse, id FROM nurses;");
            while(resultSet.next()){
                if(resultSet.getString(1).equals(username)){
                    int id = resultSet.getInt(2);
                    ResultSet changeRegistration = statement.executeQuery("DELETE FROM nurses WHERE id = " + id);
                    return 0;
                }
            }
            resultSet = statement.executeQuery("SELECT nameOfPatient, id FROM patients");
            while(resultSet.next()){
                if(resultSet.getString(1).equals(username)){
                    int id = resultSet.getInt(2);
                    ResultSet changeRegistration = statement.executeQuery("DELETE FROM patients WHERE id = " + id);
                    return 0;
                }
            }
            resultSet = statement.executeQuery("SELECT nameOfWardBoy, id FROM wardboys");
            while(resultSet.next()){
                if(resultSet.getString(1).equals(username)){
                    int id = resultSet.getInt(2);
                    ResultSet changeRegistration = statement.executeQuery("DELETE FROM wardboys WHERE id = " + id);
                    return 0;
                }
            }
*/
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

}
