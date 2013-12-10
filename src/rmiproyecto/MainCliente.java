/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiproyecto;

/**
 *
 * @author Juan Leonardo
 */
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class MainCliente {
    
    public Mensaje impl ;
    public MensajeCB mCB ;
    
    public void sendMessage (CB cb, String name, String message, ArrayList<ProxyClient> personas) {
        try {
            System.out.println("Sender: "+cb.getID() + " - "+ name  + " - " + message);
             ProxyClient pc = (ProxyClient) impl.getClient(name);
             
            for (int i = 0; i < personas.size(); i++) {
                
                ProxyClient pc2 = (ProxyClient) impl.getClient(personas.get(i).user);
                System.out.println("to " + pc2.user);

                //TODO Date is null
                ProxyMessage pm = new ProxyMessage(pc , pc2, null, message);
                impl.enviarMensaje(cb.getID(), name  , message, pm);
                //ProxyMessage pm2 = new ProxyMessage(pc2 , pc, null, message);
                //impl.enviarMensaje(cb.getID(), name  , message, pm2);
            }
                        
            
           
            
            
            
            

            
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
            mCB = new CB();
            mCB.setFather(this);
            System.out.print("Su nombre: ");
            m = "NO-Name";
            mCB.setName(m);
            // call server's method         
            
            impl.registrarCB(mCB);
            
            /*
            while (true) {
                System.out.print("Mensaje: ");
                m = scn.nextLine();
                impl.enviarMensaje(((CB)mCB).getID(), mCB.getName()  , m);
            } 
            */
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }           
    
    public static void main(String[] args) {
        MainCliente main = new MainCliente();
        main.doTest();
    }
}