package Game;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static Game.Console.Action.QUIT;

public class Play extends State{

    TextGraphics graphics;
    protected static final int width = 64;
    protected static final int height = 36;
    TerminalSize terminalSize = new TerminalSize(width, height);
    public Console console;
    private final int sizeFont = 20;
    private boolean alreadyExecuted = false;


    public void run() throws IOException, URISyntaxException, FontFormatException {

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
}
