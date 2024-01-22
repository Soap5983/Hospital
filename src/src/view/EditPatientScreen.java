package src.view;

import src.util.DBManager;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EditPatientScreen {
    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;

    public EditPatientScreen(int patientID, String originalName, String originalDiagnosis, int originalPayment, int originalRoom) {
        initialize(patientID, originalName, originalDiagnosis, originalPayment, originalRoom);
    }

    private void initialize(int patientID, String originalName, String originalDiagnosis, int originalPayment, int originalRoom) {
        frame = new JFrame();
        frame.setBounds(100, 100, 469, 451);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Име:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(91, 110, 96, 43);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Диагноза:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(91, 163, 96, 43);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Плащане:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_2.setBounds(91, 216, 96, 43);
        frame.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Стая:");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_3.setBounds(91, 271, 96, 43);
        frame.getContentPane().add(lblNewLabel_3);

        textField = new JTextField();
        textField.setBounds(317, 126, 96, 19);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(317, 179, 96, 19);
        frame.getContentPane().add(textField_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(317, 232, 96, 19);
        frame.getContentPane().add(textField_2);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(317, 287, 96, 19);
        frame.getContentPane().add(textField_3);

        JLabel lblNewLabel_4 = new JLabel("Редактирайте:");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel_4.setBounds(126, 45, 242, 43);
        frame.getContentPane().add(lblNewLabel_4);

        JButton btnNewButton = new JButton("Запази промените");
        btnNewButton.setBounds(190, 341, 136, 34);
        frame.getContentPane().add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            private String originalN = originalName;
            private String originalD = originalDiagnosis;
            private int originalP = originalPayment;
            private int originalR = originalRoom;

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!textField.getText().equals("")){
                    originalN = textField.getText();
                }
                if(!textField_1.getText().equals("")){
                    this.originalD = textField_1.getText();
                }
                if(!textField_2.getText().equals("")){
                    this.originalP = Integer.parseInt((textField_2.getText()));
                }
                if(!textField_3.getText().equals("")){
                   this.originalR = Integer.parseInt((textField_3.getText()));
                }

                DBManager manager;
                try {
                    manager = DBManager.getInstance();
                    DataSource dataSource = manager.getDataSource();
                    Connection connection = dataSource.getConnection();
                    Statement statement = connection.createStatement();

                    statement.executeUpdate("UPDATE patients SET patients.nameOfPatient ='" + originalN + "'" +
                                    ", patients.diagnosis = '" + originalD + "', patients.payment = '" + originalP + "', patients.roomNumber = '" + originalR + "' WHERE patients.id = '" + patientID + "';");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        frame.setVisible(true);
    }
}

