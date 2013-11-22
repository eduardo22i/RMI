/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class ChatImpl extends UnicastRemoteObject implements Chatting {
    private ChatServer cs;
 
    public ChatImpl(ChatServer cs) throws RemoteException
    {
        super();
        this.cs = cs;
    }
 
    public void sendPublicMessage(String keyword, String username, String message) throws RemoteException
    {
        cs.sendPublicMessage(keyword, username, message);
    }
 
    public ArrayList getClientList() throws RemoteException
    {
        return cs.getClientList();
    }
 
    public void connect(String username) throws RemoteException
    {
        cs.connect(username);
    }
 
    public void disconnect(String username) throws RemoteException
    {
        cs.disconnect(username);
    }
    
}
