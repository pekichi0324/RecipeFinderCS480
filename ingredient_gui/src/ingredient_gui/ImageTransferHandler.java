/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingredient_gui;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class ImageTransferHandler extends TransferHandler {

    @Override
    protected Transferable createTransferable(JComponent c) {
        JList list = (JList) c;
    	DefaultListModel dm = (DefaultListModel)list.getModel();
        ArrayList<File> files = new ArrayList<File>();
        for (Object obj: list.getSelectedValuesList()) {
            files.add((File)obj);
        }
        return new FileTransferable(files);
    }

    @Override
    public int getSourceActions(JComponent c) {
        return MOVE;
    }
}

class FileTransferable implements Transferable {

    private ArrayList<File> files;

    public FileTransferable(ArrayList<File> files2) {
        this.files = files2;
    }

    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{DataFlavor.javaFileListFlavor};
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(DataFlavor.javaFileListFlavor);
    }

    public Object getTransferData(DataFlavor flavor)
            throws UnsupportedFlavorException, IOException {
        if (!isDataFlavorSupported(flavor)) {
            throw new UnsupportedFlavorException(flavor);
        }
        return files;
    }
}