package inventory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Dashboard extends JFrame implements ActionListener {

    JFrame f;
    private String username = "";
    JButton inventoryproduct, inventoryviewproduct, inventoryupdateproduct, inventorysellproduct, inventoryviewbill, inventoryexit;

    public Dashboard(String tempUsername) {
        f = new JFrame("Home Page");
        f.setBackground(Color.BLACK);
        f.setBounds(0, 0, 700, 500);
        f.setLayout(null);

        JLabel inventorydash = new JLabel("DASHBOARD");
        inventorydash.setBounds(520, 80, 400, 60);
        inventorydash.setFont(new Font("Times New Roman", Font.BOLD, 40));
        inventorydash.setForeground(Color.BLUE);
        f.add(inventorydash);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/addProduct.png"));
        Image i2 = i1.getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT);
        inventoryproduct = new JButton("Add Product", new ImageIcon(i2));
        inventoryproduct.setBounds(300, 200, 250, 60);
        inventoryproduct.setFont(new Font("Times New Roman", Font.BOLD, 20));
        inventoryproduct.setBorder(BorderFactory.createLineBorder(Color.black));
        inventoryproduct.setFocusPainted(false);
        inventoryproduct.addActionListener(this);
        f.add(inventoryproduct);
        
        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("images/updateUser.png"));
        Image i6 = i5.getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT);
        inventoryupdateproduct = new JButton("Update Product", new ImageIcon(i6));
        inventoryupdateproduct.setBounds(300, 300, 250, 60);
        inventoryupdateproduct.setFont(new Font("Times New Roman", Font.BOLD, 20));
        inventoryupdateproduct.setBorder(BorderFactory.createLineBorder(Color.black));
        inventoryupdateproduct.setFocusPainted(false);
        inventoryupdateproduct.addActionListener(this);
        f.add(inventoryupdateproduct);

        ImageIcon i9 = new ImageIcon(ClassLoader.getSystemResource("images/viewUser.png"));
        Image i10 = i9.getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT);
        inventoryviewproduct = new JButton("View Product", new ImageIcon(i10));
        inventoryviewproduct.setBounds(300, 400, 250, 60);
        inventoryviewproduct.setFont(new Font("Times New Roman", Font.BOLD, 20));
        inventoryviewproduct.setBorder(BorderFactory.createLineBorder(Color.black));
        inventoryviewproduct.setFocusPainted(false);
        inventoryviewproduct.addActionListener(this);
        //login.addActionListener(this);
        f.add(inventoryviewproduct);

        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("images/sellProduct.png"));
        Image i12 = i11.getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT);
        inventorysellproduct = new JButton("Sell Product", new ImageIcon(i12));
        inventorysellproduct.setBounds(700, 200, 250, 60);
        inventorysellproduct.setFont(new Font("Times New Roman", Font.BOLD, 20));
        inventorysellproduct.setBorder(BorderFactory.createLineBorder(Color.black));
        inventorysellproduct.setFocusPainted(false);
        inventorysellproduct.addActionListener(this);
        f.add(inventorysellproduct);

        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("images/viewBill.png"));
        Image i14 = i13.getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT);
        inventoryviewbill = new JButton("View Bill", new ImageIcon(i14));
        inventoryviewbill.setBounds(700, 300, 250, 60);
        inventoryviewbill.setFont(new Font("Times New Roman", Font.BOLD, 20));
        inventoryviewbill.setBorder(BorderFactory.createLineBorder(Color.black));
        inventoryviewbill.setFocusPainted(false);
        inventoryviewbill.addActionListener(this);
        //login.addActionListener(this);
        f.add(inventoryviewbill);

        

        ImageIcon i17 = new ImageIcon(ClassLoader.getSystemResource("images/exit.png"));
        Image i18 = i17.getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT);
        inventoryexit = new JButton("Exit", new ImageIcon(i18));
        inventoryexit.setBounds(520, 500, 250, 60);
        inventoryexit.setFont(new Font("Times New Roman", Font.BOLD, 20));
        inventoryexit.setBorder(BorderFactory.createLineBorder(Color.black));
        inventoryexit.setFocusPainted(false);
        inventoryexit.addActionListener(this);
        f.add(inventoryexit);

        f.setVisible(true);//Setting the visibility of the Frame
        f.setSize(1300, 768);
        f.setLocation(90, 40);
        f.getContentPane().setBackground(Color.PINK);

        username = tempUsername;
    }

    private void productName(String nameOrUniqueId) {

    }

    public void actionPerformed(ActionEvent ae) {

        String msg = ae.getActionCommand();
        if (msg.equals("Logout")) {
            f.setVisible(false);
            new Login();
        } else if (msg.equals("Exit")) {
            f.setVisible(false);
        } else if (msg.equals("Add Product")) {
            new AddProduct().setVisible(true);
        } else if (msg.equals("View Product")) {
            new ViewProduct(username).setVisible(true);
        } else if (msg.equals("Update Product")) {

            new UpdateProduct().setVisible(true);
        } else if (msg.equals("Sell Product")) {
            new SellProduct(username).setVisible(true);
        } else if (msg.equals("View Bill")) {
            new ViewBill().setVisible(true);
        }

    }

    public static void main(String args[]) {
        new Dashboard("inventoryusername");
    }
}

