package Game;

import com.googlecode.lanterna.TerminalSize;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Play extends State{

    protected static final int width = 64;
    protected static final int height = 36;
    TerminalSize terminalSize = new TerminalSize(width, height);
    public Console console;
    private final int sizeFont = 20;

    public Play() throws URISyntaxException, FontFormatException {
        console = new Console();
    }

    public void start() {
        createTerminal(sizeFont, terminalSize);
    }

    public void run() {
        start();
        while(Game.state == 2) {
            console.run();
        }
    }
}
