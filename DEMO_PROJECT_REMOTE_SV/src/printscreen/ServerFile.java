/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package printscreen;


import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ServerSocketFactory;
import org.apache.commons.io.IOUtils;
import print_network.FileContainer;


public class ServerFile extends Thread {

	/* Define socket and parameters */
    private Socket socket = null;
    private File dstFile = null;
    private static ObjectOutputStream outputStream = null;
    //private ObjectInputStream inputStream = null;
    private FileEvent fileEvent = null;
    private static String fname = "test.txt";
    private FileOutputStream fileOutputStream = null;
    private String destinationPath1 =  "c:/up1/";
    private String destinationPathFilename =  "c:/up1/test.text";
    ArrayList<ObjectOutputStream> clientstreams = new ArrayList<ObjectOutputStream>();
    private ServerSocket serverSocket;
    private class ClientHandler extends  Thread{
         private  Socket clientSocket;
         ObjectInputStream inputStream;
         FileContainer c;

        public ClientHandler(Socket clientSocket) throws IOException, ClassNotFoundException{
            this.clientSocket = clientSocket;
            inputStream = new ObjectInputStream(clientSocket.getInputStream());
            c=(FileContainer) inputStream.readObject();
           // inputStream = new ObjectInputStream(clientSocket.getInputStream());
        }
        @Override
        public void run() {
            try {
              //  ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                while(true){
                     Object o = inputStream.readObject();
                    System.out.println("Read object: "+o);
                     //   Object o = inputStream.readObject();
                   // System.out.println("Read object: "+o);
                            /*
                        File file = new File("c:/up1", "abc.text");
                                
                    InputStream is = clientSocket.getInputStream();
                        BufferedInputStream bis = new BufferedInputStream(is);
                        try (DataInputStream dis = new DataInputStream(bis)) {
                            FileOutputStream fos = new FileOutputStream(file);
                            try (BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                                bos.write(IOUtils.toByteArray(dis));
                            }
                        }
                            */
                    
                      //  ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
                      //  FileContainer fc=(FileContainer) in.readObject(); // here you will need to add type casting
                    
                }
            }catch(SocketException e){
                System.out.println(clientSocket.getInetAddress().getHostAddress()+" disconnected from the Server");
                //clientstreams.remove(clientSocket);
            }catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }   
    
    private void waitForClients(ServerSocket mySocket) throws ClassNotFoundException {
        while(true){
            try {
                Socket client = mySocket.accept();
                //File tempFile = new File(path, fileName);
                //File tempFile = new File("c:/up1", "abc.text");
                //InputStream is = client.getInputStream();
                //BufferedInputStream bis = new BufferedInputStream(is);
                //try (DataInputStream dis = new DataInputStream(bis)) {
                //    FileOutputStream fos = new FileOutputStream(tempFile);
               //     try (BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                //        bos.write(IOUtils.toByteArray(dis));
               //     }
               // }
              //  System.out.println("Ready to receive");
             //   Socket client = mySocket.accept();
                
                //clientstreams.add(new ObjectOutputStream(client.getOutputStream()));
                System.out.println(client.getInetAddress().getHostAddress()+" connected to the Server");
                Thread t = new Thread(new ClientHandler(client));
                t.start();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
  
    
 
                   
        }
    }
    public void shareToAll(Object objectToSchare){
        for(ObjectOutputStream stream:clientstreams){
            try {
                stream.writeObject(objectToSchare);
                stream.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public void fileServer() throws IOException {
        //ServerSocket sersock = new ServerSocket(9876);
        //waitForClients(sersock);
       

    }
    ServerFile() throws IOException {
            serverSocket = ServerSocketFactory.getDefault().createServerSocket(9876);
    }
    @Override
        public void run() {
            while (true) {
                try {
                    final  Socket socketToClient = serverSocket.accept();
                    Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // limit scope of input/output to where they're actually used
                        //ObjectInputStream inputStream = new ObjectInputStream(socketToClient.getInputStream());
                       // try (ObjectInputStream input = new ObjectInputStream(socketToClient.getInputStream());
                       //      FileContainer   c=(FileContainer) input.readObject();
                            //    DataOutputStream output = new DataOutputStream(socketToClient.getOutputStream())) {
                            //output.writeUTF("Hey, this is the server!");
                            //output.flush();
                            //System.out.println(input.readUTF());
                                try{
                          ObjectInputStream input = new ObjectInputStream(socketToClient.getInputStream());
                          FileContainer   c=(FileContainer) input.readObject();
                          System.out.println(c.getFilename()); 
                            File file = new File("c:/up1", c.getFilename());
                            OutputStream    os   = new FileOutputStream(file);  
                            os.write(c.getData());
                            System.out.println("Successfully created file");

                            // Close the file
                            os.close();
                            
                        } catch (IOException e) {
                            System.out.println();
                            e.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(ServerFile.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        // implicitly close socket when done with it
                        try {
                            socketToClient.close();
                        } catch (IOException e) {
                            System.out.println();
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
                   // ClientHandler clientHandler = new ClientHandler(socketToClient);
                  //  clientHandler.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
    public static void main(String args[]) throws Exception {                           // establishing the connection with the server
         //File server call
        ServerFile s = new ServerFile();
        s.start();
    }

	/* Function for remove word from string */
    public static String removeWord(String string, String word) {
        if (string.contains(word)) {
            String tempWord = word + " ";
            string = string.replaceAll(tempWord, "");
            tempWord = " " + word;
            string = string.replaceAll(tempWord, "");
        }
        return string;
    }

    public void sendFile() throws IOException {

		/* file event - management */
        fileEvent = new FileEvent();
        String fileName = fname.substring(fname.lastIndexOf("/") + 1, fname.length());
        String path = fname.substring(0, fname.lastIndexOf("/") + 1);
        fileEvent.setDestinationDirectory(destinationPath1);
        fileEvent.setFilename(fileName);
        fileEvent.setSourceDirectory(fname);
        File file = new File(fname);
        if (file.isFile()) {
            try {
			/* Read input name and data of file from client */
                DataInputStream diStream = new DataInputStream(new FileInputStream(file));
                long len = (int) file.length();
                byte[] fileBytes = new byte[(int) len];
                int read = 0;
                int numRead = 0;
                while (read < fileBytes.length && (numRead = diStream.read(fileBytes, read, fileBytes.length - read)) >= 0) {
                    read = read + numRead;
                }

		/* send file with all its attributes */
                fileEvent.setFileSize(len);
                fileEvent.setFileData(fileBytes);
                fileEvent.setStatus("Success");
            } catch (Exception e) {
                e.printStackTrace();
                fileEvent.setStatus("Error");
            }
        } else {

		/* not proper path error  */
            System.out.println("path specified is not pointing to a file");
            fileEvent.setStatus("Error");
        }

        try {
            outputStream.writeObject(fileEvent);
            System.out.println("Done...Going to exit");
            Thread.sleep(3000);
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

	/* download file function */

}
