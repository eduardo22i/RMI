/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiproyecto;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Juan Leonardo
 */
public interface MensajeCB extends Remote {
    public String getMensaje(String name, String mensaje) throws RemoteException;
    public void setId(int id)  throws RemoteException;
    public int getID()  throws RemoteException;
    public String getName()  throws RemoteException;
    public void setName(String name)  throws RemoteException;
    public void setFather(MainCliente mc) throws RemoteException;
}

