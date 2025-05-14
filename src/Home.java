import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {

    public Home(){

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        ImageIcon backgroungImage = new ImageIcon(ClassLoader.getSystemResource("icons/front.jpg"));
        JLabel imageLabel = new JLabel(backgroungImage);
        imageLabel.setBounds(100,100,1600,800);
        add(imageLabel);

        JLabel heading = new JLabel("RIPHAH AIR WELCOMES YOU");
        heading.setForeground(Color.BLUE);
        heading.setBounds(400,40,1000,40);
        heading.setFont(new Font("Tahoma",Font.PLAIN, 40));
        imageLabel.add(heading);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu details = new JMenu("Details");
        menuBar.add(details);

        JMenuItem flightDetail = new JMenuItem("Flight Details");
        flightDetail.addActionListener(this);
        details.add(flightDetail);

        JMenuItem customerDetail = new JMenuItem("Add Customer Details");
        customerDetail.addActionListener(this);
        details.add(customerDetail);

        JMenuItem reservationDetail = new JMenuItem("Reservations");
        details.add(reservationDetail);

        JMenuItem bookfFight = new JMenuItem("Book Flight");
        bookfFight.addActionListener(this);
        details.add(bookfFight);

        JMenuItem JourneyDetail = new JMenuItem("Journey Details");
        JourneyDetail.addActionListener(this);
        details.add(JourneyDetail);

        JMenuItem ticketCancellation = new JMenuItem("Cancel Ticket");
        ticketCancellation.addActionListener(this);
        details.add(ticketCancellation);

        JMenu ticket = new JMenu("Ticekt");
        menuBar.add(ticket);

        JMenuItem boardingPass = new JMenuItem("Boarding Pass");
        ticket.add(boardingPass);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String text = e.getActionCommand();

        if (text.equals("Flight Details")) {
            new FlightDetails();

        } else if (text.equals("Add Customer Details")) {
            new AddCustomer();
        } else if (text.equals("Book Flight")) {
            new BookFlight();
        } else if (text.equals("Journey Details")) {
            new JourneyDetails();
        } else if (text.equals("Cancel Ticket")) {
            new Cancel();
        }


    }
}
