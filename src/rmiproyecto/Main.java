/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiproyecto;

/**
 *
 * @author Juan Leonardo
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
     
    private void startServer(){
        try {
            // create on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);
             
            // create a new service named myMessage
            registry.rebind("miMensaje", new MensajeImpl());
        } catch (Exception e) {
            e.printStackTrace();
        }      
        System.out.println("El sistema esta listo");
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startServer();
    }

}
