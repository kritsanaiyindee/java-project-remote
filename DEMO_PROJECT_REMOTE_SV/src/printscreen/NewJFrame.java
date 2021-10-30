package printscreen;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JOptionPane;
import com.sun.jna.Library;
import com.sun.jna.Native;
import org.xerial.snappy.Snappy;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
public class NewJFrame extends javax.swing.JFrame 
{
    private final static int SERVER_PORT = 9999;
	private final static int SERVER_CURSOR_PORT = SERVER_PORT - 1;
	private final static int SERVER_KEBOARD_PORT = SERVER_PORT - 2;
	private DataOutputStream dataOutputStream;
	private ObjectOutputStream objectOutputStream;
	private Image cursor;
	private String myFont = "????";
	private BufferedImage screenImage;
	private Rectangle rect;
	private ServerSocket imageSeverSocket = null;
	private ServerSocket cursorServerSocket = null;
	private ServerSocket keyboardServerSocket = null;
	private Socket imageSocket = null;
	private Socket cursorSocket = null;
	private Socket keyboardSocket = null;
	private Robot robot;
	private int screenWidth, screenHeight;
	private Boolean isRunning = false;
	private Thread imgThread;
	private static int new_Width = 1920;
	private static int new_Height = 1080;
	private JButton startBtn;
	private JButton stopBtn;
	private JTextField widthTextfield;
	private JTextField heightTextfield;
	private JRadioButton compressTrueRBtn;
	private JRadioButton compressFalseRBtn;
	private JLabel widthLabel;
	private JLabel heightLabel;
	private JLabel compressLabel;
	private URL cursorURL = getClass().getClassLoader().getResource("cursor.gif");
	private Boolean isCompress = true;
	private JFrame fff = this;
	private final int MOUSE_MOVE = 1;
	private final int MOUSE_PRESSD = 2;
	private final int MOUSE_RELEASED = 3;
	private final int MOUSE_DOWN_WHEEL = 4;
	private final int MOUSE_UP_WHEEL = 5;
	private final int KEY_PRESSED = 6;
	private final int KEY_RELEASED = 7;
	private final int KEY_CHANGE_LANGUAGE = 8;
	int count = 0, count2 = 0;
	private NetworkScreenServer.User32jna u32 = NetworkScreenServer.User32jna.INSTANCE;
	private int buffersize = 1;
	private BufferedImage[] img = new BufferedImage[buffersize];
	private Vector<byte[]> imgvec = new Vector<>();
  public NewJFrame() 
  {
    initComponents();
    //FontAwesomeIcon fntIcon = new FontAwesomeIcon();
    //fntIcon.setGlyphName("UNDO");
    //jButton1.setI
    //jButton1.setGraphic(fntIcon);
    
  }
  	

	
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server Menu");
        setAlwaysOnTop(true);
        setResizable(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/remote2.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/1037316.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/1037316.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setText("File Transfer");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel4.setText("Remote");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(123, 123, 123)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jButton2))
                .addContainerGap(72, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  public  static class Read_data_B implements Runnable
  {   
    @Override
    public void run()
    {
      try 
      {
        DatagramSocket d = new DatagramSocket(9878);
        byte[] b = new byte[100];
        DatagramPacket p = new DatagramPacket(b, b.length);
        while(true)
        {           
          d.receive(p);
          String s = new String( p.getData() );
          s = s.substring(0, s.indexOf('#') );
          String s2 = s.substring(6, s.length());
          String s3[] = s2.split(",");
          jTextArea1.append(s3[0]+" "+s3[1]+"\n");
          Runtime.getRuntime().exec("cmd /c C:\\m\\mouse.exe moveTo "+s3[0]+"x"+s3[1]);
          Runtime.getRuntime().exec("cmd /c C:\\m\\mouse.exe click");
        }
      }
      catch (Exception e) 
      {
        JOptionPane.showMessageDialog(null,e.toString(),"หัวข้อ",JOptionPane.INFORMATION_MESSAGE );
      } 
    }
  }
  public  static class Read_data_A implements Runnable
  {   
    @Override
    public void run()
    {
      while(true)
      {
        try 
        {
          Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
          BufferedImage capture = new Robot().createScreenCapture(screenRect);   
          
          ByteArrayOutputStream os = new ByteArrayOutputStream();
          ImageOutputStream ios = ImageIO.createImageOutputStream(os);          
          JPEGImageWriteParam jpegParams = new JPEGImageWriteParam(null);
          jpegParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
          jpegParams.setCompressionQuality(0.2f);
          ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
          jpgWriter.setOutput(ios);
          jpgWriter.write(null,new IIOImage(capture, null, null),jpegParams);
          os.flush();
       
          byte[] b = os.toByteArray();
          DatagramSocket clientSocket = new DatagramSocket();       
          InetAddress IPAddress = InetAddress.getByName("192.168.1.45");
          DatagramPacket packet = new DatagramPacket(b, b.length, IPAddress, 9876);
          clientSocket.send(packet);
          Thread.sleep(1000);
        } 
        catch (Exception e) 
        {
          JOptionPane.showMessageDialog(null,e.toString(),"หัวข้อ",JOptionPane.INFORMATION_MESSAGE );
        }    
      }
    }
  }
  
  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    try 
    {
     new NetworkScreenServer();   
    } 
    catch (Exception e) {}
  }//GEN-LAST:event_jButton1ActionPerformed

  private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
   //File server call
        ServerFile s = new ServerFile();
        try {
            s.fileServer();
        } catch (IOException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
  }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        //File server call
        ServerFile s = new ServerFile();
        try {
            s.fileServer();
        } catch (IOException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed
  
  public static void main(String args[]) 
  {
    	java.awt.EventQueue.invokeLater(new Runnable() 
    {
      public void run() 
      {
        new NewJFrame().setVisible(true);
      }
    });
  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
