import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Realiza un programa Java que admita desde la línea de comandos un nombre de máquina o
 * una dirección IP y visualice información sobre ella.
 */

public class Practica1 {
    public static void main(String[] args) {
        String url = pedirUrl();
        printDatosUrl(url);
    }

    private static String pedirUrl() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce una url a analizar: ");
        String url = sc.nextLine();
        return url;
    }


    private static void printDatosUrl(String url) {
        InetAddress dir = null;
        try {
            dir = InetAddress.getByName(url);
            System.out.println("\tSalida toString(): " + dir);
            System.out.println("\tMétodo getHostName(): " + dir.getHostName());
            System.out.println("\tMétodo getHostAddress(): " + dir.getHostAddress());
            System.out.println("\tMétodo getCanonicalHostName(): " + dir.getCanonicalHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

}
