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
public class ProxyMessage {
    public int id;
    public int from;
    public int to;
    public Date date;
    public String message;
    
    public ProxyMessage () {
        
    }
    
    public ProxyMessage (int f, int t, Date d, String m) {
        this.from = f;
        this.to = t;
        this.date = d;
        this.message = m;
    }
    
    public int getId() {
        return id;
    }
}
