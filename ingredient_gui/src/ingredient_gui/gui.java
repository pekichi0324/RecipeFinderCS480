/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ingredient_gui;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassResult;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImage;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import javax.swing.DropMode;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class gui extends JFrame implements DropTargetListener {
	private ArrayList<myImage> imgNames = new ArrayList<myImage>();
        private ListSelectionModel listSelectionModel;
	private VisualRecognition service = new VisualRecognition(
			  VisualRecognition.VERSION_DATE_2016_05_20
			);
        private JLabel titleLabel;
	final int norm = NORMAL;
	final int imageHeight = 800;
	final int imageWidth = 525;
	final Container container = this;
	static String [] classifierList = new String[10];
	static String [] unrelatedList = {"nutrition", "food","beige color","dish", "vegetable" }; 
	static int currentIndex = 0;
	static List<ClassResult> myList;
	
	
    /**
     * Creates new form gui
     */
    public gui() {
        initComponents();
        this.setLocationRelativeTo(null);
        start();
    }
  
    public void start() {
    	myImage[] imgArr = new ImageList().getImageNames(); // initialize imgArr with all image names
    	for (myImage m : imgArr) {
    		imgNames.add(m);
    	}
    	
    	service.setApiKey("f9ef8b5896c3db5e0a4a6caa297e2a011a825bab");
        
        // list selection method init
        listSelectionModel = imageList.getSelectionModel();
        listSelectionModel.addListSelectionListener(
                            new ListSelectionListener() {
                                public void valueChanged(ListSelectionEvent e) {
                                    int i = imageList.getSelectedIndex();
                                    img.setIcon(imgNames.get(i).scale(imageHeight, imageWidth, norm));
                                }
                            }); // changes the main image to whichever one is selected
        listSelectionModel.setSelectionMode(
                        ListSelectionModel.SINGLE_SELECTION); // only single selection
    	
    	System.out.println("imageHeight: " + imageHeight);
        ImageIcon icon = null;
        icon = imgNames.get(0).scale(imageHeight, imageWidth, norm);
		if(icon != null) {
			img.setIcon(icon);
		}else {
			System.out.println("was null");
		}
		
		imageList.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        imageList.setCellRenderer(new ImgRenderer());
        imageList.setVisibleRowCount(1);
    	imageList.setListData(imgNames.toArray());
        
    	imageSelect.setViewportView(imageList);
    	imageSelect.setVerticalScrollBarPolicy(
                javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
    	Dimension d = new Dimension(800,525);
    	img.setSize(d);
    	img.setMinimumSize(d);
    	img.setMaximumSize(d);
        
        initDragDrop(); // enable drag-and-drop activity for images
        recipeText.setEditable(false);
        recipeText.setLineWrap(true);
        recipeText.setWrapStyleWord(true);
        
        this.setTitle("Recipe Finder");
        recipeDialog.setTitle("Recipe Results");
        
    }
    
    private void initDragDrop() {
        imageList.setDropMode(DropMode.INSERT);
        imageList.setDragEnabled(true);
        imageList.setTransferHandler(new ImageTransferHandler());
    }
    
    public static boolean isInternetReachable()
    {
        try {
            //make a URL to a known source
            URL url = new URL("http://www.google.com");

            //open a connection to that source
            HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();

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
    private void initComponents() {

        recipeDialog = new javax.swing.JDialog();
        imgRecipePanel = new javax.swing.JPanel();
        imgRecipeLabel = new javax.swing.JLabel();
        resultPromptPane = new javax.swing.JPanel();
        resultLabel = new javax.swing.JLabel();
        noButton = new javax.swing.JButton();
        yesButton = new javax.swing.JButton();
        recipeScrollPane = new javax.swing.JScrollPane();
        recipeText = new javax.swing.JTextArea();
        selectedImage = new javax.swing.JPanel();
        img = new javax.swing.JLabel();
        imageSelect = new javax.swing.JScrollPane();
        imageList = new javax.swing.JList();
        setImage = new javax.swing.JButton();

        recipeDialog.setName("recipeDialog"); // NOI18N

        imgRecipePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout imgRecipePanelLayout = new javax.swing.GroupLayout(imgRecipePanel);
        imgRecipePanel.setLayout(imgRecipePanelLayout);
        imgRecipePanelLayout.setHorizontalGroup(
            imgRecipePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgRecipeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );
        imgRecipePanelLayout.setVerticalGroup(
            imgRecipePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgRecipeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        resultPromptPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        resultLabel.setText("Is this result accurate?");
        
        noButton.setText("No");
        noButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noButtonActionPerformed(evt);
            }
        });

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
        recipeText.setText("Loading...");
        recipeScrollPane.setViewportView(recipeText);

        javax.swing.GroupLayout recipeDialogLayout = new javax.swing.GroupLayout(recipeDialog.getContentPane());
        recipeDialog.getContentPane().setLayout(recipeDialogLayout);
        recipeDialogLayout.setHorizontalGroup(
            recipeDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, recipeDialogLayout.createSequentialGroup()
                .addComponent(imgRecipePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(imgRecipePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(recipeDialogLayout.createSequentialGroup()
                .addComponent(recipeScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultPromptPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
            	//reset the current index since a user just chooses a new picture
            	currentIndex = 0;
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
        
        showRecipeDialog(); // update and display recipe dialog window
    	
        Runnable watsonThread = new Runnable() {
        	File f = null;
        	int x = 0;
			public void run() {
				if(i < 0) {
		    		f = new File(imgNames.get(0).getPath());
		    	}
		    	else {
		    		f = new File(imgNames.get(i).getPath());
		    	}
			    try{
			    	InputStream imagesStream = new FileInputStream(f);
			    	//can add parameters here too
			    	if (i < 0)
			    		x = 0;
			    	else
			    		x = i;
			    	if(isInternetReachable()) {
			    		String searchTerm = "THISISANERROR";
				    	ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
				    	  .imagesFile(imagesStream)
				    	  .imagesFilename(imgNames.get(x).getImageName())
				    	  .build();
				    	ClassifiedImages result = service.classify(classifyOptions).execute();
				    	
				    	/* KEYWORD DISPLAY */
				    	
				    	// What image is being processed
				    	List<ClassifiedImage> resultList = result.getImages();
				    	System.out.println(resultList);
				    	System.out.println("---");

				    	System.out.println(resultList.get(0).getClassifiers().get(0).getClasses().size());
				    	
				    	// List of objects of keywords/scores
				    	List<ClassResult> classResult = resultList.get(0).getClassifiers().get(0).getClasses();
				    	
//				    							NICK'S TYPE HIERARCHY IMPLEMENTATION
				    	List<ClassResult> finalList = new LinkedList<ClassResult>();
				    	for (int z = 0; z < resultList.get(0).getClassifiers().get(0).getClasses().size(); z++) {
				    		if (classResult.get(z).getTypeHierarchy() != null && classResult.get(z).getTypeHierarchy().contains("food")) {
				    			finalList.add(classResult.get(z));
				    		}
				    		System.out.println(classResult.get(z).getClassName());
				    		System.out.println(classResult.get(z).getScore());
				    	}
				    	if(!finalList.isEmpty()) {
				    		Collections.sort(finalList, new ClassifierIdSort());
				    		Collections.reverse(finalList);
				    		myList = finalList;
				    		for (int q = 0; q < finalList.size(); q++) {
				    			System.out.println(finalList.get(q).getClassName() + " : " + finalList.get(q).getScore());
				    		}
				    		searchTerm = finalList.get(currentIndex).getClassName().replaceAll(" ", "%20");
				    	}
				    	
				    	try {
					    	HtmlUnitDriver driver;
					    	Font old;
					    	driver = new HtmlUnitDriver();
					    	if(searchTerm!="THISISANERROR"){
					    		driver.get("http://www.bigoven.com/recipes/" + searchTerm + "/best");
					    		driver.findElement(By.xpath("//div[2]/div/div/a/img")).click();
					    		System.out.println("New place: "+ driver.getCurrentUrl());
					    		String ingredients = driver.findElement(By.xpath("//div[@class='ingredients']")).getText();
	
		                        
					    		String title = driver.findElement(By.xpath("//h1")).getText();
					    		String directions = driver.findElement(By.xpath("//div[@class='recipe-instructions']")).getText();
					    		String url_open = driver.getCurrentUrl();
					    		driver.quit();
						//java.awt.Desktop.getDesktop().browse(java.net.URI.create(url_open));
		                        
					    		titleLabel = new JLabel(title);
					    		System.out.println(recipeText.getSize().getWidth());
					    		titleLabel.setSize(new Dimension(539, 30)); // x, y
					    		old = recipeText.getFont();
					    		float size = old.getSize() + 5.0f;
					    		titleLabel.setFont(old.deriveFont(size));
					    		recipeText.setText("");
					    		recipeText.add(titleLabel);
					    		recipeText.append("\n-----------------------------\n" +ingredients +
					    				"\nDirections" + "\n-----------------------------\n" + directions);
					    	}else {
					    		recipeText.setText("Sorry we were not able to find a recipe\nfor this image. Please try a new one.");	
		                        yesButton.setEnabled(false);
		                        noButton.setEnabled(false);
					    	}
				    	}catch(Exception e) {
				    		Font old = recipeText.getFont();
				    		
	                        recipeText.setText("Sorry we were not able to find a recipe\nfor this image. Please try a new one.");	
	                        yesButton.setEnabled(false);
	                        noButton.setEnabled(false);
				    	}
						container.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						b.setEnabled(true);
						yesButton.setEnabled(true);
                        noButton.setEnabled(true);
			    	}else {
			    		container.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			    		b.setEnabled(true);
			    		yesButton.setEnabled(true);
                        noButton.setEnabled(true);
			    	}
					Thread.sleep(1000);
			    }catch(Exception e){
			    }   
			}
		};
		Thread watson = new Thread(watsonThread);
		watson.start();
        recipeText.setText("Please wait...");
		if(i < 0) {
			img.setIcon(imgNames.get(0).scale(imageHeight, imageWidth, norm));
    		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    		b.setEnabled(false);
    		yesButton.setEnabled(false);
            noButton.setEnabled(false);
    		wait(b);
    	}
    	else {
    		img.setIcon(imgNames.get(i).scale(imageHeight, imageWidth, norm));
    		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    		b.setEnabled(false);
    		yesButton.setEnabled(false);
            noButton.setEnabled(false);
    		wait(b);
    	}
        
    }//GEN-LAST:event_setImageActionPerformed
    private void wait(final JButton b) {
    	Runnable waitThread = new Runnable() {

			public void run() {
				int counter = 0;
				if (titleLabel != null) {
					recipeText.remove(titleLabel);
				}
				while(!b.isEnabled()) {
					if(counter == 0) {
						recipeText.setText("Loading.\nPlease wait!");
					}else if(counter == 1) {
						recipeText.setText("Loading..\nPlease wait!");
					}else {
						recipeText.setText("Loading...\nPlease wait!");
					}
					counter++;
					if(counter>3)
						counter = 0;
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
    	};
    	Thread wait = new Thread(waitThread);
    	wait.start();
    	
	}
    private void showRecipeDialog() {
        int i = imageList.getSelectedIndex();
        
        if (i < 0) {
            imgRecipeLabel.setIcon(imgNames.get(0).scale(imageHeight, imageWidth, norm));
        }
        else {
            imgRecipeLabel.setIcon(imgNames.get(i).scale(imageHeight, imageWidth, norm));
        }
        recipeDialog.pack();
        recipeDialog.setLocationRelativeTo(this); // center dialog on main window
        recipeDialog.setVisible(true);
    }
    
    private void yesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yesButtonActionPerformed
        // TODO add your handling code here:
    	System.out.println("Yes pressed!");
    }//GEN-LAST:event_yesButtonActionPerformed

    private void noButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noButtonActionPerformed
      
    	currentIndex++;
    	reClassify();
    }//GEN-LAST:event_noButtonActionPerformed
    private void reClassify() {
		yesButton.setEnabled(false);
     noButton.setEnabled(false);
	 Runnable webThread = new Runnable() {
			public void run() {
			    try{
			    	if(isInternetReachable()) {
			    		String searchTerm = "THISISANERROR";
				    	/* KEYWORD DISPLAY */
				    	if(!myList.isEmpty()) {
				    		System.out.println("Current index: " + currentIndex);
				    		for(int q = 0; q < myList.size();q++) {
				    			System.out.println("Q List: " + q);
				    		}
				    		if(currentIndex < myList.size()) {
				    			searchTerm = myList.get(currentIndex).getClassName().replaceAll(" ", "%20");
				    			System.out.println("searchTerm is: " + searchTerm);
				    		}
				    	}
				    	try {
					    	HtmlUnitDriver driver;
					    	driver = new HtmlUnitDriver();
					    	System.out.println("Searchterm is: " + searchTerm);
					    	if(searchTerm!="THISISANERROR"){
					    		driver.get("http://www.bigoven.com/recipes/" + searchTerm + "/best");
					    		driver.findElement(By.xpath("//div[2]/div/div/a/img")).click();
					    		System.out.println("New place: "+ driver.getCurrentUrl());
					    		String ingredients = driver.findElement(By.xpath("//div[@class='ingredients']")).getText();
					    		String title = driver.findElement(By.xpath("//h1")).getText();
					    		String directions = driver.findElement(By.xpath("//div[@class='recipe-instructions']")).getText();
					    		String url_open = driver.getCurrentUrl();
					    		driver.quit();
					    		

					    		titleLabel = new JLabel(title);
					    		System.out.println(recipeText.getSize().getWidth());
					    		titleLabel.setSize(new Dimension(539, 30)); // x, y
					    		Font old = recipeText.getFont();
					    		float size = old.getSize() + 5.0f;
					    		titleLabel.setFont(old.deriveFont(size));
		   
					    		recipeText.setText("");
					    		recipeText.add(titleLabel);
					    		recipeText.setText("\n-----------------------------\n" +ingredients +
					    				"\nDirections" + "\n-----------------------------\n" + directions);
					    	}else {
					    		recipeText.setText("Sorry we were not able to find a recipe\nfor this image. Please try a new one.");	
		                        yesButton.setEnabled(false);
		                        noButton.setEnabled(false);
					    	}
				    	}catch(Exception e) {
				    		Font old = recipeText.getFont();
				    		//float size = font.getSize() + 5.0f;
				    		//recipeText.setFont( font.deriveFont(size) );
				    		if(currentIndex < myList.size()-1) {
				    			currentIndex++;
				    			run();
				    		}else {
 	                        recipeText.setText("Sorry we were not able to find a recipe\nfor this image. Please try a new one.");	
 	                        yesButton.setEnabled(false);
 	                        noButton.setEnabled(false);
				    		}
				    	}
						container.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						yesButton.setEnabled(true);
                     noButton.setEnabled(true);
			    	}else {
			    		container.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			    		yesButton.setEnabled(true);
                    noButton.setEnabled(true);
			    	}
					Thread.sleep(1000);
			    }catch(Exception e){
			    }   
			}
		};
		Thread web = new Thread(webThread);
		web.start();
		wait(noButton);
}
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
    
    // TransferListener methods BEGIN
    public void dragEnter(DropTargetDragEvent dtde) {}
    public void dragOver(DropTargetDragEvent dtde) {}
    public void dropActionChanged(DropTargetDragEvent dtde) {}
    public void dragExit(DropTargetEvent dte) {}
    public void drop(DropTargetDropEvent evt) {
    	int action = evt.getDropAction();
        evt.acceptDrop(action);
        try {
            Transferable data = evt.getTransferable();
            if (data.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                List<File> files = (List<File>) data.getTransferData(
                        DataFlavor.javaFileListFlavor);
                for (File file : files) {
                	System.out.println(file.getAbsolutePath());
                	imgNames.add(new myImage(file)); // add the file name to the image name list
            		imageList.setListData(imgNames.toArray());
                }
            }
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            evt.dropComplete(true);
        }
    }
    // TransferListener methods END

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList imageList;
    private javax.swing.JScrollPane imageSelect;
    private javax.swing.JLabel img;
    private javax.swing.JLabel imgRecipeLabel;
    private javax.swing.JPanel imgRecipePanel;
    private javax.swing.JButton noButton;
    private javax.swing.JDialog recipeDialog;
    private javax.swing.JScrollPane recipeScrollPane;
    private javax.swing.JTextArea recipeText;
    private javax.swing.JLabel resultLabel;
    private javax.swing.JPanel resultPromptPane;
    private javax.swing.JPanel selectedImage;
    private javax.swing.JButton setImage;
    private javax.swing.JButton yesButton;
    // End of variables declaration//GEN-END:variables

}
