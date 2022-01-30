package ldts.game.states;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import ldts.game.Console;
import ldts.game.Game;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Play extends State{

    TextGraphics graphics;
    public static final int width = 64;
    public static final int height = 36;
    TerminalSize terminalSize = new TerminalSize(width, height);
    public Console console;


    public void run() throws IOException, URISyntaxException, FontFormatException {
        int sizeFont = 20;

        close();

        createTerminal(sizeFont, terminalSize);
        graphics = screen.newTextGraphics();
        console = new Console(graphics);
        console.addKeyBoardListener(Game.getInstance().getKeyBoardObserver());
        console.run();

        while(Game.state == 2){
            Game.getInstance().getKeyBoardObserver().setListener(console);
        }

        close();
        Game.menu.start();
    }

    public static TerminalScreen getScreen(){return screen;}
}
