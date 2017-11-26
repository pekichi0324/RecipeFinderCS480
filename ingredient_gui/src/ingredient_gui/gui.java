/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingredient_gui;

import java.awt.Button;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassResult;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImage;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Evan
 */
public class gui extends javax.swing.JFrame {
	private myImage[] imgNames = new ImageList().getImageNames();
        private ListSelectionModel listSelectionModel;
	private VisualRecognition service = new VisualRecognition(
			  VisualRecognition.VERSION_DATE_2016_05_20
			);
	final int norm = NORMAL;
	final int imageHeight = 800;
	final int imageWidth = 525;
	final Container container = this;

	//Thread watson = null;
    /**
     * Creates new form gui
     */
    public gui() {
        initComponents();
        this.setLocationRelativeTo(null);
        start();
    }
  
    public void start() {
    	
    	/* OLD KEY */
//    	service.setApiKey("af16cab33a7b47433d5ce63aace1d08f379afa2a");
    	
    	service.setApiKey("f9ef8b5896c3db5e0a4a6caa297e2a011a825bab");
        
        // list selection method init
        listSelectionModel = imageList.getSelectionModel();
        listSelectionModel.addListSelectionListener(
                            new ListSelectionListener() {
                                public void valueChanged(ListSelectionEvent e) {
                                    int i = imageList.getSelectedIndex();
                                    img.setIcon(imgNames[i].scale(imageHeight, imageWidth, norm));
                                }
                            });
        listSelectionModel.setSelectionMode(
                        ListSelectionModel.SINGLE_SELECTION); // only single selection
    	
    	System.out.println("imageHeight: " + imageHeight);
        ImageIcon icon = null;
        icon = imgNames[0].scale(imageHeight, imageWidth, norm);
		if(icon != null) {
			img.setIcon(icon);
		}else {
			System.out.println("was null");
		}
		
		imageList.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        imageList.setCellRenderer(new ImgRenderer());
        imageList.setVisibleRowCount(1);
    	imageList.setListData(imgNames);
    	imageSelect.setViewportView(imageList);
    	imageSelect.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
    	Dimension d = new Dimension(800,525);
    	img.setSize(d);
    	img.setMinimumSize(d);
    	img.setMaximumSize(d);
    }
    public static boolean isInternetReachable()
    {
        try {
            //make a URL to a known source
            URL url = new URL("http://www.google.com");

            //open a connection to that source
            HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();

            //trying to retrieve data from the source. If there
            //is no connection, this line will fail
            Object objData = urlConnect.getContent();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
        	javax.swing.JOptionPane.showMessageDialog(null,
   				 "No Internet Connect Detected!", 
   				 "Alert", 
   				 javax.swing.JOptionPane.ERROR);
            return false;
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
        	javax.swing.JOptionPane.showMessageDialog(null,
   				 "No Internet Connect Detected!", 
   				 "Alert", 
   				 javax.swing.JOptionPane.ERROR);
            return false;
        }
        return true;
    }
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = ImgRenderer.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
                return null;
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        testerFrame = new javax.swing.JFrame();
        selectedImage1 = new javax.swing.JPanel();
        img1 = new javax.swing.JLabel();
        imageSelect1 = new javax.swing.JScrollPane();
        imageList1 = new javax.swing.JList();
        setImage1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        userTypeDialog = new javax.swing.JDialog();
        userButton = new javax.swing.JButton();
        testerButton = new javax.swing.JButton();
        chooseLabel = new javax.swing.JLabel();
        recipeDialog = new javax.swing.JDialog();
        imgRecipe = new javax.swing.JPanel();
        resultPromptPane = new javax.swing.JPanel();
        resultLabel = new javax.swing.JLabel();
        noButton = new javax.swing.JButton();
        yesButton = new javax.swing.JButton();
        recipeScrollPane = new javax.swing.JScrollPane();
        recipeText = new javax.swing.JTextArea();
        testerPane = new javax.swing.JFrame();
        selectedImage2 = new javax.swing.JPanel();
        img2 = new javax.swing.JLabel();
        imageSelect2 = new javax.swing.JScrollPane();
        imageList2 = new javax.swing.JList();
        setImage2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        selectedImage = new javax.swing.JPanel();
        img = new javax.swing.JLabel();
        imageSelect = new javax.swing.JScrollPane();
        imageList = new javax.swing.JList();
        setImage = new javax.swing.JButton();

        testerFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        img1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/donut platter.jpg"))); // NOI18N
        img1.setText("jLabel1");

        javax.swing.GroupLayout selectedImage1Layout = new javax.swing.GroupLayout(selectedImage1);
        selectedImage1.setLayout(selectedImage1Layout);
        selectedImage1Layout.setHorizontalGroup(
            selectedImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(img1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        selectedImage1Layout.setVerticalGroup(
            selectedImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(img1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        imageList1.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        imageSelect1.setViewportView(imageList1);

        setImage1.setText("Choose");
        setImage1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setImage1ActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout testerFrameLayout = new javax.swing.GroupLayout(testerFrame.getContentPane());
        testerFrame.getContentPane().setLayout(testerFrameLayout);
        testerFrameLayout.setHorizontalGroup(
            testerFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(testerFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(testerFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(testerFrameLayout.createSequentialGroup()
                        .addComponent(selectedImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, testerFrameLayout.createSequentialGroup()
                        .addComponent(imageSelect1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(setImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        testerFrameLayout.setVerticalGroup(
            testerFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(testerFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(testerFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(selectedImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(testerFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageSelect1, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(setImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        userTypeDialog.setAlwaysOnTop(true);
        userTypeDialog.setName(""); // NOI18N

        userButton.setText("User");
        userButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userButtonActionPerformed(evt);
            }
        });

        testerButton.setText("Tester");
        testerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testerButtonActionPerformed(evt);
            }
        });

        chooseLabel.setText("Choose the user type:");

        javax.swing.GroupLayout userTypeDialogLayout = new javax.swing.GroupLayout(userTypeDialog.getContentPane());
        userTypeDialog.getContentPane().setLayout(userTypeDialogLayout);
        userTypeDialogLayout.setHorizontalGroup(
            userTypeDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userTypeDialogLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(userTypeDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userTypeDialogLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(chooseLabel))
                    .addGroup(userTypeDialogLayout.createSequentialGroup()
                        .addComponent(userButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(testerButton)))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        userTypeDialogLayout.setVerticalGroup(
            userTypeDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userTypeDialogLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(chooseLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(userTypeDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(testerButton)
                    .addComponent(userButton))
                .addGap(31, 31, 31))
        );

        recipeDialog.setName("recipeDialog"); // NOI18N

        imgRecipe.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout imgRecipeLayout = new javax.swing.GroupLayout(imgRecipe);
        imgRecipe.setLayout(imgRecipeLayout);
        imgRecipeLayout.setHorizontalGroup(
            imgRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );
        imgRecipeLayout.setVerticalGroup(
            imgRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        resultPromptPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        resultLabel.setText("Is this result accurate?");

        noButton.setText("No");

        yesButton.setText("Yes");
        yesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yesButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout resultPromptPaneLayout = new javax.swing.GroupLayout(resultPromptPane);
        resultPromptPane.setLayout(resultPromptPaneLayout);
        resultPromptPaneLayout.setHorizontalGroup(
            resultPromptPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultPromptPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resultPromptPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(resultPromptPaneLayout.createSequentialGroup()
                        .addComponent(yesButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(noButton))
                    .addGroup(resultPromptPaneLayout.createSequentialGroup()
                        .addComponent(resultLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        resultPromptPaneLayout.setVerticalGroup(
            resultPromptPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultPromptPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(resultLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(resultPromptPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yesButton)
                    .addComponent(noButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        recipeText.setColumns(20);
        recipeText.setRows(5);
        recipeScrollPane.setViewportView(recipeText);

        javax.swing.GroupLayout recipeDialogLayout = new javax.swing.GroupLayout(recipeDialog.getContentPane());
        recipeDialog.getContentPane().setLayout(recipeDialogLayout);
        recipeDialogLayout.setHorizontalGroup(
            recipeDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, recipeDialogLayout.createSequentialGroup()
                .addComponent(imgRecipe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(recipeDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(recipeDialogLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 407, Short.MAX_VALUE)
                        .addComponent(resultPromptPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(recipeDialogLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(recipeScrollPane))))
        );
        recipeDialogLayout.setVerticalGroup(
            recipeDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgRecipe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(recipeDialogLayout.createSequentialGroup()
                .addComponent(recipeScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultPromptPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        testerPane.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        img2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/donut platter.jpg"))); // NOI18N
        img2.setText("jLabel1");

        javax.swing.GroupLayout selectedImage2Layout = new javax.swing.GroupLayout(selectedImage2);
        selectedImage2.setLayout(selectedImage2Layout);
        selectedImage2Layout.setHorizontalGroup(
            selectedImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(img2, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        selectedImage2Layout.setVerticalGroup(
            selectedImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(img2, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        imageList2.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        imageSelect2.setViewportView(imageList2);

        setImage2.setText("Choose");
        setImage2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setImage2ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout testerPaneLayout = new javax.swing.GroupLayout(testerPane.getContentPane());
        testerPane.getContentPane().setLayout(testerPaneLayout);
        testerPaneLayout.setHorizontalGroup(
            testerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(testerPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(testerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(testerPaneLayout.createSequentialGroup()
                        .addComponent(imageSelect2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(setImage2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(testerPaneLayout.createSequentialGroup()
                        .addComponent(selectedImage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)))
                .addContainerGap())
        );
        testerPaneLayout.setVerticalGroup(
            testerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(testerPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(testerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(selectedImage2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(testerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageSelect2, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(setImage2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/donut platter.jpg"))); // NOI18N
        img.setText("jLabel1");

        javax.swing.GroupLayout selectedImageLayout = new javax.swing.GroupLayout(selectedImage);
        selectedImage.setLayout(selectedImageLayout);
        selectedImageLayout.setHorizontalGroup(
            selectedImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        selectedImageLayout.setVerticalGroup(
            selectedImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        imageList.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        imageSelect.setViewportView(imageList);

        setImage.setText("Choose");
        setImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imageSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(setImage, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(selectedImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectedImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageSelect, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(setImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setImageActionPerformed
        //String returnedInfo = "PUT WATSON INFO HERE";
    	final int i = imageList.getSelectedIndex();
    	final JButton b = (JButton) evt.getSource();
    	
        Runnable watsonThread = new Runnable() {
        	File f = null;
        	int x = 0;
			public void run() {
				if(i < 0) {
		    		f = new File(imgNames[0].getPath());
		    	}
		    	else {
		    		f = new File(imgNames[i].getPath());
		    	}
			    try{
			    	InputStream imagesStream = new FileInputStream(f);
			    	//can add parameters here too
			    	if (i < 0)
			    		x = 0;
			    	else
			    		x = i;
			    	if(isInternetReachable()) {
				    	ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
				    	  .imagesFile(imagesStream)
				    	  .imagesFilename(imgNames[x].getImageName())
				    	  .build();
				    	ClassifiedImages result = service.classify(classifyOptions).execute();
				    	
				    	/* KEYWORD DISPLAY */
				    	
				    	// What image is being processed
				    	List<ClassifiedImage> resultList = result.getImages();
				    	System.out.println(resultList);
				    	System.out.println("---");
//				    	String[] badWords = {"dish", "nutrition", "food"};

				    	System.out.println(resultList.get(0).getClassifiers().get(0).getClasses().size());
				    	
				    	// List of objects of keywords/scores
				    	List<ClassResult> classResult = resultList.get(0).getClassifiers().get(0).getClasses();
				    	for (int z = 0; z < resultList.get(0).getClassifiers().get(0).getClasses().size(); z++) {
//				    		if (classResult.get(z).getClassName().contains(badWords[0]) || classResult.get(z).getClassName().contains(badWords[0])
//				    					|| classResult.get(z).getClassName().contains(badWords[0])) {
//				    			classResult.remove(classResult.get(z));
//				    		}
				    		System.out.println(classResult.get(z).getClassName());
				    		System.out.println(classResult.get(z).getScore());
				    	}
				    	
				    	
				    	// test to open the receipe website 
				    	// Need to replace "" with %20 since url does not take ""
				    	String searchTerm = classResult.get(0).getClassName().replaceAll(" ","%20");
			    		String url_open ="http://allrecipes.com/search/results/?wt=" + searchTerm + "&sort=re";
			    		java.awt.Desktop.getDesktop().browse(java.net.URI.create(url_open));
				    	/* ****************** */
		
						container.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						b.setEnabled(true);
			    	}else {
			    		container.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			    		b.setEnabled(true);
			    	}
					Thread.sleep(1000);
			    }catch(Exception e){
			    }   
			}
		};
		Thread watson = new Thread(watsonThread);
		watson.start();
		if(i < 0) {
			img.setIcon(imgNames[0].scale(imageHeight, imageWidth, norm));
    		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    		b.setEnabled(false);
    	}
    	else {
    		img.setIcon(imgNames[i].scale(imageHeight, imageWidth, norm));
    		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    		b.setEnabled(false);
    	}
        
    }//GEN-LAST:event_setImageActionPerformed

    private void userButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userButtonActionPerformed

    private void testerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testerButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_testerButtonActionPerformed

    private void yesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yesButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yesButtonActionPerformed

    private void setImage1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setImage1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_setImage1ActionPerformed

    private void setImage2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setImage2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_setImage2ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gui().setVisible(true);
            }
        });
    }
    
    public static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
        BufferedImage dbi = null;
        if(sbi != null) {
            dbi = new BufferedImage(dWidth, dHeight, imageType);
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
            g.drawRenderedImage(sbi, at);
        }
        return dbi;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel chooseLabel;
    private javax.swing.JList imageList;
    private javax.swing.JList imageList1;
    private javax.swing.JList imageList2;
    private javax.swing.JScrollPane imageSelect;
    private javax.swing.JScrollPane imageSelect1;
    private javax.swing.JScrollPane imageSelect2;
    private javax.swing.JLabel img;
    private javax.swing.JLabel img1;
    private javax.swing.JLabel img2;
    private javax.swing.JPanel imgRecipe;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JButton noButton;
    private javax.swing.JDialog recipeDialog;
    private javax.swing.JScrollPane recipeScrollPane;
    private javax.swing.JTextArea recipeText;
    private javax.swing.JLabel resultLabel;
    private javax.swing.JPanel resultPromptPane;
    private javax.swing.JPanel selectedImage;
    private javax.swing.JPanel selectedImage1;
    private javax.swing.JPanel selectedImage2;
    private javax.swing.JButton setImage;
    private javax.swing.JButton setImage1;
    private javax.swing.JButton setImage2;
    private javax.swing.JButton testerButton;
    private javax.swing.JFrame testerFrame;
    private javax.swing.JFrame testerPane;
    private javax.swing.JButton userButton;
    private javax.swing.JDialog userTypeDialog;
    private javax.swing.JButton yesButton;
    // End of variables declaration//GEN-END:variables
}
