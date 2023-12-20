import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * PRÁCTICA 2
 * Crea un programa servidor que pueda atender N clientes, siendo N
 * un parámetro que el usuario introducirá por teclado.
 * Debe enviar a cada cliente un mensaje indicando el número de cliente que es.
 * El cliente mostrará el mensaje recibido por su salida estándar
 */
public class Servidor {
    //Usamos un puerto registrado
    private final int NUM_PUERTO = 6000;
    private int clientesActuales = 0;
    private ServerSocket serverSocket;
    private int numClientes;

    /**
     * El constructor expone el puerto indicado
     * y almacena el número de clientes a aceptar
     */
    public Servidor(int numClientes){
        try {
            serverSocket = new ServerSocket(NUM_PUERTO);
            this.numClientes = numClientes;
            System.out.println("A la espera de " + numClientes + " clientes .......");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cierra el puerto a la escucha
     * @return true si se cierra correctamemte. False en otro caso
     */
    public void cerrar(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Acepta al cliente y se comunica con él
     */
    private void aceptaClientes() {
        for (int i = 0; i < numClientes; i++) {
            //Lo hago con try-with-resources porque haremos toda la comunicación y la cerraremos
            try(Socket cliente = serverSocket.accept();) {
                comunicarConCliente(cliente);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Comunica al cliente su número de cliente
     */
    private void comunicarConCliente(Socket cliente) {
        try(DataOutputStream salida = new DataOutputStream(cliente.getOutputStream())) {
            clientesActuales++;
            //Pasamos a cada cliente su número de cliente
            salida.writeInt(clientesActuales);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int numClientes = pideNumClientes();
        Servidor servidor = new Servidor(numClientes);
        servidor.aceptaClientes();
        servidor.cerrar();
        System.out.println("Servidor cerrado");
    }

    private static int pideNumClientes() {
        int numClientes = 0;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.print("Introduce el número de clientes a aceptar: ");
            try{
                numClientes = sc.nextInt();
            } catch (InputMismatchException e){
                numClientes = 0;
            }
        }while(numClientes == 0);

        return numClientes;
    }
}
