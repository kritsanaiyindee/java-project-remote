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

public class ServerFile {

	/* Define socket and parameters */
    private Socket socket = null;
    private File dstFile = null;
    private static ObjectOutputStream outputStream = null;
    private ObjectInputStream inputStream = null;
    private FileEvent fileEvent = null;
    private static String fname = "test.txt";
    private FileOutputStream fileOutputStream = null;
    private String destinationPath1 =  "c:/up1/";
    private String destinationPathFilename =  "c:/up1/test.text";
    ArrayList<ObjectOutputStream> clientstreams = new ArrayList<ObjectOutputStream>();
    private class ClientHandler implements Runnable{
        Socket clientSocket;

        public ClientHandler(Socket clientSocket){
            this.clientSocket = clientSocket;
        }
        @Override
        public void run() {
            try {
                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                while(true){
                    try {
                        ois.readObject();

                    } catch (ClassNotFoundException | IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }catch(SocketException e){
                System.out.println(clientSocket.getInetAddress().getHostAddress()+" disconnected from the Server");
                //clientstreams.remove(clientSocket);
            }catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }   
    
    private void waitForClients(ServerSocket mySocket) {
        while(true){
            try {
                System.out.println("Ready to receive");
                Socket client = mySocket.accept();
                
                clientstreams.add(new ObjectOutputStream(client.getOutputStream()));
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
        ServerSocket sersock = new ServerSocket(1234);
        waitForClients(sersock);
        
        

    }

    public static void main(String args[]) throws Exception {                           // establishing the connection with the server
         //File server call
        ServerFile s = new ServerFile();
        try {
            s.fileServer();
        } catch (IOException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public void downloadFile() {
        try {

		/* error if path is not found or file event is not handled */
            fileEvent = (FileEvent) inputStream.readObject();
            if (fileEvent.getStatus().equalsIgnoreCase("Error")) {
                System.out.println("Error occurred ..So exiting");
                System.exit(0);
            }

		/* successfully downloaded */
            String outputFile = fileEvent.getDestinationDirectory() + fileEvent.getFilename();
            if (!new File(fileEvent.getDestinationDirectory()).exists()) {
                new File(fileEvent.getDestinationDirectory()).mkdirs();
            }

		/* Destination of output file */
            dstFile = new File(outputFile);
            fileOutputStream = new FileOutputStream(dstFile);
            fileOutputStream.write(fileEvent.getFileData());
            fileOutputStream.flush();
            fileOutputStream.close();
            System.out.println("Output file : " + outputFile + " is successfully saved ");
            Thread.sleep(3000);
            System.exit(0);

		/* catch error */
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
