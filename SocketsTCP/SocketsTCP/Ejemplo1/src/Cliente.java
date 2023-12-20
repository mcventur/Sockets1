import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
    private final int NUM_PUERTO = 6000;

    private final String HOST = "localhost";

    public Cliente() {
        //1 - Conectarse con el servidor. Con try-with-resources
        try(Socket socketCliente = new Socket(HOST, NUM_PUERTO);) {
            //2 - Envío + Recepción de datos. Con try-with-resources
            try(DataOutputStream flujoSalida = new DataOutputStream(socketCliente.getOutputStream());
            DataInputStream flujoEntrada = new DataInputStream(socketCliente.getInputStream());){
                //2B - Enviar información
                flujoSalida.writeUTF("Cliente A");
                //2B  - DataOutputStream tiene otros métodos para escribir distintos tipos primitivos
                flujoSalida.writeInt(43);
                //2C - Recibir información
                String mensaje = null;
                try{
                    while((mensaje = flujoEntrada.readUTF()) != null){
                        System.out.println("Mensaje leído: " + mensaje);
                    }
                } catch (EOFException e){
                    //esta excepción se lanza al llegar al final del flujo si se sigue intentado leer de él
                    System.out.println("Se ha llegado al final del flujo");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Cliente();
    }
}
