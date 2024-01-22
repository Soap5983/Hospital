package src.view;

import javax.swing.*;
import java.awt.*;

public class SuccessfulLogin extends JFrame {
    private JFrame frame;

    public SuccessfulLogin(int doctorId) {
        initialize(doctorId);
    }

    private void initialize(int doctorId) {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Успешен вход!");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel.setBounds(90, 74, 261, 84);
        frame.getContentPane().add(lblNewLabel);


        frame.setVisible(true);

        AssignmentTable assignmentTable = new AssignmentTable(doctorId);
    }
}
