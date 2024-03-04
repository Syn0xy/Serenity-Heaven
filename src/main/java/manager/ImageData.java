package manager;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageData {
    
    protected String name;
    
    protected String path;

    protected int width;

    protected int height;

    protected int imageCount;

    protected Image[] frames;
    
    protected ImageData(String name, String path, int width, int height, int imageCount) {
        this.name = name;
        this.path = path;
        this.width = width;
        this.height = height;
        this.imageCount = imageCount;
    }
    
    protected ImageData(String name, String path, int width, int height) {
        this(name, path, width, height, 1);
    }
    
    protected ImageData(String name, String path, int imageCount) {
        this(name, path, 1, 1, imageCount);
    }
    
    protected ImageData(String name, String path) {
        this(name, path, 1, 1);
    }
    
    protected void loadImages(){
        try {
             BufferedImage img = ImageIO.read(new File(path));
             if(imageCount > 1){
                 int numParts = imageCount;
                 int partWidth = img.getWidth() / numParts;
                 int partHeight = img.getHeight();
         
                 frames = new Image[numParts];
         
                 for (int i = 0; i < numParts; i++) {
                     int x = i * partWidth;
                     BufferedImage part = img.getSubimage(x, 0, partWidth, partHeight);
                     frames[i] = part;
                 }
             }else{
                frames = new Image[]{ img };
             }
        } catch (Exception e) {
            System.err.println(e.getMessage() + ": " + path);
        }
    }

}
