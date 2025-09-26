package Project;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.toedter.calendar.JDateChooser;
import java.util.*;
public class BookFlight extends JFrame implements ActionListener {
    JTextField tfaadhar;
    JLabel tfname,tfnationality,tfaddress,labelgender,labelfname,labelfcode;
    JButton BookFlight,flight,fetchButton;
    Choice source,destination;
    JDateChooser dcdate;
    public BookFlight(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading=new JLabel("Book Flight");
        heading.setBounds(420,20,500,35);
        heading.setFont(new Font("Tahoma",Font.PLAIN,32));
        heading.setForeground(Color.BLUE);
        add(heading);

        JLabel lblaadhar=new JLabel("Aadhar");
        lblaadhar.setBounds(60,80,150,25);
        lblaadhar.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblaadhar);

        tfaadhar=new JTextField();
        tfaadhar.setBounds(220,80,150,25);
        add(tfaadhar);



        fetchButton=new JButton("Fetch User");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380,80,120,25);
        fetchButton.addActionListener(this);
        add(fetchButton);


        JLabel lblname=new JLabel("Name");
        lblname.setBounds(60,130,150,25);
        lblname.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblname);

        tfname=new JLabel();
        tfname.setBounds(220,130,150,25);
        add(tfname);



        JLabel lblnationality=new JLabel("Nationality");
        lblnationality.setBounds(60,180,150,25);
        lblnationality.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblnationality);

        tfnationality=new JLabel();
        tfnationality.setBounds(220,180,150,25);
        add(tfnationality);

        JLabel lbladdress=new JLabel("Address");
        lbladdress.setBounds(60,230,150,25);
        lbladdress.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lbladdress);

        tfaddress=new JLabel();
        tfaddress.setBounds(220,230,150,25);
        add(tfaddress);

        JLabel lblgender=new JLabel("Gender");
        lblgender.setBounds(60,280,150,25);
        lblgender.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblgender);

        labelgender=new JLabel("Gender");
        labelgender.setBounds(220,280,150,25);
        labelgender.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(labelgender);

        JLabel lblsource=new JLabel("Source");
        lblsource.setBounds(60,330,150,25);
        lblsource.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblsource);


        source=new Choice();
        source.setBounds(220,330,150,25);
        add(source);

        JLabel lbldest=new JLabel("Destination");
        lbldest.setBounds(60,380,150,25);
        lbldest.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lbldest);

        destination=new Choice();
        destination.setBounds(220,380,150,25);
        add( destination);


        try{
            Conn c=new Conn();
            String query="select * from flight";
            ResultSet rs= c.s.executeQuery(query);
            while(rs.next()){
                source.add(rs.getString("source"));
                destination.add(rs.getString("destination"));
            }
        }catch(Exception d){
            d.printStackTrace();
        }


        flight=new JButton("Fetch Flight");
        flight.setBackground(Color.BLACK);
        flight.setForeground(Color.WHITE);
        flight.setBounds(380,380,120,25);
        flight.addActionListener(this);
        add(flight);


        JLabel lblfname=new JLabel("Flight Name");
        lblfname.setBounds(60,430,150,25);
        lblfname.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblfname);

        labelfname=new JLabel();
        labelfname.setBounds(220,430,150,25);
        add(labelfname);

        JLabel lblfcode=new JLabel("Flight Code");
        lblfcode.setBounds(60,480,150,25);
        lblfcode.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblfcode);

        labelfcode=new JLabel();
        labelfcode.setBounds(220,480,150,25);
        add(labelfcode);

        JLabel lbldate=new JLabel("Date of Travel");
        lbldate.setBounds(60,530,150,25);
        lbldate.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lbldate);

        dcdate=new JDateChooser();
        dcdate.setBounds(220,530,150,25);
        add(dcdate);

        ImageIcon image=new ImageIcon("src/Project/icons/details.jpg");
        Image image2=image.getImage().getScaledInstance(450,320,Image.SCALE_DEFAULT);
        ImageIcon image3=new ImageIcon(image2);
        JLabel lblimage=new JLabel(image3);
        lblimage.setBounds(550,80,500,410);
        add(lblimage);

        BookFlight=new JButton("Book Flight");
        BookFlight.setBackground(Color.BLACK);
        BookFlight.setForeground(Color.WHITE);
        BookFlight.setBounds(220,580,150,25);
        BookFlight.addActionListener(this);
        add(BookFlight);




        setSize(1100,700);
        setLocation(200,50);
        setVisible(true);
    }

    public static void main(String[] args){
        new BookFlight();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== fetchButton){
            String aadhar=tfaadhar.getText();
            System.out.println("Searching for Aadhar: '" + aadhar + "'");
            try{
                Conn conn=new Conn();
                String query="SELECT * FROM PASSENGER WHERE AADHAR = '"+aadhar+"' ";
                ResultSet rs=  conn.s.executeQuery(query);
                if(rs.next()){
                    tfname.setText(rs.getString("name"));
                    tfnationality.setText(rs.getString("nationality"));
                    tfaddress.setText(rs.getString("address"));
                    labelgender.setText(rs.getString("gender"));
                }else{
                    JOptionPane.showMessageDialog(null,"Please enter correct Aadhar");
                }
            }
            catch(Exception d){
                d.printStackTrace();
            }
        }

        if(e.getSource()== flight){
            String src=source.getSelectedItem();
            String dest=destination.getSelectedItem();

            try{
                Conn conn=new Conn();
                String query="SELECT * FROM flight WHERE source = '"+src+"' and destination = '"+dest+"' ";
                ResultSet rs=  conn.s.executeQuery(query);
                if(rs.next()){
                    labelfname.setText(rs.getString("f_name"));
                    labelfcode.setText(rs.getString("f_code"));

                }else{
                    JOptionPane.showMessageDialog(null,"No Flights Found");
                }
            }
            catch(Exception d){
                d.printStackTrace();
            }
        }
        else if(e.getSource()==BookFlight){
            Random random=new Random();
            String aadhar=tfaadhar.getText();
            String name= tfname.getText();
            String nationality=tfnationality.getText();
            String flightname= labelfname.getText();
            String flightcode=labelfcode.getText();
            String src=source.getSelectedItem();
            String desc=destination.getSelectedItem();
            String ddate=((JTextField) dcdate.getDateEditor().getUiComponent()).getText();

            try{
                Conn conn=new Conn();
                String query="insert into reservation values('PNR-"+random.nextInt(1000000)+"','TIC-"+random.nextInt(10000)+"','"+aadhar+"','"+name+"','"+nationality+"','"+flightname+"','"+flightcode+"','"+src+"','"+desc+"','"+ddate+"');";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Ticket Booked Successfull");
                setVisible(false);
            }
            catch(Exception d){
                d.printStackTrace();
            }
        }

    }
}
