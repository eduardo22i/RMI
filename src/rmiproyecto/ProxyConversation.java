package rmiproyecto;

import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eduardoirias
 */
public class ProxyConversation  implements java.io.Serializable {
    public int id;
    public String name;
    public ArrayList<ProxyClient> subscribers = new ArrayList<ProxyClient>();
    
    public ProxyConversation() {
        
    }
    
}
