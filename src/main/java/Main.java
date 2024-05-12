import manager.ImageManager;
import model.scene.GameScene;

public class Main {
    
    public static void main(String[] args) {
        ImageManager.init();
        GameScene.getInstance().start(150);
    }

}
