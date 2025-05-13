import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JTextField usernameTextField;
    JPasswordField passwordField;

    public Login(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel usernameLabel = new JLabel("username");
        usernameLabel.setBounds(20,20,100,20);
        add(usernameLabel);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(130,20,200,20);
        add(usernameTextField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(20,60,100,20);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(130,60,200,20);
        add(passwordField);

        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(40,120,120,20);
        resetButton.addActionListener(this);
        add(resetButton);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(190,120,120,20);
        loginButton.addActionListener(this);
        add(loginButton);



        setSize(400,250);
        setLocation(600,250);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Reset")) {
            usernameTextField.setText("");
            passwordField.setText("");
        } else if (e.getActionCommand().equals("Login")) {
            String username = usernameTextField.getText();
            String password = passwordField.getText();

            try {
                Connection connect = DBconnection.getConnection();
                String query = "SELECT * FROM login WHERE username = ? AND password = ?";
                PreparedStatement pst = connect.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);

                ResultSet result = pst.executeQuery();

                if (result.next()) {

                    JOptionPane.showMessageDialog(null, "Login Successful");
                    new Home();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username and Password");
                }

                connect.close();

            } catch (RuntimeException | SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}