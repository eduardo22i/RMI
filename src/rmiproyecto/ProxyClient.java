/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiproyecto;

import javax.swing.ImageIcon;

/**
 *
 * @author eduardoirias
 */
public class ProxyClient implements java.io.Serializable {
    
    public int id;
    public String name;
    public String lastName;
    public String user;
    public String email;
    public ImageIcon icon;
    public String iconloc;
    
    public ProxyClient () {
         
    }
    
    public ProxyClient (int id, String name, ImageIcon icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }
    
    
    
    
    
}
