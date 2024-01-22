package src.view;
import src.model.Patient;
import src.util.DBManager;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;
import javax.swing.JFrame;

import javax.swing.JTable;
import javax.swing.JLabel;
public class AssignmentTable {
    private JFrame frame;
    private JTable table;

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
            while(resultSetIDs.next()){
                    ResultSet resultSetPatientData = statement.executeQuery("SELECT * FROM patients WHERE id = '" + resultSetIDs.getInt(1) + "';");
                    if(resultSetPatientData.next()) {
                        Patient currentPatient = new Patient(resultSetPatientData.getString(2),
                                resultSetPatientData.getString(4), resultSetPatientData.getInt(5),
                                resultSetPatientData.getInt(6));
                        patientData.add(currentPatient);
                        System.out.println(currentPatient.toString());
                    }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        frame = new JFrame();
        frame.setBounds(100, 100, 804, 466);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        String[] columnNames = {"Име", "Диагноза","Плащане","Стая"};
        String[][] patientDataForTable = initializeData(patientData);
        table = new JTable(patientDataForTable, columnNames);
        table.setBounds(63, 55, 673, 312);
        frame.getContentPane().add(table);
        frame.setVisible(true);
    }

    public String[][] initializeData(ArrayList<Patient> patients){
        String[][] output = new String[patients.size()][4];
        for(int i=0; i<patients.size(); i++){
            output[i][0] = patients.get(i).getUsername();
            output[i][1] = patients.get(i).getDiagnosis();
            output[i][2] = Integer.toString(patients.get(i).getPayment());
            output[i][3] = Integer.toString(patients.get(i).getRoomNumber());
        }
        return output;
    }
}
