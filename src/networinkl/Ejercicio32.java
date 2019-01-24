package networinkl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alejandro
 */
public class Ejercicio32 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        URL myUrl = null;
        input = br.readLine();
        while (!"".equals(input)) {

            try {
                myUrl = new URL(input);

            } catch (MalformedURLException x) {
                x.printStackTrace();

            }
            System.out.println("*Protocol-> " + myUrl.getProtocol());
            System.out.println("*Authority-> " + myUrl.getAuthority());
            System.out.println("*Host-> " + myUrl.getHost());
            System.out.println("Port-> " + myUrl.getPort());
            System.out.println("Host->  " + myUrl.getPath());
            System.out.println("Query-> " + myUrl.getQuery());
            System.out.println("File-> " + myUrl.getFile());
            System.out.println("Ref->   " + myUrl.getRef());
            input = br.readLine();
        }

    }
}
