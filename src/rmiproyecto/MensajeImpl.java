/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiproyecto;

/**
 *
 * @author Juan Leonardo
 */

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;
import java.util.TimerTask;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MensajeImpl extends UnicastRemoteObject implements Mensaje {
    public Vector<ProxyMessage> v =  new Vector();    
    public Vector clienteLista = new Vector();
    
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    
  
    private String[][] servers = { {"js-alienware.local", "3306"},
                                   {"macbook-air-de-eduardo.local", "8889"},
                                   {"macbook-air-de-eduardo.local", "8889"}
                                 };
    private int servidorActual = 0;
    int cont = 0;
    
    public class CustomComparator implements Comparator<ProxyMessage> {
        

        @Override
        public int compare(ProxyMessage o1, ProxyMessage o2) {
            return o1.date.compareTo(o2.date);
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    private  class MessageLoop extends TimerTask {
        private Connection connect = null;
        private Statement statement = null;
        private PreparedStatement preparedStatement = null;
        private ResultSet resultSet = null;
        MiVentanta ventana;
        MensajeImpl mi;
        ArrayList usuarios = new ArrayList();
        ArrayList messages = new ArrayList();
        int user = 0;
        int conv = 0;
        
        private String[][] servers = { {"js-alienware.local", "3306"},
                                   {"macbook-air-de-eduardo.local", "8889"},
                                   {"macbook-air-de-eduardo.local", "8889"}
                                 };
    private int servidorActual = 0;
    int cont = 0;
         
         public MessageLoop( ) {
             
         }
         public void run () {
             //this.
         }
         
         
         
         public void getMessagesThread(int user, int conv ) throws RemoteException {
         System.out.println("IN " + user);
         
         ArrayList usuarios = new ArrayList();
         ArrayList messages = new ArrayList();
         ArrayList types = new ArrayList();
         ArrayList <Date> fechas = new ArrayList<Date>();
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /*   
        MessageLoop ml = new MessageLoop();
       


        ml.mi = this;
        ml.user = user;
        ml.conv = conv;
        ml.login();
        */ 
        //asdf
       
         try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
           
            connect = DriverManager.getConnection("jdbc:mysql://"+this.servers[0][0] +":" + this.servers[0][1] +"/so2?"
                    + "user=root&password=root");

            

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("SELECT * FROM so2.mensaje WHERE idconversacion = " + conv + "");
            
            
            while (resultSet.next()) {
                
                usuarios.add(resultSet.getString("idusuario"));
                messages.add(resultSet.getString("message"));
                types.add(resultSet.getString("idtipo"));
                
                String str_date = resultSet.getString("fecha");
                System.out.println(str_date);
                DateFormat formatter;
                Date date;
                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = formatter.parse(str_date);
                
                
                fechas.add(date);
                System.out.println("MENSAJE: " + resultSet.getString("message"));

                


            }

        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            close();
        }
        
          try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
           
            connect = DriverManager.getConnection("jdbc:mysql://"+this.servers[1][0] +":" + this.servers[1][1] +"/so2?"
                    + "user=root&password=root");

            

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("SELECT * FROM so2.mensaje WHERE idconversacion = " + conv + "");
            
            
            while (resultSet.next()) {

                
                usuarios.add(resultSet.getString("idusuario"));
                messages.add(resultSet.getString("message"));
                types.add(resultSet.getString("idtipo"));
                
                String str_date = resultSet.getString("fecha");
                System.out.println(str_date);
                DateFormat formatter;
                Date date;
                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = formatter.parse(str_date);

                fechas.add(date);
                
                System.out.println("MENSAJE: " + resultSet.getString("message"));

                


            }

        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            close();
        }
         
         System.out.println("clienteLista: "+ mi.clienteLista.size());
         int userinarray = 0;
         //TODO Sacar usuario no conectado
         for (int i = 0; i < mi.clienteLista.size(); i++) {
             if (mi.getClient(((MensajeCB) mi.clienteLista.get(i)).getName()).id == user ) {
                    System.out.println("user is = " + i);
                    userinarray = i;
                }
         }
         
         ArrayList <ProxyMessage> proxMens = new ArrayList<ProxyMessage>();
         
         
         System.out.println("Messages size: " +messages.size());
         for (int i = 0; i < messages.size(); i++) {
             ProxyMessage pm = new ProxyMessage();
             
             String id = mi.getClientUser(Integer.parseInt( usuarios.get(i).toString() ));
             
             pm.from = mi.getClient(id);
             pm.message = messages.get(i).toString();
             pm.type = Integer.parseInt(types.get(i).toString());
             
             if (pm.type == 2) {
                 URL url;
                BufferedImage img;
                try {
                    url = new URL(pm.message );

                    img = ImageIO.read(url);
                    pm.icon = new ImageIcon(img);

                } catch (IOException ex) {
                    Logger.getLogger(MainCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
            
             pm.date = (Date) fechas.get(i);
             
             System.out.println("My messages: "+   messages.get(i).toString());
             //if (((MensajeCB) clienteLista.get(i)).getID() == pms.to.id) {
             //}
             System.out.println("FROM:" + pm.from.name );
             
             proxMens.add(pm);
             
         }
         
         Collections.sort(proxMens, new CustomComparator());
         
         
         for (int i = 0; i < proxMens.size(); i++) {
             System.out.println("enviado" + proxMens.get(i).date);
             ((MensajeCB) mi.clienteLista.get(userinarray)).getMensaje(proxMens.get(i));
             
         }
         
         
        

    }
    
         
         
         public ArrayList <ProxyConversation> getConversation(int userid) throws RemoteException {
         
     
        ArrayList  convs = new ArrayList();
        

        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:8889/so2?"
                    + "user=root&password=root");


            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("SELECT c.id, c.nombre "
                    + "FROM so2.conversacion c "
                    + "JOIN so2.usuarioperteneceaconversacion upc "
                    + "ON c.id = upc.idconversacion "
                    + "WHERE upc.idusuario = '" + userid + "'");
            
            
            while (resultSet.next()) {
                
                ProxyConversation pcnv = new ProxyConversation();
                
                pcnv.id = Integer.parseInt(resultSet.getString("id"));
                pcnv.name = resultSet.getString("nombre") + "";
                
                System.out.println("Conv id: " + pcnv.id + " - name: " + pcnv.name);
                
                
                
                
                
                
                
                convs.add(pcnv);
                
                
            }
            
            
            

        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            close();
        }
        
        
        

        return convs;


    }
    
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    //ENDOF THING
    
    
    
    
    

    
    
    public MensajeImpl() throws RemoteException {
        /*
        v.add("Juan Perez");
        v.add("Ana Perez"); 
        v.add("Maria Perez");
        v.add("Carlos Perez");
        * */
    }
    
    public void enviarMensaje(ProxyMessage pms, int idf) throws RemoteException {
        //TODO: save message in conversation
        /*
         MessageLoop ml = new MessageLoop();
         ml.mi = this;
         ml.enviarMensajeThread(id, name, message, pms, idf);
         */


        int cont = 0;



        for (int i = 0; i < clienteLista.size(); i++) {
            //TODO


            MensajeCB mcbin = ((MensajeCB) clienteLista.get(i));
            if (mcbin != null) {

                //if (((MensajeCB) clienteLista.get(j)).getID() == user) {
                System.out.println("THE USER IS " + mcbin.getName());
                ProxyClient pcb = this.getClient(mcbin.getName());

                //if (pcb.id == pms.to.id || pcb.id == pms.from.id) {
                if (pcb.id == pms.to.id ) {
                    //ProxyMessage pm = (ProxyMessage) pms;
                    System.out.println("id con: " + pms.to.name);
                    //ProxyClient pc = this.getClient(name);
                    //pm.from = pc;




                    //v.add(pms);
                    System.out.println("o " + idf);

                    System.out.println(mcbin.getName());


                    if (pms.type == 2) {
                        Random rand = new Random();
                        int randomNum = rand.nextInt((1000 - 1) + 1) + 1;
                        Image source = pms.icon.getImage();
                        int w = source.getWidth(null);
                        int h = source.getHeight(null);
                        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
                        Graphics2D g2d = (Graphics2D) image.getGraphics();
                        g2d.drawImage(source, 0, 0, null);
                        g2d.dispose();

                        //TODO: MACBOOK
                        String url = "/Users/eduardoirias/Sites/RMI-Files/photos/" + pms.from.user + randomNum + ".png";

                        try {
                            ImageIO.write(image, "png", new File(url));
                        } catch (IOException ex) {
                            Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        System.out.println("Comenzando");

                        String url2 = "http://macbook-air-de-eduardo.local:8888/RMI-Files/photos/" + pms.from.user + randomNum + ".png";
                        pms.message = url2;
                    }




                    ((MensajeCB) clienteLista.get(mcbin.getID())).getMensaje(pms);

                    if (cont == 0 && idf == 0) {
                        System.out.println("SAVE MESSAGE");
                        saveMessage(pms);
                    }

                    cont++;

                }

            }
            /*
             * else {
             clienteLista.remove(i);
             i--;
             }
             * 
             * */



        }

    }
    
    public String getX(int id) {
        String text = "";
        int todelete = 0;
        for (int i = 0; i < v.size(); i++) {
            try {
                System.out.println("Message " + v.get(i).to + " - " + id);
            //Add if
            //if (v.get(i).to == id) {
            if (v.get(i).from.id != id) {
                System.out.println("Obtuve mensaje" + v.size());
                text = v.get(i).message;
                todelete = i;
            }
            //}
            } catch (Exception e) {
            }
            
        }
        
        if (v.size() > 0 ) {
            //v.remove(todelete);
        }
        
        return text;
    }
    
    
    public ProxyMessage getMessage(int id) throws RemoteException {
        ProxyMessage text = null;
        System.out.println("ok!");
        int todelete = 0;
        for (int i = 0; i < v.size(); i++) {
            try {
                System.out.println("Message " + v.get(i).to + " - " + id);
            //Add if
            //if (v.get(i).to == id) {
            //if (v.get(i).from != id) {
                System.out.println("Obtuve mensaje" + v.size());
                text = v.get(i);
                todelete = i;
            //}
            //}
            } catch (Exception e) {
            }
            
        }
        
        if (v.size() > 0 ) {
            //v.remove(todelete);
        }
        
        return text;
    }
    
    /*MYSQL*/
    
    /**
     * 
     * @param Client 
     */
    //@SuppressWarnings("empty-statement")
     public void  saveMessage(ProxyMessage pm) throws RemoteException {

        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            //IPS
            
            connect = DriverManager.getConnection("jdbc:mysql://"+this.servers[servidorActual][0]+":" + this.servers[servidorActual][1] +"/so2?"
                    + "user=root&password=root");


            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            String query = "INSERT INTO so2.mensaje (idconversacion, idtipo, idusuario, fecha, message) VALUES (" + pm.conversation.id + "," + pm.type + "," + pm.from.id + ", NOW(), '" + pm.message + "')";
            
            System.out.println(query);
            preparedStatement = connect.prepareStatement(query);
            

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            close();
        }
        
        
        System.out.println("Save the jedi (client) " + pm.to.name);
        
        cont++;
        if (cont%2 == 0) {
            if (servidorActual == 1) {
                servidorActual = 0;
            } else {
                servidorActual++;
            }
        }



    }
    
     
    /**
     * 
     * @param Client 
     */
    //@SuppressWarnings("empty-statement")
     public ProxyClient getClient(String user) throws RemoteException {
        ProxyClient pc = new ProxyClient();
        
        int cont = 0;
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:8889/so2?"
                    + "user=root&password=root");


            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("SELECT * FROM so2.usuario WHERE user = '" + user + "'");
            
            while (resultSet.next()) {

                int id = Integer.parseInt(resultSet.getString("id"));
                
                pc.id = id;
                pc.name = resultSet.getString("nombre");
                pc.lastName = resultSet.getString("apellido");
                pc.user = resultSet.getString("user");
                pc.icon = null;
                pc.iconloc = resultSet.getString("photo");
                
                
                URL url;
                BufferedImage img;
                try {
                    url = new URL(pc.iconloc );

                    img = ImageIO.read(url);
                    pc.icon = new ImageIcon(img);

                } catch (IOException ex) {
                    Logger.getLogger(MainCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                
        
            
                //pc.id = id.intValue() + 0;

                 cont++;
            }
            
            

        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            close();
        }
        
        if (cont == 0) {
            pc = null;
            return pc;
        }
        
        System.out.println("The return of the jedi (client) " + pc.name);


        return pc;


    }
    
    
     /**
     * 
     * @param Client 
     */
    //@SuppressWarnings("empty-statement")
     public String getClientUser(int id) throws RemoteException {
        String user = "";


        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:8889/so2?"
                    + "user=root&password=root");


            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("SELECT * FROM so2.usuario WHERE id = '" + id + "'");
            
            
            while (resultSet.next()) {

                user = resultSet.getString("user");
               


            }

        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            close();
        }
        
        


        return user;


    }
    
     
    /**
     * 
     * @param Client 
     */
    //@SuppressWarnings("empty-statement")
     public void getMessages(int user, int conv ) throws RemoteException {
         MessageLoop ml = new MessageLoop();
         ml.mi = this;
         ml.servers = this.servers;
         ml.cont = this.cont;
         ml.conv = conv;
         ml.getMessagesThread(user, conv);
         
         /*
         System.out.println("IN " + user);
         
         ArrayList usuarios = new ArrayList();
         ArrayList messages = new ArrayList();
         ArrayList types = new ArrayList();
         ArrayList <Date> fechas = new ArrayList<Date>();
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /*   
        MessageLoop ml = new MessageLoop();
       


        ml.mi = this;
        ml.user = user;
        ml.conv = conv;
        ml.login();
        * / 
        //asdf
       
         try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
           
            connect = DriverManager.getConnection("jdbc:mysql://"+this.servers[0][0] +":" + this.servers[0][1] +"/so2?"
                    + "user=root&password=root");

            

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("SELECT * FROM so2.mensaje WHERE idconversacion = " + conv + "");
            
            
            while (resultSet.next()) {
                
                usuarios.add(resultSet.getString("idusuario"));
                messages.add(resultSet.getString("message"));
                types.add(resultSet.getString("idtipo"));
                
                String str_date = resultSet.getString("fecha");
                System.out.println(str_date);
                DateFormat formatter;
                Date date;
                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = formatter.parse(str_date);
                
                
                fechas.add(date);
                System.out.println("MENSAJE: " + resultSet.getString("message"));

                


            }

        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            close();
        }
        
          try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
           
            connect = DriverManager.getConnection("jdbc:mysql://"+this.servers[1][0] +":" + this.servers[1][1] +"/so2?"
                    + "user=root&password=root");

            

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("SELECT * FROM so2.mensaje WHERE idconversacion = " + conv + "");
            
            
            while (resultSet.next()) {

                
                usuarios.add(resultSet.getString("idusuario"));
                messages.add(resultSet.getString("message"));
                types.add(resultSet.getString("idtipo"));
                
                String str_date = resultSet.getString("fecha");
                System.out.println(str_date);
                DateFormat formatter;
                Date date;
                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = formatter.parse(str_date);

                fechas.add(date);
                
                System.out.println("MENSAJE: " + resultSet.getString("message"));

                


            }

        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            close();
        }
         
         System.out.println("clienteLista: "+ clienteLista.size());
         int userinarray = 0;
         //TODO Sacar usuario no conectado
         for (int i = 0; i < clienteLista.size(); i++) {
             if (this.getClient(((MensajeCB) clienteLista.get(i)).getName()).id == user ) {
                    System.out.println("user is = " + i);
                    userinarray = i;
                }
         }
         
         ArrayList <ProxyMessage> proxMens = new ArrayList<ProxyMessage>();
         
         
         System.out.println("Messages size: " +messages.size());
         for (int i = 0; i < messages.size(); i++) {
             ProxyMessage pm = new ProxyMessage();
             
             String id = this.getClientUser(Integer.parseInt( usuarios.get(i).toString() ));
             
             pm.from = this.getClient(id);
             pm.message = messages.get(i).toString();
             pm.type = Integer.parseInt(types.get(i).toString());
             
             if (pm.type == 2) {
                 URL url;
                BufferedImage img;
                try {
                    url = new URL(pm.message );

                    img = ImageIO.read(url);
                    pm.icon = new ImageIcon(img);

                } catch (IOException ex) {
                    Logger.getLogger(MainCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
            
             pm.date = (Date) fechas.get(i);
             
             System.out.println("My messages: "+   messages.get(i).toString());
             //if (((MensajeCB) clienteLista.get(i)).getID() == pms.to.id) {
             //}
             System.out.println("FROM:" + pm.from.name );
             
             proxMens.add(pm);
             
         }
         
         Collections.sort(proxMens, new CustomComparator());
         
         
         for (int i = 0; i < proxMens.size(); i++) {
             System.out.println("enviado" + proxMens.get(i).date);
             ((MensajeCB) clienteLista.get(userinarray)).getMensaje(proxMens.get(i));
             
         }
         
         
        
*/
    }
    
     
     
    /**
     * 
     * @param CONV 
     */
    //@SuppressWarnings("empty-statement")
     public ArrayList <ProxyConversation> getConversation(int userid) throws RemoteException {
         
         MessageLoop ml = new MessageLoop();
         ml.mi = this;
         return ml.getConversation(userid);
         
         /*
        ArrayList  convs = new ArrayList();
        

        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:8889/so2?"
                    + "user=root&password=root");


            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("SELECT c.id, c.nombre "
                    + "FROM so2.conversacion c "
                    + "JOIN so2.usuarioperteneceaconversacion upc "
                    + "ON c.id = upc.idconversacion "
                    + "WHERE upc.idusuario = '" + userid + "'");
            
            
            while (resultSet.next()) {
                
                ProxyConversation pcnv = new ProxyConversation();
                
                pcnv.id = Integer.parseInt(resultSet.getString("id"));
                pcnv.name = resultSet.getString("nombre") + "";
                
                System.out.println("Conv id: " + pcnv.id + " - name: " + pcnv.name);
                
                
                
                
                
                
                
                convs.add(pcnv);
                
                
            }
            
            
            

        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            close();
        }
        
        
        

        return convs;
        * */

    }
    
     
    public ArrayList <ProxyClient> getClientsFromConversation(int convid) throws RemoteException {
        /*
        MessageLoop ml = new MessageLoop();
        ml.mi = this;
        return ml.getClientsFromConversation(convid);
        */
        
        ArrayList  convs = new ArrayList();
        
        //user
         Connection cnt = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            cnt = DriverManager
                    .getConnection("jdbc:mysql://localhost:8889/so2?"
                    + "user=root&password=root");

        
        // Statements allow to issue SQL queries to the database
            stmt = cnt.createStatement();
            String query = "SELECT us.id, us.user, us.apellido "
                    + "FROM so2.conversacion c "
                    + "JOIN so2.usuarioperteneceaconversacion upc "
                    + "ON c.id = upc.idconversacion "
                    + "JOIN so2.usuario us "
                    + "ON upc.idusuario = us.id "
                    + "WHERE c.id = '" + convid + "'";
            
            System.out.println(query);
            // Result set get the result of the SQL query
            rs = stmt.executeQuery(query);
         
            
            while (rs.next()) {
                System.out.println("User: "+rs.getString("user") );
                convs.add((ProxyClient)this.getClient(rs.getString("user") ));
                
            }
        
            } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            close();
        }
         return convs;
    }
     /**
     * 
     * @param DB 
     */
     public boolean login(String user, String password)  {
        boolean ans = false;
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:8889/so2?"
                    + "user=root&password=root");

            System.out.println("CONECTADO!");
            
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("SELECT * FROM so2.usuario WHERE user = '" + user
                    + "' AND password = '" + password +"'");
            ans = writeResultSet(resultSet);


        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            close();
        }
        
        return ans;

    }

    private boolean writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        boolean ans = false;

        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            String id = resultSet.getString("id");
            String nombre = resultSet.getString("nombre");

            System.out.println("Id: " + id);
            //this.Id = Integer.getInteger(id);
            
            System.out.println("Nombre: " + nombre);
            
            //doTest();
            
            ans = true;
        }
        
        return ans;
    }
    
     /**
     * 
     * @param DB 
     */
     public boolean addUserToConv(String user, int conv)  {
         ProxyClient pc= null;
         try {
            pc= this.getClient(user);
        } catch (RemoteException ex) {
            //Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
         if (pc == null) {
             return false;
         }
         
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:8889/so2?"
                    + "user=root&password=root");

            System.out.println("CONECTADO!");
            
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            String query = "INSERT INTO so2.usuarioperteneceaconversacion (idusuario, idconversacion) VALUES ('" + pc.id   + " ', '" + conv   + "')";
            
            System.out.println(query);
            preparedStatement = connect.prepareStatement(query);
            

            preparedStatement.executeUpdate();
            
                    
                  

        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            close();
        }
        
        int userinarray = 0;
        for (int i = 0; i < clienteLista.size(); i++) {
             try {
                 if (( (ProxyClient)(this.getClient( ((MensajeCB) clienteLista.get(i)).getName() ))).id  == pc.id ) {
                        System.out.println("user is = " + i);
                        userinarray = i;
                   }
             } catch (RemoteException ex) {
                 Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
        try {
            ((MensajeCB) clienteLista.get(userinarray)).makehimrefresh();
        } catch (RemoteException ex) {
            Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
             
         
        
        
        try {
            ((MensajeCB)this.clienteLista.get(0)).makehimrefresh();
        } catch (RemoteException ex) {
            Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return true;
     }
    
    /**
     * 
     * @param DB 
     */
     public boolean createConv(int user, String conv)  {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:8889/so2?"
                    + "user=root&password=root");

            System.out.println("CONECTADO!");
            
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            String query = "INSERT INTO so2.conversacion (nombre) VALUES ('" + conv   + "')";
            
            System.out.println(query);
            preparedStatement = connect.prepareStatement(query);
            

            preparedStatement.executeUpdate();
            
                    
                  

        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            close();
        }
        
        //Sd toma el id de la conversación
        
        int idconv = -1;
        
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:8889/so2?"
                    + "user=root&password=root");

            System.out.println("CONECTADO!");
            
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("SELECT * FROM so2.conversacion WHERE nombre = '" + conv +"'");
            
            while(resultSet.next()) {
                idconv = resultSet.getInt("id");
            }


        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            close();
        }
        
        
        //Se guarda el usuario en la conversación
        
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:8889/so2?"
                    + "user=root&password=root");

            System.out.println("CONECTADO!");
            
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            String query = "INSERT INTO so2.usuarioperteneceaconversacion (idusuario, idconversacion) VALUES ('" + user   + "', '" + idconv   + "')";
            
            System.out.println(query);
            preparedStatement = connect.prepareStatement(query);
            

            preparedStatement.executeUpdate();
            
                    
                  

        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            close();
        }
        
        
        return true;

    }
    
    
    
    public void registrar(ProxyClient cliente, String password){
        String id="";
        
        //BufferedImage image = (BufferedImage)((Image) cliente.icon.getImage());
        Image source = cliente.icon.getImage();
        int w = source.getWidth(null);
        int h = source.getHeight(null);
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D)image.getGraphics();
        g2d.drawImage(source, 0, 0, null);
        g2d.dispose();
        
        //TODO: MACBOOK
        
        String url = "/Users/eduardoirias/Sites/RMI-Files/users/" + cliente.user+".png";
        
        try {
            ImageIO.write(image, "png",new File(url));
        } catch (IOException ex) {
            Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Comenzando");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:8889/so2?"
                    + "user=root&password=root");
             System.out.println("Insertando");
             System.out.println("Nombre: "+ cliente.lastName);
             System.out.println("user: "+ cliente.user);
             String url2 = "http://macbook-air-de-eduardo.local:8888/RMI-Files/users/" + cliente.user+".png";

            // Result set get the result of the SQL query
            preparedStatement = connect.prepareStatement("INSERT INTO so2.usuario (nombre,apellido,correo,user,password,photo) VALUE ('"+cliente.name+"','"
                    + cliente.lastName+"','"+cliente.email+"','"+cliente.user+"','"+password+"','"+url2+"')");
            
            preparedStatement.executeUpdate();
            
        }catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            close();
        }
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:8889/so2?"
                    + "user=root&password=root");

            System.out.println("CONECTADO!");
            
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("SELECT * FROM so2.usuario WHERE user = '" + cliente.user+"'");
            
            while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            id = resultSet.getString("id");
            
            
            //doTest();
            
            
        }

        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            close();
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:8889/so2?"
                    + "user=root&password=root");
             System.out.println("Insertando");
             System.out.println("Nombre: "+ cliente.name);
             System.out.println("user: "+ cliente.user);
            // Result set get the result of the SQL query
            preparedStatement = connect.prepareStatement("INSERT INTO so2.usuarioperteneceaconversacion (idusuario,idconversacion) VALUE ('"+id+"','1')");
            
            preparedStatement.executeUpdate();
            
        }catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            close();
        }
        
    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {
        }
    }

    
    
    
    /*REGISTER*/ 
    public int registrarCB(MensajeCB mcb) {
        
        clienteLista.add(mcb);
        
        try {
            mcb.setId(clienteLista.size()-1);
            //mcb.setName("new");
        } catch (RemoteException ex) {
            Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        int id= 0;
        try {
            id= mcb.getID();
        } catch (RemoteException ex) {
            Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    public void deRegistrarCB(MensajeCB mcb, int user) {
        int userinarray = 0;
        for (int i = 0; i < clienteLista.size(); i++) {
            
            try {
                if (this.getClient( ((MensajeCB) clienteLista.get(i)).getName() ).id == user ) {
                   System.out.println("user is = " + i);
                   userinarray = i;
                   i = clienteLista.size(); 
               }
            } catch (RemoteException ex) {
                Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
              
             
         }
        
        
        clienteLista.removeElement(mcb);
        
        for (int i = 0; i < clienteLista.size(); i++) {
            try {
                ((MensajeCB)clienteLista.get(i)).setId(i);
            } catch (RemoteException ex) {
                Logger.getLogger(MensajeImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
