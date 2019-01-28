package server45;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author 2112107
 */
public class server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Integer port;
        try { 
            port = new Integer(System.getenv("PORT"));
        } catch (Exception e) {
            port = 35000;
        }
        
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        

        Socket clientSocket = null;
        while (true) {
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            solicitud multi = new solicitud(clientSocket);
            multi.run();
        }

    }
}