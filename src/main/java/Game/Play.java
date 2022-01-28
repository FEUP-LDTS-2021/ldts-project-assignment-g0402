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
    private boolean alreadyExecuted = false;

    public Play() {
        console = new Console();
    }

    public void start() throws IOException {
        State.close();
        createTerminal(sizeFont, terminalSize);
    }

    public void run() throws IOException {
        if(!alreadyExecuted) {
            start();
            alreadyExecuted = true;
        }
        console.run();
    }
}
