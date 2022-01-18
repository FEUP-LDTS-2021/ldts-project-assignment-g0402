package Game;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;



public class Game{
    private final KeyBoardObserver keyBoardObserver;
    private final Console console;
    private static Game singleton = null;
    protected boolean exit;
    public static final int refreshTime = 1000;
    private Game() throws URISyntaxException, FontFormatException {
        this.console = new Console();
        this.keyBoardObserver = new KeyBoardObserver();
    }

    public void start()  {

        console.addKeyBoardListener(keyBoardObserver);
        keyBoardObserver.setListener(console);
        console.run();

    }

    public static Game getInstance() throws URISyntaxException, FontFormatException {
        if(singleton == null){
            singleton = new Game();
        }
        return singleton;
    }

    public KeyBoardObserver getKeyBoardObserver() {
        return keyBoardObserver;
    }

    public static void main(String[] args) throws URISyntaxException, FontFormatException, IOException {
        singleton = getInstance();
        singleton.start();
    }
}