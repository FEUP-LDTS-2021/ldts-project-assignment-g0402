package ldts.game;

import com.googlecode.lanterna.TextColor;
import ldts.game.states.Instructions;
import ldts.game.states.Menu;
import ldts.game.states.Play;
import ldts.game.states.State;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;


public class Game{
    private static KeyBoardObserver keyBoardObserver;
    private final Play play; public static ldts.game.states.Menu menu; private final Instructions instructions;
    private static Game singleton = null;
    protected static boolean exit;
    public static int state = 1;
    public static Font font;

    /*Constants*/
    public static final int refreshTime = 1000;
    public static long fireDelay = System.currentTimeMillis();

    /*Colors of the Game*/
    public static final TextColor colorMonster =  new TextColor.RGB(0,200,50);
    public static final TextColor colorPlayer =  new TextColor.RGB(255, 255, 255);
    public static final TextColor colorScenario = new TextColor.RGB(15,20,45);


    private Game() throws URISyntaxException, FontFormatException, IOException {
        /*      Import font of the game     */
        URL resource = getClass().getClassLoader().getResource("invaderv2.ttf");
        File fontFile = new File(resource.toURI());
        font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        this.menu = new Menu();
        this.play = new Play();
        this.instructions = new Instructions();
        keyBoardObserver = new KeyBoardObserver();
    }

    public void start() throws IOException, URISyntaxException, FontFormatException {
        menu.start();
        do {
            switch (state) {
                case 1 -> menu.run();
                case 2 -> play.run();
                case 3 -> instructions.run();
                case 4 -> exit = true;
            }
        } while (!exit);
        State.close();
    }

    public static Game getInstance() throws URISyntaxException, FontFormatException, IOException {
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