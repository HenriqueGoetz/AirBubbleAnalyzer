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
public class DialogVideo extends javax.swing.JDialog {

    private static ArrayList<Mat> frames = new ArrayList<>();
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

    private boolean closed = false;

    /**
     * Creates new form FrameVideo
     *
     * @param frame
     */
    public DialogVideo(ArrayList<Mat> frame) {
        initComponents();
        frames = frame;
        jsTime.setMaximum(frames.size() - 1);
        DialogScaleImage.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                closed = true;
            }
        });

        DialogScale.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                closed = true;
            }
        });
    }

    public boolean getClosed() {
        return closed;
    }

    public void StartFrameScaleImage() {
        DialogScaleImage.setLocationRelativeTo(null);
        DialogScaleImage.setModal(true);
        lblImage.setIcon(new ImageIcon(quantization(resize(matToBufferedImage(toCanny(frames.get(0)))))));
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        StartDialogScale();
        if (!getClosed()) {
            scaleCm = Float.parseFloat(txtArea.getText());
            lblAreaBaseScale.setText(txtArea.getText());
            DialogScaleImage.setVisible(true);
        }
    }

    private void StartDialogScale() {
        DialogScale.setLocationRelativeTo(null);
        DialogScale.setModal(true);
        DialogScale.setVisible(true);
    }

    private DialogVideo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void StartFrameLoop() {
        loopVideo threadLoop = new loopVideo();
        threadLoop.start();
    }

    public class loopVideo extends Thread {

        @Override
        public void run() {
            while (true) {
                BufferedImage imagemoriginal = matToBufferedImage(frames.get(jsTime.getValue()));
                imagemoriginal = resize(imagemoriginal);

                lblImagemOriginal.setIcon(new ImageIcon(imagemoriginal));
                lblImagemOriginal.setHorizontalAlignment(SwingConstants.CENTER);

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
        return !paused;
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
        if (onClick == DRAW) {
            return true;
        }
        return false;
    }

    private void scale() {
        btnScale.setText("Draw");
        onClick = 1;
    }

    private void draw() {
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

        DialogScale = new javax.swing.JDialog();
        jLabel10 = new javax.swing.JLabel();
        txtArea = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnOk = new javax.swing.JButton();
        DialogScaleImage = new javax.swing.JDialog();
        jpImage = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        btnWhite = new javax.swing.JButton();
        btnBlack = new javax.swing.JButton();
        btnScale = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        lblAreaBaseScale = new javax.swing.JLabel();
        btnPause = new javax.swing.JButton();
        jpVideo = new javax.swing.JPanel();
        lblImagemOriginal = new javax.swing.JLabel();
        jsTime = new javax.swing.JSlider();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jsliderVelocidade = new javax.swing.JSlider();
        jPanel5 = new javax.swing.JPanel();
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
        btnGetStartPoint = new javax.swing.JButton();
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

        DialogScaleImage.setMinimumSize(new java.awt.Dimension(1230, 760));
        DialogScaleImage.setPreferredSize(new java.awt.Dimension(1230, 760));

        jpImage.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpImage.setPreferredSize(new java.awt.Dimension(1200, 640));

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
            .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 1196, Short.MAX_VALUE)
        );
        jpImageLayout.setVerticalGroup(
            jpImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setText("Color:");

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

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnWhite, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBlack, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(28, 28, 28))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBlack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnWhite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnScale.setText(" Scale");
        btnScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScaleActionPerformed(evt);
            }
        });

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Escala Informada:");

        lblAreaBaseScale.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel16)
                .addGap(58, 58, 58)
                .addComponent(lblAreaBaseScale, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAreaBaseScale, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel16)))
                .addContainerGap())
        );

        javax.swing.GroupLayout DialogScaleImageLayout = new javax.swing.GroupLayout(DialogScaleImage.getContentPane());
        DialogScaleImage.getContentPane().setLayout(DialogScaleImageLayout);
        DialogScaleImageLayout.setHorizontalGroup(
            DialogScaleImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogScaleImageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DialogScaleImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(DialogScaleImageLayout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnScale, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        DialogScaleImageLayout.setVerticalGroup(
            DialogScaleImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogScaleImageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DialogScaleImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnScale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Video Analyzer");
        setPreferredSize(new java.awt.Dimension(1600, 800));

        btnPause.setText("Pause");
        btnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });

        jpVideo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpVideo.setPreferredSize(new java.awt.Dimension(1200, 640));

        lblImagemOriginal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jpVideoLayout = new javax.swing.GroupLayout(jpVideo);
        jpVideo.setLayout(jpVideoLayout);
        jpVideoLayout.setHorizontalGroup(
            jpVideoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImagemOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, 1196, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jpVideoLayout.setVerticalGroup(
            jpVideoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImagemOriginal, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
        );

        jsTime.setMajorTickSpacing(1);
        jsTime.setMinimum(1);
        jsTime.setMinorTickSpacing(1);
        jsTime.setPaintTicks(true);
        jsTime.setToolTipText("");
        jsTime.setValue(1);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Velocidade:");

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jsliderVelocidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsliderVelocidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Frame Number:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Rightmost Pixel:");

        lblEndPointFrame.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lblEndPointRightmost.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnGetEndPoint.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnGetEndPoint.setText("Get End Point");
        btnGetEndPoint.setMaximumSize(new java.awt.Dimension(265, 50));
        btnGetEndPoint.setMinimumSize(new java.awt.Dimension(250, 50));
        btnGetEndPoint.setPreferredSize(new java.awt.Dimension(265, 50));
        btnGetEndPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetEndPointActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(lblEndPointFrame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblEndPointRightmost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btnGetEndPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(btnGetEndPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(lblEndPointFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(lblEndPointRightmost, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Frame Number:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Rightmost Pixel:");

        lblStartPointFrame.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lblStartPointRightmost.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnGetStartPoint.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnGetStartPoint.setText("Get Start Point");
        btnGetStartPoint.setMaximumSize(new java.awt.Dimension(265, 50));
        btnGetStartPoint.setMinimumSize(new java.awt.Dimension(250, 50));
        btnGetStartPoint.setPreferredSize(new java.awt.Dimension(265, 50));
        btnGetStartPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetStartPointActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnGetStartPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(lblStartPointFrame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblStartPointRightmost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(btnGetStartPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(lblStartPointFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStartPointRightmost, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Escala Informada:");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblAreaCm.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAreaCm.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel12.setText("cm²");

        jLabel13.setText("Pixels");

        lblAreaPixels.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAreaPixels.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblAreaCm, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAreaPixels, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(11, 11, 11)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAreaCm, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAreaPixels, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblSpeed.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblSpeed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSpeed.setText("---");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("m/s");

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

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSpeed, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSpeed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(btnSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSpeed)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jpVideo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPause, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jsTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpVideo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPause, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jsTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        DialogGetRightmostPixel frame = new DialogGetRightmostPixel();
        frame.setImage(frames.get(jsTime.getValue()));
        frame.setModal(true);
        frame.setVisible(true);
        if (!frame.wasClosed()) {
            lblStartPointRightmost.setText(String.valueOf(frame.getRightmostPixel()));
        }
    }//GEN-LAST:event_btnGetStartPointActionPerformed

    private void btnGetEndPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetEndPointActionPerformed
        pause();
        lblEndPointFrame.setText(String.valueOf(jsTime.getValue()));
        DialogGetRightmostPixel frame = new DialogGetRightmostPixel();
        frame.setImage(frames.get(jsTime.getValue()));
        frame.setModal(true);
        frame.setVisible(true);
        if (!frame.wasClosed()) {
            lblEndPointRightmost.setText(String.valueOf(frame.getRightmostPixel()));
        }
    }//GEN-LAST:event_btnGetEndPointActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        if (!txtArea.getText().isEmpty()) {
            DialogScale.setVisible(false);
        }
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnSpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSpeedActionPerformed

        if (!lblStartPointRightmost.getText().isEmpty() && !lblEndPointRightmost.getText().isEmpty() && !lblStartPointFrame.getText().equals(lblEndPointFrame.getText())) {
            DecimalFormat df = new DecimalFormat("0.00");
            int difference = Integer.valueOf(lblEndPointRightmost.getText()) - Integer.valueOf(lblStartPointRightmost.getText());
            double displacement = difference * Math.sqrt(scaleCm) / Math.sqrt(scalePixels) / 100; // cm -> m (/100)
            double result = displacement / ((Integer.valueOf(lblEndPointFrame.getText()) - Integer.valueOf(lblStartPointFrame.getText())) / fps); // frames -> s (/fps)
            lblSpeed.setText(String.valueOf(df.format(result)));
        }
    }//GEN-LAST:event_btnSpeedActionPerformed

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
         }    }//GEN-LAST:event_lblImageMouseDragged

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
                DialogScaleImage.setVisible(false);
                lblAreaCm.setText(String.valueOf(scaleCm));
                lblAreaPixels.setText(String.valueOf(scalePixels));
                StartFrameLoop();
                this.setVisible(true);
                break;
        }
    }//GEN-LAST:event_lblImageMouseClicked

    private void btnWhiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWhiteActionPerformed
        cor = WHITE;
    }//GEN-LAST:event_btnWhiteActionPerformed

    private void btnBlackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBlackActionPerformed
        cor = BLACK;        cor = BLACK;    }//GEN-LAST:event_btnBlackActionPerformed

    private void btnScaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScaleActionPerformed
        if (isDrawing()) {
            scale();
        } else {
            draw();
        }
    }//GEN-LAST:event_btnScaleActionPerformed

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
            java.util.logging.Logger.getLogger(DialogVideo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogVideo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogVideo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogVideo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new DialogVideo().setVisible(true);
        });

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DialogScale;
    private javax.swing.JDialog DialogScaleImage;
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
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jpImage;
    private static javax.swing.JPanel jpVideo;
    private static javax.swing.JSlider jsTime;
    private static javax.swing.JSlider jsliderVelocidade;
    private javax.swing.JLabel lblAreaBaseScale;
    private javax.swing.JLabel lblAreaCm;
    private javax.swing.JLabel lblAreaPixels;
    private javax.swing.JLabel lblEndPointFrame;
    private javax.swing.JLabel lblEndPointRightmost;
    private static javax.swing.JLabel lblImage;
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
        ArrayList<Point> points = new ArrayList<>();
        ArrayList<Point> visitados = new ArrayList<>();
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
        int w = jpVideo.getSize().width - 10; // -10 Padding
        int h = jpVideo.getSize().height - 10; // -10 Padding

        if (img.getHeight() > h || img.getWidth() > w) {
            int newW, newH;

            if ((double) img.getHeight() / h < (double) img.getWidth() / w) {
                newW = w;
                newH = (int) (img.getHeight() / ((double) img.getWidth() / w));
            } else {
                newH = h;
                newW = (int) (img.getWidth() / ((double) img.getHeight() / h));
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

}
