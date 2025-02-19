package inventory;
import common.OpenPdf;
import dao.ConnectionProvider;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import dao.InventoryUtils;
import java.io.FileOutputStream;
import java.util.Date;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import java.time.LocalDate;

public class SellProduct extends JFrame implements ActionListener {

    JFrame f;
    JTable t;
    DefaultTableModel modell, model;
    JTextField searchinventory, productid, nameupdate, companyupdate, priceunit, unitstotal, pricetotal;
    JButton cart, purchase;
    JTable cartTable;
    DefaultTableModel cartModel;
    private String numberPattern = "^[0-9]*$";
    private int finalTotalPrice = 0;
    private String billId = "";
    private String username = "";
    JLabel rsprice;

    public SellProduct(String tempUsername) {
        f = new JFrame("Sell Product");
        f.setBounds(0, 0, 700, 500);
        f.setLayout(null);

        JLabel sellInventoryTitle = new JLabel("Sell Product");
        sellInventoryTitle.setBounds(520, 10, 400, 60);
        sellInventoryTitle.setFont(new Font("Times New Roman", Font.BOLD, 40));
        sellInventoryTitle.setForeground(Color.BLUE);
        f.add(sellInventoryTitle);

        JLabel searchtitle = new JLabel("Search");
        searchtitle.setBounds(250, 100, 300, 40);
        searchtitle.setFont(new Font("Times New Roman", Font.BOLD, 25));
        searchtitle.setForeground(Color.BLUE);
        f.add(searchtitle);

        searchinventory = new JTextField();
        searchinventory.setBounds(120, 150, 350, 20);
        searchinventory.setBorder(BorderFactory.createLineBorder(Color.black));
        searchinventory.setFont(new Font("Times New Roman", Font.BOLD, 25));
        f.add(searchinventory);

        searchinventory.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                searchinventoryKeyReleased(e); // Call the method without parentheses
            }
        });

        modell = new DefaultTableModel();
        modell.addColumn("Product");

        t = new JTable(modell);
        t.setFont(new Font("Arial", Font.PLAIN, 15));
        t.setRowHeight(20);

        JScrollPane scrollPane = new JScrollPane(t); // Add the JTable to a JScrollPane
        scrollPane.setBounds(120, 200, 350, 500);
        f.add(scrollPane);

        JLabel inventoryid = new JLabel("Product Id");
        inventoryid.setBounds(600, 100, 170, 40);
        inventoryid.setFont(new Font("Times New Roman", Font.BOLD, 20));
        inventoryid.setForeground(Color.BLUE);
        f.add(inventoryid);

        productid = new JTextField();
        productid.setBounds(600, 150, 250, 20);
        productid.setBorder(BorderFactory.createLineBorder(Color.black));
        productid.setFont(new Font("Times New Roman", Font.BOLD, 18));
        f.add(productid);

        JLabel updatename = new JLabel("Name");
        updatename.setBounds(600, 180, 170, 40);
        updatename.setFont(new Font("Times New Roman", Font.BOLD, 20));
        updatename.setForeground(Color.BLUE);
        f.add(updatename);

        nameupdate = new JTextField();
        nameupdate.setBounds(600, 230, 250, 20);
        nameupdate.setBorder(BorderFactory.createLineBorder(Color.black));
        nameupdate.setFont(new Font("Times New Roman", Font.BOLD, 18));
        f.add(nameupdate);

        JLabel updatecompnay = new JLabel("Company Name");
        updatecompnay.setBounds(600, 260, 170, 40);
        updatecompnay.setFont(new Font("Times New Roman", Font.BOLD, 20));
        updatecompnay.setForeground(Color.BLUE);
        f.add(updatecompnay);

        companyupdate = new JTextField();
        companyupdate.setBounds(600, 310, 250, 20);
        companyupdate.setBorder(BorderFactory.createLineBorder(Color.black));
        companyupdate.setFont(new Font("Times New Roman", Font.BOLD, 18));
        f.add(companyupdate);

        JLabel updateprice = new JLabel("Price per Product");
        updateprice.setBounds(970, 100, 170, 40);
        updateprice.setFont(new Font("Times New Roman", Font.BOLD, 20));
        updateprice.setForeground(Color.BLUE);
        f.add(updateprice);

        priceunit = new JTextField();
        priceunit.setBounds(970, 150, 250, 20);
        priceunit.setBorder(BorderFactory.createLineBorder(Color.black));
        priceunit.setFont(new Font("Times New Roman", Font.BOLD, 18));
        f.add(priceunit);

        JLabel totlaunits = new JLabel("No. of Products");
        totlaunits.setBounds(970, 180, 170, 40);
        totlaunits.setFont(new Font("Times New Roman", Font.BOLD, 20));
        totlaunits.setForeground(Color.BLUE);
        f.add(totlaunits);

        unitstotal = new JTextField();
        unitstotal.setBounds(970, 230, 250, 20);
        unitstotal.setBorder(BorderFactory.createLineBorder(Color.black));
        unitstotal.setFont(new Font("Times New Roman", Font.BOLD, 18));
        f.add(unitstotal);

        JLabel totalprice = new JLabel("Total Price");
        totalprice.setBounds(970, 260, 170, 40);
        totalprice.setFont(new Font("Times New Roman", Font.BOLD, 20));
        totalprice.setForeground(Color.BLUE);
        f.add(totalprice);

        pricetotal = new JTextField();
        pricetotal.setBounds(970, 310, 250, 20);
        pricetotal.setBorder(BorderFactory.createLineBorder(Color.black));
        pricetotal.setFont(new Font("Times New Roman", Font.BOLD, 18));
        f.add(pricetotal);

        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("images/add to cart.png"));
        Image i4 = i3.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        cart = new JButton("Add to cart", new ImageIcon(i4));
        cart.setBounds(1000, 360, 140, 25);
        cart.addActionListener(this);
        f.add(cart);

        cartModel = new DefaultTableModel();
        cartModel.addColumn("Product ID");
        cartModel.addColumn("Name");
        cartModel.addColumn("Company Name");
        cartModel.addColumn("Price per Product");
        cartModel.addColumn("No of Products");
        cartModel.addColumn("Total Price");

        cartTable = new JTable(cartModel);
        cartTable.setFont(new Font("Arial", Font.PLAIN, 15));
        cartTable.setRowHeight(20);

        JScrollPane cartScrollPane = new JScrollPane(cartTable);
        cartScrollPane.setBounds(600, 420, 600, 250);
        f.add(cartScrollPane);

        JLabel rs = new JLabel("RS:");
        rs.setBounds(700, 680, 50, 40);
        rs.setFont(new Font("Times New Roman", Font.BOLD, 30));
        rs.setForeground(Color.BLUE);
        f.add(rs);

        rsprice = new JLabel("00");
        rsprice.setBounds(750, 680, 100, 40);
        rsprice.setFont(new Font("Times New Roman", Font.BOLD, 30));
        rsprice.setForeground(Color.BLUE);
        f.add(rsprice);

        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("images/generate bill & print.png"));
        Image i6 = i5.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        purchase = new JButton("Purchase & Print", new ImageIcon(i6));
        purchase.setBounds(950, 690, 180, 25);
        purchase.addActionListener(this);
        f.add(purchase);

        f.setSize(1368, 800);
        f.setLocation(90, 10);
        f.getContentPane().setBackground(Color.PINK);   ///////////////////////
        f.setVisible(true);

        username = tempUsername;
        setLocationRelativeTo(null);

        productName("");
        productid.setEditable(false);
        nameupdate.setEditable(false);
        companyupdate.setEditable(false);
        priceunit.setEditable(false);
        pricetotal.setEditable(false);

        t.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                productsTableMouseClicked(e); // Call the method with the MouseEvent as a parameter
            }
        });

        unitstotal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                unitstotalKeyReleased(e); // Call the method with the KeyEvent as a parameter
            }
        });

        cartTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cartTableMouseClicked(e);
            }
        });

    }

    private void productName(String nameOrUniqueId) {
        DefaultTableModel model = (DefaultTableModel) t.getModel();
        model.setRowCount(0);
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from product where name like  '" + nameOrUniqueId + "%' or productId like '" + nameOrUniqueId + "%'");
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("productId") + " - " + rs.getString("name")});
            }
        } catch (Exception ee) {
            JOptionPane.showMessageDialog(null, ee);
        }
    }

    public void clearProductFields() {
        productid.setText("");
        nameupdate.setText("");
        companyupdate.setText("");
        priceunit.setText("");
        unitstotal.setText("");
        pricetotal.setText("");
    }

    public String getUniqueId(String prefix) {
        return prefix + System.nanoTime();
    }

    public void searchinventoryKeyReleased(KeyEvent evt) {
        String search = searchinventory.getText();
        productName(search);
    }

    public void productsTableMouseClicked(MouseEvent evt) {
        int index = t.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) t.getModel();
        String nameOrUniqueId = model.getValueAt(index, 0).toString();
        String productId = nameOrUniqueId.split(" - ")[0]; // Extract the uniqueId from the selected row
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from product where productId = '" + productId + "'");
            if (rs.next()) {
                productid.setText(rs.getString("productId"));
                nameupdate.setText(rs.getString("name"));
                companyupdate.setText(rs.getString("companyName"));
                priceunit.setText(rs.getString("price"));
                unitstotal.setText("");
                pricetotal.setText("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void unitstotalKeyReleased(KeyEvent evt) {
        String noOfUnits = unitstotal.getText();
        if (!noOfUnits.equals("")) {
            String price = priceunit.getText();
            if (!noOfUnits.matches(numberPattern)) {
                JOptionPane.showMessageDialog(null, "Number of Products field is invalid");
                pricetotal.setText("");
            }
            int totalPrice = Integer.parseInt(noOfUnits) * Integer.parseInt(price);
            pricetotal.setText(String.valueOf(totalPrice));
        } else {
            pricetotal.setText("");
        }
    }

    private void updateTotalPrice() {
        int total = 0;
        for (int row = 0; row < cartTable.getRowCount(); row++) {
            int price = Integer.parseInt(cartTable.getValueAt(row, 5).toString());
            total += price;
        }
        rsprice.setText(String.valueOf(total));
    }

    private void decreaseQuantity(String productId, int quantityToDecrease) {
        try {
            Connection con = ConnectionProvider.getCon();
            PreparedStatement pst = con.prepareStatement("UPDATE product SET quantity = quantity - ? WHERE productId = ?");
            pst.setInt(1, quantityToDecrease);
            pst.setString(2, productId);
            int rowsUpdated = pst.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Quantity decreased successfully");
            } else {
                System.out.println("Error decreasing quantity");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cart) {
            String noOfUnits = unitstotal.getText();
            String productId = productid.getText(); // Use the medicine ID field
            if (!noOfUnits.equals("") && !productId.equals("")) {
                String name = nameupdate.getText();
                String companyName = companyupdate.getText();
                String pricePerUnit = priceunit.getText();
                String totalPrice = pricetotal.getText();
                int checkStock = 0;
                int checkProductAlreadyExistInCart = 0;

                try {
                    Connection con = ConnectionProvider.getCon();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("select * from product where productId='" + productId + "'");
                    if (rs.next()) {
                        int quantity = rs.getInt("quantity");
                        if (quantity >= Integer.parseInt(noOfUnits)) {
                            checkStock = 1;
                        } else {
                            JOptionPane.showMessageDialog(null, "Product is out of Stock. Only " + rs.getInt("quantity") + " Left");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }

                if (checkStock == 1) {
                    DefaultTableModel dtm = (DefaultTableModel) cartTable.getModel();
                    if (cartTable.getRowCount() != 0) {
                        for (int i = 0; i < cartTable.getRowCount(); i++) {
                            if (Integer.parseInt(dtm.getValueAt(i, 0).toString()) == Integer.parseInt(productId)) {
                                checkProductAlreadyExistInCart = 1;
                                JOptionPane.showMessageDialog(null, "Product already exists in the cart");
                            }
                        }
                    }
                    if (checkProductAlreadyExistInCart == 0) {
                        dtm.addRow(new Object[]{productId, name, companyName, pricePerUnit, noOfUnits, totalPrice});
                        cartTable.setModel(dtm);
                        finalTotalPrice += Integer.parseInt(totalPrice);
                        rsprice.setText(String.valueOf(finalTotalPrice));
                        // Decrease the quantity in the database
                        decreaseQuantity(productId, Integer.parseInt(noOfUnits));
                        JOptionPane.showMessageDialog(null, "Added Successfully");
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "No of Products and Product Id is required");
            }
        } else if (e.getSource() == purchase) {
            if (finalTotalPrice != 0) {
                billId = getUniqueId("bill-");
                try {
                    Connection con = ConnectionProvider.getCon();
                    PreparedStatement pst = con.prepareStatement("INSERT INTO bill (billId, billDate, totalPaid, generatedBy) VALUES (?, ?, ?, ?)");
                    pst.setString(1, billId);
                    pst.setDate(2, new java.sql.Date(new Date().getTime())); // Use java.sql.Date here
                    pst.setInt(3, finalTotalPrice);
                    pst.setString(4, username); // Assuming 'username' is the user who generated the bill

                    int rowsInserted = pst.executeUpdate();

                    if (rowsInserted > 0) {
                        System.out.println("Bill details inserted successfully");
                    } else {
                        System.out.println("Error inserting bill details");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }

                DefaultTableModel dtm = (DefaultTableModel) cartTable.getModel();
                if (cartTable.getRowCount() != 0) {
                    try {
                        com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
                        PdfWriter.getInstance(doc, new FileOutputStream(InventoryUtils.billPath + billId + ".pdf"));
                        doc.open();

                        // Add bill details (e.g., bill ID, date, total paid) to the PDF
                        doc.add(new Paragraph("Bill ID: " + billId));
                        doc.add(new Paragraph("Date: " + new Date()));
                        doc.add(new Paragraph("Total Paid: " + finalTotalPrice));

                        PdfPTable table = new PdfPTable(6); // 6 columns for your table
                        table.addCell("Product Id");
                        table.addCell("Name");
                        table.addCell("Company Name");
                        table.addCell("Price per Product");
                        table.addCell("No of Products");
                        table.addCell("Total Price");

                        // Add items from the cart to the PDF
                        for (int i = 0; i < cartTable.getRowCount(); i++) {
                            String productId = cartTable.getValueAt(i, 0).toString();
                            String name = cartTable.getValueAt(i, 1).toString();
                            String companyName = cartTable.getValueAt(i, 2).toString();
                            String pricePerUnit = cartTable.getValueAt(i, 3).toString();
                            String noOfUnits = cartTable.getValueAt(i, 4).toString();
                            String totalPrice = cartTable.getValueAt(i, 5).toString();

                            table.addCell(productId);
                            table.addCell(name);
                            table.addCell(companyName);
                            table.addCell(pricePerUnit);
                            table.addCell(noOfUnits);
                            table.addCell(totalPrice);
                        }

                        doc.add(table);
                        doc.close();

                        // Open the generated PDF
                        OpenPdf.openById(billId);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please add Product to cart");
                }
            }
        }
    }

    public void cartTableMouseClicked(java.awt.event.MouseEvent evt) {
        int index = cartTable.getSelectedRow();
        int a = JOptionPane.showConfirmDialog(null, "Do you want to remove this Product", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
            String total = model.getValueAt(index, 5).toString();
            finalTotalPrice -= Integer.parseInt(total);
            rsprice.setText(String.valueOf(finalTotalPrice));
            cartModel.removeRow(index);

        }
    }

    public static void main(String args[]) {
        new SellProduct("sellproductusername");
    }
}
