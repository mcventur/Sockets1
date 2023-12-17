import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestInetAddress {
    private static final String URL = "www.google.es";

    public static void main(String[] args) {
        InetAddress dir = null;
        try {
            //Localhost
            System.out.println("==============================================");
            System.out.println("SALIDA PARA LOCALHOST");
            dir = InetAddress.getLocalHost();
            pruebaMetodos(dir);

            //www.google.es
            System.out.println("==============================================");
            System.out.println("SALIDA PARA UN NOMBRE DE HOST O URL");
            dir = InetAddress.getByName(URL);
            pruebaMetodos(dir);
            //Array con todas las direcciones ip asignadas al mismo host
            InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());
            for (InetAddress direccion : direcciones) {
                System.out.println(direccion.toString());
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void pruebaMetodos(InetAddress dir) {
        System.out.println("\tSalida toString(): " + dir);
        System.out.println("\tMétodo getHostName(): " + dir.getHostName());
        System.out.println("\tMétodo getHostAddress(): " + dir.getHostAddress());
        System.out.println("\tMétodo getCanonicalHostName(): " + dir.getCanonicalHostName());
    }
}
