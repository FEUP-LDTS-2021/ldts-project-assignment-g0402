package Game;

import com.googlecode.lanterna.TextColor;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;



public class Game{
    private final KeyBoardObserver keyBoardObserver;
    private final Play play; private final Menu menu; private final Instructions instructions;
    private static Game singleton = null;
    protected static boolean exit;
    protected static int state = 1;

    /*Constants*/
    public static final int refreshTime = 1000;
    public static long fireDelay = System.currentTimeMillis();

    /*Colors of the Game*/
    public static final TextColor colorMonster =  new TextColor.RGB(0,200,50);
    public static final TextColor colorPlayer =  new TextColor.RGB(255, 255, 255);
    public static final TextColor colorScenario = new TextColor.RGB(15,20,45);


    private Game() throws URISyntaxException, FontFormatException {
        this.menu = new Menu();
        this.play = new Play();
        this.instructions = new Instructions();
        this.keyBoardObserver = new KeyBoardObserver();
    }

    public void start() throws IOException {
        play.console.addKeyBoardListener(keyBoardObserver);
        keyBoardObserver.setListener(play.console);
        while(!exit) {
            switch (state) {
                case 1 -> menu.run();
                case 2 -> play.run();
                case 3 -> instructions.run();
            }
        }
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