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

    public MainFrame() {
        initComponents();
    }

    private static BufferedImage resize(BufferedImage img) {
        if (img.getHeight() > 550 || img.getWidth() > 750) {
            int newW, newH;

            if (img.getHeight() / 550 > img.getWidth() / 750) {
                newH = 550;
                newW = img.getWidth() / (img.getHeight() / 550);
            } else {
                newW = 750;
                newH = img.getHeight() / (img.getWidth() / 750);
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

    private static BufferedImage matToBufferedImage(Mat m) {

        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (m.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
        m.get(0, 0, ((DataBufferByte) image.getRaster().getDataBuffer()).getData()); // get all the pixels
        return image;
    }

    private static BufferedImage toGrayScale(BufferedImage img) {

        BufferedImage img_new = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

        for (int row = 0; row < img_new.getHeight(); row++) {
            for (int col = 0; col < img_new.getWidth(); col++) {
                int rgb = img.getRGB(col, row);
                Color c = new Color(rgb);
                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();
                int k = (int) ((red * 0.299) + (0.587 * green) + (0.114 * blue));
                Color temp = new Color(k, k, k);
                img_new.setRGB(col, row, temp.getRGB());
            }
        }
        return img_new;
    }

    private static Mat toConvert(Mat mat, double contraste, double brilho) {
        Mat retorno = new Mat();
        mat.convertTo(retorno, -1, contraste, brilho);
        return retorno;
    }

    private static Mat toCanny(Mat mat) {
        Mat retorno = new Mat();
        Mat aux = mat;
        //Imgproc.cvtColor(aux, retorno, Imgproc.COLOR_RGB2GRAY);
        Imgproc.Canny(retorno, retorno, 150, 250, 3, false);
        retorno.convertTo(retorno, CvType.CV_8U);
        return retorno;
    }

    private static Mat toGray(Mat mat) {
        Mat retorno = new Mat();
        Imgproc.cvtColor(mat, retorno, Imgproc.COLOR_RGBA2GRAY);
        return retorno;
    }

    private static Mat toColorfull(Mat mat) {
        Mat retorno = new Mat();
        Imgproc.cvtColor(mat, retorno, Imgproc.COLOR_BGR2RGB);
        return retorno;
    }

    private static Mat toSobel(Mat mat) {
        Mat retorno = new Mat();
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2GRAY);
        Imgproc.Sobel(mat, retorno, mat.depth(), 1, 1);
        return (retorno);
    }

    private static Mat quantizationImage(Mat img) {
        Mat source = img;
        Mat finalImage = source.clone();
        Mat cannyImage = source.clone();
        cannyImage = toCanny(cannyImage);

        finalImage = toColorfull(finalImage);
        source.convertTo(source, CvType.CV_64FC3);

        int tons = 2;
        int intervalo = (int) 255 / (tons - 1);

        for (int col = 0; col < source.cols(); col++) {
            for (int row = 0; row < source.rows(); row++) {
                double[] temp = new double[source.channels()];
                source.get(row, col, temp);

                int k = (int) temp[0];
                int aux = 0;
                double finalColor = 0;

                for (int i = 0; i < tons; i++) {
                    if (i == 0) {
                        aux = Math.abs(k);
                    } else if (aux > Math.abs(k - (i * intervalo))) {
                        aux = Math.abs(k - (i * intervalo));
                        finalColor = i * intervalo;
                    }

                    if (k > 130 && k < 180) {
                        finalImage.put(row, col, new double[]{100, 255, 100});
                    } else if (k >180) {
                        finalImage.put(row, col, new double[]{255, 0, 0});
                    } else {

                        byte[] tempCanny = new byte[cannyImage.channels()];
                        cannyImage.get(row, col, tempCanny);
                        int l = (int) tempCanny[0];

                        if (l == 255) {
                            finalColor = 0;
                        }

                        finalImage.put(row, col, new double[]{finalColor, finalColor, finalColor});
                    }

                }
            }
        }
        return finalImage;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setPreferredSize(new java.awt.Dimension(750, 550));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImagemOriginal, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImagemOriginal, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setPreferredSize(new java.awt.Dimension(750, 550));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImagemNova, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImagemNova, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        jsliderVelocidade.setOrientation(javax.swing.JSlider.VERTICAL);
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jsliderVelocidade, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsliderVelocidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnPause.setText("Pause");
        btnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jsliderTempo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(394, 394, 394)
                        .addComponent(btnPause, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(68, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addComponent(lblStatus)
                .addGap(651, 651, 651))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsliderTempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPause)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStatus)
                    .addComponent(jLabel2))
                .addContainerGap(54, Short.MAX_VALUE))
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

        VideoCapture webSource = new VideoCapture("teste.mov");
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
            System.out.println("Error: File not founded.");
        } else {
            lblStatus.setForeground(new Color(0, 102, 0));
            lblStatus.setText("Running");
            jsliderTempo.setMinimum(0);
            jsliderTempo.setMaximum(frames.size() - 1);
            jsliderTempo.setValue(0);

            while (true) {

                BufferedImage imagemoriginal = matToBufferedImage(frames.get(jsliderTempo.getValue()));
                imagemoriginal = resize(imagemoriginal);

                BufferedImage imagemnova = matToBufferedImage(quantizationImage(toGray(frames.get(jsliderTempo.getValue()))));
                imagemnova = resize(imagemnova);

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
