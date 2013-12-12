/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiproyecto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

/**
 *
 * @author eduardoirias
 */
public class MyCellRenderer extends JPanel implements ListCellRenderer {

    final static ImageIcon longIcon = new ImageIcon("long.gif");
    final static ImageIcon shortIcon = new ImageIcon("short.gif");
    // This is the only method defined by ListCellRenderer.
    // We just reconfigure the JLabel each time we're called.
    public int senderId = 0;
    JLabel title = new JLabel("", JLabel.LEFT);
    JLabel sender = new JLabel("", JLabel.RIGHT);
    JLabel subtitle = new JLabel("", JLabel.LEFT);
    private Container container;

    public Component getListCellRendererComponent(
            JList list, // the list
            Object value, // value to display
            int index, // cell index
            boolean isSelected, // is the cell selected
            boolean cellHasFocus) // does the cell have focus
    {
        //container = this.getContentPane();
        //container.setLayout(null
        ProxyMessage messageinfo = (ProxyMessage) value;
        
        setLayout(new GridLayout(2, 1, 15, 0));

        /*
         JPanel painel3 = new JPanel();
         painel3.setBackground(Color.white);
         painel3.setLayout(null);
         painel3.setBounds(0, 0, this.getWidth(), this.getHeight());

         this.add(painel3);
         */

        Font font = title.getFont();
        // same font but bold
        Font newLabelFont = new Font(title.getFont().getName(), Font.BOLD, title.getFont().getSize());
        title.setFont(newLabelFont);
        title.setText(messageinfo.from.name +" dice: ");
        
        //this.get
        
        //String s = value.toString();
        
        title.setBounds(0, 0, 400, 60);
        //subtitle.setBounds(title.getBounds().x, title.getBounds().y + 50, 1000, 80);

        //painel3.add(title);

        
        sender.setText("R" + messageinfo.type );
                
        this.add(title);
        this.add(sender);
        this.add(subtitle);
        //this.setToolTipText("name");

        

        //System.out.println("id: "+messageinfo.getId());
        ImageIcon icon;
        if (messageinfo.from.icon == null) {
             icon = (new javax.swing.ImageIcon(getClass().getResource("/rmiproyecto/user_default_photo.png")));
        } else {
            icon = messageinfo.from.icon ;
        } 

        //this.add(img);
        Image image = icon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        icon = new ImageIcon(newimg);  // transform it back

        title.setIcon(icon);
        
        ImageIcon photo = null;
        if (messageinfo.type == 1) {
            subtitle.setText(messageinfo.getMessage());
            subtitle.setIcon(null);
        } else  if (messageinfo.type == 2) {
            subtitle.setText("");
            photo = messageinfo.icon ;
            
            Image image2 = photo.getImage(); // transform it 
            Image newimg2 = image2.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            photo = new ImageIcon(newimg2);  // 
        
            subtitle.setIcon(photo);
        }
        
       
        
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        //sender.setBounds(0, 0, 20, 20);
        setEnabled(list.isEnabled());
        setFont(list.getFont());
        setOpaque(false);
        return this;
    }

    
}
