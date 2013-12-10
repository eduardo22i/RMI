/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiproyecto;

import java.awt.Image;
import java.awt.image.BufferedImage;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.IOException;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;



//DB

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


/**
 *
 * @author Juan Leonardo
 */
public class MiVentanta extends javax.swing.JFrame   {

    /**
     * Creates new form MiVentanta
     */
    private static class MessageLoop extends TimerTask {
         MiVentanta ventana;
         public MessageLoop(MiVentanta ventana) {
              this.ventana = ventana;
         }
        public void run() {
            //ventana.ReadMessages();

        }
    }

    public MiVentanta() {
        
        
        initComponents();
        
        jPanel2.setVisible(false);
        jPanel3.setVisible(false);
        //MiVentanta main = new MiVentanta();
        //
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        buttonSend = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        conversations = new javax.swing.JList();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        JTextFieldNombre = new javax.swing.JTextField();
        JTextFieldCorreo = new javax.swing.JTextField();
        JTextFieldApellido = new javax.swing.JTextField();
        JTextFieldPassword = new javax.swing.JTextField();
        JTextFieldUser1 = new javax.swing.JTextField();
        jButtonImage = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setAutoscrolls(true);

        jScrollPane2.setViewportView(jList1);

        buttonSend.setText("Send");
        buttonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSendActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rmiproyecto/user_default_photo.png"))); // NOI18N

        jScrollPane1.setViewportView(jTextPane1);

        conversations.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        conversations.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        conversations.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                conversationsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(conversations);

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel4.setText("My name");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rmiproyecto/user_default_photo.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("jLabel6");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel5)))
                .addGap(6, 6, 6))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap(715, Short.MAX_VALUE)
                    .addComponent(buttonSend)
                    .addGap(20, 20, 20)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6))
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap(485, Short.MAX_VALUE)
                    .addComponent(buttonSend)
                    .addContainerGap()))
        );

        jPanel2.setBounds(0, 0, 810, 520);
        jLayeredPane1.add(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        JTextFieldNombre.setText("Nombre");

        JTextFieldCorreo.setText("Correo");

        JTextFieldApellido.setText("Apellido");

        JTextFieldPassword.setText("Password");

        JTextFieldUser1.setText("User");

        jButtonImage.setText("Image");

        jButton3.setText("Sign Up");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JTextFieldNombre)
                            .addComponent(JTextFieldCorreo)
                            .addComponent(jButtonImage, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                        .addGap(113, 113, 113)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JTextFieldApellido)
                            .addComponent(JTextFieldPassword)
                            .addComponent(JTextFieldUser1, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))))
                .addContainerGap(228, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(JTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(JTextFieldCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(JTextFieldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(JTextFieldUser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTextFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonImage))
                .addGap(65, 65, 65)
                .addComponent(jButton3)
                .addContainerGap(160, Short.MAX_VALUE))
        );

        jPanel3.setBounds(0, 0, 810, 530);
        jLayeredPane1.add(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setText("Log in");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Username:");

        jLabel3.setText("Password");

        jButton2.setText("Sign up");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton1))
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addContainerGap(209, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(200, Short.MAX_VALUE))
        );

        jPanel1.setBounds(0, 0, 810, 520);
        jLayeredPane1.add(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    private void doTest(){
        try {
             /*
            // fire to localhost port 1099
            Registry myRegistry = LocateRegistry.getRegistry("macbook-air-de-eduardo.local", 1099);
             
            
            Scanner scn = new Scanner(System.in);
            String m = ""; 
            // search for myMessage service15
            
            impl = (Mensaje) myRegistry.lookup("miMensaje");
            mCB =  new CB();
            System.out.print("Su nombre: ");
            m = jTextField1.getText();
            mCB.setName(m);
            // call server's method         
            
            impl.registrarCB(mCB);
            */
               /*        
            while (true) {
                System.out.print("Mensaje: ");
                m = scn.nextLine();
                impl.enviarMensaje(((CB)mCB).getID(), mCB.getName()  , m);
            }    
            * */
            
            
            
            //Change Layer
            jPanel1.setVisible(false);
            jPanel2.setVisible(true);
            
            jList1.setAutoscrolls(true);
            jList1.setCellRenderer(new MyCellRenderer());
            
            //User info
            ProxyClient pc = impl.getClient(mCB.getName() );
            
            
            
            
            conversationsArray = impl.getConversation(pc.id);
            
            
            
            final ArrayList al = new ArrayList();
            
            for (int i = 0; i < conversationsArray.size(); i++) {
                al.add(conversationsArray.get(i).name);
            }
            
            conversations.setModel(new javax.swing.AbstractListModel() {

                public int getSize() {
                    return al.size();
                }

                public Object getElementAt(int i) {
                    return al.get(i);
                }
            });
            
            
            
            try {
                conversationsArray.get(0).subscribers = impl.getClientsFromConversation( conversationsArray.get(0).id);
            } catch (RemoteException ex) {
                Logger.getLogger(MiVentanta.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("The user: " + ((ProxyClient) conversationsArray.get(0).subscribers.get(0)).user );
            
            this.Id = pc.id;
            this.mCB.setId(Id);
            jLabel4.setText(pc.name + " " + pc.lastName);
            jLabel6.setText(pc.user);
            
            ImageIcon icon =  pc.icon;
 
            //icon = new javax.swing.ImageIcon(getClass().getResource("http://macbook-air-de-eduardo.local:8888/RPSOL/images/psrsl-04.png"));
            
            Image image = icon.getImage();
            Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            icon = new ImageIcon(newimg); 
            jLabel1.setIcon(icon);
            
            
            
            Timer timer = new Timer();
            MessageLoop msl = new MessageLoop(this);
            //TODO
            //timer.schedule(msl,0  ,1000);
            
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }           
    
    private void buttonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSendActionPerformed
        // TODO add your handling code here:
        
        if (!jTextPane1.getText().equals("")) {
            /*
            final ProxyMessage[] strings1 = new ProxyMessage[jList1.getModel().getSize() + 1];
            for (int i = 0; i < jList1.getModel().getSize(); i++) {
                System.out.println(jList1.getModel().getElementAt(i).toString());
                ProxyMessage sti = (ProxyMessage)jList1.getModel().getElementAt(i);
                stringssub.add(sti);
                strings1[i] = sti;
            }
            //strings1[jList1.getModel().getSize()] = jTextPane1.getText();
            //listModel.addElement("new item");

            jList1.setModel(new javax.swing.AbstractListModel() {
                String[] strings = strings1;

                public int getSize() {
                    return strings.length;
                }

                public Object getElementAt(int i) {
                    return strings[i];
                }
            });

            jList1.setAutoscrolls(true);
            jList1.setCellRenderer(new MyCellRenderer());
            */


            System.out.print("Mensaje: ");
            String m = jTextPane1.getText();
            /*
                try {
                    impl.enviarMensaje(((CB) mCB).getID(), mCB.getName(), m);
                } catch (RemoteException ex) {
                    Logger.getLogger(MiVentanta.class.getName()).log(Level.SEVERE, null, ex);
                }
                * */
            try {
                
                mc.sendMessage((CB) mCB, mCB.getName(), m,  conversationsArray.get(actualconv ).subscribers );
            } catch (RemoteException ex) {
                Logger.getLogger(MiVentanta.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            jTextPane1.setText("");
            
            
        }
    }//GEN-LAST:event_buttonSendActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        // fire to localhost port 1099
       Registry myRegistry;
        try {
            myRegistry = LocateRegistry.getRegistry("macbook-air-de-eduardo.local", 1099);
            Scanner scn = new Scanner(System.in);
            String m = ""; 
            // search for myMessage service15
           try {
               impl = (Mensaje) myRegistry.lookup("miMensaje");
           } catch (NotBoundException ex) {
               Logger.getLogger(MiVentanta.class.getName()).log(Level.SEVERE, null, ex);
           } catch (AccessException ex) {
               Logger.getLogger(MiVentanta.class.getName()).log(Level.SEVERE, null, ex);
           }
           
            //mCB =  new CB();
            System.out.print("Su nombre: ");
            m = jTextField1.getText();
            mCB.setName(m);
        
            
        } catch (RemoteException ex) {
            Logger.getLogger(MiVentanta.class.getName()).log(Level.SEVERE, null, ex);
        }
             
            
            
        try {
            if (impl.login( this.jTextField1.getText(), this.jTextField2.getText() ) ) {
                this.doTest();
            } else {
                JOptionPane.showMessageDialog(this,  "Incorrect", "Incorrect", JOptionPane.DEFAULT_OPTION); ;
            }
        } catch (RemoteException ex) {
            Logger.getLogger(MiVentanta.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //Change Layer
            jPanel1.setVisible(false);
            jPanel2.setVisible(false);
            jPanel3.setVisible(true);
            
            
    }//GEN-LAST:event_jButton2ActionPerformed

    private void conversationsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_conversationsMouseClicked
        // TODO add your handling code here:

        if (!conversations.getCellBounds(conversations.getSelectedIndex(), conversations.getSelectedIndex()).contains(evt.getPoint())) {
            conversations.removeSelectionInterval(conversations.getSelectedIndex(), conversations.getSelectedIndex());
        }
        
        
        
        if (conversations.getSelectedIndex() >= 0){
            System.out.println("Selected: " + conversations.getSelectedIndex()  + " length:" + conversationsArray.size());
            ProxyConversation pcnv =(ProxyConversation) conversationsArray.get(conversations.getSelectedIndex() );
        
            System.out.println("conv id: "+ pcnv.name );
            
            actualconv = conversations.getSelectedIndex() ;
            try {
                conversationsArray.get(conversations.getSelectedIndex() ).subscribers = impl.getClientsFromConversation(pcnv.id);
            } catch (RemoteException ex) {
                Logger.getLogger(MiVentanta.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //System.out.println("Primero " +   conversationsArray.get(conversations.getSelectedIndex() ).subscribers.get(0).user);
        }
    }//GEN-LAST:event_conversationsMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //MARTIN AQUÍ VA TU CÓDIGO
        
    }//GEN-LAST:event_jButton3ActionPerformed

    
    public void ReadMessages (String name, String mensaje, ProxyMessage pm) {
        ProxyMessage text = (ProxyMessage) pm;
        
        /*
        try {
            // TODO add your handling code here:
            text =  impl.getMessage(Id);
            
        } catch (RemoteException ex) {
            Logger.getLogger(MiVentanta.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        System.out.println(text.getMessage());
        
        if (!text.getMessage().equals("") && !text.getMessage().equals(LastId)) {
            LastId = text.getMessage();
            
            final ProxyMessage[] strings1 = new ProxyMessage[jList1.getModel().getSize() + 1];
            for (int i = 0; i < jList1.getModel().getSize(); i++) {
                System.out.println(jList1.getModel().getElementAt(i));
                ProxyMessage sti = (ProxyMessage)jList1.getModel().getElementAt(i);
                stringssub.add(sti);
                strings1[i] = sti;
            }
            strings1[jList1.getModel().getSize()] = text;
            //listModel.addElement("new item");

            jList1.setModel(new javax.swing.AbstractListModel() {
                ProxyMessage[] strings = strings1;

                public int getSize() {
                    return strings.length;
                }

                public Object getElementAt(int i) {
                    return strings[i];
                }
            });

            jList1.setAutoscrolls(true);
            MyCellRenderer cmr = new MyCellRenderer();
            cmr.senderId = this.Id;
            
            jList1.setCellRenderer(cmr);
        }
    }
    /**
     * @param args the command line arguments
     */
    
    
    
    
    

    
    /**
     * 
     * @param MensajeDB Implementation 
     */
    
    public String getMensaje(String name, String mensaje, ProxyMessage pm)  {
        System.out.println("\n"+ name + " dice: " + mensaje);
        //JOptionPane.showConfirmDialog(this, "\n"+ name + " dice: " + mensaje);
        ReadMessages ( name,  mensaje, pm) ;
        return name + " dice: " + mensaje;
    }
    
    public void setId(int id) {
        this.Id = id;
        System.out.println("Mi id:" + id);
    }
    public int getID() {
        return this.Id;
    }
    public void setName(String name) {
        this.name= name ;
    } 
    
    public String getName() {
        return this.name;
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MiVentanta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MiVentanta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MiVentanta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MiVentanta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MiVentanta().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JTextFieldApellido;
    private javax.swing.JTextField JTextFieldCorreo;
    private javax.swing.JTextField JTextFieldNombre;
    private javax.swing.JTextField JTextFieldPassword;
    private javax.swing.JTextField JTextFieldUser1;
    private javax.swing.JButton buttonSend;
    private javax.swing.JList conversations;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonImage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
    ArrayList <ProxyMessage> stringssub = new ArrayList <ProxyMessage> () ;
    Mensaje impl;
    MensajeCB mCB;
    int Id;
    String LastId;
    String name;
    MainCliente mc;
    
    
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    public ArrayList <ProxyConversation> conversationsArray = new ArrayList <ProxyConversation>();
    int actualconv = 0;
    
}
