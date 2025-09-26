package Project;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import com.toedter.calendar.JDateChooser;
import java.util.*;
public class Cancel extends JFrame implements ActionListener {
    JTextField tfpnr;
    JLabel tfname,cancellationno, lblfcode,lbldateoftravel;
    JButton flight,fetchButton;
    public Cancel(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        Random random=new Random();


        JLabel heading=new JLabel("CANCELLATION");
        heading.setBounds(180,20,250,35);
        heading.setFont(new Font("Tahoma",Font.PLAIN,32));
        add(heading);

        ImageIcon image=new ImageIcon("src/Project/icons/cancel.jpg");
        Image image2=image.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon image3=new ImageIcon(image2);
        JLabel image4=new JLabel(image3);
        image4.setBounds(470,130,250,250);
        add(image4);

        JLabel lblaadhar=new JLabel("PNR Number");
        lblaadhar.setBounds(60,80,150,25);
        lblaadhar.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblaadhar);

        tfpnr=new JTextField();
        tfpnr.setBounds(220,80,150,25);
        add(tfpnr);



        fetchButton=new JButton("Show Details");
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

        JLabel lblnationality=new JLabel("Cancellation No");
        lblnationality.setBounds(60,180,150,25);
        lblnationality.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblnationality);

        cancellationno=new JLabel(""+random.nextInt(1000000));
        cancellationno.setBounds(220,180,150,25);
        add(cancellationno);

        JLabel lbladdress=new JLabel("Flight Code");
        lbladdress.setBounds(60,230,150,25);
        lbladdress.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lbladdress);

        lblfcode=new JLabel();
        lblfcode.setBounds(220,230,150,25);
        add( lblfcode);

        JLabel lblgender=new JLabel("Date");
        lblgender.setBounds(60,280,150,25);
        lblgender.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblgender);

        lbldateoftravel=new JLabel("Gender");
        lbldateoftravel.setBounds(220,280,150,25);
        lbldateoftravel.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lbldateoftravel);

        JLabel lblsource=new JLabel("Source");
        lblsource.setBounds(60,330,150,25);
        lblsource.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblsource);

        flight=new JButton("Cancel");
        flight.setBackground(Color.BLACK);
        flight.setForeground(Color.WHITE);
        flight.setBounds(220,330,120,25);
        flight.addActionListener(this);
        add(flight);

        setSize(800,480);
        setLocation(350,150);
        setVisible(true);
    }

    public static void main(String[] args){
        new Cancel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==fetchButton){
            String pnr=tfpnr.getText();

            try{
                Conn conn=new Conn();
                String query="SELECT * FROM reservation WHERE PNR = '"+pnr+"' ";
                ResultSet rs=  conn.s.executeQuery(query);
                if(rs.next()){
                    tfname.setText(rs.getString("name"));
                    lblfcode.setText(rs.getString("flightcode"));
                    lbldateoftravel.setText(rs.getString("ddate"));
                }else{
                    JOptionPane.showMessageDialog(null,"Please enter correct PNR Number");
                }
            }
            catch(Exception d){
                d.printStackTrace();
            }
        }

        if(e.getSource()== flight){
            String name=tfname.getText();
            String pnr=tfpnr.getText();
            String cancelno=cancellationno.getText();
            String fcode=lblfcode.getText();
            String date=lbldateoftravel.getText();

            try{
                Conn conn=new Conn();
                String query="insert into cancel values ('"+pnr+"','"+name+"','"+cancelno+"','"+fcode+"','"+date+"');";
                conn.s.executeUpdate(query);
                conn.s.executeUpdate("delete from reservation where PNR= '"+pnr+"'");
                JOptionPane.showMessageDialog(null,"Ticket Cancelled");
                setVisible(false);
            }
            catch(Exception d){
                d.printStackTrace();
            }
        }
    }
}
