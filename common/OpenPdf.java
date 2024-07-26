/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import dao.InventoryUtils;

/**
 *
 * @author GAYATRI
 */
public class OpenPdf {
    public static void openById(String id) {
        try {
            File file = new File(InventoryUtils.billPath + id + ".pdf");
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                JOptionPane.showMessageDialog(null, "File does not exist.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}


