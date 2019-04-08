/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import static frames.FrameVideo.matToBufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Henrique Goetz
 */
public class FrameGetRightmostPixel extends javax.swing.JDialog {

    /**
     * Creates new form FrameGetRightmostPixel
     */
    private int onClick = 0; // 0 - Draw // 2 - Paint
    private int cor = 0; // 0 - White // 1 - Black 

    private int rightmostPixel;

    private static final int WHITE = 0;
    private static final int BLACK = 1;
    private static final int DRAW = 0;
    private static final int PAINT = 2;

    public FrameGetRightmostPixel() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public void setImage(Mat frame) {
        lblImage.setIcon(new ImageIcon(quantization(resize((matToBufferedImage(toCanny(frame)))))));
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public int getRightmostPixel() {
        return rightmostPixel;
    }

    // To change options
    private boolean isDrawing() {
        if (onClick == DRAW) {
            return true;
        }
        return false;
    }

    private void paint() {
        btnPaint.setText("Draw");
        onClick = 2;
    }

    private void draw() {
        btnPaint.setText("Paint");
        onClick = 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPaint = new javax.swing.JButton();
        jpImage = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnWhite = new javax.swing.JButton();
        btnBlack = new javax.swing.JButton();
        btnFinish = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Speed Analyzer");
        setPreferredSize(new java.awt.Dimension(1460, 970));

        btnPaint.setText("Paint");
        btnPaint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaintActionPerformed(evt);
            }
        });

        jpImage.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpImage.setPreferredSize(new java.awt.Dimension(1400, 700));

        lblImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblImage.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                lblImageMouseDragged(evt);
            }
        });
        lblImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImageMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpImageLayout = new javax.swing.GroupLayout(jpImage);
        jpImage.setLayout(jpImageLayout);
        jpImageLayout.setHorizontalGroup(
            jpImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 1396, Short.MAX_VALUE)
        );
        jpImageLayout.setVerticalGroup(
            jpImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
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
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
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

        btnFinish.setText("Finish");
        btnFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinishActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(btnPaint, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(btnFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(203, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPaint, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPaintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaintActionPerformed
        if (isDrawing()) {
            paint();
        } else {
            draw();
        }
    }//GEN-LAST:event_btnPaintActionPerformed

    private void lblImageMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImageMouseDragged
        if (onClick == DRAW) {
            ImageIcon img = (ImageIcon) lblImage.getIcon();
            Image image = img.getImage();
            BufferedImage buffered = (BufferedImage) image;

            Point position = MouseInfo.getPointerInfo().getLocation();
            Color color;
            if (cor == WHITE) {
                color = new Color(255, 255, 255);
            } else {
                color = new Color(0, 0, 0);
            }

            buffered.setRGB((int) (position.getX() + lblImage.getX() - lblImage.getLocationOnScreen().x - (jpImage.getWidth() - buffered.getWidth()) / 2), (int) (position.getY() + lblImage.getY() - lblImage.getLocationOnScreen().y - (jpImage.getHeight() - buffered.getHeight()) / 2), color.getRGB());
            lblImage.setIcon(new ImageIcon(buffered));
            lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        }
    }//GEN-LAST:event_lblImageMouseDragged

    private void lblImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImageMouseClicked

        ImageIcon img = (ImageIcon) lblImage.getIcon();
        Image image = img.getImage();
        BufferedImage buffered = (BufferedImage) image;

        Point position = MouseInfo.getPointerInfo().getLocation();
        switch (onClick) {
            case DRAW:
                Color color;
                if (cor == WHITE) {
                    color = new Color(255, 255, 255);
                } else {
                    color = new Color(0, 0, 0);
                }
                buffered.setRGB((int) (position.getX() + lblImage.getX() - lblImage.getLocationOnScreen().x - (jpImage.getWidth() - buffered.getWidth()) / 2), (int) (position.getY() + lblImage.getY() - lblImage.getLocationOnScreen().y - (jpImage.getHeight() - buffered.getHeight()) / 2), color.getRGB());
                lblImage.setIcon(new ImageIcon(buffered));
                lblImage.setHorizontalAlignment(SwingConstants.CENTER);
                break;
            case PAINT:
                Point initialPoint = new Point((int) (position.getX() + lblImage.getX() - lblImage.getLocationOnScreen().x - (jpImage.getWidth() - buffered.getWidth()) / 2), (int) (position.getY() + lblImage.getY() - lblImage.getLocationOnScreen().y - (jpImage.getHeight() - buffered.getHeight()) / 2));
                buffered = paintPixelsFromImage(buffered, initialPoint);

                lblImage.setIcon(new ImageIcon(buffered));
                lblImage.setHorizontalAlignment(SwingConstants.CENTER);
                break;
        }
    }//GEN-LAST:event_lblImageMouseClicked

    private void btnWhiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWhiteActionPerformed
        cor = WHITE;
    }//GEN-LAST:event_btnWhiteActionPerformed

    private void btnBlackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBlackActionPerformed
        cor = BLACK;
    }//GEN-LAST:event_btnBlackActionPerformed

    private void btnFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinishActionPerformed
        ImageIcon img = (ImageIcon) lblImage.getIcon();
        Image image = img.getImage();
        BufferedImage buffered = (BufferedImage) image;
        rightmostPixel = lookForRightmostPixel(buffered);
        this.setVisible(false);
    }//GEN-LAST:event_btnFinishActionPerformed

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
            java.util.logging.Logger.getLogger(FrameGetRightmostPixel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameGetRightmostPixel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameGetRightmostPixel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameGetRightmostPixel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameGetRightmostPixel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBlack;
    private javax.swing.JButton btnFinish;
    private javax.swing.JButton btnPaint;
    private javax.swing.JButton btnWhite;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jpImage;
    private static javax.swing.JLabel lblImage;
    // End of variables declaration//GEN-END:variables

    private static Mat toCanny(Mat mat) {
        Mat retorno = new Mat();
        Imgproc.cvtColor(mat, retorno, Imgproc.COLOR_RGB2GRAY);
        Imgproc.Canny(retorno, retorno, 50, 50 * 3, 3, false);
        retorno.convertTo(retorno, CvType.CV_8U);
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

    private BufferedImage paintPixelsFromImage(BufferedImage image, Point initialPoint) {

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

            }
            points.remove(point);
        }

        return image;
    }

    private static BufferedImage quantization(BufferedImage image) {

        Color black = new Color(0, 0, 0);
        Color white = new Color(255, 255, 255);

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color rgb = new Color(image.getRGB(i, j));
                if (image.getRGB(i, j) != black.getRGB() && image.getRGB(i, j) != white.getRGB()) {
                    if (rgb.getRed() < 30) {
                        image.setRGB(i, j, black.getRGB());
                    } else {
                        image.setRGB(i, j, white.getRGB());
                    }
                }
            }
        }

        return image;
    }

    private static int lookForRightmostPixel(BufferedImage image) {

        Color gray = new Color(128, 128, 128);
        for (int i = image.getWidth() - 1; i >= 0; i--) {
            for (int j = 0; j < image.getHeight(); j++) {
                if (image.getRGB(i, j) == gray.getRGB()) {
                    return i;
                }
            }
        }

        return -1; //ERROR
    }
}