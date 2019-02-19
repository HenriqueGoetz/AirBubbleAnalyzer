package src;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author HENRIQUE
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    private static boolean paused = false;
    private static int numberOfFrames;

    private static BufferedImage quantization(BufferedImage image) {

        Color black = new Color(0, 0, 0);
        Color white = new Color(255, 255, 255);

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color rgb = new Color(image.getRGB(i, j));
                if (image.getRGB(i, j) != black.getRGB() && image.getRGB(i, j) != white.getRGB()) {
                    if (rgb.getRed() < 128) {
                        image.setRGB(i, j, black.getRGB());
                    } else {
                        image.setRGB(i, j, white.getRGB());
                    }
                }
            }
        }

        return image;
    }

    ArrayList<Mat> frames = new ArrayList();

    public MainFrame() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
    }

    private static BufferedImage resize(BufferedImage img) {
        int w = 750;
        int h = 550;
        if (img.getHeight() > h || img.getWidth() > w) {
            int newW, newH;

            if (img.getHeight() / h > img.getWidth() / w) {
                newH = h;
                newW = img.getWidth() / (img.getHeight() / h);
            } else {
                newW = w;
                newH = img.getHeight() / (img.getWidth() / w);
            }

            Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
            BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
            Graphics2D g2d = dimg.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();
            return dimg;
        }
        return img;
    }

    private static Mat toCanny(Mat mat) {
        Mat retorno = new Mat();
        Imgproc.cvtColor(mat, retorno, Imgproc.COLOR_RGB2GRAY);
        Imgproc.Canny(retorno, retorno, 150, 250, 3, false);
        retorno.convertTo(retorno, CvType.CV_8U);
        return retorno;
    }

    private static Mat toRGB(Mat mat) {

        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2RGB);

        return mat;
    }

    private static Mat toGray(Mat mat) {
        Mat retorno = new Mat();
        Imgproc.cvtColor(mat, retorno, Imgproc.COLOR_RGBA2GRAY);
        return retorno;
    }

    public static BufferedImage matToBufferedImage(Mat m) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (m.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
        m.get(0, 0, ((DataBufferByte) image.getRaster().getDataBuffer()).getData());
        return image;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblImagemOriginal = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblImagemNova = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        jsliderTempo = new javax.swing.JSlider();
        jPanel3 = new javax.swing.JPanel();
        jsliderVelocidade = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        btnPause = new javax.swing.JButton();
        btnGetImage = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setPreferredSize(new java.awt.Dimension(750, 550));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImagemOriginal, javax.swing.GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImagemOriginal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setPreferredSize(new java.awt.Dimension(750, 550));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImagemNova, javax.swing.GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImagemNova, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel2.setText("Status: ");

        lblStatus.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lblStatus.setText("Loading");

        jsliderTempo.setMajorTickSpacing(1);
        jsliderTempo.setMaximum(10);
        jsliderTempo.setMinimum(1);
        jsliderTempo.setMinorTickSpacing(1);
        jsliderTempo.setToolTipText("");
        jsliderTempo.setValue(1);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jsliderVelocidade.setMajorTickSpacing(1);
        jsliderVelocidade.setMaximum(10);
        jsliderVelocidade.setMinimum(1);
        jsliderVelocidade.setMinorTickSpacing(1);
        jsliderVelocidade.setPaintLabels(true);
        jsliderVelocidade.setPaintTicks(true);
        jsliderVelocidade.setSnapToTicks(true);
        jsliderVelocidade.setToolTipText("");
        jsliderVelocidade.setValue(1);

        jLabel1.setText("Velocidade:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jsliderVelocidade, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jsliderVelocidade, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnPause.setText("Pause");
        btnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });

        btnGetImage.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        btnGetImage.setText("Get image");
        btnGetImage.setEnabled(false);
        btnGetImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jsliderTempo, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPause, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(btnGetImage)
                        .addGap(64, 64, 64)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblStatus)))
                .addContainerGap(227, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsliderTempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPause, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(lblStatus)
                        .addComponent(btnGetImage, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(151, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseActionPerformed
        // TODO add your handling code here:
        if (btnPause.getText() == "Pause") {
            lblStatus.setForeground(Color.red);
            lblStatus.setText("Paused");
            btnPause.setText(" Run ");
            paused = true;
        } else {
            lblStatus.setForeground(new Color(0, 102, 0));
            lblStatus.setText("Running");
            btnPause.setText("Pause");
            paused = false;
        }
    }//GEN-LAST:event_btnPauseActionPerformed

    private void btnGetImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetImageActionPerformed

        lblStatus.setForeground(Color.red);
        lblStatus.setText("Paused");
        btnPause.setText(" Run ");
        paused = true;

        FrameEdictor frame = new FrameEdictor();
        ImageIcon img = (ImageIcon) lblImagemNova.getIcon();
        Image image = img.getImage();
        BufferedImage buffered = (BufferedImage) image;
        frame.LoadImage(buffered);

        frame.setVisible(true);

    }//GEN-LAST:event_btnGetImageActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });

        VideoCapture webSource = new VideoCapture("teste02.mp4");
        MatOfByte mem = new MatOfByte();
        ArrayList<Mat> frames = new ArrayList();

        int count = 0;
        while (webSource.grab() && (count < 100 || true)) {
            Mat frame = new Mat();
            webSource.retrieve(frame);
            Imgcodecs.imencode(".bmp", frame, mem);
            count++;
            frames.add(frame);
        }

        if (frames.isEmpty()) {
            lblStatus.setText("Error!");
        } else {
            lblStatus.setForeground(new Color(0, 102, 0));
            lblStatus.setText("Running");
            btnGetImage.setEnabled(true);
            jsliderTempo.setMinimum(0);
            jsliderTempo.setMaximum(frames.size() - 1);
            jsliderTempo.setValue(0);

            while (true) {

                BufferedImage imagemoriginal = matToBufferedImage(frames.get(jsliderTempo.getValue()));
                imagemoriginal = resize(imagemoriginal);

                BufferedImage imagemnova = matToBufferedImage(toRGB(toCanny((frames.get(jsliderTempo.getValue())))));
                imagemnova = resize(imagemnova);

                imagemnova = quantization(imagemnova);

                lblImagemOriginal.setIcon(new ImageIcon(imagemoriginal));
                lblImagemOriginal.setHorizontalAlignment(SwingConstants.CENTER);

                lblImagemNova.setIcon(new ImageIcon(imagemnova));
                lblImagemNova.setHorizontalAlignment(SwingConstants.CENTER);

                if (!paused) {
                    switch (jsliderVelocidade.getValue()) {
                        case 1:
                            if (jsliderTempo.getValue() + 1 < frames.size()) {
                                jsliderTempo.setValue(jsliderTempo.getValue() + 1);
                            } else {
                                jsliderTempo.setValue(frames.size() - 1);
                            }
                            break;
                        case 2:
                            if (jsliderTempo.getValue() + 2 < frames.size()) {
                                jsliderTempo.setValue(jsliderTempo.getValue() + 2);
                            } else {
                                jsliderTempo.setValue(frames.size() - 1);
                            }
                            break;
                        case 3:
                            if (jsliderTempo.getValue() + 3 < frames.size()) {
                                jsliderTempo.setValue(jsliderTempo.getValue() + 3);
                            } else {
                                jsliderTempo.setValue(frames.size() - 1);
                            }
                            break;
                        case 4:
                            if (jsliderTempo.getValue() + 4 < frames.size()) {
                                jsliderTempo.setValue(jsliderTempo.getValue() + 4);
                            } else {
                                jsliderTempo.setValue(frames.size() - 1);
                            }
                            break;
                        case 5:
                            if (jsliderTempo.getValue() + 5 < frames.size()) {
                                jsliderTempo.setValue(jsliderTempo.getValue() + 5);
                            } else {
                                jsliderTempo.setValue(frames.size() - 1);
                            }
                            break;
                        case 6:
                            if (jsliderTempo.getValue() + 6 < frames.size()) {
                                jsliderTempo.setValue(jsliderTempo.getValue() + 6);
                            } else {
                                jsliderTempo.setValue(frames.size() - 1);
                            }
                            break;
                        case 7:
                            if (jsliderTempo.getValue() + 7 < frames.size()) {
                                jsliderTempo.setValue(jsliderTempo.getValue() + 7);
                            } else {
                                jsliderTempo.setValue(frames.size() - 1);
                            }
                            break;
                        case 8:
                            if (jsliderTempo.getValue() + 8 < frames.size()) {
                                jsliderTempo.setValue(jsliderTempo.getValue() + 8);
                            } else {
                                jsliderTempo.setValue(frames.size() - 1);
                            }
                            break;
                        case 9:
                            if (jsliderTempo.getValue() + 9 < frames.size()) {
                                jsliderTempo.setValue(jsliderTempo.getValue() + 9);
                            } else {
                                jsliderTempo.setValue(frames.size() - 1);
                            }
                            break;
                        case 10:
                            if (jsliderTempo.getValue() + 10 < frames.size()) {
                                jsliderTempo.setValue(jsliderTempo.getValue() + 10);
                            } else {
                                jsliderTempo.setValue(frames.size() - 1);
                            }
                            break;
                    }
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton btnGetImage;
    private javax.swing.JButton btnPause;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private static javax.swing.JSlider jsliderTempo;
    private static javax.swing.JSlider jsliderVelocidade;
    private static javax.swing.JLabel lblImagemNova;
    private static javax.swing.JLabel lblImagemOriginal;
    private static javax.swing.JLabel lblStatus;
    // End of variables declaration//GEN-END:variables
}
