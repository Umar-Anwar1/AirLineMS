import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cancel extends JFrame implements ActionListener {

    JTextField TicketIDTextfield;
    JButton fetchDetailButton, CancelButton;
    JLabel NameTextfield, flightNameTextfield, caneleNumberTextfield, travelDateTextfield, flightCodeTextfield;

    public Cancel() {
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setSize(800, 500);
        setLocation(200, 100);
        setVisible(true);

        JLabel heading = new JLabel("CANCEL FLIGHT");
        heading.setBounds(280, 20, 250, 35);
        heading.setForeground(Color.BLUE);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        add(heading);

        ImageIcon Image1 = new ImageIcon(ClassLoader.getSystemResource("icons/cancel.jpg"));
        Image image2 = Image1.getImage().getScaledInstance(300, 250, Image.SCALE_DEFAULT);
        ImageIcon detailsImage = new ImageIcon(image2);
        JLabel ImageLabel = new JLabel(detailsImage);
        ImageLabel.setBounds(420, 125, 300, 250);
        add(ImageLabel);

        JLabel TicketID = new JLabel("Ticket ID");
        TicketID.setBounds(60, 80, 150, 25);
        TicketID.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(TicketID);

        TicketIDTextfield = new JTextField();
        TicketIDTextfield.setBounds(170, 80, 130, 22);
        add(TicketIDTextfield);

        fetchDetailButton = new JButton("Fetch Details");
        fetchDetailButton.setBackground(Color.BLACK);
        fetchDetailButton.setForeground(Color.WHITE);
        fetchDetailButton.setBounds(315, 80, 120, 22);
        fetchDetailButton.addActionListener(this);
        add(fetchDetailButton);

        JLabel customerName = new JLabel("Name");
        customerName.setBounds(60, 130, 150, 25);
        customerName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(customerName);

        NameTextfield = new JLabel();
        NameTextfield.setBounds(220, 130, 170, 25);
        add(NameTextfield);

        JLabel caneleNumber = new JLabel("Cancellation Number");
        caneleNumber.setBounds(60, 180, 180, 25);
        caneleNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(caneleNumber);

        caneleNumberTextfield = new JLabel();
        caneleNumberTextfield.setBounds(220, 180, 170, 25);
        caneleNumberTextfield.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(caneleNumberTextfield);

        int randomNumber = (int) (Math.random() * 900000) + 100000;
        caneleNumberTextfield.setText(String.valueOf(randomNumber));

        JLabel flightCode = new JLabel("Flight Code");
        flightCode.setBounds(60, 230, 150, 25);
        flightCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(flightCode);

        flightCodeTextfield = new JLabel();
        flightCodeTextfield.setBounds(220, 230, 170, 25);
        add(flightCodeTextfield);

        JLabel flightName = new JLabel("Flight Name");
        flightName.setBounds(60, 280, 150, 25);
        flightName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(flightName);

        flightNameTextfield = new JLabel();
        flightNameTextfield.setBounds(220, 280, 170, 25);
        add(flightNameTextfield);

        JLabel travelDate = new JLabel("Travel Date");
        travelDate.setBounds(60, 330, 150, 25);
        travelDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(travelDate);

        travelDateTextfield = new JLabel();
        travelDateTextfield.setBounds(220, 330, 170, 25);
        add(travelDateTextfield);

        CancelButton = new JButton("Cancel Flight");
        CancelButton.setBackground(Color.BLACK);
        CancelButton.setForeground(Color.WHITE);
        CancelButton.setBounds(250, 400, 120, 24);
        CancelButton.addActionListener(this);
        add(CancelButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == fetchDetailButton) {
            String ticketID = TicketIDTextfield.getText();

            try {
                Connection connect = DBconnection.getConnection();
                String query = "SELECT * FROM passenger_details WHERE id = ?";
                PreparedStatement pst = connect.prepareStatement(query);
                pst.setString(1, ticketID);
                ResultSet result = pst.executeQuery();

                if (result.next()) {
                    NameTextfield.setText(result.getString("name"));
                    flightNameTextfield.setText(result.getString("flight_name"));
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

        } else if (e.getSource() == CancelButton) {

            String ticketId = TicketIDTextfield.getText();
            String name = NameTextfield.getText();
            String flightName = flightNameTextfield.getText();
            String cancellationNumber = caneleNumberTextfield.getText();
            String travelDate = travelDateTextfield.getText();
            String flightCode = flightCodeTextfield.getText();

            try {
                Connection connect = DBconnection.getConnection();

                String insertQuery = "INSERT INTO cancel_ticket (ticketId, name, cancellation_number, flight_code, flight_name, travel_date) VALUES (?, ?, ?, ?, ?, ?)";
                String deleteQuery = "DELETE FROM passenger_details WHERE id = ?";

                PreparedStatement insertPst = connect.prepareStatement(insertQuery);
                PreparedStatement deletePst = connect.prepareStatement(deleteQuery);

                insertPst.setString(1, ticketId);
                insertPst.setString(2, name);
                insertPst.setString(3, cancellationNumber);
                insertPst.setString(4, flightCode);
                insertPst.setString(5, flightName);
                insertPst.setString(6, travelDate);

                deletePst.setString(1, ticketId);

                int deleteResult = deletePst.executeUpdate();
                int insertResult = insertPst.executeUpdate();

                if (insertResult > 0 && deleteResult > 0) {
                    JOptionPane.showMessageDialog(null, "Flight canceled and details saved successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Cancellation failed.");
                }

                connect.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
            }
        }
    }
}
