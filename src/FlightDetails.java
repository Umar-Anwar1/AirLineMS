import javax.swing.*;
import net.proteanit.sql.DbUtils;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightDetails extends JFrame {

    public FlightDetails(){

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setSize(1000,600);
        setLocation(150,50);
        setVisible(true);

        JTable flightDetail = new JTable();


        try {

            Connection connect = DBconnection.getConnection();

            String query = "Select * from flight_details";

            PreparedStatement pst = connect.prepareStatement(query);

            ResultSet result = pst.executeQuery();

            flightDetail.setModel(DbUtils.resultSetToTableModel(result));
            flightDetail.setBounds(0,0,1000,600);
            add(flightDetail);

            JScrollPane scrollPane = new JScrollPane(flightDetail);
            scrollPane.setBounds(0, 0, 1000, 600);  // Adjust position and size as needed
            add(scrollPane);

        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
