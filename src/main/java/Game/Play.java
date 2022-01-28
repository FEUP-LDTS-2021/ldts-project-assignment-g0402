package Game;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
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

    public void run() throws IOException {
        if(!alreadyExecuted) {
            State.close();
            createTerminal(sizeFont, terminalSize);
            graphics = screen.newTextGraphics();
            console = new Console(graphics);
            console.addKeyBoardListener(Game.keyBoardObserver);
            Game.keyBoardObserver.setListener(console);
            alreadyExecuted = true;
        }

        console.run();
    }
}
