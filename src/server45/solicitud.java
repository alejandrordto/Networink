package server45;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AsusPC
 * 
 * 
 * 
 */
public class solicitud extends Thread{
    private Socket clientSocket;
    
    
    
     public solicitud(Socket clientSocket) {
        this.clientSocket = clientSocket;
    
        
  
    }
    @Override
    public void run(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine = in.readLine();
            String outputLine,formato,resultado;
            byte[] bytes = null;
            if (inputLine != null) {
                inputLine = inputLine.split(" ")[1];
                if (inputLine.endsWith(".html")) {
                    bytes = Files.readAllBytes(new File("./" + inputLine).toPath());
                    resultado = "" + bytes.length;
                    formato = "text/html";
                } else if (inputLine.endsWith(".png")) {
                    bytes = Files.readAllBytes(new File("./" + inputLine).toPath());
                    resultado = "" + bytes.length;
                    formato = "image/png";
                } else {
                    bytes = Files.readAllBytes(new File("./error.html").toPath());
                    resultado = "" + bytes.length;
                    formato = "text/html";
                }
            } else {
                bytes = Files.readAllBytes(new File("./error.html").toPath());
                resultado = "" + bytes.length;
                formato = "text/html";
            }
            outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: "
                    + formato
                    + "\r\n"
                    + resultado
                    + "\r\n\r\n";
            
            byte[] hByte = outputLine.getBytes();
            byte[] rta = new byte[bytes.length + hByte.length];
            for (int i = 0; i < hByte.length; i++) {
                rta[i] = hByte[i];
            }
            for (int i = hByte.length; i < hByte.length + bytes.length; i++) {
                rta[i] = bytes[i - hByte.length];
            }
            clientSocket.getOutputStream().write(rta);
            clientSocket.close();
        } catch (IOException e) {
            Logger.getLogger(solicitud.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}