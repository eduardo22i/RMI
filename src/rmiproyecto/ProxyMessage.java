/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiproyecto;

import java.util.Date;

/**
 *
 * @author eduardoirias
 */
public class ProxyMessage implements java.io.Serializable {
    
    public int id;
    
    public ProxyClient from;
    
    public ProxyClient to;
    
    public Date date;
    
    public String fromName;
    
    public String message;
    
    public ProxyMessage () {
        
    }
    
    public ProxyMessage (ProxyClient f, ProxyClient t, Date d, String m) {
        this.from = f;
        this.to = t;
        this.date = d;
        this.message = m;
    }
    
    public int getId() {
        return id;
    }
    
    public void setFrom(ProxyClient fn) {
        from = fn;
    }
    
    public void setFromName(String fn) {
        fromName = fn;
    }
    
    public String getFromName() {
        return fromName;
    }
    
    
    public String getMessage() {
        return message;
    }
}
