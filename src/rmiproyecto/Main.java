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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

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
