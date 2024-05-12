package view;

import java.awt.Component;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import input.Input;
import model.scene.GameScene;
import utils.Updatable;

public class GameView extends View implements Updatable {

    private static final int WIDTH = (int)(SCREEN_WIDTH * (2.0 / 3.0));

    private static final int HEIGHT = (int)(SCREEN_HEIGHT * (2.0 / 3.0));

    private static final String TITLE = "Serenity Heaven";

    private GameScene gameScene;

    private GameCanvas canvas;

    public GameView(GameScene gameScene) {
        this.gameScene = gameScene;
        gameScene.attach(this);
        init(WIDTH, HEIGHT);
    }

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public Component getContent() {
        return canvas = new GameCanvas(gameScene);
    }

    @Override
    public KeyListener getKeyAdapter() {
        return Input.getInstance();
    }

    @Override
    public MouseAdapter getMouseAdapter() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                canvas.interact();
            }
        };
    }
        
    @Override
    public void update() {
        repaint();
        refreshFrames();
    }
    
}
