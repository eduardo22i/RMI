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
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class MensajeImpl extends UnicastRemoteObject implements Mensaje {
    public Vector<ProxyMessage> v =  new Vector();    
    public Vector clienteLista = new Vector();
    
    public MensajeImpl() throws RemoteException {
        /*
        v.add("Juan Perez");
        v.add("Ana Perez"); 
        v.add("Maria Perez");
        v.add("Carlos Perez");
        * */
    }
    public void enviarMensaje(int id, String name, String message) throws RemoteException {
        //TODO: means all
        
        for (int i=0; i<clienteLista.size(); i++) {            
            if (i != id) {
                //TODO
                try {
                    ProxyMessage pm = new ProxyMessage(id, 0, null, ((MensajeCB)clienteLista.get(i)).getMensaje(name , message) ) ;
                    pm.id = v.size();
                    v.add(pm);
                } catch (Exception e) {
                }
               
            }
                
        }
    }    
    
    public String getX(int id) {
        String text = "";
        int todelete = 0;
        for (int i = 0; i < v.size(); i++) {
            try {
                System.out.println("Message " + v.get(i).to + " - " + id);
            //Add if
            //if (v.get(i).to == id) {
            if (v.get(i).from != id) {
                System.out.println("Obtuve mensaje" + v.size());
                text = v.get(i).message;
                todelete = i;
            }
            //}
            } catch (Exception e) {
            }
            
        }
        
        if (v.size() > 0 ) {
            //v.remove(todelete);
        }
        
        return text;
    }
    
    public int registrarCB(MensajeCB mcb) {
        clienteLista.add(mcb);        
        try {
            mcb.setId(clienteLista.size()-1);
        } catch (RemoteException ex) {
            Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        int id= 0;
        try {
            id= mcb.getID();
        } catch (RemoteException ex) {
            Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    public void deRegistrarCB(MensajeCB mcb) {
        clienteLista.removeElement(mcb);
    }
}
