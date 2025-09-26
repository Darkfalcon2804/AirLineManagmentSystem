package Project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {
    public Home(){
        setLayout(null);


        ImageIcon i1 = new ImageIcon("src/Project/icons/front.jpg");
        JLabel image=new JLabel(i1);
        image.setBounds(0,0,1600,900);
        add(image);
        JLabel heading=new JLabel("Dark Falcon Welcomes You");
        heading.setBounds(500,20,1000,40);
        heading.setForeground(Color.BLUE);
        heading.setFont(new Font("Tahoma",Font.PLAIN,36));
        image.add(heading);

        JMenuBar menubar=new  JMenuBar();
        setJMenuBar(menubar);
        JMenu details=new JMenu("Details");
        menubar.add(details);

        JMenuItem flightDetails=new JMenuItem("Flight Details");
        flightDetails.addActionListener(this);
        details.add(flightDetails);

        JMenuItem customerDetails=new JMenuItem("Add Customer Details");
        customerDetails.addActionListener(this);
        details.add( customerDetails);

        JMenuItem bookFlight=new JMenuItem("Book Flight");
        bookFlight.addActionListener(this);
        details.add(bookFlight);

        JMenuItem journeydetails=new JMenuItem("Journey Details");
        journeydetails.addActionListener(this);
        details.add(journeydetails);

        JMenuItem ticketCancellation=new JMenuItem("Cancel Ticket");
        ticketCancellation.addActionListener(this);
        details.add(ticketCancellation);

        JMenu ticket=new JMenu("Ticket");
        menubar.add(ticket);

        JMenuItem boardingPass=new JMenuItem("Boarding Pass");
        boardingPass.addActionListener(this);
        ticket.add(boardingPass);
//        setSize(100,500);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
    public static void main(String[] args){
        new Home();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text=e.getActionCommand();
        if(text.equals("Add Customer Details")){
            new AddCustomer();
        }else if(text.equals("Flight Details")){
            new FlightInfo();
        } else if(text.equals("Book Flight")){
            new BookFlight();
        }else if(text.equals("Journey Details")){
            new JourneyDetails();
        }
        else if(text.equals("Cancel Ticket")){
            new Cancel();
        }
        else if(text.equals("Boarding Pass")){
            new BoardingPass();
        }
    }

}