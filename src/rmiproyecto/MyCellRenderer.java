/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiproyecto;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author eduardoirias
 */
public class MyCellRenderer extends JLabel implements ListCellRenderer {
     final static ImageIcon longIcon = new ImageIcon("long.gif");
     final static ImageIcon shortIcon = new ImageIcon("short.gif");

     // This is the only method defined by ListCellRenderer.
     // We just reconfigure the JLabel each time we're called.
     public int senderId = 0;
     JLabel subtitle = new JLabel();
     public Component getListCellRendererComponent(
       JList list,           // the list
       Object value,            // value to display
       int index,               // cell index
       boolean isSelected,      // is the cell selected
       boolean cellHasFocus)    // does the cell have focus
     {
         subtitle.setText("Hola!!! ");
         subtitle.setBounds(this.getBounds() );
         //this.get
         String s = value.toString();
         setText(s);
         this.setToolTipText("name");
          ImageIcon icon;
         if (index % 2 == 0) {
             icon = (new javax.swing.ImageIcon(getClass().getResource("/rmiproyecto/icons-10.png")));
         } else {
             icon = (new javax.swing.ImageIcon(getClass().getResource("/rmiproyecto/user_default_photo.png")));
         }
         
         setIcon(icon); 
         if (isSelected) {
             setBackground(list.getSelectionBackground());
             setForeground(list.getSelectionForeground());
         } else {
             setBackground(list.getBackground());
             setForeground(list.getForeground());
         }
         setEnabled(list.isEnabled());
         setFont(list.getFont());
         setOpaque(false);
         return this;
     }
}
