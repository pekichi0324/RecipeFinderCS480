/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingredient_gui;

import java.awt.Color;
import java.awt.Component;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Evan
 */
public class ImgRenderer extends JLabel implements ListCellRenderer {
	private static final Color HIGHLIGHT_COLOR = new Color(128, 128, 128);
	
    public ImgRenderer() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
   }

    public Component getListCellRendererComponent(JList list, 
            Object value, 
            int index, 
            boolean isSelected, 
            boolean cellHasFocus) {
    	myImage image = (myImage) value;
    	setIcon(image.scale(100,100,0));
    	
    	if (isSelected) {
    	      setBackground(HIGHLIGHT_COLOR);
    	      setForeground(Color.white);
    	    } else {
    	      setBackground(Color.white);
    	      setForeground(Color.black);
    	    }
        //int selectedIndex = ((Integer)value).intValue();
        
//        ImageIcon icon = new ImageIcon(imageNames[selectedIndex]);
//        String image = imageNames[selectedIndex];
        return this;
    }
    
}
