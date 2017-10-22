package ingredient_gui;

import java.io.File;

public class ImageList {
  private static myImage[] imageNames = getImages();
  /**
   * Generate list of all files in "img" folder
   * @return String[] of all file names
   */
  private static myImage[] getImages() {
      File folder = new File("img");
      File[] listOfFiles = folder.listFiles();
      myImage[] ret = new myImage[listOfFiles.length];
      
      for (int i = 0; i < listOfFiles.length; i++) {
          ret[i] = new myImage(listOfFiles[i].getName());
          System.out.println("Path to file: " + ret[i].getImage());
      }
      return ret;
  }
  public myImage[] getImageNames() { return imageNames; }
}
