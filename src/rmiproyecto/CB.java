/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiproyecto;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JOptionPane;

/**
 *
 * @author Juan Leonardo
 */
public class CB extends UnicastRemoteObject implements MensajeCB {
    private int id;
    public String name;
    public MiVentanta cv = new MiVentanta();
    public ProxyClient pc= null;

    public CB() throws RemoteException {
        super();
        cv.mCB = this;
        cv.setVisible(true);
        cv.setDefaultCloseOperation(1);
    }
    
    public void getLastMensaje(ProxyMessage pm) throws RemoteException {
           System.out.println("ESTOY DENTRO");
        
    }
    public String getMensaje(ProxyMessage pm)  {
        System.out.println(pm.from.user + " dice: " + pm.message); 
        pm.message = pm.message.replaceAll(":\\)", "☺");
        pm.message = pm.message.replaceAll(":\\(", "☹");
        pm.message = pm.message.replaceAll("\\(\\*\\)", "★");
        pm.message = pm.message.replaceAll("\\(f\\)", "☃");
        pm.message = pm.message.replaceAll("\\(r\\)", "⚅");
        pm.message = pm.message.replaceAll("\\(c\\)", "☕");
        
        
        cv.getMensaje(pm);
        //System.out.println("\n"+ name + " dice: " + mensaje);
        //JOptionPane.showConfirmDialog(null, "\n"+ name + " dice: " + mensaje);
        return pm.from.user + " dice: " + pm.message;
    }
    
    public void setId(int id) {
        this.id = id;
        cv.Id = id;
        System.out.println("Mi id:" + id);
    }
    public int getID() {
        return id;
    }
    public void setName(String name) {
        this.name= name ;
    } 
    
    public String getName() {
        return this.name;
    }
    
    public ProxyClient getProxyClient() {
        return this.pc;
    }
    public void setProxyClient(ProxyClient pc)  {
        this.pc = pc;
        
    }
    
    public void setFather(MainCliente mc) {
        System.out.println("Father ok");
        cv.mc = mc;
    }
}   
