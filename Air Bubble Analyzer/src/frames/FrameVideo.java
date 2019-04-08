/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.text.DecimalFormat;
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
public class FrameVideo extends javax.swing.JFrame {

    private static ArrayList<Mat> frames = new ArrayList();
    private static boolean paused = false;

    private double scalePixels; // Scale area selected.
    private double scaleCm;     // Area provided.

    //Defines
    private static final int WHITE = 0;
    private static final int BLACK = 1;
    private static final int DRAW = 0;
    private static final int SCALE = 1;

    private int onClick = 0; // 0 - Draw // 1 - Scale
    private int cor = 0; // 0 - White // 1 - Black

    private double fps; // Frames per second

    /**
     * Creates new form FrameVideo
     *
     * @param frame
     */
    public FrameVideo(ArrayList<Mat> frame) {
        initComponents();
        frames = frame;
        jsTime.setMaximum(frames.size() - 1);
        this.setVisible(true);
    }

    public void StartFrameScaleImage() {
        FrameScaleImage.setLocationRelativeTo(null);
        lblImage.setIcon(new ImageIcon(quantization(resize(matToBufferedImage(toCanny(frames.get(0)))))));
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        FrameScaleImage.setVisible(true);
        StartDialogScale();
        scaleCm = Float.parseFloat(txtArea.getText());
        lblAreaBaseScale.setText(txtArea.getText());
    }

    private void StartDialogScale() {
        DialogScale.setLocationRelativeTo(null);
        DialogScale.setModal(true);
        DialogScale.setVisible(true);
    }

    private FrameVideo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void StartFrameLoop() {
        loopVideo threadLoop = new loopVideo();
        threadLoop.start();
    }

    public class loopVideo extends Thread {

        public void run() {
            while (true) {
                BufferedImage imagemoriginal = matToBufferedImage(frames.get(jsTime.getValue()));
                imagemoriginal = resize(imagemoriginal);

                BufferedImage imagemnova = matToBufferedImage(toRGB(toCanny((frames.get(jsTime.getValue())))));
                imagemnova = resize(imagemnova);

                imagemnova = quantization(imagemnova);

                lblImagemOriginal.setIcon(new ImageIcon(imagemoriginal));
                lblImagemOriginal.setHorizontalAlignment(SwingConstants.CENTER);

                lblImagemNova.setIcon(new ImageIcon(imagemnova));
                lblImagemNova.setHorizontalAlignment(SwingConstants.CENTER);

                if (!paused) {
                    switch (jsliderVelocidade.getValue()) {
                        case 1:
                            if (jsTime.getValue() + 1 < frames.size()) {
                                jsTime.setValue(jsTime.getValue() + 1);
                            } else {
                                jsTime.setValue(frames.size() - 1);
                            }
                            break;
                        case 2:
                            if (jsTime.getValue() + 2 < frames.size()) {
                                jsTime.setValue(jsTime.getValue() + 2);
                            } else {
                                jsTime.setValue(frames.size() - 1);
                            }
                            break;
                        case 3:
                            if (jsTime.getValue() + 3 < frames.size()) {
                                jsTime.setValue(jsTime.getValue() + 3);
                            } else {
                                jsTime.setValue(frames.size() - 1);
                            }
                            break;
                        case 4:
                            if (jsTime.getValue() + 4 < frames.size()) {
                                jsTime.setValue(jsTime.getValue() + 4);
                            } else {
                                jsTime.setValue(frames.size() - 1);
                            }
                            break;
                        case 5:
                            if (jsTime.getValue() + 5 < frames.size()) {
                                jsTime.setValue(jsTime.getValue() + 5);
                            } else {
                                jsTime.setValue(frames.size() - 1);
                            }
                            break;
                        case 6:
                            if (jsTime.getValue() + 6 < frames.size()) {
                                jsTime.setValue(jsTime.getValue() + 6);
                            } else {
                                jsTime.setValue(frames.size() - 1);
                            }
                            break;
                        case 7:
                            if (jsTime.getValue() + 7 < frames.size()) {
                                jsTime.setValue(jsTime.getValue() + 7);
                            } else {
                                jsTime.setValue(frames.size() - 1);
                            }
                            break;
                        case 8:
                            if (jsTime.getValue() + 8 < frames.size()) {
                                jsTime.setValue(jsTime.getValue() + 8);
                            } else {
                                jsTime.setValue(frames.size() - 1);
                            }
                            break;
                        case 9:
                            if (jsTime.getValue() + 9 < frames.size()) {
                                jsTime.setValue(jsTime.getValue() + 9);
                            } else {
                                jsTime.setValue(frames.size() - 1);
                            }
                            break;
                        case 10:
                            if (jsTime.getValue() + 10 < frames.size()) {
                                jsTime.setValue(jsTime.getValue() + 10);
                            } else {
                                jsTime.setValue(frames.size() - 1);
                            }
                            break;

                    }
                }
            }

        }
    }

    //Get e set
    
    public double getFPS() {
        return this.fps;
    }

    public void setFPS(double value) {
        this.fps = value;
    }

    // To change status and options
    
    private boolean isRunning() {
        if (!paused) {
            return true;
        }
        return false;
    }

    private void run() {
        btnPause.setText("Pause");
        paused = false;
    }

    private void pause() {
        btnPause.setText(" Run ");
        paused = true;
    }
    
     private boolean isDrawing() {
        if (onClick == 0) {
            return true;
        }
        return false;
    }

    private void scale(){
        btnScale.setText("Draw");
        onClick = 1;
    }
    
    private void draw(){
        btnScale.setText("Scale");
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

        FrameScaleImage = new javax.swing.JFrame();
        btnScale = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblAreaBaseScale = new javax.swing.JLabel();
        jpImage = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btnWhite = new javax.swing.JButton();
        btnBlack = new javax.swing.JButton();
        DialogScale = new javax.swing.JDialog();
        jLabel10 = new javax.swing.JLabel();
        txtArea = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnOk = new javax.swing.JButton();
        btnPause = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblImagemOriginal = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jsCannyParameter = new javax.swing.JSlider();
        jPanel2 = new javax.swing.JPanel();
        lblImagemNova = new javax.swing.JLabel();
        jsTime = new javax.swing.JSlider();
        jPanel3 = new javax.swing.JPanel();
        jsliderVelocidade = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnGetStartPoint = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblEndPointFrame = new javax.swing.JLabel();
        lblEndPointRightmost = new javax.swing.JLabel();
        btnGetEndPoint = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblStartPointFrame = new javax.swing.JLabel();
        lblStartPointRightmost = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lblAreaCm = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblAreaPixels = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lblSpeed = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnSpeed = new javax.swing.JButton();

        FrameScaleImage.setTitle("Scale");
        FrameScaleImage.setMinimumSize(new java.awt.Dimension(1460, 970));

        btnScale.setText(" Scale");
        btnScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScaleActionPerformed(evt);
            }
        });

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Escala Informada:");

        lblAreaBaseScale.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel7)
                .addGap(47, 47, 47)
                .addComponent(lblAreaBaseScale, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(lblAreaBaseScale, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

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

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setText("Color:");

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

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnWhite, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBlack, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addGap(28, 28, 28))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBlack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnWhite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout FrameScaleImageLayout = new javax.swing.GroupLayout(FrameScaleImage.getContentPane());
        FrameScaleImage.getContentPane().setLayout(FrameScaleImageLayout);
        FrameScaleImageLayout.setHorizontalGroup(
            FrameScaleImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FrameScaleImageLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(FrameScaleImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FrameScaleImageLayout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnScale, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        FrameScaleImageLayout.setVerticalGroup(
            FrameScaleImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FrameScaleImageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FrameScaleImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnScale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        DialogScale.setTitle("Scale");
        DialogScale.setMinimumSize(new java.awt.Dimension(360, 180));

        jLabel10.setText("Please, inform the base area:");

        txtArea.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel11.setText("cm² ");

        btnOk.setText("Ok");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DialogScaleLayout = new javax.swing.GroupLayout(DialogScale.getContentPane());
        DialogScale.getContentPane().setLayout(DialogScaleLayout);
        DialogScaleLayout.setHorizontalGroup(
            DialogScaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogScaleLayout.createSequentialGroup()
                .addGroup(DialogScaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DialogScaleLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtArea, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11))
                    .addGroup(DialogScaleLayout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        DialogScaleLayout.setVerticalGroup(
            DialogScaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogScaleLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(DialogScaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(txtArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(btnOk)
                .addGap(22, 22, 22))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Video Analyzer");

        btnPause.setText("Pause");
        btnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setPreferredSize(new java.awt.Dimension(750, 550));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImagemOriginal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImagemOriginal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Canny Parameter:");

        jsCannyParameter.setMajorTickSpacing(50);
        jsCannyParameter.setMaximum(400);
        jsCannyParameter.setMinorTickSpacing(10);
        jsCannyParameter.setPaintLabels(true);
        jsCannyParameter.setPaintTicks(true);
        jsCannyParameter.setSnapToTicks(true);
        jsCannyParameter.setToolTipText("");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jsCannyParameter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(34, 34, 34))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jsCannyParameter, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jsTime.setMajorTickSpacing(1);
        jsTime.setMinimum(1);
        jsTime.setMinorTickSpacing(1);
        jsTime.setPaintTicks(true);
        jsTime.setToolTipText("");
        jsTime.setValue(1);

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

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnGetStartPoint.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        btnGetStartPoint.setText("Get Start Point");
        btnGetStartPoint.setMaximumSize(new java.awt.Dimension(265, 50));
        btnGetStartPoint.setMinimumSize(new java.awt.Dimension(250, 50));
        btnGetStartPoint.setPreferredSize(new java.awt.Dimension(265, 50));
        btnGetStartPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetStartPointActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Frame Number:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Rightmost Pixel:");

        lblEndPointFrame.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lblEndPointRightmost.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblEndPointFrame, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(lblEndPointRightmost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(lblEndPointFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(lblEndPointRightmost, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        btnGetEndPoint.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        btnGetEndPoint.setText("Get End Point");
        btnGetEndPoint.setMaximumSize(new java.awt.Dimension(265, 50));
        btnGetEndPoint.setMinimumSize(new java.awt.Dimension(250, 50));
        btnGetEndPoint.setPreferredSize(new java.awt.Dimension(265, 50));
        btnGetEndPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetEndPointActionPerformed(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Frame Number:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Rightmost Pixel:");

        lblStartPointFrame.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lblStartPointRightmost.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblStartPointRightmost, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(lblStartPointFrame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStartPointFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblStartPointRightmost, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Escala Informada:");

        lblAreaCm.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel12.setText("cm²");

        jLabel13.setText("Pixels");

        lblAreaPixels.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAreaCm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAreaPixels, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(lblAreaCm, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAreaPixels, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblSpeed.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblSpeed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSpeed.setText("---");

        jLabel14.setText("m/s");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lblSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel14)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblSpeed)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(23, 23, 23))
        );

        btnSpeed.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnSpeed.setText("Speed");
        btnSpeed.setMaximumSize(new java.awt.Dimension(70, 50));
        btnSpeed.setMinimumSize(new java.awt.Dimension(70, 50));
        btnSpeed.setPreferredSize(new java.awt.Dimension(70, 50));
        btnSpeed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSpeedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGetStartPoint, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGetEndPoint, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSpeed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSpeed, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnGetStartPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnGetEndPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jsTime, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnPause, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(239, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jsTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPause, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseActionPerformed
        if (isRunning()) {
            pause();
        } else {
            run();
        }
    }//GEN-LAST:event_btnPauseActionPerformed
    
    private void btnGetStartPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetStartPointActionPerformed
        pause();
        lblStartPointFrame.setText(String.valueOf(jsTime.getValue()));
        FrameGetRightmostPixel frame = new FrameGetRightmostPixel();
        frame.setImage(frames.get(jsTime.getValue()));
        frame.setModal(true);
        frame.setVisible(true);
        lblStartPointRightmost.setText(String.valueOf(frame.getRightmostPixel()));
    }//GEN-LAST:event_btnGetStartPointActionPerformed

    private void btnGetEndPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetEndPointActionPerformed
        pause();
        lblEndPointFrame.setText(String.valueOf(jsTime.getValue()));
        FrameGetRightmostPixel frame = new FrameGetRightmostPixel();
        frame.setImage(frames.get(jsTime.getValue()));
        frame.setModal(true);
        frame.setVisible(true);
        lblEndPointRightmost.setText(String.valueOf(frame.getRightmostPixel()));
    }//GEN-LAST:event_btnGetEndPointActionPerformed

    private void btnScaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScaleActionPerformed
        if(isDrawing()){
            scale();
        }else{
            draw();
        }
    }//GEN-LAST:event_btnScaleActionPerformed
    
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
            case SCALE:
                Point initialPoint = new Point((int) (position.getX() + lblImage.getX() - lblImage.getLocationOnScreen().x - (jpImage.getWidth() - buffered.getWidth()) / 2), (int) (position.getY() + lblImage.getY() - lblImage.getLocationOnScreen().y - (jpImage.getHeight() - buffered.getHeight()) / 2));
                scalePixels = countPixelsFromImageWithoutPaint(buffered, initialPoint);
                FrameScaleImage.setVisible(false);
                lblAreaCm.setText(String.valueOf(scaleCm));
                lblAreaPixels.setText(String.valueOf(scalePixels));
                StartFrameLoop();
                break;
        }
    }//GEN-LAST:event_lblImageMouseClicked

    private void btnWhiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWhiteActionPerformed
        cor = WHITE;
    }//GEN-LAST:event_btnWhiteActionPerformed

    private void btnBlackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBlackActionPerformed
        cor = BLACK;
    }//GEN-LAST:event_btnBlackActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        if (!txtArea.getText().isEmpty()) {
            DialogScale.setVisible(false);
        }
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnSpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSpeedActionPerformed
        
        if(!lblStartPointRightmost.getText().isEmpty() && !lblEndPointRightmost.getText().isEmpty()){
            DecimalFormat df = new DecimalFormat("0.00");
            int difference = Integer.valueOf(lblEndPointRightmost.getText()) - Integer.valueOf(lblStartPointRightmost.getText());
            double displacement = difference * Math.sqrt(scaleCm) / Math.sqrt(scalePixels)/100; // cm -> m (/100)
            double result = displacement / ((Integer.valueOf(lblEndPointFrame.getText())-Integer.valueOf(lblStartPointFrame.getText())) / fps); // frames -> s (/fps)
            lblSpeed.setText(String.valueOf(df.format(result)));
        }
    }//GEN-LAST:event_btnSpeedActionPerformed

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
            java.util.logging.Logger.getLogger(FrameVideo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameVideo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameVideo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameVideo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FrameVideo().setVisible(true);
        });

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DialogScale;
    private javax.swing.JFrame FrameScaleImage;
    private javax.swing.JButton btnBlack;
    private static javax.swing.JButton btnGetEndPoint;
    private static javax.swing.JButton btnGetStartPoint;
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnPause;
    private javax.swing.JButton btnScale;
    private javax.swing.JButton btnSpeed;
    private javax.swing.JButton btnWhite;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jpImage;
    private static javax.swing.JSlider jsCannyParameter;
    private static javax.swing.JSlider jsTime;
    private static javax.swing.JSlider jsliderVelocidade;
    private javax.swing.JLabel lblAreaBaseScale;
    private javax.swing.JLabel lblAreaCm;
    private javax.swing.JLabel lblAreaPixels;
    private javax.swing.JLabel lblEndPointFrame;
    private javax.swing.JLabel lblEndPointRightmost;
    private static javax.swing.JLabel lblImage;
    private static javax.swing.JLabel lblImagemNova;
    private static javax.swing.JLabel lblImagemOriginal;
    private javax.swing.JLabel lblSpeed;
    private javax.swing.JLabel lblStartPointFrame;
    private javax.swing.JLabel lblStartPointRightmost;
    private javax.swing.JTextField txtArea;
    // End of variables declaration//GEN-END:variables

    //Other functions
    
    private int countPixelsFromImageWithoutPaint(BufferedImage image, Point initialPoint) {
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
                points.add(new Point(point.x - 1, point.y));
                points.add(new Point(point.x + 1, point.y));
                points.add(new Point(point.x, point.y - 1));
                points.add(new Point(point.x, point.y + 1));
                nPixels++;
            }
            points.remove(point);
        }

        return nPixels;
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

    private static Mat toCanny(Mat mat) {
        Mat retorno = new Mat();
        Imgproc.cvtColor(mat, retorno, Imgproc.COLOR_RGB2GRAY);
        Imgproc.Canny(retorno, retorno, jsCannyParameter.getValue(), jsCannyParameter.getValue() * 3, 3, false);
        retorno.convertTo(retorno, CvType.CV_8U);
        return retorno;
    }

    private static Mat toRGB(Mat mat) {
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2RGB);
        return mat;
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

}
