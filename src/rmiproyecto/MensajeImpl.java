/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiproyecto;

/**
 *
 * @author Juan Leonardo
 */

import java.awt.image.BufferedImage;
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
import java.util.ArrayList;
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
    
    public MensajeImpl() throws RemoteException {
        /*
        v.add("Juan Perez");
        v.add("Ana Perez"); 
        v.add("Maria Perez");
        v.add("Carlos Perez");
        * */
    }
    public void enviarMensaje(int id, String name, String message, ProxyMessage pms) throws RemoteException {
        //TODO: save message in conversation
        
        
        int cont = 0;
        for (int i = 0; i < clienteLista.size(); i++) {
            //TODO
            try {

                if ((MensajeCB) clienteLista.get(i) != null) {
                    if (((MensajeCB) clienteLista.get(i)).getID() == pms.to.id) {

                        System.out.println("sender id: " + id);
                        ProxyMessage pm = (ProxyMessage) pms;
                        
                        System.out.println("id con: " + pms.conversation.id);
                        
                        
                        ProxyClient pc = this.getClient(name);

                        pm.from = pc;
                        
                        if (cont == 0) {
                            saveMessage(pm);
                        }
                        cont++;
                        v.add(pm);
                        System.out.println("o");
                        ((MensajeCB) clienteLista.get(i)).getMensaje(pm);


                    }
                } else {
                    clienteLista.remove(i);
                    i--;
                }
            } catch (Exception e) {
            }

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
        ProxyClient pc = new ProxyClient();


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
            String query = "INSERT INTO so2.mensajes (idconversacion, idtipo, idusuario, fecha, message) VALUES (" + pm.conversation.id + "," + 1 + "," + pm.from.id + ", NOW(), '" + pm.message + "')";
            
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
        
        
        System.out.println("The return of the jedi (client) " + pc.name);




    }
    
     
    /**
     * 
     * @param Client 
     */
    //@SuppressWarnings("empty-statement")
     public ProxyClient getClient(String user) throws RemoteException {
        ProxyClient pc = new ProxyClient();


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
        
        ArrayList usuarios = new ArrayList();
        ArrayList messages = new ArrayList();


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
            resultSet = statement.executeQuery("SELECT * FROM so2.mensajes WHERE idconversacion = " + conv + "");
            
            
            while (resultSet.next()) {


                ProxyMessage pm = new ProxyMessage();
                
                usuarios.add(resultSet.getString("idusuario"));
                messages.add(resultSet.getString("message"));
                
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
        

         for (int i = 0; i < messages.size(); i++) {
             ProxyMessage pm = new ProxyMessage();
             
             String id = this.getClientUser(Integer.parseInt( usuarios.get(i).toString() ));
             
             pm.from = this.getClient(id);
             pm.message = messages.get(i).toString();
             
             System.out.println("My messages: "+   messages.get(i).toString());
             //if (((MensajeCB) clienteLista.get(i)).getID() == pms.to.id) {
             //}
             System.out.println("FROM:" + pm.from.name );
             
             ((MensajeCB) clienteLista.get(user)).getMensaje(pm);
         }
        

    }
    
     
     
    /**
     * 
     * @param CONV 
     */
    //@SuppressWarnings("empty-statement")
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
    
     
    public ArrayList <ProxyClient> getClientsFromConversation(int convid) throws RemoteException {
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
    public void registrar(String name, String apellido, String email, String user, String password, String photo){
        String id="";
        System.out.println("Comenzando");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:8889/so2?"
                    + "user=root&password=root");
             System.out.println("Insertando");
             System.out.println("Nombre: "+ name);
             System.out.println("user: "+ user);
            // Result set get the result of the SQL query
            preparedStatement = connect.prepareStatement("INSERT INTO so2.usuario (nombre,apellido,correo,user,password,photo) VALUE ('"+name+"','"
                    + apellido+"','"+email+"','"+user+"','"+password+"','"+photo+"')");
            
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
            resultSet = statement.executeQuery("SELECT * FROM so2.usuario WHERE user = '" + user+"'");
            
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
             System.out.println("Nombre: "+ name);
             System.out.println("user: "+ user);
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
    public void deRegistrarCB(MensajeCB mcb) {
        clienteLista.removeElement(mcb);
    }
}
