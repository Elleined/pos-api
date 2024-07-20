package com.elleined.pos_api.ui.main;

import com.elleined.pos_api.model.product.Product;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Getter
public class ProductPanel extends javax.swing.JPanel {

    @Value("${image.path}")
    private String imagePath;

    private final Product product;

    public ProductPanel(Product product) throws IOException {
        this.product = product;
        initComponents();

        Path image = Path.of(imagePath, product.getImage());
        lblPicture.setIcon(new ImageIcon(new ImageIcon(Files.readAllBytes(image)).getImage().getScaledInstance(picturePanel.getWidth(), picturePanel.getHeight(), Image.SCALE_SMOOTH)));
        lblName.setText(product.getName());
        lblPrice.setText(String.valueOf(product.getPrice()));
        taDescription.setText(product.getDescription());

        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        picturePanel = new javax.swing.JPanel();
        lblPicture = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblPrice = new javax.swing.JLabel();
        tsScrollPanel = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();

        setMaximumSize(new java.awt.Dimension(160, 270));
        setMinimumSize(new java.awt.Dimension(160, 270));

        picturePanel.setMaximumSize(new java.awt.Dimension(148, 150));
        picturePanel.setMinimumSize(new java.awt.Dimension(148, 150));

        lblPicture.setMaximumSize(new java.awt.Dimension(148, 150));
        lblPicture.setMinimumSize(new java.awt.Dimension(148, 150));
        lblPicture.setPreferredSize(new java.awt.Dimension(148, 150));

        javax.swing.GroupLayout picturePanelLayout = new javax.swing.GroupLayout(picturePanel);
        picturePanel.setLayout(picturePanelLayout);
        picturePanelLayout.setHorizontalGroup(
            picturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPicture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        picturePanelLayout.setVerticalGroup(
            picturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPicture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        lblName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        taDescription.setEditable(false);
        taDescription.setColumns(20);
        taDescription.setRows(5);
        tsScrollPanel.setViewportView(taDescription);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tsScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(picturePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(lblPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(picturePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tsScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPicture;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JPanel picturePanel;
    private javax.swing.JTextArea taDescription;
    private javax.swing.JScrollPane tsScrollPanel;
    // End of variables declaration//GEN-END:variables
}
