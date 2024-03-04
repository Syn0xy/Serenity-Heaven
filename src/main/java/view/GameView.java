package view;

import input.Input;
import model.GameScene;
import view.util.Observer;
import view.util.Subject;

public class GameView extends View implements Observer {

    private static final int WIDTH = (int)(SCREEN_WIDTH * (2.0 / 3.0));

    private static final int HEIGHT = (int)(SCREEN_HEIGHT * (2.0 / 3.0));

    private static final String TITLE = "Serenity Heaven";

    public GameView(GameScene gameScene) {
        super(WIDTH, HEIGHT);
        gameScene.attach(this);
        addKeyListener(Input.getInstance());
        add(new GameCanvas(gameScene));
        revalidate();
    }

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public void update(Subject subj) {
        repaint();
        refreshFrames();
    }

    @Override
    public void update(Subject subj, Object data) {

    }
    
}
