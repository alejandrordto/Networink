/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networinkl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author 2112107
 */
public class ejercisio2 {

    public static void main(String[] args) throws Exception {

        System.out.println("ingrese un URL");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        System.out.println("ingrese un donde guardara su archivo, Encontrara un archivo llamado resultado donde decida guardarlo.");
        String direccion = br.readLine();
        direccion = direccion + "/resultado.html";
        URL google = new URL(input);
        try (BufferedReader reader
                = new BufferedReader(new InputStreamReader(google.openStream()))) {
            String inputLine = null;
            File archivo = new File(direccion);

            if (!archivo.exists()) {
                FileWriter fw = new FileWriter(archivo);
                BufferedWriter bw = new BufferedWriter(fw);
                while ((inputLine = reader.readLine()) != null) {

                    bw.write(inputLine);
                }
                bw.close();
            } else {
                System.out.println("El archivo ingresado no es valido o ya existe.");
            }

        } catch (IOException x) {
            System.err.println(x);
        }
    }
}
