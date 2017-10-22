package ingredient_gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class myImage {
	private final String imagePath;
	private ImageIcon image;
	private BufferedImage bufImage;
	private File file;
	private String imageName;
	
	public myImage(String imagePath) {
		this.imagePath = "img/" + imagePath;
		imageName = imagePath;
		file = new File(this.imagePath);
		try {
			bufImage = ImageIO.read(file);
		} catch (IOException e) {
			System.out.println("should never happen");
		}
		image = new ImageIcon(imagePath);
	}
	public String getImageName() {return imageName;}
	public String getPath() {return imagePath;}
	public ImageIcon getImage() {
		if(image == null) {
			return new ImageIcon(imagePath);
		}
		return image;
	}
	public ImageIcon scale(int imageHeight, int imageWidth, int NORMAL) {
		if(image == null) {
			//non scaled version
			return new ImageIcon(imagePath);
		}
		return new ImageIcon(bufImage.getScaledInstance(imageHeight, imageWidth, NORMAL));
	}
}
