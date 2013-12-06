/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiproyecto;

/**
 *
 * @author Juan Leonardo
 */
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainCliente {
    
    public Mensaje impl ;
    
    public void sendMessage (CB cb, String name, String message) {
        try {
            System.out.println(cb.getID() + " - "+ name  + " - " + message);
            impl.enviarMensaje(cb.getID(), name  , message);
        } catch (RemoteException ex) {
            Logger.getLogger(MainCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void doTest(){
        try {
            // fire to localhost port 1099
            Registry myRegistry = LocateRegistry.getRegistry("macbook-air-de-eduardo.local", 1099);
             
            
            Scanner scn = new Scanner(System.in);
            String m = ""; 
            // search for myMessage service15

            impl = (Mensaje) myRegistry.lookup("miMensaje");
            MensajeCB mCB = new CB();
            mCB.setFather(this);
            System.out.print("Su nombre: ");
            m = "NOTIN";
            mCB.setName(m);
            // call server's method         
            
            impl.registrarCB(mCB);
                
            while (true) {
                System.out.print("Mensaje: ");
                m = scn.nextLine();
                impl.enviarMensaje(((CB)mCB).getID(), mCB.getName()  , m);
            }                         
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }           
    
    public static void main(String[] args) {
        MainCliente main = new MainCliente();
        main.doTest();
    }
}