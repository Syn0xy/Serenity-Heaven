package input;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

import util.Vector2;

public class Input implements KeyListener{

    private static Map<KeyCode, InputKeyCode> INPUTS_KEY_CODE = InputKeyCode.getInputsKeyCode();

    private static Input singleton;

    public static Input getInstance(){
        if(singleton == null) singleton = new Input();
        return singleton;
    }
    
    public static void update(){
        for(InputKeyCode input : INPUTS_KEY_CODE.values()){
            input.update();
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        try {
            INPUTS_KEY_CODE.get(KeyCode.key(keyEvent)).enter();
        } catch (KeyNotAssociatedException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        try {
            INPUTS_KEY_CODE.get(KeyCode.key(keyEvent)).exit();
        } catch (KeyNotAssociatedException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {}
    
    public static boolean getKey(KeyCode keyCode){
        return INPUTS_KEY_CODE.get(keyCode).isStay();
    }

    public static boolean getKeyDown(KeyCode keyCode){
        return INPUTS_KEY_CODE.get(keyCode).isEnter();
    }

    public static boolean getKeyUp(KeyCode keyCode){
        return INPUTS_KEY_CODE.get(keyCode).isExit();
    }

    public static Vector2 getMousePosition(){
        Point point = MouseInfo.getPointerInfo().getLocation();
        return new Vector2(
            (float)point.getX(),
            (float)point.getY()
        );
    }

}
