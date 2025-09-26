package Project;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class FlightInfo extends JFrame {
    JTable table;

    public FlightInfo() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        DefaultTableModel model = new DefaultTableModel();
        table = new JTable(model);

        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("SELECT * FROM flight");

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();


            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(rsmd.getColumnName(i));
            }


            while (rs.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = rs.getObject(i);
                }
                model.addRow(rowData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 80, 700, 350);

        add(scrollPane);

        setSize(800, 500);
        setLocation(400, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FlightInfo();
    }
}
