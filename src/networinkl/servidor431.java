/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networinkl;

import java.net.*;
import java.io.*;

/**
 *
 * @author 2112107
 */
public class servidor431 {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine;
        while ((inputLine = in.readLine()) != null) {
            int numero=0;
            try {
                numero = Integer.parseInt(inputLine);
                numero = numero * numero;
            } catch (Exception x) {
                System.out.println("No es un numero");
            }
            System.out.println("Mensaje: " + inputLine);
            outputLine = "Respuesta : " + numero;
            out.println(outputLine);
            if (outputLine.equals("Respuestas: Bye.")) {
                break;
            }
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
