/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiproyecto;

/**
 *
 * @author Juan Leonardo
 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.swing.JFrame;
 
public interface Mensaje extends Remote {
    void enviarMensaje(int id, String name, String message) throws RemoteException;
    String getX(int id) throws RemoteException;   
    
    
    public int registrarCB(MensajeCB mcb) throws RemoteException;;
    public void deRegistrarCB(MensajeCB mcb) throws RemoteException;;
}
