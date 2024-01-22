package src.view;
import src.model.Patient;
import src.util.DBManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

import javax.sql.DataSource;
import javax.swing.*;

public class AssignmentTable {
    private JFrame frame;
    private JTable table;
    private JLabel lblNewLabel_1;
    private JTextField textField;

    public AssignmentTable(int doctorId) {
        initialize(doctorId);
    }

    private void initialize(int doctorId) {
        DBManager manager;
        ArrayList<Patient> patientData = new ArrayList<Patient>();
        try {
            manager = DBManager.getInstance();
            DataSource dataSource = manager.getDataSource();
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSetIDs = statement.executeQuery("SELECT patientID FROM assignments WHERE doctorID = '" + doctorId + "';");
            while (resultSetIDs.next()) {
                ResultSet resultSetPatientData = statement.executeQuery("SELECT * FROM patients WHERE id = '" + resultSetIDs.getInt(1) + "';");
                if (resultSetPatientData.next()) {
                    Patient currentPatient = new Patient(resultSetPatientData.getInt(1), resultSetPatientData.getString(2),
                            resultSetPatientData.getString(4), resultSetPatientData.getInt(5),
                            resultSetPatientData.getInt(6));
                    patientData.add(currentPatient);
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        frame = new JFrame();
        frame.setBounds(100, 100, 1000, 1466);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        String[] columnNames = {"Име", "Диагноза", "Плащане", "Стая"};
        String[][] patientDataForTable = initializeData(patientData);
        table = new JTable(patientDataForTable, columnNames);
        table.setBounds(63, 55, 673, 312);
        frame.getContentPane().add(table);
        lblNewLabel_1 = new JLabel("Въведете име на пациент за редакция:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(103, 395, 370, 24);
        frame.getContentPane().add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBounds(562, 402, 96, 19);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
        String input = textField.getText();

        JButton btnNewButton_2 = new JButton("Изтрий");
        btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_2.setBounds(300, 500, 400, 42);
        frame.getContentPane().add(btnNewButton_2);
        btnNewButton_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletePatient(textField.getText());
            }
        });


        JButton btnNewButton_4 = new JButton("Редактирай");
        btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_4.setBounds(400, 600, 400, 42);
        frame.getContentPane().add(btnNewButton_4);
        btnNewButton_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();
                Patient toBeEditted = new Patient();
                for(int i=0; i<patientData.size(); i++){
                    if(patientData.get(i).getUsername().equals(name)){
                        toBeEditted = patientData.get(i);
                    }
                }
                EditPatientScreen editPatientScreen = new EditPatientScreen(getPatientID(name), toBeEditted.getUsername(), toBeEditted.getDiagnosis(), toBeEditted.getPayment(), toBeEditted.getRoomNumber());

            }
        });
        frame.setVisible(true);
    }

    public String[][] initializeData(ArrayList<Patient> patients) {
        String[][] output = new String[patients.size()][4];
        for (int i = 0; i < patients.size(); i++) {
            output[i][0] = patients.get(i).getUsername();
            output[i][1] = patients.get(i).getDiagnosis();
            output[i][2] = Integer.toString(patients.get(i).getPayment());
            output[i][3] = Integer.toString(patients.get(i).getRoomNumber());
        }
        return output;
    }

    public void deletePatient(String nameOfPatient) {
        DBManager manager;
        try {
            manager = DBManager.getInstance();
            DataSource dataSource = manager.getDataSource();
            Connection connection = dataSource.getConnection();
            //Statement statement = connection.createStatement();
            //ResultSet resultSet = statement.executeQuery("Delete FROM patients WHERE nameOfPatient = '" + nameOfPatient + "';");
            //resultSet.first();
            //resultSet.deleteRow();
           /*try( PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM patients WHERE nameOfPatient = ?")){
               preparedStatement.setString(1, nameOfPatient );
               System.out.println(preparedStatement);
               int rowsAffected = preparedStatement.executeUpdate();
               if(rowsAffected > 0){
                   System.out.println("Congratulations!");
               }
           };

            */
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM patients WHERE nameOfPatient ='" + nameOfPatient + "';");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getPatientID(String username) {
        DBManager manager;
        try {
            manager = DBManager.getInstance();
            DataSource dataSource = manager.getDataSource();
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT patients.id FROM patients WHERE nameOfPatient = '" + username + "';");
            if(resultSet.next()){
                int output = resultSet.getInt(1);
                System.out.println(output);
                return output;
            }
            return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
