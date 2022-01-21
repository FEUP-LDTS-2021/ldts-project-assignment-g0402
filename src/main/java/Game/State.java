package Game;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

abstract public class State {
    protected static TerminalScreen screen;
    protected final int width = 32;
    protected final int height = 18;
    private final int sizeFont = 40;

    Collection<SGR> title = new ArrayList<>();
    Collection<SGR> text = new ArrayList<>();
    Collection<SGR> selected = new ArrayList<>();

    protected void createTerminal(){
        try {
            /*Import font for the game*/
            URL resource = getClass().getClassLoader().getResource("invaderv2.ttf");
            File fontFile = new File(resource.toURI());
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

            TerminalSize terminalSize = new TerminalSize(width, height);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

            terminalFactory.setTerminalEmulatorTitle("Lonely Earth Invader");

            Font loadedFont = font.deriveFont(Font.PLAIN, sizeFont);
            AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
            terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
            terminalFactory.setForceAWTOverSwing(true);

            Terminal terminal = terminalFactory.createTerminal();

            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();

            title.add(SGR.CIRCLED);
            title.add(SGR.BOLD);
            text.add(SGR.BORDERED);
            selected.add(SGR.BLINK);

        } catch (IOException | FontFormatException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        screen.clear();
    }

    public void refresh() throws IOException {
        screen.refresh();
        screen.doResizeIfNecessary();
    }

    public void close() throws IOException {
        screen.close();
    }

    protected void draw(){
        try {
            clear();
            drawText();
            refresh();
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    protected void drawText(){}
}
