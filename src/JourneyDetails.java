import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class JourneyDetails extends JFrame implements ActionListener {

    JTable JourneyDetail;
    JTextField TicketId;
    JButton showJourneyDetails;

    public JourneyDetails() {
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setSize(1200, 500);
        setLocation(70, 80);

        JLabel ticketDetails = new JLabel("Ticket Details");
        ticketDetails.setFont(new Font("Tahoma", Font.PLAIN, 18));
        ticketDetails.setBounds(50, 50, 120, 25);
        add(ticketDetails);

        TicketId = new JTextField();
        TicketId.setBounds(160, 50, 120, 25);
        add(TicketId);

        showJourneyDetails = new JButton("Show Details");
        showJourneyDetails.setBackground(Color.BLACK);
        showJourneyDetails.setForeground(Color.WHITE);
        showJourneyDetails.setBounds(290, 50, 120, 25);
        showJourneyDetails.addActionListener(this);
        add(showJourneyDetails);

        JourneyDetail = new JTable();
        JScrollPane scrollPane = new JScrollPane(JourneyDetail);
        scrollPane.setBounds(0, 100, 1180, 100);
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String id = TicketId.getText();
            Connection connect = DBconnection.getConnection();
            String query = "SELECT * FROM passenger_details WHERE id = ?";
            PreparedStatement pst = connect.prepareStatement(query);
            pst.setString(1, id);
            ResultSet result = pst.executeQuery();

            if (!result.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "No information Found");
            } else {
                JourneyDetail.setModel(DbUtils.resultSetToTableModel(result));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
        }
    }
}
