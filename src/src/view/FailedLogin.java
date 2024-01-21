package src.view;

import javax.swing.*;
import java.awt.*;

public class FailedLogin extends JFrame {
    private JFrame frame;

    public FailedLogin() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Неуспешен вход");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel.setBounds(84, 63, 263, 113);
        frame.getContentPane().add(lblNewLabel);
        frame.setVisible(true);
    }

}
