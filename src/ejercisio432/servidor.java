/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercisio432;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author 2112107
 */
public class servidor {

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
        String bandera = "cos";
        while ((inputLine = in.readLine()) != null) {

            outputLine = "Respuesta:" + inputLine;

            try {
                Double input = 0.0;

                if (inputLine.contains("?")) {

                    if (inputLine.contains("/")) {
                        String[] lista = inputLine.split("/");
                        Double x = 0.0;
                        Double y = 0.0;
                        boolean flag = false;
                        int num = 0;
                        for (String i : lista) {
                            if (i.contains("?")) {
                                if (i.length() == 1) {
                                    input = Math.PI;
                                } else if (i.startsWith("?")) {
                                    x = Double.parseDouble(i.substring(1));
                                    x = x * Math.PI;
                                } else {
                                    x=Double.parseDouble(i.substring(0, i.length()-1));;
                                    x = x * Math.PI;
                                }
                            } else {
                                y = Double.parseDouble(i);
                                if (num == 1) {
                                    flag = true;
                                }
                            }
                            num += 1;

                        }
                        if (flag) {
                            input = x / y;
                        } else {
                            input = y / x;
                        }
                    } else {

                        if (inputLine.length() == 1) {
                            input = Math.PI;
                        } else if (inputLine.startsWith("?")) {
                            input = Double.parseDouble(inputLine.substring(1));
                            input = input * Math.PI;
                        } else {
                            
                            input=Double.parseDouble(inputLine.substring(0, inputLine.length()-1));
                            input = input * Math.PI;
                        }

                    }
                } else {
                    input = Double.parseDouble(inputLine);
                }
                Double respuesta = null;

                if (bandera.equals("sin")) {
                    respuesta = Math.sin(input);
                } else if (bandera.equals("cos")) {
                    respuesta = Math.cos(input);
                } else {
                    respuesta = Math.tan(input);
                }
                out.println("Respuesta: " + respuesta);
            } catch (NumberFormatException e) {
                if (inputLine.startsWith("fun:")) {
                    if (inputLine.substring(4).equals("sin") || inputLine.substring(4).equals("cos") || inputLine.substring(4).equals("tan")) {
                        bandera = inputLine.substring(4);
                        out.println("Operacion actual: " + bandera);

                    } else {
                        out.println("The operation is invalid, try again");
                    }
                } else {
                    if (outputLine.equals("Respuesta: Bye.")) {
                        break;
                    }
                    out.println("The input is invalid, try again");
                }
            }
            System.out.println("Mensaje: " + inputLine);

        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
