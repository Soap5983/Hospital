package bg.smg.view;

import bg.smg.model.*;
import bg.smg.util.DBManager;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

public class RegistrationScreen extends JFrame {
    private JFrame frame;
    private JTextField textField;
    private JPasswordField passwordField;
    private ArrayList<Client> newRegistrations;

    public RegistrationScreen() {
        initialize();
        newRegistrations =  new ArrayList<Client>();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 607, 483);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Регистрация:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel.setBounds(203, 31, 193, 56);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Въведете потребителско име:");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(44, 97, 287, 56);
        frame.getContentPane().add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBounds(352, 109, 201, 42);
        frame.getContentPane().add(textField);
        textField.setColumns(10);


        JLabel lblNewLabel_1_1 = new JLabel("Въведете парола:");
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_1.setBounds(44, 183, 181, 56);
        frame.getContentPane().add(lblNewLabel_1_1);

        passwordField = new JPasswordField();
        passwordField.setBounds(352, 183, 201, 42);
        frame.getContentPane().add(passwordField);

        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.setBounds(361, 287, 148, 21);
        frame.getContentPane().add(comboBox);
        comboBox.addItem("Доктор");
        comboBox.addItem("Медицинска сестра");
        comboBox.addItem("Санитар");
        comboBox.addItem("Пациент");


        JLabel lblNewLabel_1_1_1 = new JLabel("Вид профил:");
        lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_1_1.setBounds(44, 265, 181, 56);
        frame.getContentPane().add(lblNewLabel_1_1_1);

        JButton btnNewButton = new JButton("Запази промените");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnNewButton.setBounds(179, 379, 230, 32);
        frame.getContentPane().add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newInputName = textField.getText();
                String newInputPass = new String(passwordField.getPassword());
                String encodedPassword = Base64.getEncoder().encodeToString(newInputPass.getBytes());
                Client client;
                if(comboBox.getSelectedItem().equals("Доктор")){
                    client = new Doctor(newInputName, encodedPassword);
                    addNewDoctorRegistrationToDatabase(newInputName, new BigInteger("0000000000"), encodedPassword, "default");
                }else if(comboBox.getSelectedItem().equals("Медицинска сестра")){
                    client = new Nurse(newInputName, newInputPass);
                }else if(comboBox.getSelectedItem().equals("Санитар")){
                    client = new WardBoy(newInputName, newInputPass);
                }else{
                    client = new Patient(newInputPass,newInputPass);
                }
                newRegistrations.add(client);

                frame.dispose();
                LoginScreen loginScreen = new LoginScreen();
            }
        });

        frame.setVisible(true);
    }

    public ArrayList<Client> getNewRegistrations() {
        return newRegistrations;
    }

    public void setNewRegistrations(ArrayList<Client> newRegistrations) {
        this.newRegistrations = newRegistrations;
    }

    public void addNewDoctorRegistrationToDatabase(String username, BigInteger UIN, String encodedPassword, String specialty){
        DBManager manager;
        try {
            manager = DBManager.getInstance();
            DataSource dataSource = manager.getDataSource();
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("INSERT INTO `doctors`(`nameOfDoctor`, `UIN`, `password`, `specialty`) " +
                    "VALUES ('" + username +  "'," + "'" + UIN.toString() + "'," + "'" + encodedPassword + "'," + "'" + specialty + "');");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}