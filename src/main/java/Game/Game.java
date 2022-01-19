package Game;

import com.googlecode.lanterna.TextColor;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;



public class Game{
    private final KeyBoardObserver keyBoardObserver;
    private final Console console;
    private static Game singleton = null;
    protected boolean exit;

    /*Constants*/
    public static final int refreshTime = 1000;

    /*Colors of the Game*/
    public static final TextColor colorMonster =  new TextColor.RGB(0,200,50);
    public static final TextColor colorPlayer =  new TextColor.RGB(255, 255, 255);
    public static final TextColor colorScenario = new TextColor.RGB(15,20,45);


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