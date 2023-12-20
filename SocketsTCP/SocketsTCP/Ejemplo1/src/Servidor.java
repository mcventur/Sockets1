import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    //Usamos un puerto registrado
    private final int NUM_PUERTO = 6000;

    public Servidor(){
        //1 - Publica el puerto. Con try-with-resources no tengo que gestionar el cierre
        try (ServerSocket servidor = new ServerSocket(NUM_PUERTO);){
            System.out.println("Esperando a cliente.......");
            //2 - Espera peticiones. Con try-with-resources
            try(Socket socketCliente = servidor.accept();){
                //3 - Atender al cliente: envío y recepción de datos
                //3A - Abrimos los flujos de entrada y salida. Con try-with-resources
                try(DataInputStream flujoEntrada = new DataInputStream(socketCliente.getInputStream());
                    DataOutputStream flujoSalida = new DataOutputStream(socketCliente.getOutputStream());){
                    //3B - Recibir información
                    String nombreCliente = flujoEntrada.readUTF();
                    //3C - Enviar información
                    flujoSalida.writeUTF(nombreCliente + ". Te has conectado con el servidor correctamente.");
                    int numeroLeido = flujoEntrada.readInt();
                    flujoSalida.writeUTF("Numero leído: " + numeroLeido);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Servidor();
    }
}
