import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddCustomer extends JFrame implements ActionListener {

    JTextField NameTextfield, NationalityTextfield, CnicTextfield, AddressTextfield, PhoneNumberTextfield, EmailTextfield;
    JRadioButton maleButton, femaleButton;
    public AddCustomer(){

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setSize(900,600);
        setLocation(200,50);
        setVisible(true);

        JLabel heading = new JLabel("ADD CUSTOMER DETAILS");
        heading.setBounds(240,20,500,35);
        heading.setForeground(Color.BLUE);
        heading.setFont(new Font("Tahoma", Font.PLAIN,32));
        add(heading);

        JLabel customerName = new JLabel("Name");
        customerName.setBounds(60,80,150,25);
        customerName.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(customerName);

        NameTextfield = new JTextField();
        NameTextfield.setBounds(220,80,170,25);
        add(NameTextfield);

        JLabel Nationality = new JLabel("Nationality");
        Nationality.setBounds(60,130,150,25);
        Nationality.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(Nationality);

        NationalityTextfield = new JTextField();
        NationalityTextfield.setBounds(220,130,170,25);
        add(NationalityTextfield);

        JLabel Cnic = new JLabel("CNIC");
        Cnic.setBounds(60,180,150,25);
        Cnic.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(Cnic);

        CnicTextfield = new JTextField();
        CnicTextfield.setBounds(220,180,170,25);
        add(CnicTextfield);

        JLabel Address = new JLabel("Address");
        Address.setBounds(60,230,150,25);
        Address.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(Address);

        AddressTextfield = new JTextField();
        AddressTextfield.setBounds(220,230,170,25);
        add(AddressTextfield);

        JLabel Gender = new JLabel("Gender");
        Gender.setBounds(60,280,150,25);
        Gender.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(Gender);

        maleButton = new JRadioButton("Male");
        maleButton.setBounds(220,280,70,25);
        maleButton.setBackground(Color.WHITE);
        add(maleButton);

        femaleButton = new JRadioButton("Female");
        femaleButton.setBounds(300,280,70,25);
        femaleButton.setBackground(Color.WHITE);
        add(femaleButton);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);

        JLabel PhoneNumber = new JLabel("Phone Number");
        PhoneNumber.setBounds(60,330,150,25);
        PhoneNumber.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(PhoneNumber);

        PhoneNumberTextfield = new JTextField();
        PhoneNumberTextfield.setBounds(220,330,170,25);
        add(PhoneNumberTextfield);

        JLabel Email = new JLabel("Email");
        Email.setBounds(60,380,150,25);
        Email.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(Email);

        EmailTextfield = new JTextField();
        EmailTextfield.setBounds(220,380,170,25);
        add(EmailTextfield);

        JButton Save = new JButton("SAVE");
        Save.setBackground(Color.BLACK);
        Save.setForeground(Color.WHITE);
        Save.setBounds(230,450,150,30);
        Save.addActionListener(this);
        add(Save);

        ImageIcon PersonImage = new ImageIcon(ClassLoader.getSystemResource("icons/emp.png"));
        JLabel ImageLabel = new JLabel(PersonImage);
        ImageLabel.setBounds(400,80,280,370);
        add(ImageLabel);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        String name = NameTextfield.getText();
        String nationality = NationalityTextfield.getText();
        String cnic = CnicTextfield.getText();
        String adress = AddressTextfield.getText();
        String phoneNumber = PhoneNumberTextfield.getText();
        String email = EmailTextfield.getText();
        String gender = null;

        if(maleButton.isSelected()){
            gender = "Male";

        }else {
            gender = "Female";
        }

        try {

            Connection connect = DBconnection.getConnection();

            String query = "INSERT INTO CustomerDetails (Name, Nationality, CNIC, Address, Gender, PhoneNumber, Email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = connect.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, nationality);
            pst.setString(3, cnic);
            pst.setString(4, adress);
            pst.setString(5, gender);
            pst.setString(6, phoneNumber);
            pst.setString(7, email);

            int rowsAffected = pst.executeUpdate();

            if(rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Customer details added successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add customer details.");
            }
        } catch (RuntimeException | SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
}
