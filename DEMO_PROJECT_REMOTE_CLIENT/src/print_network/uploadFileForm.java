
package print_network;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import print_network.FileEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.io.IOUtils;

public class uploadFileForm extends javax.swing.JFrame {
static String ip="localhost";
   
    public uploadFileForm(String ipInit) {
        this.ip = "localhost";
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jFileChooser1.setCurrentDirectory(new java.io.File("C:\\pgp"));
        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFileChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private static String sourceFilePath2;
    private static ObjectInputStream inputStream = null;
    private static ObjectOutputStream outputStream = null;
    private FileEvent fileEvent = null;
    private File dstFile = null;
    private static String fname = null;
    private FileOutputStream fileOutputStream = null;
    private String destinationPath = "c:/up1/";

	/* send file from client to server */
    public void sendFile() {
        
        String fileName = fname.substring(fname.lastIndexOf("/") + 1, fname.length());
        String path = fname.substring(0, fname.lastIndexOf("/") + 1);
        
    try (Socket socket = new Socket("localhost", 9876)) {
        File file = new File(path, fileName);
        ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
        FileContainer fc=new FileContainer();
        fc.setFilename(fileName);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        long size = Files.size(file.toPath());
        fc.setData(fileContent);
        fc.setSize(size);
       // out.writeObject(fc);
        
/*
        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        try (DataOutputStream dos = new DataOutputStream(bos)) {                
            FileInputStream fis = new FileInputStream(file);
            try (BufferedInputStream bis = new BufferedInputStream(fis)) {
                dos.write(IOUtils.toByteArray(bis));
            }
        }
      */
         //ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
         out.writeObject(fc);
         out.close();
        
         //socket.close();

        //socket.close();
    }
    catch(Exception e) {
        e.printStackTrace();
    }
    
    
    /*
        fileEvent = new FileEvent();
        
        fileEvent.setDestinationDirectory(destinationPath);
        fileEvent.setFilename(fileName);
        fileEvent.setSourceDirectory(fname);
        File file = new File(fname);
        if (file.isFile()) {
            try {
                DataInputStream diStream = new DataInputStream(new FileInputStream(file));
                long len = (int) file.length();
                byte[] fileBytes = new byte[(int) len];
                int read = 0;
                int numRead = 0;
                while (read < fileBytes.length && (numRead = diStream.read(fileBytes, read, fileBytes.length - read)) >= 0) {
                    read = read + numRead;
                }
                fileEvent.setFileSize(len);
                fileEvent.setFileData(fileBytes);
                fileEvent.setStatus("Success");
            } catch (Exception e) {
                e.printStackTrace();
                fileEvent.setStatus("Error");
            }
        } else {
            System.out.println("path specified is not pointing to a file");
            fileEvent.setStatus("Error");
        }

        try {
            outputStream.writeObject("test");
            JOptionPane.showMessageDialog(null, "File uploaded successfully!");
            System.out.println("Done...Going to exit");
            Thread.sleep(3000);
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(uploadFileForm.class.getName()).log(Level.SEVERE, null, ex);
        }
*/
    }

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        try {
      
          String serverName = ip; //loop back ip   
            //this.setBounds(550, 150, 800, 700);
           // this.setResizable(false);
           // Socket sock = new Socket(serverName, 9876);
            Scanner input = new Scanner(System.in);
            System.out.print("Enter the file name : ");
            String keyRead = jFileChooser1.getSelectedFile().getAbsolutePath();
            System.out.println("Old path: " + keyRead);
            keyRead = keyRead.replaceAll("\\\\", "/");
          
            System.out.println("Path: " + keyRead);
          
            fname = keyRead;
           // OutputStream ostream = null;
          //  ostream = sock.getOutputStream();
         //   PrintWriter pwrite = new PrintWriter(ostream, true);
         //   pwrite.println("");
         //   outputStream = new ObjectOutputStream(sock.getOutputStream());
            this.sendFile();
           // sock.close();


        } catch (Exception ex) {
            Logger.getLogger(uploadFileForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jFileChooser1ActionPerformed

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
            java.util.logging.Logger.getLogger(uploadFileForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(uploadFileForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(uploadFileForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(uploadFileForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new uploadFileForm(ip).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser jFileChooser1;
    // End of variables declaration//GEN-END:variables
}

