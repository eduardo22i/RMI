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

    public CB() throws RemoteException {
        super();
        cv.mCB = this;
        cv.setVisible(true);
    }
    public String getMensaje(String name, String mensaje)  {
        cv.getMensaje(name, mensaje);
        //System.out.println("\n"+ name + " dice: " + mensaje);
        //JOptionPane.showConfirmDialog(null, "\n"+ name + " dice: " + mensaje);
        return name + " dice: " + mensaje;
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
    
    public void setFather(MainCliente mc) {
        System.out.println("Father ok");
        cv.mc = mc;
    }
}   
