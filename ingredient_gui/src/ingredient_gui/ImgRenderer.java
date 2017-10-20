/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingredient_gui;

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
    private static String[] imageNames = getImages();
    /**
     * Generate list of all files in "img" folder
     * @return String[] of all file names
     */
    private static String[] getImages() {
        File folder = new File("img");
    	File[] listOfFiles = folder.listFiles();
        String[] ret = new String[listOfFiles.length];
        
        for (int i = 0; i < listOfFiles.length; i++) {
            ret[i] = listOfFiles[i].getName();
        }
        return ret;
    }
    public String[] getImageNames() { return imageNames; }
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
    	
        int selectedIndex = ((Integer)value).intValue();
        
        ImageIcon icon = new ImageIcon(imageNames[selectedIndex]);
        String image = imageNames[selectedIndex];
        setIcon(icon);

        return this;
    }
    
}
