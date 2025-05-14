import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import com.toedter.calendar.JDateChooser;

public class BookFlight extends JFrame implements ActionListener {

    JTextField CnicTextfield;
    JLabel NameTextfield, NationalityTextfield, AddressTextfield,GenderLabel, EmailTextfield, flightCodeTextfield, flightNameTextfield, departureTimeTextfield, ArivalTimeTextfield;
    JButton SaveDetailButton, fetchDetailButton, fetchFlightDetailButton;
    Choice getSource, getDestination;
    JDateChooser chooseDate;
    public BookFlight(){

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setSize(1000,700);
        setLocation(200,0);
        setVisible(true);

        JLabel heading = new JLabel("BOOK FLIGHT");
        heading.setBounds(380,20,500,35);
        heading.setForeground(Color.BLUE);
        heading.setFont(new Font("Tahoma", Font.PLAIN,32));
        add(heading);

        JLabel Cnic = new JLabel("CNIC");
        Cnic.setBounds(60,80,150,25);
        Cnic.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(Cnic);

        CnicTextfield = new JTextField();
        CnicTextfield.setBounds(220,80,170,25);
        add(CnicTextfield);

        fetchDetailButton = new JButton("Fetch Details");
        fetchDetailButton.setBackground(Color.BLACK);
        fetchDetailButton.setForeground(Color.WHITE);
        fetchDetailButton.setBounds(400,80,120,24);
        fetchDetailButton.addActionListener(this);
        add(fetchDetailButton);

        JLabel customerName = new JLabel("Name");
        customerName.setBounds(60,130,150,25);
        customerName.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(customerName);

        NameTextfield = new JLabel();
        NameTextfield.setBounds(220,130,170,25);
        add(NameTextfield);

        JLabel Nationality = new JLabel("Nationality");
        Nationality.setBounds(60,180,150,25);
        Nationality.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(Nationality);

        NationalityTextfield = new JLabel();
        NationalityTextfield.setBounds(220,180,170,25);
        add(NationalityTextfield);

        JLabel Address = new JLabel("Address");
        Address.setBounds(60,230,150,25);
        Address.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(Address);

        AddressTextfield = new JLabel();
        AddressTextfield.setBounds(220,230,170,25);
        add(AddressTextfield);

        JLabel Gender = new JLabel("Gender");
        Gender.setBounds(60,280,150,25);
        Gender.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(Gender);

        GenderLabel = new JLabel("Gender");
        GenderLabel.setBounds(220,280,150,25);
        add(GenderLabel);

        JLabel Email = new JLabel("Email");
        Email.setBounds(60,330,150,25);
        Email.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(Email);

        EmailTextfield = new JLabel();
        EmailTextfield.setBounds(220,330,170,25);
        add(EmailTextfield);

        JLabel Source = new JLabel("Source");
        Source.setBounds(60,380,150,25);
        Source.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(Source);

        getSource = new Choice();
        getSource.setBounds(220,380,150,25);
        add(getSource);

        JLabel Destination = new JLabel("Destination");
        Destination.setBounds(60,430,150,25);
        Destination.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(Destination);

        getDestination = new Choice();
        getDestination.setBounds(220,430,150,25);
        add(getDestination);

        fetchFlightDetailButton = new JButton("Fetch Details");
        fetchFlightDetailButton.setBackground(Color.BLACK);
        fetchFlightDetailButton.setForeground(Color.WHITE);
        fetchFlightDetailButton.setBounds(390,430,120,21);
        fetchFlightDetailButton.addActionListener(this);
        add(fetchFlightDetailButton);

        JLabel flightName = new JLabel("flight Name");
        flightName.setBounds(60,480,150,25);
        flightName.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(flightName);

        flightNameTextfield = new JLabel();
        flightNameTextfield.setBounds(220,480,170,25);
        add(flightNameTextfield);

        JLabel flightCode = new JLabel("flight Code");
        flightCode.setBounds(60,530,150,25);
        flightCode.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(flightCode);

        flightCodeTextfield = new JLabel();
        flightCodeTextfield.setBounds(220,530,170,25);
        add(flightCodeTextfield);

        JLabel departureTime = new JLabel("Departure Time");
        departureTime.setBounds(520,480,150,25);
        departureTime.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(departureTime);

        departureTimeTextfield = new JLabel();
        departureTimeTextfield.setBounds(680,480,170,25);
        add(departureTimeTextfield);

        JLabel ArivalTime = new JLabel("Arival Time");
        ArivalTime.setBounds(520,530,150,25);
        ArivalTime.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(ArivalTime);

        ArivalTimeTextfield = new JLabel();
        ArivalTimeTextfield.setBounds(680,530,170,25);
        add(ArivalTimeTextfield);

        JLabel Traveldate = new JLabel("Date of Travel");
        Traveldate.setBounds(60,580,150,25);
        Traveldate.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(Traveldate);

        chooseDate = new JDateChooser();
        chooseDate.setBounds(220,580,150,25);
        add(chooseDate);

        ImageIcon Image1 = new ImageIcon(ClassLoader.getSystemResource("icons/details.jpg"));
        Image image2 = Image1.getImage().getScaledInstance(500,300,Image.SCALE_DEFAULT);
        ImageIcon detailsImage = new ImageIcon(image2);
        JLabel ImageLabel = new JLabel(detailsImage);
        ImageLabel.setBounds(420,118,500,300);
        add(ImageLabel);

        SaveDetailButton = new JButton("Book Flight");
        SaveDetailButton.setBackground(Color.BLACK);
        SaveDetailButton.setForeground(Color.WHITE);
        SaveDetailButton.setBounds(400,620,120,24);
        SaveDetailButton.addActionListener(this);
        add(SaveDetailButton);



        try {
            Connection connect = DBconnection.getConnection();
            String query = "SELECT * FROM flight_details";

            Statement stmt = connect.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                getSource.add(result.getString("source"));
                getDestination.add(result.getString("destination"));
            }

            result.close();
            stmt.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == fetchDetailButton){
            String cnic = CnicTextfield.getText();

            try{

                Connection connect  = DBconnection.getConnection();
                String Query = "select * from CustomerDetails where CNIC = ? " ;
                PreparedStatement pst = connect.prepareStatement(Query);
                pst.setString(1,cnic);
                ResultSet result = pst.executeQuery();

                if(result.next()){

                    NameTextfield.setText(result.getString("Name"));
                    NationalityTextfield.setText(result.getString("Nationality"));
                    AddressTextfield.setText(result.getString("Address"));
                    GenderLabel.setText(result.getString("Gender"));
                    EmailTextfield.setText(result.getString("Email"));

                } else {
                    JOptionPane.showMessageDialog(null, "User does not exist");
                }
            } catch (RuntimeException | SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == fetchFlightDetailButton) {

            String source = getSource.getSelectedItem();
            String destination = getDestination.getSelectedItem();


            try{

                Connection connect  = DBconnection.getConnection();
                String Query = "SELECT * FROM flight_details where source = ? And destination = ?";
                PreparedStatement pst = connect.prepareStatement(Query);
                pst.setString(1,source);
                pst.setString(2,destination);
                ResultSet result = pst.executeQuery();

                if(result.next()){

                    flightCodeTextfield.setText(result.getString("flight_code"));
                    flightNameTextfield.setText(result.getString("flight_name"));
                    departureTimeTextfield.setText(result.getString("departure_time"));
                    ArivalTimeTextfield.setText(result.getString("arrival_time"));

            } else {
                JOptionPane.showMessageDialog(null, "Flight does not exist");
            }
        } catch (RuntimeException | SQLException ex){
            throw new RuntimeException(ex);
        }

        }else {

            String name = NameTextfield.getText();
            String nationality = NationalityTextfield.getText();
            String gender = GenderLabel.getText();
            String email = EmailTextfield.getText();
            String fligtCode = flightCodeTextfield.getText();
            String fligtName = flightNameTextfield.getText();
            String departureTime = departureTimeTextfield.getText();
            String arivalTime = ArivalTimeTextfield.getText();
            String source = getSource.getSelectedItem();
            String destination = getDestination.getSelectedItem();
            String date = ((JTextField) chooseDate.getDateEditor().getUiComponent()).getText();

            try{

                Connection connect  = DBconnection.getConnection();
                String Query = "INSERT INTO passenger_details " +
                        "(id, name, nationality, gender, email, flight_code, flight_name, departure_time, arrival_time, source, destination, travel_date) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                int randomId = (int)(Math.random() * 9000000) + 1000000;
                String ticketId = "pk-" + randomId;
                PreparedStatement pst = connect.prepareStatement(Query);
                pst.setString(1, ticketId);  // Set the randomly generated ID
                pst.setString(2, name);
                pst.setString(3, nationality);
                pst.setString(4, gender);
                pst.setString(5, email);
                pst.setString(6, fligtCode);
                pst.setString(7, fligtName);
                pst.setString(8, departureTime);
                pst.setString(9, arivalTime);
                pst.setString(10, source);
                pst.setString(11, destination);
                pst.setString(12, date);
                int isRowAffected = pst.executeUpdate();

                if(isRowAffected >= 1){

                    JOptionPane.showMessageDialog(null, "Ticket Booked successfully!");

                } else {
                    JOptionPane.showMessageDialog(null, "Failed to insert record`");
                }
            } catch (RuntimeException | SQLException ex){
                throw new RuntimeException(ex);
            }


        }
    }
}
