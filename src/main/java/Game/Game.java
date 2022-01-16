package Game;

import java.awt.*;
import java.net.URISyntaxException;

public class Game {
    private final KeyBoardObserver keyBoardObserver;
    private final Console console;
    private static Game singleton = null;
    private final int fps;

    private Game(int fps) throws URISyntaxException, FontFormatException {
        this.console = new Console();
        this.keyBoardObserver = new KeyBoardObserver();
        this.fps = fps;
    }

    public void start(){
        int frameTime = 1000 / this.fps;

        console.addKeyBoardListener(keyBoardObserver);
        keyBoardObserver.setListener(console);
        console.run();

        while (singleton != null && console != null){
            long startTime = System.currentTimeMillis();

            //gameState.step(this, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            if (sleepTime > 0) try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {

            }
        }
    }

    public static Game getInstance() throws URISyntaxException, FontFormatException {
        if(singleton == null){
            singleton = new Game(20);
        }
        return singleton;
    }

    public KeyBoardObserver getKeyBoardObserver() {
        return keyBoardObserver;
    }

    public static void main(String[] args) throws URISyntaxException, FontFormatException {
        singleton = getInstance();
        singleton.start();
    }
}