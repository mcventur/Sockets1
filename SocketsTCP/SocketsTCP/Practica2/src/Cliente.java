import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
    private final int NUM_PUERTO = 6000;

    private final String HOST = "localhost";

    private Socket socketCliente;

    /**
     * El constructor abre la conexión con el servidor
     */
    public Cliente() {
        try {
            socketCliente = new Socket(HOST, NUM_PUERTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cierra la conexión con el servidor
     */
    public void cerrar(){
        try {
            socketCliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recibe e imprime el número recibido del servidor
     */
    public void recibirNumero(){
        try(DataInputStream entrada = new DataInputStream(socketCliente.getInputStream())) {
            System.out.println("Num de cliente recibido " + entrada.readInt());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Ejecutar tantas veces como clientes se esperen en el servidor
     */
    public static void main(String[] args) {
        Cliente c = new Cliente();
        c.recibirNumero();
        c.cerrar();
    }
}
