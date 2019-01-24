/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networinkl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2112107
 */
public class ejercisio1 {

    public static void main(String[] args) {
        read();

    }

    //EJERCICIO 1
    private static void read() {
        try {
            URL myUrl = new URL("http://campusvirtual.escuelaing.edu.co:8080/moodle/course/view.php?id=1506#11h03m59s");
            System.out.println("*Protocol-> " + myUrl.getProtocol());
            System.out.println("*Authority-> " + myUrl.getAuthority());
            System.out.println("*Host-> " + myUrl.getHost());
            System.out.println("Port-> " + myUrl.getPort());
            System.out.println("Host->  " + myUrl.getPath());
            System.out.println("Query-> " + myUrl.getQuery());
            System.out.println("File-> " + myUrl.getFile());
            System.out.println("Ref->   " + myUrl.getRef());

        } catch (MalformedURLException ex) {

            ex.printStackTrace();

        }

    }

}
