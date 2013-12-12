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
import java.util.ArrayList;
 
public interface Mensaje extends Remote {
    void enviarMensaje(ProxyMessage pms, int idf) throws RemoteException;
    String getX(int id) throws RemoteException;   
    ProxyMessage getMessage(int id) throws RemoteException;
    
    boolean login(String user, String password) throws RemoteException;
    public void registrar(ProxyClient cliente, String password) throws RemoteException;
    ProxyClient getClient(String user) throws RemoteException;
    
    ArrayList <ProxyConversation> getConversation(int userid) throws RemoteException;
    ArrayList <ProxyClient> getClientsFromConversation(int convid) throws RemoteException;
    
    void getMessages(int user, int conv ) throws RemoteException;
    
    public int registrarCB(MensajeCB mcb) throws RemoteException;;
    public void deRegistrarCB(MensajeCB mcb) throws RemoteException;;
}
