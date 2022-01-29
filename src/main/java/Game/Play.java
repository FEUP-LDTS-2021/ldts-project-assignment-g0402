package Game;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Play extends State{

    TextGraphics graphics;
    protected static final int width = 64;
    protected static final int height = 36;
    TerminalSize terminalSize = new TerminalSize(width, height);
    public Console console;
    private final int sizeFont = 20;
    private boolean alreadyExecuted = false;

    public Play() {

    }

    public void run() throws IOException, URISyntaxException, FontFormatException {

        State.close();
        createTerminal(sizeFont, terminalSize);
        graphics = screen.newTextGraphics();
        console = new Console(graphics);
        console.addKeyBoardListener(Game.getInstance().getKeyBoardObserver());
        Game.getInstance().getKeyBoardObserver().setListener(console);


        while(Game.state == 2){
            if(!alreadyExecuted){
                console.run();
                alreadyExecuted = true;
            }
            //System.out.println(Game.state);
        }
        System.out.println("Left the PLAY State");
        Game.menu.restart();
    }
}
