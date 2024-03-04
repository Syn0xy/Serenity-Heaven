package manager;

import java.awt.Image;

public class StaticSprite implements Sprite {

    private Image image;

    private int width;

    private int height;

    public StaticSprite(String dataName){
        ImageData data = ImageManager.get(dataName);
        this.image = data.frames[0];
        this.width = data.width;
        this.height = data.height;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public int getWidth() {
        return width;
    }
    
    @Override
    public int getHeight() {
        return height;
    }
    
}
