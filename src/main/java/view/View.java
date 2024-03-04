package view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

public abstract class View extends JFrame {

    protected static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    protected static final int SCREEN_WIDTH = (int)SCREEN_SIZE.getWidth();

    protected static final int SCREEN_HEIGHT = (int)SCREEN_SIZE.getHeight();
    
    private double framesPerSecond;

    private double milliSeconds;

    private long prevTime = 0;

    private long crntTime = 0;

    private double timeDiff;

    private int counter = 0;
    
    public abstract String getTitle();

    protected View(int width, int height){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFocusTraversalKeysEnabled(false);
        setTitle(getTitle());
        setSize(width, height);
        setLocation(center());
        setVisible(true);
    }
    
    protected Point center(){
        return new Point((SCREEN_WIDTH - getWidth()) / 2, (SCREEN_HEIGHT - getHeight()) / 2);
    }
    
    Runtime runtime = Runtime.getRuntime();

    protected void refreshFrames(){
        crntTime = System.currentTimeMillis();
        timeDiff = crntTime - prevTime;
        counter++;
        if(timeDiff >= 1000){
            long memoryBytes  = runtime.totalMemory() - runtime.freeMemory();
            double memoryGigabytes = memoryBytes / Math.pow(1024, 3);

            framesPerSecond = (1000.0 / timeDiff) * counter;
            milliSeconds = timeDiff / counter;
            String title = getTitle() + " - " +
                String.format("%,.2f fps", framesPerSecond) + " / " +
                String.format("%,.3f ms", milliSeconds) + "  / " +
                String.format("%.2f go", memoryGigabytes);
            setTitle(title);
            prevTime = crntTime;
            counter = 0;
        }
    }

}
