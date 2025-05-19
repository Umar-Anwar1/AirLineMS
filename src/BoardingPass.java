import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardingPass extends JFrame implements ActionListener {

    JTextField TicketIDTextfield;
    JButton fetchDetailButton;
    JLabel NameTextfield, NationalityTextfield, SourceTextfield, destinationTextfield, flightNameTextfield, flightCodeTextfield, travelDateTextfield;

    public BoardingPass() {

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setSize(900, 400);
        setLocation(200, 100);

        JLabel heading = new JLabel("RIPHAH AIR");
        heading.setBounds(300, -5, 250, 35);
        heading.setForeground(Color.BLUE);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        add(heading);

        JLabel Subheading = new JLabel("Boarding Pass"); // Fixed typo here
        Subheading.setBounds(300, 40, 300, 28);
        Subheading.setForeground(Color.BLACK);
        Subheading.setFont(new Font("Tahoma", Font.PLAIN, 25));
        add(Subheading);

        JLabel TicketID = new JLabel("Ticket ID");
        TicketID.setBounds(60, 100, 150, 25);
        TicketID.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(TicketID);

        TicketIDTextfield = new JTextField();
        TicketIDTextfield.setBounds(170, 100, 130, 22);
        add(TicketIDTextfield);

        fetchDetailButton = new JButton("Fetch Details");
        fetchDetailButton.setBackground(Color.BLACK);
        fetchDetailButton.setForeground(Color.WHITE);
        fetchDetailButton.setBounds(315, 100, 120, 22);
        fetchDetailButton.addActionListener(this);
        add(fetchDetailButton);

        JLabel customerName = new JLabel("Name");
        customerName.setBounds(60, 140, 150, 25);
        customerName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(customerName);

        NameTextfield = new JLabel();
        NameTextfield.setBounds(220, 140, 170, 25);
        add(NameTextfield);

        JLabel Nationality = new JLabel("Nationality");
        Nationality.setBounds(60, 180, 150, 25);
        Nationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(Nationality);

        NationalityTextfield = new JLabel();
        NationalityTextfield.setBounds(220, 180, 170, 25);
        add(NationalityTextfield);

        JLabel Source = new JLabel("Source");
        Source.setBounds(60, 220, 150, 25);
        Source.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(Source);

        SourceTextfield = new JLabel();
        SourceTextfield.setBounds(220, 220, 170, 25);
        add(SourceTextfield);

        JLabel destination = new JLabel("Destination");
        destination.setBounds(320, 220, 150, 25);
        destination.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(destination);

        destinationTextfield = new JLabel();
        destinationTextfield.setBounds(460, 220, 170, 25);
        add(destinationTextfield);

        JLabel flightName = new JLabel("Flight Name");
        flightName.setBounds(60, 260, 150, 25);
        flightName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(flightName);

        flightNameTextfield = new JLabel();
        flightNameTextfield.setBounds(220, 260, 170, 25);
        add(flightNameTextfield);

        JLabel flightCode = new JLabel("Flight Code");
        flightCode.setBounds(320, 260, 150, 25);
        flightCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(flightCode);

        flightCodeTextfield = new JLabel();
        flightCodeTextfield.setBounds(460, 260, 170, 25);
        add(flightCodeTextfield);

        JLabel travelDate = new JLabel("Travel Date");
        travelDate.setBounds(60, 300, 150, 25);
        travelDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(travelDate);

        travelDateTextfield = new JLabel();
        travelDateTextfield.setBounds(220, 300, 170, 25);
        add(travelDateTextfield);

        ImageIcon Image1 = new ImageIcon(ClassLoader.getSystemResource("icons/plane.png"));
        Image image2 = Image1.getImage().getScaledInstance(400, 350, Image.SCALE_DEFAULT);
        ImageIcon detailsImage = new ImageIcon(image2);
        JLabel ImageLabel = new JLabel(detailsImage);
        ImageLabel.setBounds(550, 80, 300, 250);
        add(ImageLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ticketID = TicketIDTextfield.getText().trim();

        if (ticketID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a Ticket ID.");
            return;
        }

        try {
            Connection connect = DBconnection.getConnection();
            String query = "SELECT * FROM passenger_details WHERE id = ?";
            PreparedStatement pst = connect.prepareStatement(query);
            pst.setString(1, ticketID);
            ResultSet result = pst.executeQuery();

            if (result.next()) {
                NameTextfield.setText(result.getString("name"));
                NationalityTextfield.setText(result.getString("nationality"));
                flightNameTextfield.setText(result.getString("flight_name"));
                SourceTextfield.setText(result.getString("source"));
                destinationTextfield.setText(result.getString("destination"));
                travelDateTextfield.setText(result.getString("travel_date"));
                flightCodeTextfield.setText(result.getString("flight_code"));
            } else {
                JOptionPane.showMessageDialog(null, "Ticket ID does not exist.");
            }

            connect.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
        }
    }

}
