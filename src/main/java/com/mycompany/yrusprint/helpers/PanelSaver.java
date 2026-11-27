/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yrusprint.helpers;

/**
 *
 * @author tempus
 */
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PanelSaver {
    public static void savePanel(JPanel panel) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Bib As Image");
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images (*.png)", "png");
        fileChooser.setFileFilter(filter);
        fileChooser.setSelectedFile(new File("exported_bib.png"));

        int userSelection = fileChooser.showSaveDialog(panel);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            if (!fileToSave.getName().toLowerCase().endsWith(".png")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".png");
            }

            if (fileToSave.exists()) {
                int response = JOptionPane.showConfirmDialog(
                    panel,
                    "File already exists. Do you want to overwrite it?",
                    "Confirm Overwrite",
                    JOptionPane.YES_NO_OPTION
                );
                if (response != JOptionPane.YES_OPTION) {
                    return;
                }
            }

            try {
                BufferedImage image = new BufferedImage(
                    panel.getWidth(),
                    panel.getHeight(),
                    BufferedImage.TYPE_INT_ARGB
                );
                Graphics2D g2d = image.createGraphics();
                panel.printAll(g2d);
                g2d.dispose();

                ImageIO.write(image, "png", fileToSave);
                JOptionPane.showMessageDialog(panel, "Saved successfully to:\n" + fileToSave.getAbsolutePath());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(panel, "Error saving image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}

