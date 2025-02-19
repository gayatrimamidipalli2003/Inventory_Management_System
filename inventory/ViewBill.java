package inventory;
import common.OpenPdf;
import dao.ConnectionProvider;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ViewBill extends JFrame {

    JFrame f;
    JTable jTable1;
    DefaultTableModel cartModel;

    public ViewBill() {
        f = new JFrame("View Bill");
        f.setBackground(Color.WHITE);
        f.setBounds(0, 0, 700, 500);
        f.setLayout(null);

        JLabel viewb = new JLabel("VIEW BILL");
        viewb.setBounds(300, 30, 300, 40);
        viewb.setFont(new Font("Times New Roman", Font.BOLD, 36));
        viewb.setForeground(Color.BLUE);
        f.add(viewb);

        cartModel = new DefaultTableModel();
        cartModel.addColumn("Bill ID");
        cartModel.addColumn("Date");
        cartModel.addColumn("Total Paid");
        cartModel.addColumn("Generated By");

        jTable1 = new JTable(cartModel);
        jTable1.setFont(new Font("Arial", Font.PLAIN, 15));
        jTable1.setRowHeight(20);

        JScrollPane cartScrollPane = new JScrollPane(jTable1);
        cartScrollPane.setBounds(40, 100, 700, 350);
        f.add(cartScrollPane);

        f.setVisible(true);
        f.setSize(800, 700);
        f.setLocation(300, 150);

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from bill");
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("billId"), rs.getString("billDate"), rs.getString("totalPaid"), rs.getString("generatedBy")});
            }
        } catch (Exception ee) {
            JOptionPane.showMessageDialog(null, ee);
        }
    }

    public void jTableMouseClicked(java.awt.event.MouseEvent evt) {
        int index = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();
        String id = model.getValueAt(index, 0).toString();
        OpenPdf.openById(id);
    }

    public static void main(String args[]) {
        new ViewBill().setVisible(true);
    }
}
