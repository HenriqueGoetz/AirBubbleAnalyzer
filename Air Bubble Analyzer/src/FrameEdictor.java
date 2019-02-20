/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.awt.Color;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

/**
 *
 * @author Henrique Goetz
 */
public class FrameEdictor extends javax.swing.JFrame {

    /**
     * Creates new form FrameEdictor
     */
    private int cor = 0; // 0 = WHITE // 1 = BLACK
    private boolean countpixels = false;
    private BufferedImage lastImage = null;

    public FrameEdictor() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void LoadImage(BufferedImage imagem) {

        lblImagem.setIcon(new ImageIcon(imagem));
        lblImagem.setHorizontalAlignment(SwingConstants.CENTER);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCountPixels = new javax.swing.JButton();
        jpImage = new javax.swing.JPanel();
        lblImagem = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnWhite = new javax.swing.JButton();
        btnBlack = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblNumberOfPixels = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblAltura = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnCountPixels.setText(" Count pixels");
        btnCountPixels.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCountPixelsActionPerformed(evt);
            }
        });

        jpImage.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpImage.setPreferredSize(new java.awt.Dimension(1400, 700));

        lblImagem.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                lblImagemMouseDragged(evt);
            }
        });
        lblImagem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblImagemMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblImagemMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jpImageLayout = new javax.swing.GroupLayout(jpImage);
        jpImage.setLayout(jpImageLayout);
        jpImageLayout.setHorizontalGroup(
            jpImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImagem, javax.swing.GroupLayout.DEFAULT_SIZE, 1396, Short.MAX_VALUE)
        );
        jpImageLayout.setVerticalGroup(
            jpImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImagem, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Color:");

        btnWhite.setBackground(new java.awt.Color(255, 255, 255));
        btnWhite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWhiteActionPerformed(evt);
            }
        });

        btnBlack.setBackground(new java.awt.Color(0, 0, 0));
        btnBlack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBlackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnWhite, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBlack, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(28, 28, 28))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBlack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnWhite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setText("Número de pixels na região:");

        lblNumberOfPixels.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblNumberOfPixels.setText("------");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setText("Altura do bolsão:");

        lblAltura.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblAltura.setText("------");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(151, 151, 151)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAltura)
                    .addComponent(lblNumberOfPixels))
                .addContainerGap(259, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblNumberOfPixels))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(lblAltura))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(btnCountPixels, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCountPixels, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblImagemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagemMousePressed
    }//GEN-LAST:event_lblImagemMousePressed

    private void lblImagemMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagemMouseDragged

        if (!countpixels) {
            ImageIcon img = (ImageIcon) lblImagem.getIcon();
            Image image = img.getImage();
            BufferedImage buffered = (BufferedImage) image;

            Point position = MouseInfo.getPointerInfo().getLocation();
            Color color;
            if (cor == 0) {
                color = new Color(255, 255, 255);
            } else {
                color = new Color(0, 0, 0);
            }

            buffered.setRGB((int) (position.getX() + lblImagem.getX() - lblImagem.getLocationOnScreen().x - (jpImage.getWidth() - buffered.getWidth()) / 2), (int) (position.getY() + lblImagem.getY() - lblImagem.getLocationOnScreen().y - (jpImage.getHeight() - buffered.getHeight()) / 2), color.getRGB());
            lblImagem.setIcon(new ImageIcon(buffered));
            lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
        }
    }//GEN-LAST:event_lblImagemMouseDragged

    private void btnWhiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWhiteActionPerformed
        cor = 0;
    }//GEN-LAST:event_btnWhiteActionPerformed

    private void btnBlackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBlackActionPerformed
        cor = 1;
    }//GEN-LAST:event_btnBlackActionPerformed

    private void btnCountPixelsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCountPixelsActionPerformed

        if (countpixels == false) {
            countpixels = true;
            btnCountPixels.setText("Stop counting");
        } else {
            countpixels = false;
            btnCountPixels.setText(" Count pixels");
        }
    }//GEN-LAST:event_btnCountPixelsActionPerformed

    private void lblImagemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagemMouseClicked

        ImageIcon img = (ImageIcon) lblImagem.getIcon();
        Image image = img.getImage();
        BufferedImage buffered = (BufferedImage) image;

        if (!countpixels) {
            Point position = MouseInfo.getPointerInfo().getLocation();
            Color color;
            if (cor == 0) {
                color = new Color(255, 255, 255);
            } else {
                color = new Color(0, 0, 0);
            }

            buffered.setRGB((int) (position.getX() + lblImagem.getX() - lblImagem.getLocationOnScreen().x - (jpImage.getWidth() - buffered.getWidth()) / 2), (int) (position.getY() + lblImagem.getY() - lblImagem.getLocationOnScreen().y - (jpImage.getHeight() - buffered.getHeight()) / 2), color.getRGB());
            lblImagem.setIcon(new ImageIcon(buffered));
            lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
        } else {
            Point position = MouseInfo.getPointerInfo().getLocation();
            Point initialPoint = new Point((int) (position.getX() + lblImagem.getX() - lblImagem.getLocationOnScreen().x - (jpImage.getWidth() - buffered.getWidth()) / 2), (int) (position.getY() + lblImagem.getY() - lblImagem.getLocationOnScreen().y - (jpImage.getHeight() - buffered.getHeight()) / 2));

            lblNumberOfPixels.setText(String.valueOf(countPixelsFromImage(buffered, initialPoint)));
            lblImagem.setIcon(new ImageIcon(buffered));
            lblImagem.setHorizontalAlignment(SwingConstants.CENTER);

        }
    }//GEN-LAST:event_lblImagemMouseClicked

    private void lblImagemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagemMouseEntered

    }//GEN-LAST:event_lblImagemMouseEntered

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameEdictor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameEdictor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameEdictor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameEdictor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameEdictor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBlack;
    private javax.swing.JButton btnCountPixels;
    private javax.swing.JButton btnWhite;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jpImage;
    private javax.swing.JLabel lblAltura;
    private javax.swing.JLabel lblImagem;
    private javax.swing.JLabel lblNumberOfPixels;
    // End of variables declaration//GEN-END:variables

    private int countPixelsFromImage(BufferedImage image, Point initialPoint) {
        int nPixels = 0;
        Color black = new Color(0, 0, 0);
        Color gray = new Color(128, 128, 128);

        ArrayList<Point> points = new ArrayList();
        ArrayList<Point> visitados = new ArrayList();

        points.add(initialPoint);

        while (!points.isEmpty()) {
            Point point = points.get(0);

            if (!(point.x == image.getWidth() || point.x == 0 || point.y == image.getHeight() || point.y == 0) && !(image.getRGB(point.x, point.y) != black.getRGB() || visitados.contains(point))) {
                visitados.add(point);
                image.setRGB(point.x, point.y, gray.getRGB());
                points.add(new Point(point.x - 1, point.y));
                points.add(new Point(point.x + 1, point.y));
                points.add(new Point(point.x, point.y - 1));
                points.add(new Point(point.x, point.y + 1));
                nPixels++;
            }
            points.remove(point);
        }
        
        int sup=0, inf = 0;
        
        while(image.getRGB(initialPoint.x, initialPoint.y-sup) == gray.getRGB()){
            sup++;
        }
        while(image.getRGB(initialPoint.x, initialPoint.y+inf) == gray.getRGB()){
            inf++;
        }
        
        lblAltura.setText(String.valueOf(inf+sup-1));
        
        return nPixels;
    }
}
