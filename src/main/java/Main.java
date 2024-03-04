import manager.ImageManager;
import model.GameScene;
import view.GameView;

public class Main{
    
    public static void main(String[] args) {
        ImageManager.init();
        GameScene gameScene = GameScene.getInstance();
        new GameView(gameScene);
        gameScene.start();
    }

}
